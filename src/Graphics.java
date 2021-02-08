// Jacob Schwartz - 2/6/2021
// The graphics class for my Programming 2 individual project: Maze Game
// Mostly copied from the Graphics.java file for the group project: The Isle of Laeso

import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Container;

public class Graphics extends JFrame {

    private static int displayState;
    JFrame frame = new JFrame();
    Container con = this.getContentPane();
    JPanel activePanel;

    public Graphics() {
        this.displayState = 0;
        this.setBounds(10, 10, 1280, 760);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }

    // This will display different things based on the displayState
    public void sceneDisplay(int displayNum) {
        switch (displayNum) {
            case 0:
                System.out.println("Title Screen");

                ImageIcon titleImage = new ImageIcon(".//res//TitleScreen.png");
                JLabel titleLabel = new JLabel(titleImage);

                JPanel titlePanel = new JPanel();
                titlePanel.setBounds(0, 0, 1271, 780); // 1271, 780
                activePanel = titlePanel;

                // titlePanel.add(titleLabel);
                // con.add(titlePanel);

                ImageIcon startButton = new ImageIcon(".//res//StartButton.png");
                JButton button = new JButton(startButton);
                button.setBounds(483, 390, 305, 95); // 305, 95

                titlePanel.add(titleLabel);
                con.add(button);
                con.add(titlePanel);

                break;
            case 1:
                System.out.println("Maze");
           
                MazeGame.gen.display(MazeGeneration.maze);

                JPanel mazePanel = new JPanel();
                mazePanel.setBackground(Color.BLACK);
                mazePanel.setBounds(0, 0, 1271, 780); // 1271, 780
                activePanel = mazePanel;
            
                con.add(mazePanel);

            
                break;
            case 2:

                break;
            case 3:

                break;
            default:
                System.out.println("Unknown number/ not yet implemented");
        }
    }

    // This will create a maze block at the location parameters
    public void mazeDisplay(int type, int x, int y){
        ImageIcon mazeBlockImage = new ImageIcon();
        if(type == 0){
            mazeBlockImage = new ImageIcon(".//res//Space.png");
        } else if(type == 1){
            mazeBlockImage = new ImageIcon(".//res//Block.png");
        } else if(type == 2){
            mazeBlockImage = new ImageIcon(".//res//Exit.png");
        } else if(type == 3){
            mazeBlockImage = new ImageIcon(".//res//Entrance.png");
        }
        
        JLabel mbLabel = new JLabel(mazeBlockImage);

        JPanel mbPanel = new JPanel();
        mbPanel.setBackground(new Color(0,0,0,0));
        mbPanel.setBounds(x, y, 157, 157); //  157, 157
    
        mbPanel.add(mbLabel);
        con.add(mbPanel);
    }

    // This will refresh the frame when it is called
    public void refresh() {
        validate();
        repaint();
    }

    public void hideActivePanel() {
        if (activePanel != null) {
            activePanel.setVisible(false);
        }
    }

    // Old main method
    /*
     * public static void main(String[] args) { Graphics g = new Graphics();
     * 
     * Scanner inputReader = new Scanner(System.in); boolean run = true; while (run)
     * { System.out.
     * println("Choose an option: (0) Title Screen (1) Credits (2) Start Screen (3) Character Creator (4) Exit"
     * ); System.out.println("                  (5) Cycle ()"); g.displayState =
     * inputReader.nextInt();
     * 
     * // This will Cycle through the first 4 options if (g.displayState == 5) { for
     * (int i = 0; i < 4; i++) { g.displayState = i; g.hideActivePanel();
     * g.display(displayState); g.refresh(); try { Thread.sleep(4000); } catch
     * (InterruptedException e) { // TODO Auto-generated catch block
     * e.printStackTrace(); } } run = false; }
     * 
     * if (g.displayState == 4) { run = false;
     * 
     * }
     * 
     * g.hideActivePanel(); g.display(displayState); g.refresh(); } g.dispose(); //
     * Closes the open window inputReader.close(); }
     */
}