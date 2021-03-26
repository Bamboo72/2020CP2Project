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

/**
 * This class handles all the graphics for the project
 */
public class Graphics extends JFrame {

    int displayState;
    JFrame frame = new JFrame();
    Container con = this.getContentPane();
    JPanel activePanel;
    ArrayList<JButton> buttonList = new ArrayList<JButton>();
    ArrayList<JPanel> panelList = new ArrayList<JPanel>();
    ArrayList<JPanel> characterList = new ArrayList<JPanel>();

    /**
     * This is the constructor for the graphics class which sets up some of the
     * JFrame attributes
     */
    public Graphics() {
        this.displayState = 0;
        this.setBounds(10, 10, 1296, 760);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }

    /**
     * This method will display different scenes depending on the displayState
     * 
     * @param displayNum
     */
    public void sceneDisplay(int displayNum) {
        switch (displayNum) {
        case 0: // Title Screen

            ImageIcon titleImage = new ImageIcon(".//res//TitleScreen.png");
            JLabel titleLabel = new JLabel(titleImage);
            JPanel titlePanel = new JPanel();
            titlePanel.setBounds(0, 0, 1280, 720);
            activePanel = titlePanel;

            ImageIcon startButtonImage = new ImageIcon(".//res//StartButton.png");
            JButton button = new JButton(startButtonImage);
            ActionListener listener = new ClickListener();
            button.addActionListener(listener);
            button.setBounds(320, 390, 305, 95); // 305, 95
            buttonList.add(button);

            ImageIcon sizeSmallButtonImage = new ImageIcon(".//res//SizeSmallButton.png");
            JButton ssbutton = new JButton(sizeSmallButtonImage);
            ActionListener ssListener = new SmallMazeButton();
            ssbutton.addActionListener(ssListener);
            ssbutton.setBounds(315, 490, 154, 90); // 154, 90
            buttonList.add(ssbutton);

            ImageIcon sizeMediumButtonImage = new ImageIcon(".//res//SizeMediumButton.png");
            JButton smbutton = new JButton(sizeMediumButtonImage);
            ActionListener smListener = new MediumMazeButton();
            smbutton.addActionListener(smListener);
            smbutton.setBounds(474, 490, 154, 90); // 154, 90
            buttonList.add(smbutton);

            ImageIcon sizeLargeButtonImage = new ImageIcon(".//res//SizeLargeButton.png");
            JButton slbutton = new JButton(sizeLargeButtonImage);
            ActionListener slListener = new LargeMazeButton();
            slbutton.addActionListener(slListener);
            slbutton.setBounds(638, 490, 154, 90); // 154, 90
            buttonList.add(slbutton);

            ImageIcon sizeMaxButtonImage = new ImageIcon(".//res//SizeMaxButton.png");
            JButton sMaxButton = new JButton(sizeMaxButtonImage);
            ActionListener sMaxListener = new MaxMazeButton();
            sMaxButton.addActionListener(sMaxListener);
            sMaxButton.setBounds(797, 490, 154, 90); // 154, 90
            buttonList.add(sMaxButton);

            ImageIcon instructionsButtonImage = new ImageIcon(".//res//InstructionsButton.png");
            JButton instructionsButton = new JButton(instructionsButtonImage);
            ActionListener goToInstructionsListener = new GoToInstructionsButton();
            instructionsButton.addActionListener(goToInstructionsListener);
            instructionsButton.setBounds(643, 390, 305, 95); // 305, 95
            buttonList.add(instructionsButton);

            titlePanel.add(titleLabel);
            con.add(button);
            con.add(ssbutton);
            con.add(smbutton);
            con.add(slbutton);
            con.add(sMaxButton);
            con.add(instructionsButton);
            con.add(titlePanel);

            break;
        case 1: // Maze Screen

            int mazeSizeX = MazeGame.theMaze.x * 64; // Mutiply block count by size
            int mazeSizeY = MazeGame.theMaze.y * 64;
            MazeGame.theMaze.display(MazeGeneration.maze);

            JPanel mazePanel = new JPanel();
            mazePanel.setBackground(new Color(188, 190, 192)); // 128, 130, 133
            mazePanel.setBounds(0, 0, mazeSizeX, mazeSizeY);
            frame.setSize(mazeSizeX, mazeSizeY);
            frame.pack();

            activePanel = mazePanel;

            MousePressListener mpl = new MousePressListener();
            mazePanel.addMouseListener(mpl);

            new KeyPressListener();

            ImageIcon XButton = new ImageIcon(".//res//XButton.png");
            JButton backButton = new JButton(XButton);
            ActionListener goHome = new GoToHomeScreen();
            backButton.addActionListener(goHome);
            backButton.setBounds(1218, 5, 60, 60); // 60, 60
            buttonList.add(backButton);

            con.add(backButton);
            con.add(mazePanel);

            break;
        case 2: // Complete! Screen

            JPanel blankPanel = new JPanel();
            blankPanel.setBackground(new Color(188, 190, 192));
            blankPanel.setBounds(0, 0, 1280, 720);
            activePanel = blankPanel;

            ImageIcon completeMaze = new ImageIcon(".//res//Complete.png");
            JLabel completeLabel = new JLabel(completeMaze);
            JPanel completePanel = new JPanel();
            completePanel.setBackground(new Color(188, 190, 192));
            completePanel.setBounds(395, 270, 490, 190); // 478, 178
            completePanel.add(completeLabel);
            panelList.add(completePanel);

            // ImageIcon saveButtonImage = new ImageIcon(".//res//SaveButton.png");
            // JButton savebutton = new JButton(saveButtonImage);
            // ActionListener saveListener = new SaveMazeButton();
            // savebutton.addActionListener(saveListener);
            // savebutton.setBounds(474, 490, 154, 90); // 154, 90
            // buttonList.add(savebutton);

            ImageIcon menuButtonImage = new ImageIcon(".//res//MenuButton.png");
            JButton menuButton = new JButton(menuButtonImage);
            ActionListener menuListener = new GoToHomeScreen();
            menuButton.addActionListener(menuListener);
            menuButton.setBounds(563, 490, 154, 90); // 154, 90
            buttonList.add(menuButton);

            // con.add(savebutton);
            con.add(menuButton);
            con.add(completePanel);
            con.add(blankPanel);

            break;
        case 3: // Instructions Screen
            // This would be for the save/load menu, but I don't have time to implement that
            // feature. I'd need to think more about it first
            // Instead I decided to make this the spot for the instructions.

            ImageIcon instuctionsImage = new ImageIcon(".//res//InstructionsScreen.png");
            JLabel instructionsLabel = new JLabel(instuctionsImage);
            JPanel instructionsPanel = new JPanel();
            instructionsPanel.add(instructionsLabel);
            instructionsPanel.setBounds(0, 0, 1280, 720);
            activePanel = instructionsPanel;

            ImageIcon instructionsXButton = new ImageIcon(".//res//XButton.png");
            JButton instructionsBackButton = new JButton(instructionsXButton);
            ActionListener anotherGoHome = new GoToHomeScreen();
            instructionsBackButton.addActionListener(anotherGoHome);
            instructionsBackButton.setBounds(1218, 5, 60, 60); // 60, 60
            buttonList.add(instructionsBackButton);

            con.add(instructionsBackButton);
            con.add(instructionsPanel);
            break;
        default:
            System.out.println("Unknown number/ not yet implemented");
        }
    }

