// Jacob Schwartz - 3/18/2021
// Mouse Listener code

public class ControllableCharacter {

    Graphics g = MazeGame.g;
    char dir;
    int x, y;

    public ControllableCharacter(char dir, int x, int y){
        this.dir = dir;
        this.x = x;
        this.y = y;
        g.characterDisplay(dir, x, y);

    }



}
