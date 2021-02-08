// Jacob Schwartz - 2/6/2021
// The entry point for my Programming 2 individual project: Maze Game

public class MazeGame {
    static Graphics g = new Graphics();
    static MazeGeneration gen = new MazeGeneration();
    public static void main(String[] args){
        
       

        g.hideActivePanel();
        g.sceneDisplay(1);
        g.refresh();

       // g.dispose(); // Closes the open window
    }
}
