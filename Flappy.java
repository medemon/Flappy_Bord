import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Flappy {
    JFrame game;
    JLabel flappy;
    Action spaceAction;
    Action fallingAction;
    Action screenAction;
    Timer timer;
    Timer timer_screen;
    JLabel[] pipes;
    Flappy(){

        //Here we create the background for the game
        game = new JFrame("Flappy Bord");
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setSize(420,420);
        game.setLayout(null);

        //Here we are creating our character, flappy
        flappy = new JLabel();
        flappy.setBackground(Color.yellow);
        flappy.setBounds(100,100,40,40);
        flappy.setOpaque(true);

        //We create a new action name spaceAction that takes from the class SpaceAction()
        spaceAction = new SpaceAction();
        fallingAction = new FallingMotion();
        screenAction = new ScreenMoving();

        //Here is how we map a keystroke to an action
        flappy.getInputMap().put(KeyStroke.getKeyStroke("UP"), "spaceAction");
        flappy.getActionMap().put("spaceAction",spaceAction);

        //Timer is for the gravity effect
        int fallingDelay = 25;
        timer = new Timer(fallingDelay,fallingAction);
        timer.start();

        //Timer for the Screen moving effect

        Random rand = new Random();
        pipes = Pipes();




        //   Here we need to now create our objects
        int screenScroll = 25;
        timer_screen = new Timer(screenScroll,screenAction);
        timer_screen.start();

        game.add(flappy);
        for(JLabel j : pipes){
            game.add(j);
        }
        game.setVisible(true);
    }
    public static JLabel[] Pipes(){
        int[] y_sizes= {75,125,175,215};
        JLabel[] pipes = new JLabel[4];
        for (int x = 0; x < 4; x++){
            pipes[x] = new JLabel();
            pipes[x].setBackground(Color.green);
            pipes[x].setBounds(380,0, 25, y_sizes[x]);
            pipes[x].setOpaque(true);
            System.out.println("Hey");
        }
        return pipes;
    }
    public class SpaceAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Up pressed");
            flappy.setLocation(flappy.getX() + 3, flappy.getY() - 35);
        }
    }
    public class FallingMotion extends AbstractAction {
        public void actionPerformed(ActionEvent e) {
            System.out.println("Falling");
            flappy.setLocation(flappy.getX(), flappy.getY() + 2);
        }
    }
    // We need to make the screen objects that it can impact
    public class ScreenMoving extends AbstractAction {
        public void actionPerformed(ActionEvent e) {
            System.out.println("Falling");
            //flappy.setLocation(flappy.getX()-1, flappy.getY());
            pipes[0].setLocation(pipes[0].getX()-1,pipes[0].getY());
            if (pipes[0].getX() < 330) {
                pipes[1].setLocation(pipes[1].getX()-1,pipes[1].getY());
            }
            if (pipes[1].getX() < 330) {
                pipes[2].setLocation(pipes[2].getX()-1,pipes[2].getY());
            }
            if (pipes[2].getX() < 330) {
                pipes[3].setLocation(pipes[3].getX()-1,pipes[3].getY());
            }
            if (pipes[3].getX() < 330 && pipes[0].getX() < 0 ) {
                pipes[0].setLocation(380,pipes[0].getY());
            }
            }

        }}