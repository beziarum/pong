
package pong.gui;

import java.awt.*;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

public class Ball extends PongItem {
    
    protected static final int INIT_SPEED = 4;
    
    protected final Image im;
    
    public Ball()
    {
	ImageIcon icon;
	im = Toolkit.getDefaultToolkit().createImage(
	    ClassLoader.getSystemResource("image/ball.png"));
	icon = new ImageIcon(im);
	width = icon.getIconWidth();
	height = icon.getIconHeight();

	speed = new Point(INIT_SPEED,INIT_SPEED);
    }

    public void animate(){}
}
