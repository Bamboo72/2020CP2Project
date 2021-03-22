// Jacob Schwartz - 3/18/2021

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class ControllableCharacter {

    Graphics g = MazeGame.g;
    char dir;
    int x, y;

    public ControllableCharacter(char dir, int x, int y){
        this.dir = dir;
        this.x = x;
        this.y = y;
        g.characterDisplay(dir, x, y);
        MousePressListener mpl = new MousePressListener();
    }

      // This is the button that adds one to the displayState
      class MousePressListener implements MouseListener {

        public void mouseClicked(MouseEvent event) {
            int x = event.getX();
            int y = event.getY();

            if(dir == 'N' && y >= MazeGeneration.currentY){
                System.out.println("Up");
                MazeGeneration.currentY--;
            } else if(dir == 'E' && x >= MazeGeneration.currentX){
                System.out.println("Right");
                MazeGeneration.currentX++;
            } else if(dir == 'S' && y < MazeGeneration.currentY){
                System.out.println("Down");
                MazeGeneration.currentY++;
            } else if(dir == 'W' && x < MazeGeneration.currentX){
                System.out.println("Left");
                MazeGeneration.currentX--;
            }

        }

        
        public void mouseEntered(MouseEvent event) { }
        public void mouseExited(MouseEvent event) { }
        public void mousePressed(MouseEvent event) { }
        public void mouseReleased(MouseEvent event) { }
   
    }

}
