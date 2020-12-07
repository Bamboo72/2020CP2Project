/**
* @author Jacob Schwartz
* @version 1.0
* @since 2020-12-7
* This is the entry point class, and uses all the other classes.
*/


public class MazeGame {


  public static void main(String[] args) {
    int mazes = 0;

    System.out.println("---------------------------------------------------");

    while (!isPossible) {

      // Generate the maze and put the robot in the maze
      mazeGen();
      System.out.println("Entering the maze...");

      for (int i = 0; i < w * h; i++) {
        if (exitFound && !trapped) {
          isPossible = true;
          break;
        }
        // if(trapped){
        //   break;
        // }
        mazeSolve();
      }
      if (!exitFound || trapped) {
        System.out.println("This maze is impossible?");
        mazes++;

      }

    }
    System.out.println(mazes + " mazes were tried.");
      // Print the maze
      System.out.println(Arrays.toString(row0));
      System.out.println(Arrays.toString(row1));
      System.out.println(Arrays.toString(row2));
      System.out.println(Arrays.toString(row3));
      System.out.println(Arrays.toString(row4));


    // while (!exitFound) {
    // mazeSolve();

    // }

  }

}
