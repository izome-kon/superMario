/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.hero;
import view.ImageStore;
/**
 *
 * @author user
 */

import java.awt.image.BufferedImage;
import java.util.Arrays;
import view.Animation;

public class MarioForm {
    public enum Forms {SUPER,MARIO}
    public enum movePic {RUN,STAND,JUMP,BACK,DIE}
    private Animation animation;
    private boolean isFire;
    private boolean isSuper;
    private Forms form;
    private movePic range;
    private int st,en,Supercount,Firecount;
    public boolean isIsFire() {
        return isFire;
    }
    
    public boolean isIsSuper() {
        return isSuper;
    }

    public Forms getForm() {
        return form;
    }

    public void setIsFire(boolean isFire) {
        this.isFire = isFire;
        Firecount = 150;
    }

    public void setIsSuper(boolean isSuper) {
        Supercount = 150;
        this.isSuper = isSuper;
    }

    public void setForm(Forms form) {
        this.form = form;
    }

    public MarioForm() {
        st=0;en=1;
        animation = new Animation(Arrays.copyOfRange(ImageStore.MARIO[1], st, en));
        isFire = false;
        isSuper = false;
        form = Forms.MARIO;
        range = movePic.STAND;
        Supercount = 0;
        Firecount = 0;
    }
    
    public void updateForm(){
        if(form == Forms.MARIO&&!isFire&&!isSuper){
        animation.setAnimation(Arrays.copyOfRange(ImageStore.MARIO[0], st, en));
        }else if(form == Forms.MARIO&&isFire&&!isSuper){
        animation.setAnimation(Arrays.copyOfRange(ImageStore.MARIO[1], st, en));
        }else if(form == Forms.SUPER&&!isFire&&!isSuper){
        animation.setAnimation(Arrays.copyOfRange(ImageStore.FIREMARIO[0], st, en));
        }else if(form == Forms.SUPER&&isFire&&!isSuper){
        animation.setAnimation(Arrays.copyOfRange(ImageStore.FIREMARIO[1], st, en));
        }else if(form == Forms.MARIO&&isSuper){
        animation.setAnimation(Arrays.copyOfRange(ImageStore.SUPERMARIO[0], st*2, en*2));
        }else if(form == Forms.SUPER&&isSuper){
        animation.setAnimation(Arrays.copyOfRange(ImageStore.FIREMARIO[1], st*2, en*2));
        }
    }
    public void checkSuper(){
        if(isSuper&&Supercount>0){
        Supercount--;
        }else{
        isSuper = false;
        Supercount = 0;
        }
    }
    public void checkFire(){
        if(isFire&&Firecount>0){
        Firecount--;
        }else{
        isFire = false;
        Firecount = 0;
        }
    }
    public void updatePicRange(){
        if(range == movePic.STAND){
            st = 0;en = 1;
        }
        if(range == movePic.RUN){
            st = 1;en = 4;
        }
        if(range == movePic.JUMP){
            st = 5;en = 6;
        }
        if(range == movePic.DIE){
            st = 6;en = 7;
        }
        if(range == movePic.BACK){
            st = 4;en = 5;
        }
    }

    public movePic getRange() {
        return range;
    }

    public void setRange(movePic range) {
        this.range = range;
    }
    
    public BufferedImage getStyle (){
        if(!isSuper){
        return animation.animate(3);
        }else
        return animation.animate(1);
    }
    
}
