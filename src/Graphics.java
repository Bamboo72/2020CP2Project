// Jacob Schwartz - 2/6/2021
// The graphics class for my Programming 2 individual project: Maze Game
// Based of of the Graphics.java file I wrote for the group project: The Isle of Laeso

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Container;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.ArrayList;

public class Graphics extends JFrame {

    private int displayState;
    JFrame frame = new JFrame();
    Container con = this.getContentPane();
    JPanel activePanel;
    ArrayList<JButton> buttonList = new ArrayList<JButton>();
    ArrayList<JPanel> panelList = new ArrayList<JPanel>();

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
            ActionListener listener = new ClickListener();
            button.addActionListener(listener);
            button.setBounds(483, 390, 305, 95); // 305, 95
            buttonList.add(button);

            titlePanel.add(titleLabel);
            con.add(button);
            con.add(titlePanel);

            break;
        case 1:
            System.out.println("Maze");

            // I want the window to resize to the maze size, but I might have to close the
            // window and open a new one that is the correct size...
            int mazeSizeX = MazeGame.theMaze.x * 64; // Mutiply block count by size
            int mazeSizeY = MazeGame.theMaze.y * 64;
            MazeGame.theMaze.display(MazeGeneration.maze);

            JPanel mazePanel = new JPanel();
            mazePanel.setBackground(new Color(188, 190, 192)); // 128, 130, 133
            mazePanel.setBounds(0, 0, mazeSizeX, mazeSizeY);
            frame.setSize(mazeSizeX, mazeSizeY);
            frame.pack();

            activePanel = mazePanel;

            ImageIcon XButton = new ImageIcon(".//res//XButton.png");
            JButton backButton = new JButton(XButton);
            ActionListener goHome = new GoToHomeScreen();
            backButton.addActionListener(goHome);
            backButton.setBounds(1210, 10, 60, 60); // 60, 60
            buttonList.add(backButton);

            con.add(backButton);
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
        panelList.add(mbPanel);
        con.add(mbPanel);
    }

    // This will display the character at the current location
    public void characterDisplay(char dir, int x, int y){
        ImageIcon characterImage = new ImageIcon();
       
        if(dir == 'N'){
            characterImage = new ImageIcon(".//res//CharacterN.png");
        } else if(dir == 'E'){
            characterImage = new ImageIcon(".//res//CharacterE.png");
        } else if(dir == 'S'){
            characterImage = new ImageIcon(".//res//CharacterS.png");
        } else if(dir == 'W'){
            characterImage = new ImageIcon(".//res//CharacterW.png");
        }
        
        JLabel characterLabel = new JLabel(characterImage);
        JPanel characterPanel = new JPanel();
        characterPanel.setBackground(new Color(0, 0, 0, 0));
        characterPanel.setBounds(x, y, 64, 74);
        characterPanel.add(characterLabel);
        con.add(characterPanel);
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
        for(JButton button: buttonList){
            button.setVisible(false);
           // buttonList.remove(button);
        }
        for(JPanel panel: panelList){
            panel.setVisible(false);
           // panelList.remove(panel);
        }
        
    }

}

// This is the button that makes a new maze
class ClickListener implements ActionListener {

    public void actionPerformed(ActionEvent event) {

        MazeGame.theMaze = new MazeGeneration(1, 10, 10);
        ControllableCharacter cc = new ControllableCharacter('N', MazeGame.theMaze.currentX, MazeGame.theMaze.currentY);

        MazeGame.theMaze.mazeReset();
        MazeGame.theMaze = new MazeGeneration(1, 10, 10); // Later change to user input sizes? Or just make multiple buttons that make different size mazes
        //characterDisplay();
        MazeGame.theMaze.mazeGen();
        MazeGame.g.hideActivePanel();
        MazeGame.g.sceneDisplay(1);
        MazeGame.g.refresh();
    }
}


// This is the button that returns to the home screen
class GoToHomeScreen implements ActionListener {

    public void actionPerformed(ActionEvent event) {
        MazeGame.g.hideActivePanel();
        MazeGame.g.sceneDisplay(0);
        MazeGame.g.refresh();
    }
}