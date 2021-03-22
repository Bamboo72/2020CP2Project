// Jacob Schwartz - 2/6/2021
// The entry point for my Programming 2 individual project: Maze Game

public class MazeGame {
    static Graphics g = new Graphics();
    //static MazeGeneration testMaze = new MazeGeneration(1, 10, 10);
    //static ControllableCharacter cc = new ControllableCharacter('N', testMaze.currentX, testMaze.currentY);

    static MazeGeneration theMaze;

    public static void main(String[] args) {

        

        //testMaze.mazeGen();
        
        g.hideActivePanel();
        g.sceneDisplay(0);
        g.refresh();
        g.refresh();

        // g.dispose(); // Closes the open window
    }
}