    /**
     * This method will create a maze block at the location parameters
     * 
     * @param type
     * @param x
     * @param y
     */
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
            mazeBlockImage = new ImageIcon(".//res//E.png");
            break;
        case 'S':
            mazeBlockImage = new ImageIcon(".//res//S.png");
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
        mbPanel.setBounds(x, y, 69, 74); // Block size is 64, 64, but use 69, 74 to look nice

        mbPanel.add(mbLabel);
        panelList.add(mbPanel);
        con.add(mbPanel);
    }

    /**
     * This method will display the character at the current location
     * 
     * @param c
     */
    public void characterDisplay(ControllableCharacter c) {
        ImageIcon characterImage = new ImageIcon();

        if (c.dir == 'N') {
            characterImage = new ImageIcon(".//res//CharacterN.png");
        } else if (c.dir == 'E') {
            characterImage = new ImageIcon(".//res//CharacterE.png");
        } else if (c.dir == 'S') {
            characterImage = new ImageIcon(".//res//CharacterS.png");
        } else if (c.dir == 'W') {
            characterImage = new ImageIcon(".//res//CharacterW.png");
        }

        JLabel characterLabel = new JLabel(characterImage);
        JPanel characterPanel = new JPanel();
        characterPanel.setBackground(new Color(0, 0, 0, 0));
        characterPanel.setBounds(c.x * 64, c.y * 64, 64, 74);
        characterPanel.add(characterLabel);
        characterList.add(characterPanel);
        con.add(characterPanel);

    }

    /**
     * This method will refresh the frame when it is called
     */
    public void refresh() {
        validate();
        repaint();
    }

    /**
     * This method will hide the active panel so a new one can be displayed
     */
    public void hideActivePanel() {
        if (activePanel != null) {
            activePanel.setVisible(false);
        }
        for (JButton button : buttonList) {
            button.setVisible(false);
        }
        for (JPanel panel : panelList) {
            panel.setVisible(false);
        }
        for (JPanel character : characterList) {
            character.setVisible(false);
        }
    }

}

