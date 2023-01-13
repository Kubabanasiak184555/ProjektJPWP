import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import javax.swing.Timer;
/** @author Jakub Banasiak T1 184555 */
public class GUI extends JFrame implements MouseListener, ActionListener {
    /** x i y to losowe współrzędne dla meteorytu */
    int x;
    int y;
    /** początkowe zycie gracza */
    int hp = 100;
    /** licznik trafień */
    int licznikmeto;
    /** czas */
    int elapsedTime = 0;
    /** czas */
    int seceond = 0;
    /** czas */
    int minutes = 0;
    int getwiek;
    /** czy START został kliknięty */
    boolean started = false;
    /** czy obiekt został trafiony */
    boolean trafienie = false;

    Font font1;
    Font font2;

    /** pasek z życiem gracza */
    JProgressBar pasek;

    MyPanel panel;

    JLabel timeLabel = new JLabel();
    /** meteoryt */
    JLabel label;
    JLabel liczniklabel;
    JLabel menu;
    JLabel menu1;
    JLabel exit;
    JLabel wynik;
    JLabel wbutton;
    JLabel zbutton;
    JLabel wtekst;
    JLabel wtekst1;
    JLabel imiewiek;

    ImageIcon meto;
    ImageIcon meto2;
    ImageIcon wybuch;

    /** imię gracza */
    JTextField username;
    /** ile lat ma gracza */
    JTextField age;


    String getusername;
    String seconds_string = String.format("%02d", seceond);
    String minutes_string = String.format("%02d", minutes);
    String filename = "out.txt";


    /** guzik startu */
    JButton start = new JButton("Start");
    /** guzik pauzy */
    JButton reset = new JButton("Reset");

    /** losowanie x*/
    Random xlos = new Random();
    /** losowanie y */
    Random ylos = new Random();

    /** Tworzenie czcionki */
    public void CreateFont()
    {
        font1 = new Font("Comic Sans MS", Font.PLAIN, 27);
        font2 = new Font("Comic Sans MS", Font.PLAIN, 18);
    }

    /** Losowanie współrzędnych x i y dla meteorytu */
    public void Los()
    {
        x = xlos.nextInt(1100);
        y = ylos.nextInt(450);
        label.setIcon(meto2);
        label.setBounds(100, 100, 250, 200);
        label.setLocation(x, y);
    }

    /** Funckja w, której znajduję się pętla która co 2 sekundy losuje nową pozycję meteorytu na planszy.*/
    public void czas()
    {
        while (true) {
            System.out.println(started);
            if (started) {
                Los();
                try {
                    Thread.sleep(2000);
                    if (!trafienie) {
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
                        liczniklabel.setText("zestrzelone meteoryty: " + licznikmeto);
                        username.setVisible(true);
                        age.setVisible(true);
                        imiewiek.setText("Podaj swoje imie i wiek : ");
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


    GUI()
    {
        CreateFont();
        licznikmeto = 0;
        panel = new MyPanel();
        label = new JLabel();
        label.setOpaque(false);
        label.addMouseListener(this);


        meto2 = new ImageIcon("meto.png");
        wybuch = new ImageIcon("wybuch.png");


        JPanel licznik = new JPanel();
        licznik.setBounds(30, 830, 350, 100);
        licznik.setOpaque(false);
        this.add(licznik);

        liczniklabel = new JLabel("zestrzelone meteoryty: " + licznikmeto);
        liczniklabel.setForeground(Color.white);
        liczniklabel.setFont(font1);
        licznik.add(liczniklabel);

        pasek = new JProgressBar(0, 100);
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

        exit = new JLabel("WYJŚCIE");
        exit.setBounds(560, 890, 350, 50);
        exit.setFont(font1);
        exit.setForeground(Color.white);
        exit.setVisible(false);
        exit.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                System.exit(0);
            }
        });
        this.add(exit);

        menu = new JLabel("MENU");
        menu.setBounds(1150, 890, 100, 50);
        menu.setFont(font1);
        menu.setForeground(Color.WHITE);
        menu.setOpaque(false);
        menu.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e) {
                pasek.setVisible(false);
                timeLabel.setVisible(false);
                liczniklabel.setVisible(false);
                start.setVisible(false);
                imiewiek.setVisible(false);
                username.setVisible(false);
                age.setVisible(false);
                menu1.setVisible(true);
                menu.setVisible(false);
                exit.setVisible(true);
            }
        });

