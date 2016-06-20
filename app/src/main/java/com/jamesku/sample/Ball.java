package com.jamesku.sample;

import com.jamesku.framework.Graphics;

/**
 * Created by user on 2016/6/20.
 */
public class Ball extends GameObject {
    private int radius;
    private int color;
    private int dropFreq;
    private boolean visible;
    final private int BOTTOM_BOARDER = 800;
    final private int RIGHT_BOARDER = 480;

    public Ball(int x, int y, int radius, int color){
        setCenterX(x);
        setCenterY(y);
        setRadius(radius);
        setColor(color);
        setSpeedX(0);
        setSpeedY(0);
        setVisible(true);
        setDropFreq(0);
    }

    public void update(){
        if(getCenterY() + getSpeedY() - getRadius() >= BOTTOM_BOARDER) {
            setVisible(false);
            return;
        }
        else{
            setCenterY(getCenterY() + getSpeedY());
        }
        if(getCenterX() + getSpeedX() + getRadius() >= RIGHT_BOARDER){
            bounce();
            setCenterX(RIGHT_BOARDER - getRadius());
        }
        else{
            setCenterX(getCenterX() + getSpeedX());
        }

        if(getCenterX() + getSpeedX() - getRadius() <= 0){
            bounce();
            setCenterX(getRadius());
        }else{
            setCenterX(getCenterX() + getSpeedX());
        }

    }

    private void bounce(){
        setSpeedX((int) (-getSpeedX() * 0.7));
    }

    public void touchedBounce(){

        if(getSpeedY()>0) {
            int randSpeedX = (int) (Math.random()*5);
            //setSpeedX((int) (-getSpeedX() * 0.7));
            setSpeedX((int) (-getSpeedX() + randSpeedX));
            setSpeedY((int) (-getSpeedY() * 4));
        }
    }

    public void draw(Graphics g){
        if(isVisible())
            g.drawCircle(getCenterX(), getCenterY(), getRadius(), getColor());
    }

    public int getRadius() {
        return radius;
    }

    public int getDropFreq(){return  dropFreq;}

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getColor() {
        return color;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setColor(int color) {
        this.color = color;

    }

    public void setDropFreq(int dropFreq){
        this.dropFreq = dropFreq;
    }
}