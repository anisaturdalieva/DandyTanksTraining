package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Main extends JPanel {
    final int BF_WIDTH = 567;
    final int BF_HEIGHT = 567;
    final int OBJECT_SIZE = 64;
    final int UP = 1;
    final int DOWN = 2;
    final int LEFT = 3;
    final int RIGHT = 4;
    final int TOP_Y = BF_HEIGHT - OBJECT_SIZE;
    final int TOP_X = BF_WIDTH - OBJECT_SIZE;

    String[][] objects = {
            {"B", "B", "B", "G", "G", "W", "G", "W", "B"},
            {"G", "G", "B", "G", "G", "G", "G", "G", "B"},
            {"B", "B", "B", "G", "G", "G", "G", "G", "B"},
            {"B", "B", "B", "G", "G", "G", "G", "B", "B"},
            {"B", "G", "G", "G", "G", "G", "G", "B", "B"},
            {"G", "G", "G", "G", "G", "G", "G", "G", "B"},
            {"B", "G", "B", "G", "G", "W", "G", "W", "B"},
            {"G", "B", "B", "G", "G", "W", "G", "W", "B"},
            {"B", "B", "B", "G", "G", "W", "G", "W", "B"},
    };

    int direction = 4;

    int bulletX = 100;
    int bulletY = 100;

    int tankX = 256;
    int tankY = 256;

    void move(int direction) throws Exception {
        this.direction = direction;

        if (dontCanMove ()) {
            System.out.println ( "Can't move!" );
            fire ();
            return;
        }
        for (int i = 0; i < OBJECT_SIZE; i++) {

            if (direction == 1) {
                tankY--;
            } else if (direction == 2) {
                tankY++;
            } else if (direction == 3) {
                tankX--;
            } else if (direction == 4) {
                tankX++;
            }
            Thread.sleep ( 33 );
            repaint ();
        }
        moveToQuadrant ( 0,0 );
    }

    void moveToQuadrant(int x, int y){
    }

    void moveRandom()throws Exception{
        Random random = new Random ();
        int direction =random.nextInt (4)+1;
        move ( direction );
    }

    boolean dontCanMove(){
        return (direction == UP && tankY == 0) || (direction == DOWN && tankY == TOP_Y)
                || (direction == LEFT && tankX == 0) || (direction == RIGHT && tankX == TOP_X)
                || nextObject ( direction ).equals ( "B" );

    }

    String nextObject(int direction){
        int y = tankY/OBJECT_SIZE;
        int x = tankX/OBJECT_SIZE;

        switch (direction){
            case UP:
                y-=64;
                break;
            case DOWN:
                y+=64;
                break;
            case LEFT:
                x-=64;
                break;
            case RIGHT:
                x+=64;
                break;
        }
        return objects[y/OBJECT_SIZE][x/OBJECT_SIZE];
    }

    boolean processInterception() {
        int y = bulletY/64;
        int x = bulletX/64;
        if(objects[y][x].equals ("B")){
            objects[x][y]="G";
            return true;
        }
        return false;
    }

    void fire() throws Exception{
        bulletX = tankX + 25;
        bulletY = tankY + 25;
        while (bulletX > 0 && bulletX < BF_WIDTH && bulletY > 0 && bulletY < BF_HEIGHT){
           switch (direction){
               case 1:
                   bulletY-=64;
                   break;
               case 2:
                   bulletY+=64;
                   break;
               case 3:
                   bulletX--;
                   break;
               case 4:
                   bulletX++;
                   break;
           }
            if(processInterception ()){
              destoyBullet ();
            }
           Thread.sleep ( 10 );
            repaint ();
        }
        destoyBullet ();
    }

    void destoyBullet(){
        bulletX=-100;
        bulletY=-100;
        repaint ();
    }

    void runTheGame () throws Exception{
        while (true) {
             moveRandom ();
        }
    }
    public static void main(String[] args) throws Exception{
        Main main=new Main  ();
        main.runTheGame ();
    }
    Main() {
        JFrame jFrame = new JFrame ( "Dandy Tanks" );
        jFrame.setMinimumSize ( new Dimension (BF_WIDTH,BF_HEIGHT+38) );
        jFrame.getContentPane ().add ( this );
        jFrame.setLocation ( 0, 0 );
        jFrame.setDefaultCloseOperation ( WindowConstants.EXIT_ON_CLOSE );
        jFrame.setVisible ( true );
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent ( g );
        g.setColor ( Color.CYAN );

        for (int y = 0; y < 9 ; y++) {
            for (int x = 0; x < 9; x++) {
                switch (objects[y][x]) {
                    case "B":
                        g.setColor ( new Color ( 246,20,79 ) );
                        break;
                    case "W":
                        g.setColor ( new Color ( 21,172,246 ) );
                        break;
                    case "G":
                        g.setColor ( new Color ( 244,233,246 ) );
                        break;
                }

                g.fillRect ( x * OBJECT_SIZE, y*OBJECT_SIZE, OBJECT_SIZE, OBJECT_SIZE );
            }
        }
        g.setColor ( Color.GRAY );
        g.fillRect ( tankX,tankY,OBJECT_SIZE,OBJECT_SIZE );
        g.setColor ( Color.MAGENTA );
        if (direction==1){
            g.fillRect ( tankX+20,tankY,24,34 );
        }else if(direction==2){
            g.fillRect ( tankX+20,tankY+30,24,34 );

        }else if(direction==3){
            g.fillRect ( tankX,tankY+20,34,24 );

        }else if(direction==4){
            g.fillRect ( tankX+30,tankY+20,34,24 );

        }
        g.setColor ( Color.CYAN );
        g.fillRect ( bulletX,bulletY,14,14 );
    }
}
