// Jacob Schwartz - 3/18/2021

/**
 * This class handles the controllabe character
 */
public class ControllableCharacter {

    Graphics g = MazeGame.g;
    char dir;
    int x, y;

    /**
     * This is the constructor which sets the character's direction and coordinates
     * 
     * @param dir
     * @param x
     * @param y
     */
    public ControllableCharacter(char dir, int x, int y) {
        this.dir = dir;
        this.x = x;
        this.y = y;
        g.characterDisplay(this);

    }

    /**
     * This method will turn the character clockwise depending on the starting
     * direction
     * 
     * @param startingDir
     */
    public void turnClockwise(char startingDir) {
        switch (startingDir) {
        case 'N':
            dir = 'E';
            break;
        case 'E':
            dir = 'S';
            break;
        case 'S':
            dir = 'W';
            break;
        case 'W':
            dir = 'N';
            break;
        }
    }

    /**
     * This method will turn the character counter clockwise depending on the
     * starting direction
     * 
     * @param startingDir
     */
    public void turnCounterClockwise(char startingDir) {
        switch (startingDir) {
        case 'N':
            dir = 'W';
            break;
        case 'E':
            dir = 'N';
            break;
        case 'S':
            dir = 'E';
            break;
        case 'W':
            dir = 'S';
            break;
        }
    }

}
