// Jacob Schwartz - 3/25/2021
// Code originated from https://stackoverflow.com/questions/18324853/checking-if-the-mouse-is-clicked-in-java
// as well as the Big Java textbook (Heavily modified to fit my project!)

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

/**
 * This class handles the mouse events that move the character
 */
class MousePressListener implements MouseListener {

    /**
     * This method detects when the mouse is clicked and moves the character
     * depending on a variety of conditions (character direction and location)
     */
    public void mouseClicked(MouseEvent event) {
        int x = event.getX();
        int y = event.getY();
        ControllableCharacter cc = MazeGame.cc;

        int ccx = cc.x * 64;
        int ccy = cc.y * 64;

        switch (cc.dir) { // I'd like to detect diagonal boundaries, but I'm not sure how
        // y < ccy && x < (ccx + 32) * ccy && x > -ccx * ccy

        case 'N':
            if (y < ccy && x < ccx + 64 && x > ccx) {
                if (allowedMoveCheck('N')) {
                    System.out.println("Go Forward");
                    cc.y--;
                }

            } else if (x > ccx + 64) {

                System.out.println("Turn Right");
                cc.turnClockwise('N');
            } else if (x < ccx) {
                System.out.println("Turn Left");
                cc.turnCounterClockwise('N');
            }
            break;
        case 'E':
            if (x > ccx && y < ccy + 64 && y > ccy) {
                if (allowedMoveCheck('E')) {
                    System.out.println("Go Forward");
                    cc.x++;
                }
            } else if (y > ccy + 64) {
                System.out.println("Turn Right");
                cc.turnClockwise('E');
            } else if (y < ccy) {
                System.out.println("Turn Left");
                cc.turnCounterClockwise('E');
            }
            break;
        case 'S':
            if (y > ccy && x < ccx + 64 && x > ccx) {
                if (allowedMoveCheck('S')) {
                    System.out.println("Go Forward");
                    cc.y++;
                }
            } else if (x < ccx) {
                System.out.println("Turn Right");
                cc.turnClockwise('S');
            } else if (x > ccx + 64) {
                System.out.println("Turn Left");
                cc.turnCounterClockwise('S');
            }
            break;
        case 'W':
            if (x < ccx && y > ccy && y < ccy + 64) {
                if (allowedMoveCheck('W')) {
                    System.out.println("Go Forward");
                    cc.x--;
                }
            } else if (y < ccy) {
                System.out.println("Turn Right");
                cc.turnClockwise('W');
            } else if (y > ccy + 64) {
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
    }

    /**
     * This method returns whether moving from a tile is allowed depending on the
     * tile and character direction
     * 
     * @param dir
     * @return boolean allowed or not depending on the tile type and character
     *         direction
     */
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
            break;

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

    /**
     * Blank method
     */
    public void mouseEntered(MouseEvent event) {
    }

    /**
     * Blank method
     */
    public void mouseExited(MouseEvent event) {
    }

    /**
     * Blank method
     */
    public void mousePressed(MouseEvent event) {
    }

    /**
     * Blank method
     */
    public void mouseReleased(MouseEvent event) {
    }

}