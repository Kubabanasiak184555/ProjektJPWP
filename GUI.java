import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.CookieHandler;
import java.net.URI;
import java.util.Map;
import java.util.Random;
import javax.swing.Timer;
import javax.swing.event.AncestorListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUI extends JFrame implements MouseListener, ActionListener
{


    MyPanel panel;
    JButton label;
    JLabel liczniklabel;
    ImageIcon meto;
    ImageIcon wybuch;
    int licznikmeto;

    Font font1;

    Klikanie klikanie = new Klikanie();
    JButton startgame;




    public void CreateFont()
    {
        font1 = new Font("Comic Sans MS",Font.PLAIN,27);
    }
    public void XYZdjęcia()
    {
        int minx = 0;
        int maxx = 1100;
        int miny =0;
        int maxy =450;
        int x = (int)(Math.random()*(maxx-minx+1)+minx); //położenie xy meteorytu
        int y = (int)(Math.random()*(maxy-miny+1)+miny);
        System.out.println("moje x" +x);
        System.out.println("moje y" +y);
        label.setBounds(x,y,250,200);


    }


    GUI()
    {


        CreateFont();
        licznikmeto=0;
        panel = new MyPanel();
        label = new JButton();

        label.setFocusPainted(false);
        label.setMargin(new Insets(0, 0, 0, 0));
        label.setContentAreaFilled(false);
        label.setBorderPainted(false);
        label.setOpaque(false);

        label.addMouseListener(this);


        label.setBounds(500,200,250,200);
        meto = new ImageIcon("met.png");
        wybuch = new ImageIcon("bum.png");
        label.setIcon(meto);
        label.addActionListener(klikanie);





        JPanel licznik = new JPanel();
        licznik.setBounds(30,830,350,100);
        licznik.setOpaque(false);
        this.add(licznik);


        liczniklabel = new JLabel("zestrzelone meteoryty: " + licznikmeto );
        liczniklabel.setForeground(Color.white);
        liczniklabel.setFont(font1);
        licznik.add(liczniklabel);

        JProgressBar pasek = new JProgressBar();
        pasek.setValue(100);
        pasek.setBounds(55,900,300,50);
        pasek.setStringPainted(true);
        pasek.setForeground(Color.red);
        this.add(pasek);


        JPanel prostokat1 = new JPanel();
        prostokat1.setBounds(0,800,426,200);
        prostokat1.setBackground(new Color(30,31,36));
        this.add(prostokat1);


        JPanel prostokat2 = new JPanel();
        prostokat2.setBounds(426,800,426,200);
        prostokat2.setBackground(new Color(30,31,36));
        //this.add(prostokat2);

        JPanel prostokat3 = new JPanel();
        prostokat3.setBounds(852,800,428,200);
        prostokat3.setBackground(new Color(30,31,36));
        this.add(prostokat3);














        this.add(label);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);
        this.setTitle("Spadające meteoryty");
    }






    public void mouseClicked(MouseEvent e) {

        XYZdjęcia();
        System.out.println("brawo");


    }

    public  class Klikanie implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {

            licznikmeto++;
            liczniklabel.setText("zestrzelone meteoryty: " + licznikmeto);
        }
    }


    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void actionPerformed(ActionEvent e) {



    }
}
