import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyPanel extends JPanel {
    final int PANEL_WIDTH = 1280;
    final int PANEL_HEIGHT = 1000;
    Image backgroundImage;
    Image ziemia;
    Image metoryt;
    JButton startgame;






    MyPanel()
    {


        this.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
        this.setBackground(Color.black);

        backgroundImage = new ImageIcon("tło.jpg").getImage();

    }


    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics g2D = (Graphics2D) g;
        g2D.drawImage(backgroundImage,0,0,null);
    }




}
