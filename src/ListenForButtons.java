// Jacob Schwartz - 2/28/2021
// Practicing with buttons

/*

    We need to remove the Jbuttons when we switch the displayState.
    My idea is to add the Jbuttons to an ArrayList and remove all Jbuttons in the ArrayList when displayState changes

*/

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Container;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListenForButtons extends JFrame {

    public static int displayState;
    JFrame frame = new JFrame();
    Container con = this.getContentPane();
    JPanel activePanel;

    static ListenForButtons buttonTester;
    ActionListener listener = new ClickListener();
    ActionListener goToTitle = new GoToTitleScreen();


    public ListenForButtons() {
        this.displayState = 0;
        this.setBounds(0, 0, 1295, 748);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }

    // This is the button that adds one to the displayState
    class ClickListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            buttonTester.displayState++;

            buttonTester.hideActivePanel();
            buttonTester.sceneDisplay(displayState);
            buttonTester.refresh();
        }
    }

    // This is the button that goes to the title screen
    class GoToTitleScreen implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            displayState = 0;

            buttonTester.hideActivePanel();
            buttonTester.sceneDisplay(displayState);
            buttonTester.refresh();
        }
    }

    // This will display different things based on the displayState
    public void sceneDisplay(int displayNum) {
        switch (displayNum) {
            case 0:
                System.out.println("Title Screen");

                JPanel titlePanel = new JPanel();
                titlePanel.setBackground(Color.RED);
                titlePanel.setBounds(0, 0, 1280, 720);
                activePanel = titlePanel;

                ImageIcon startButton = new ImageIcon(".//res//tlink.jpg");
                JButton button = new JButton(startButton);
                button.setBounds(483, 190, 400, 390);
                button.addActionListener(listener);

                con.add(button);
                con.add(titlePanel);

                break;
            case 1:
                System.out.println("1"); // This stuff is called mazePanel and mazeButton because I took the code from
                                         // my maze game.

                JPanel mazePanel = new JPanel();
                mazePanel.setBackground(Color.ORANGE);
                mazePanel.setBounds(0, 0, 1280, 720);
                frame.pack();

                activePanel = mazePanel;

                JButton mazeButton = new JButton("1");
                mazeButton.setBounds(483, 190, 400, 390);
                mazeButton.addActionListener(listener);

                con.add(mazeButton);
                con.add(mazePanel);

                break;
            case 2:

                System.out.println("2");

                JPanel panel1 = new JPanel();
                panel1.setBackground(Color.YELLOW);
                panel1.setBounds(0, 0, 1280, 720);
                frame.pack();

                activePanel = panel1;

                JButton button1 = new JButton("2");
                button1.setBounds(483, 190, 400, 390);
                button1.addActionListener(listener);

                con.add(button1);
                con.add(panel1);

                break;
            case 3:

                System.out.println("3");

                JPanel panel2 = new JPanel();
                panel2.setBackground(Color.GREEN);
                panel2.setBounds(0, 0, 1280, 720);
                frame.pack();

                activePanel = panel2;

                JButton button2 = new JButton("3");
                button2.setBounds(483, 190, 400, 390);
                button2.addActionListener(listener);

                JButton titleButton = new JButton("title");
                titleButton.setBounds(0, 0, 100, 60);
                titleButton.addActionListener(goToTitle);

                con.add(titleButton);
                con.add(button2);
                con.add(panel2);

                break;

            case 4:
            default:
                System.out.println("Unknown number/ not yet implemented");
        }
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

    public static void main(String[] args) {

        buttonTester = new ListenForButtons();

        buttonTester.sceneDisplay(0);
        buttonTester.refresh();

    }

}