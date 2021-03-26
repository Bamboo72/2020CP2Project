// Jacob Schwartz - 3/25/2021
// This is an alternative method of controling the character

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This class handles the key events that move the character
 */
class KeyPressListener extends JFrame implements KeyListener {

    JFrame keyFrame;

    public KeyPressListener() {

        JPanel p = new JPanel();
        JLabel label = new JLabel("Key Listener!");
        p.add(label);
        add(p);
        addKeyListener(this);
        setBounds(1280, -200, 10, 10);
        setVisible(true);
    }

    // Empty method
    public void keyPressed(KeyEvent event) {
    }

    // Empty method
    public void keyTyped(KeyEvent event) {
    }

    public void keyReleased(KeyEvent event) {

        ControllableCharacter cc = MazeGame.cc;

        switch (cc.dir) {

        case 'N':
            if (event.getKeyCode() == KeyEvent.VK_UP) {
                if (allowedMoveCheck('N')) {
                    System.out.println("Go Forward");
                    cc.y--;
                }

            } else if (event.getKeyCode() == KeyEvent.VK_RIGHT) {

                System.out.println("Turn Right");
                cc.turnClockwise('N');
            } else if (event.getKeyCode() == KeyEvent.VK_LEFT) {
                System.out.println("Turn Left");
                cc.turnCounterClockwise('N');
            }
            break;
        case 'E':
            if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
                if (allowedMoveCheck('E')) {
                    System.out.println("Go Forward");
                    cc.x++;
                }
            } else if (event.getKeyCode() == KeyEvent.VK_DOWN) {
                System.out.println("Turn Right");
                cc.turnClockwise('E');
            } else if (event.getKeyCode() == KeyEvent.VK_UP) {
                System.out.println("Turn Left");
                cc.turnCounterClockwise('E');
            }
            break;
        case 'S':
            if (event.getKeyCode() == KeyEvent.VK_DOWN) {
                if (allowedMoveCheck('S')) {
                    System.out.println("Go Forward");
                    cc.y++;
                }
            } else if (event.getKeyCode() == KeyEvent.VK_LEFT) {
                System.out.println("Turn Right");
                cc.turnClockwise('S');
            } else if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
                System.out.println("Turn Left");
                cc.turnCounterClockwise('S');
            }
            break;
        case 'W':
            if (event.getKeyCode() == KeyEvent.VK_LEFT) {
                if (allowedMoveCheck('W')) {
                    System.out.println("Go Forward");
                    cc.x--;
                }
            } else if (event.getKeyCode() == KeyEvent.VK_UP) {
                System.out.println("Turn Right");
                cc.turnClockwise('W');
            } else if (event.getKeyCode() == KeyEvent.VK_DOWN) {
                System.out.println("Turn Left");
                cc.turnCounterClockwise('W');
            }
            break;

        }

        MazeGame.g.hideActivePanel();
        MazeGame.g.characterDisplay(cc);

        MazeGame.g.sceneDisplay(1);
        MazeGame.g.refresh();

        if (MazeGeneration.getTileType(cc.x, cc.y) == 'E') {
            MazeGame.g.hideActivePanel();
            MazeGame.g.sceneDisplay(2);
            MazeGame.g.refresh();

        }
        // keyFrame.dispose(); // This generates a java.lang.NullPointerException
    }

    // This method returns whether moving from a tile is allowed depending on the
    // tile and direction
    public boolean allowedMoveCheck(char dir) {
        boolean allowed = false;
        ControllableCharacter cc = MazeGame.cc;
        char currentTile = MazeGeneration.getTileType(cc.x, cc.y);

        switch (currentTile) {

        case 'O':
            allowed = true;
            break;
        case '#':
            allowed = false;
            break;
        case 'E':
            allowed = false;
            break;
        case 'S':
            allowed = true;

        case '4':
            allowed = false;
            break;
        case '0':
            allowed = true;
            break;
        case '=':
            if (dir == 'N' || dir == 'S') {
                allowed = false;
            } else if (dir == 'E' || dir == 'W') {
                allowed = true;
            }
            break;
        case 'P':
            if (dir == 'N' || dir == 'S') {
                allowed = true;
            } else if (dir == 'E' || dir == 'W') {
                allowed = false;
            }
            break;

        case 'N':
            if (dir == 'S') {
                allowed = true;
            } else {
                allowed = false;
            }
            break;
        case ')':
            if (dir == 'W') {
                allowed = true;
            } else {
                allowed = false;
            }
            break;
        case 'U':
            if (dir == 'N') {
                allowed = true;
            } else {
                allowed = false;
            }
            break;
        case 'C':
            if (dir == 'E') {
                allowed = true;
            } else {
                allowed = false;
            }
            break;

        case '7':
            if (dir == 'N' || dir == 'E') {
                allowed = false;
            } else if (dir == 'S' || dir == 'W') {
                allowed = true;
            }
            break;
        case 'J':
            if (dir == 'N' || dir == 'W') {
                allowed = true;
            } else if (dir == 'E' || dir == 'S') {
                allowed = false;
            }
            break;
        case 'L':
            if (dir == 'N' || dir == 'E') {
                allowed = true;
            } else if (dir == 'S' || dir == 'W') {
                allowed = false;
            }
            break;
        case 'R':
            if (dir == 'N' || dir == 'W') {
                allowed = false;
            } else if (dir == 'E' || dir == 'S') {
                allowed = true;
            }
            break;

        case 'T':
            if (dir == 'N') {
                allowed = false;
            } else {
                allowed = true;
            }
            break;
        case 'I':
            if (dir == 'E') {
                allowed = false;
            } else {
                allowed = true;
            }
            break;
        case 'B':
            if (dir == 'S') {
                allowed = false;
            } else {
                allowed = true;
            }
            break;
        case '1':
            if (dir == 'W') {
                allowed = false;
            } else {
                allowed = true;
            }
            break;

        }

        return allowed;
    }

}
