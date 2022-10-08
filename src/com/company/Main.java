package com.company;

import javax.swing.*;
import java.awt.*;

public class Main extends JPanel {
    final int BF_WIDTH =567;
    final int BF_HEIGHT =567;

    int direction = 1;
    int bulletX =320;
    int bulletY=320;
    int x = 256;
    int y = 256;
    void move (int direction) throws Exception{
        this.direction =direction;

        if(direction==1){
            y--;
        }else if (direction==2){
            y++;
        }else if (direction==3){
            x--;
        }else if(direction==4){
            x++;
        }
        Thread.sleep ( 33 );
        repaint ();
    }
    void runTheGame () throws Exception{
        while (y!=BF_HEIGHT-64){
            move ( 2 );
        }
    }

    void moveUp() throws Exception{
        direction = 1;
        while (y != 0){
            y--;
            Thread.sleep ( 33 );
            repaint ();
        }
    }void moveLeft() throws Exception{
        direction =3;
        while (x!= 0){
            x--;
            Thread.sleep ( 33 );
            repaint ();
        }
    }void moveDown() throws Exception{
        direction = 2;
        while (y != BF_HEIGHT-64){
            y++;
            Thread.sleep ( 33 );
            repaint ();
        }
    }void moveRight() throws Exception{
        direction = 4;
        while (x != BF_WIDTH - 64){
            x++;
            Thread.sleep ( 33 );
            repaint ();
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
        g.setColor ( Color.ORANGE );
        g.fillRect ( x,y,64,64 );
        g.setColor ( Color.GREEN );
        if (direction==1){
            g.fillRect ( x+20,y,24,34 );
        }else if(direction==2){
            g.fillRect ( x+20,y+30,24,34 );

        }else if(direction==3){
            g.fillRect ( x,y+20,34,24 );

        }else if(direction==4){
            g.fillRect ( x+30,y+20,34,24 );

        }
        g.setColor ( Color.BLUE );
        g.fillRect ( bulletX,bulletY,14,14 );
    }
}
