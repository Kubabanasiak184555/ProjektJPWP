import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.Timer;
public class GUI extends JFrame implements MouseListener, ActionListener {
    int hp = 100;
    JProgressBar pasek;
    MyPanel panel;
    JLabel label;
    JLabel liczniklabel;
    ImageIcon meto;
    ImageIcon meto2;

    ImageIcon wybuch;
    int licznikmeto;
    Font font1;
    Font font2;
    int elapsedTime = 0;
    int seceond = 0;
    int minutes = 0;
    boolean started = false;
    boolean trafienie =false;
    boolean rozpoczecie =false;
    JTextField username;
    String getusername;
    int getwiek;
    JLabel imiewiek;
    JTextField age;


    String seconds_string = String.format("%02d", seceond);
    String minutes_string = String.format("%02d", minutes);
    JButton start = new JButton("Start");
    JButton reset = new JButton("Reset");
    JLabel timeLabel = new JLabel();
    int x;
    int y;
    Random xlos = new Random();
    Random ylos = new Random();




    public void CreateFont()
    {
        font1 = new Font("Comic Sans MS", Font.PLAIN, 27);
        font2 = new Font("Comic Sans MS", Font.PLAIN, 18);
    }


    public void Los()
    {
        x = xlos.nextInt(1100);
        y = ylos.nextInt(450);
        label.setIcon(meto2);
        label.setBounds(100,100,200,200);
        label.setLocation(x,y);
        System.out.println("moje x: " + x);
        System.out.println("moje y: " + y);

    }

