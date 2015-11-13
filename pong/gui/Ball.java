
package pong.gui;

import java.awt.*;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

public class Ball extends PongItem {
    
    protected static final int INIT_SPEED = 4;
    
    
    public Ball()
    {
    load("image/ball.png");
	speed = new Point(INIT_SPEED,INIT_SPEED);
    }

    
}
