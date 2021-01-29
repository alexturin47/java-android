package com.example.game_test;

import android.os.CountDownTimer;
import android.util.Log;

public class Bug {
    private static final int  SPEED =5;
    private int X;
    private int Y;
    private int dX;
    private int dY;
    private int angle;
    private int speed;
    private boolean lives;
//    private byte img;


    public Bug(int x, int y, int dX, int dY, boolean lives) {
        X = x;
        Y = y;
        this.dX = dX;
        this.dY = dY;
        this.lives = lives;
        int m = SPEED;
        this.speed = (int) (Math.random() * ++m);
        this.angle = 0;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public void setX(int x) {
        X = x;
    }

    public void setY(int y) {
        Y = y;
    }

    public void setdX(int dX) {
        this.dX = dX;
    }

    public void setdY(int dY) {
        this.dY = dY;
    }

    public void setLives(boolean lives) {
        this.lives = lives;
    }

    public int getAngle() {
        return angle;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public int getdX() {
        return dX;
    }

    public int getdY() {
        return dY;
    }

    public boolean isLives() {
        return lives;
    }

    public int getSpeed() {
        return speed;
    }
}

class BugThread implements Runnable{

    Thread thrd;
    Bug bug;

    BugThread(Bug bug){
        thrd = new Thread(this);
        this.bug = bug;
    }

    public static BugThread createAndStart(Bug bug){
        BugThread bugThread = new BugThread(bug);

        bugThread.thrd.start();
        return bugThread;
    }


    @Override
    public void run() {
        int d=0;

        while(true){
            try {
                d = rnd(2);

                switch(d){
                    case 0: bug.setdY(-bug.getSpeed());
                            bug.setdX(0);
                            bug.setAngle(0);
                            break;
                    case 1: bug.setdX(bug.getSpeed());
                            bug.setdY(-bug.getSpeed());
                            bug.setAngle(45);
                            break;
                    case 2: bug.setdX(bug.getSpeed());
                            bug.setdY(0);
                            bug.setAngle(90);
                        break;
                    case 3: bug.setdX(bug.getSpeed());
                            bug.setdY(bug.getSpeed());
                            bug.setAngle(135);
                        break;
                    case 4: bug.setdX(0);
                            bug.setdY(bug.getSpeed());
                            bug.setAngle(180);
                        break;
                    case 5: bug.setdX(-bug.getSpeed());
                            bug.setdY(bug.getSpeed());
                            bug.setAngle(225);
                        break;
                    case 6: bug.setdX(-bug.getSpeed());
                            bug.setdY(0);
                            bug.setAngle(270);
                        break;
                    case 7: bug.setdX(-bug.getSpeed());
                            bug.setdY(-bug.getSpeed());
                            bug.setAngle(315);
                        break;
                }
                int X = bug.getX()+bug.getdX();
                int Y = bug.getY()+bug.getdY();
                bug.setX(X);
                bug.setY(Y);
                Thread.sleep(2000); //1000 - 1 сек
            } catch (InterruptedException ex) {
            }
        }


    }

    public static int rnd(int max){
        //int dp = max + Math.abs(min) + 1;
        //return  (int) (Math.random() * dp) - max;
        return (int) (Math.random() * ++max);
    }
}