    public void czas()
        {

                while(true)
                {
                    System.out.println(started);
                    if (started == true)
                    {



                        Los();


                        try {

                            Thread.sleep(2000);
                            if (trafienie == false) {
                                hp = hp - 10;
                                pasek.setValue(hp);
                            }
                            trafienie = false;
                            if (hp == 0) {
                                JFrame frame = new JFrame();
                                frame.setSize(430, 200);
                                frame.setLayout(null);
                                frame.setTitle("Przegrałeś");
                                frame.setVisible(true);
                                frame.setResizable(false);
                                JLabel wynik = new JLabel();
                                wynik.setBounds(0, 0, 426, 200);
                                wynik.setBackground(Color.black);
                                wynik.setOpaque(true);
                                wynik.setFont(font2);
                                wynik.setText("Przegrałeś spróbuj jesze raz");
                                wynik.setForeground(Color.WHITE);
                                frame.add(wynik);
                                hp = 100;
                                pasek.setValue(hp);
                                licznikmeto = 0;
                                liczniklabel.setText("zestrzelone meteoryty: " + licznikmeto);
                                label.setIcon(null);
                                timer.stop();
                                start.setText("START");
                                started = false;
                                restart();
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();

                        }

                    }
                }
            }



    Timer timer = new Timer(1000, new ActionListener()
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            elapsedTime = elapsedTime + 1000;
            minutes = (elapsedTime / 60000) % 60;
            seceond = (elapsedTime / 1000) % 60;
            seconds_string = String.format("%02d", seceond);
            minutes_string = String.format("%02d", minutes);
            timeLabel.setText("Czas: " + minutes_string + ":" + seconds_string);
        }

    });





    GUI() {




        CreateFont();
        licznikmeto = 0;
        panel = new MyPanel();
        label = new JLabel();

        label.setOpaque(false);

        label.addMouseListener(this);



        meto = new ImageIcon("met.png");
        meto2 = new ImageIcon("met2.png");
        wybuch = new ImageIcon("bum.png");




        JPanel licznik = new JPanel();
        licznik.setBounds(30, 830, 350, 100);
        licznik.setOpaque(false);
        this.add(licznik);


        liczniklabel = new JLabel("zestrzelone meteoryty: " + licznikmeto);
        liczniklabel.setForeground(Color.white);
        liczniklabel.setFont(font1);
        licznik.add(liczniklabel);



        pasek = new JProgressBar(0,100);
        pasek.setValue(100);
        pasek.setBounds(55, 900, 300, 50);
        pasek.setStringPainted(true);
        pasek.setForeground(Color.red);
        this.add(pasek);



        JPanel prostokat1 = new JPanel();
        prostokat1.setBounds(0, 800, 1280, 200);
        prostokat1.setBackground(new Color(30, 31, 36));
        prostokat1.setOpaque(true);

            imiewiek = new JLabel();
            imiewiek.setText("Podaj swoje imie i wiek : ");
            imiewiek.setBounds(800, 800, 350, 100);
            imiewiek.setFont(font1);
            imiewiek.setForeground(Color.white);
            imiewiek.setOpaque(false);
            this.add(imiewiek);



            username = new JTextField("Imie");
            username.setBounds(800, 900, 100, 20);
            this.add(username);

            age = new JTextField("Wiek");
            age.setBounds(920, 900, 100, 20);
            this.add(age);






        timeLabel.setText("Czas: " + minutes_string + ":" + seconds_string);
        timeLabel.setBounds(560, 800, 350, 100);
        timeLabel.setFont(font1);
        timeLabel.setForeground(Color.white);
        timeLabel.setOpaque(false);


        start.setBounds(580, 900, 100, 50);
        start.setFocusable(false);
        start.addActionListener(this);

        this.add(timeLabel);
        this.add(start);
        this.add(reset);
        this.add(prostokat1);
        this.add(label);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);
        this.setTitle("Spadające meteoryty");
        pasek.setValue(hp);
        czas();
    }
    public void mouseClicked(MouseEvent e) {
    }
    public void mousePressed(MouseEvent e) {
        System.out.println("brawo");
        label.setIcon(null);
        licznikmeto++;
        trafienie=true;
        if(trafienie==true)
        {


        }
        trafienie=true;
        liczniklabel.setText("zestrzelone meteoryty: " + licznikmeto);
        if(licznikmeto==1)//TUTAJ WARUNEK WYGRANEJ
        {
            timer.stop();
            start.setText("START");
            JFrame frame = new JFrame();
            frame.setSize(430,200);
            frame.setLayout(null);
            frame.setTitle("WYNIK GRY");
            frame.setVisible(true);
            frame.setResizable(false);
            JLabel wynik = new JLabel();
            wynik.setBounds(0, 0, 426, 200);
            wynik.setBackground(Color.black);
            wynik.setOpaque(true);
            wynik.setFont(font2);
            wynik.setText("Zestrzelone meteoryty: "+licznikmeto +" Twoj czas to: "+ minutes_string + ":" + seconds_string);
            wynik.setForeground(Color.WHITE);
            frame.add(wynik);
            licznikmeto=0;
            liczniklabel.setText("zestrzelone meteoryty: " + licznikmeto);
            username.setVisible(true);
            age.setVisible(true);
            imiewiek.setText("Podaj swoje imie i wiek : ");
            hp=100;
            pasek.setValue(hp);
            started =false;
            restart();
        }


    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == start)
            start();
        if (started == false) {
            started = true;
            getwiek = Integer.parseInt(age.getText());
            getusername=username.getText();
            System.out.println(getusername);
            System.out.println(getwiek);
            username.setVisible(false);
            age.setVisible(false);
            imiewiek.setText("Imie: "+getusername+" Wiek: "+getwiek);
            start.setText("STOP");
            start();

        } else {
            started = false;
            start.setText("START");
            stop();

        }
        if (e.getSource() == reset) {
        started=false;
        start.setText("START");
        restart();

        }
    }
    void start()
    {

        timer.start();
    }
    void stop()
    {

        timer.stop();
    }
    void restart()
    {
        timer.stop();
        elapsedTime=0;
        seceond=0;
        minutes=0;
        seconds_string = String.format("%02d", seceond);
        minutes_string = String.format("%02d", minutes);
        timeLabel.setText("Czas: " + minutes_string + ":" + seconds_string);
    }

}
