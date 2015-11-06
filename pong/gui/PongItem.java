
package pong.gui;

import javax.swing.JPanel;
import java.awt.*;
import javax.swing.JPanel;
import javax.swing.ImageIcon;


abstract public class PongItem extends JPanel {
    private static final long serialVerisonUID = 1L;

    protected final Image im;

    protected int width;
    
    protected int height;
    
    protected Point position;

    protected static final int INIT_SPEED;
    
    protected Point speed;

    abstract public void animate();

    
}
