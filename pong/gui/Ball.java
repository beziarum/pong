
package pong.gui;

import java.awt.*;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

public class Ball extends PongItem {
    
    protected static final int INIT_SPEED = 4;
    
    
    public Ball()
    {
	
	ImageIcon icon;
	im = Toolkit.getDefaultToolkit().createImage(
	    ClassLoader.getSystemRessource("image/ball.png"));
	icon = new ImageIcon(im);
	width = icon.getIconWidth();
	height = icon.getIconHeigth();

	speed = new Point(INIT_SPEED,INIT_SPEED);
    }

    public void animate(){}
}
