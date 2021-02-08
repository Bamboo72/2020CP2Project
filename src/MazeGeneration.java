// Jacob Schwartz - 2/6/2021
// The maze generation class for my Programming 2 individual project: Maze Game

/*
    Notes:
    Look into using ArrayLists for the maze arrays
*/

public class MazeGeneration {

    Graphics g = MazeGame.g;

    // (0,0) V
    static char[] row0 = { '#', '#', '#', '#', 'E', 'O', 'O', '#', 'O', 'O', };
    static char[] row1 = { '#', '#', '#', '#', '#', '#', 'O', 'O', '#', 'O', };
    static char[] row2 = { '#', 'O', 'O', 'O', 'O', 'O', '#', 'O', 'O', 'O', };
    static char[] row3 = { '#', 'O', '#', 'O', '#', 'O', '#', '#', '#', 'O', };
    static char[] row4 = { '#', 'O', '#', 'O', '#', 'O', 'O', 'O', 'O', 'O', };
    // Starting positon is here ^

    static char[][] maze = { row0, row1, row2, row3, row4 };
    // static char[][] maze = new char[h][w];

    public void display(char[][] maze) {
        int rowNum = 0;
        int type = 0;
        for (char[] row : maze) {
            for (int i = 0; i < row.length; i++) {

                if (row[i] == '#') {
                    type = 1;
                } else if (row[i] == 'O') {
                   type = 0;
                } else if (row[i] == 'E') {
                    type = 2;
                }
                g.mazeDisplay(type, 157*i, 153*rowNum); //157
            }
            rowNum++;
        }
    }

}
