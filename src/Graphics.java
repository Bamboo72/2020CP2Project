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
        // this.setBounds(10, 10, 1280, 760);
        this.setBounds(0, 0, 1295, 748);
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
            titlePanel.setBounds(0, 0, 1280, 720);
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

            // I want the window to resize to the maze size, but I might have to close the
            // window and open a new one that is the correct size...
            int mazeSizeX = MazeGame.testMaze.x * 64; // Mutiply block count by size
            int mazeSizeY = MazeGame.testMaze.y * 64;
            MazeGame.testMaze.display(MazeGeneration.maze);

            JPanel mazePanel = new JPanel();
            mazePanel.setBackground(Color.BLACK);
            mazePanel.setBounds(0, 0, mazeSizeX, mazeSizeY);
            frame.setSize(mazeSizeX, mazeSizeY);
            frame.pack();

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
    public void mazeDisplay(char type, int x, int y) { // Block type and where to display
        ImageIcon mazeBlockImage = new ImageIcon();

        switch (type) {

        case 'O':
            mazeBlockImage = new ImageIcon(".//res//0.png");
            break;
        case '#':
            mazeBlockImage = new ImageIcon(".//res//4.png");
            break;
        case 'E':
            mazeBlockImage = new ImageIcon(".//res//Exit.png");
            break;
        case 'S':
            mazeBlockImage = new ImageIcon(".//res//Entrance.png");
            break;

        case '4':
            mazeBlockImage = new ImageIcon(".//res//4.png");
            break;
        case '0':
            mazeBlockImage = new ImageIcon(".//res//0.png");
            break;
        case '=':
            mazeBlockImage = new ImageIcon(".//res//=.png");
            break;
        case 'P':
            mazeBlockImage = new ImageIcon(".//res//P.png");
            break;

        case 'N':
            mazeBlockImage = new ImageIcon(".//res//N.png");
            break;
        case ')':
            mazeBlockImage = new ImageIcon(".//res//).png");
            break;
        case 'U':
            mazeBlockImage = new ImageIcon(".//res//U.png");
            break;
        case 'C':
            mazeBlockImage = new ImageIcon(".//res//C.png");
            break;

        case '7':
            mazeBlockImage = new ImageIcon(".//res//7.png");
            break;
        case 'J':
            mazeBlockImage = new ImageIcon(".//res//J.png");
            break;
        case 'L':
            mazeBlockImage = new ImageIcon(".//res//L.png");
            break;
        case 'R':
            mazeBlockImage = new ImageIcon(".//res//R.png");
            break;

        case 'T':
            mazeBlockImage = new ImageIcon(".//res//T.png");
            break;
        case 'I':
            mazeBlockImage = new ImageIcon(".//res//I.png");
            break;
        case 'B':
            mazeBlockImage = new ImageIcon(".//res//B.png");
            break;
        case '1':
            mazeBlockImage = new ImageIcon(".//res//1.png");
            break;

        default:
            mazeBlockImage = new ImageIcon();

        }

        JLabel mbLabel = new JLabel(mazeBlockImage);

        JPanel mbPanel = new JPanel();
        mbPanel.setBackground(new Color(0, 0, 0, 0));
        // mbPanel.setBounds(x, y, 157, 157); // 157, 157 for Large Blocks
        mbPanel.setBounds(x, y, 64, 74); // 64, 64 for Small Blocks

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

}