/**
 * This class makes the button that makes a new maze
 */
class ClickListener implements ActionListener {

    static int paramX = 10;
    static int paramY = 10;
    static boolean firstMaze = true;

    public void actionPerformed(ActionEvent event) {

        if (!firstMaze) { // This somehow works? Alrighty.
            MazeGame.theMaze.mazeReset();
        }

        // Maze Sizes: Small: 6, 6 Medium: 10, 10 Large: 14, 10 Max: 19, 10
        MazeGame.theMaze = new MazeGeneration(1, paramX, paramY);

        MazeGame.theMaze.mazeGen();
        firstMaze = false;
        MazeGame.cc = new ControllableCharacter('N', paramX - 1, paramY - 1);

        MazeGame.g.hideActivePanel();
        MazeGame.g.sceneDisplay(1);
        MazeGame.g.refresh();
    }
}

/**
 * This class makes the button that sets the maze to small size parameters
 */
class SmallMazeButton implements ActionListener {

    public void actionPerformed(ActionEvent event) {
        ClickListener.paramX = 6;
        ClickListener.paramY = 6;
    }
}

/**
 * This class makes the button that sets the maze to small medium size
 * parameters
 */
class MediumMazeButton implements ActionListener {

    public void actionPerformed(ActionEvent event) {
        ClickListener.paramX = 10;
        ClickListener.paramY = 10;
    }
}

/**
 * This class makes the button that sets the maze to large size parameters
 */
class LargeMazeButton implements ActionListener {

    public void actionPerformed(ActionEvent event) {
        ClickListener.paramX = 14;
        ClickListener.paramY = 10;
    }
}

/**
 * This class makes the button that sets the maze to max size parameters
 */
class MaxMazeButton implements ActionListener {

    public void actionPerformed(ActionEvent event) {
        ClickListener.paramX = 19;
        ClickListener.paramY = 10;
    }
}

/**
 * This class makes the button that switches the scene display to the home
 * screen
 */
class GoToHomeScreen implements ActionListener {

    public void actionPerformed(ActionEvent event) {
        MazeGame.g.hideActivePanel();
        MazeGame.g.sceneDisplay(0);
        MazeGame.g.refresh();

    }
}

/**
 * This class makes the button that switches the scene display to the
 * instructions screen
 */
class GoToInstructionsButton implements ActionListener {

    public void actionPerformed(ActionEvent event) {
        MazeGame.g.hideActivePanel();
        MazeGame.g.sceneDisplay(3);
        MazeGame.g.refresh();

    }
}

/**
 * This class makes the button that switches the scene display to the save
 * screen (Currently Unused)
 */
class SaveMazeButton implements ActionListener {

    public void actionPerformed(ActionEvent event) {
        MazeGame.g.hideActivePanel();
        MazeGame.g.sceneDisplay(2);
        MazeGame.g.refresh();

    }
}
