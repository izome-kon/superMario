package model.prize;

import java.awt.Rectangle;


public interface Prize {

public String getPoint();
public void setPoints(String s);
public void reveal();
public void updateLocation();
public boolean CollisionPrize(Rectangle r);
}