        menu1 = new JLabel("MENU");
        menu1.setBounds(1150, 890, 100, 50);
        menu1.setFont(font1);
        menu1.setForeground(Color.WHITE);
        menu1.setOpaque(false);
        menu1.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e) {
                menu.setVisible(true);
                menu1.setVisible(false);
                pasek.setVisible(true);
                timeLabel.setVisible(true);
                liczniklabel.setVisible(true);
                start.setVisible(true);
                imiewiek.setVisible(true);
                username.setVisible(true);
                age.setVisible(true);
                exit.setVisible(false);
            }
        });

        menu1.setVisible(false);
        this.add(menu);
        this.add(menu1);
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
    /** Możliwość klikania meteorytu co skutuje dodawaniem puntków oraz wygrania gry */
    public void mousePressed(MouseEvent e) {
        label.setIcon(wybuch);
        licznikmeto++;
        trafienie = true;
        trafienie = true;
        liczniklabel.setText("zestrzelone meteoryty: " + licznikmeto);
        if (licznikmeto == 20) {
            label.setIcon(null);
            timer.stop();
            start.setText("START");
            JFrame frame = new JFrame();
            frame.setSize(1280, 1000);
            frame.setTitle("Wynik gry");
            frame.setVisible(true);

            wynik = new JLabel();
            wynik.setFont(font1);
            wynik.setBounds(300, 100, 680, 600);
            wynik.setBackground(Color.black);
            wynik.setOpaque(true);
            wynik.setVisible(true);

            wtekst = new JLabel();
            wtekst.setText("Użytkownik zestrzelił: " + licznikmeto + " meteorytów " + "w czasie: " + minutes_string + ":" + seconds_string);
            wtekst.setFont(font1);
            wtekst.setForeground(Color.WHITE);
            wtekst.setBounds(310, 300, 700, 50);
            wtekst.setOpaque(false);
            wtekst.setVisible(true);

            wtekst1 = new JLabel();
            wtekst1.setText("Twoje imię to: " + getusername + " i masz: " + getwiek + " lat");
            wtekst1.setFont(font1);
            wtekst1.setForeground(Color.WHITE);
            wtekst1.setBounds(450, 200, 700, 50);
            wtekst1.setOpaque(false);
            wtekst1.setVisible(true);

            wbutton = new JLabel("GRAJ DALEJ");
            wbutton.setFont(font1);
            wbutton.setForeground(Color.WHITE);
            wbutton.setBounds(550, 600, 200, 50);
            wbutton.setOpaque(false);
            wbutton.setVisible(true);
            wbutton.addMouseListener(new MouseAdapter()
            {
                @Override
                public void mouseClicked(MouseEvent e) {
                    frame.setVisible(false);
                }
            });

            zbutton = new JLabel("Zapisz Wynik");
            zbutton.setFont(font1);
            zbutton.setForeground(Color.WHITE);
            zbutton.setBounds(550, 500, 200, 50);
            zbutton.setOpaque(false);
            zbutton.setVisible(true);
            zbutton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    zbutton.setVisible(false);
                    try {
                        PrintWriter outputStream = new PrintWriter(filename);
                        outputStream.println("Imie: " + getusername + " Wiek: " + getwiek + " Liczba zestrzelonych meteorytów: " + licznikmeto + " Czas: " + minutes_string + ":" + seconds_string);
                        outputStream.close();

                    } catch (FileNotFoundException t) {
                        t.printStackTrace();
                    }

                }
            });

            frame.add(zbutton);
            frame.add(wbutton);
            frame.add(wtekst1);
            frame.add(wtekst);
            frame.add(wynik);
            licznikmeto = 0;
            liczniklabel.setText("zestrzelone meteoryty: " + licznikmeto);
            username.setVisible(true);
            age.setVisible(true);
            imiewiek.setText("Podaj swoje imie i wiek : ");
            hp = 100;
            pasek.setValue(hp);
            started = false;
            restart();
        }


    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }
    /** Funkcionalność zegara i przycisku START*/
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == start)
            start();
        if (!started) {
            started = true;
            getwiek = Integer.parseInt(age.getText());
            getusername = username.getText();
            System.out.println(getusername);
            System.out.println(getwiek);
            username.setVisible(false);
            age.setVisible(false);
            imiewiek.setText("Imie: " + getusername + " Wiek: " + getwiek);
            start.setText("STOP");
            start();

        } else {
            started = false;
            start.setText("START");
            stop();

        }
        if (e.getSource() == reset) {
            started = false;
            start.setText("START");
            restart();

        }
    }

    void start() {

        timer.start();
    }

    void stop() {

        timer.stop();
    }

    void restart() {
        timer.stop();
        elapsedTime = 0;
        seceond = 0;
        minutes = 0;
        seconds_string = String.format("%02d", seceond);
        minutes_string = String.format("%02d", minutes);
        timeLabel.setText("Czas: " + minutes_string + ":" + seconds_string);
    }

}
