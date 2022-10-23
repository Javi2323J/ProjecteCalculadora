package ProjecteCalculadora;
//Importem tots els paquets necessaris per aquest programa.
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;
//Inicialitzem totes les variables que farem servir i definim principalment els arrays dels elements de JFrame, juntament amb els Strings que donaran text als elements previament mencionats, i també arrays amb les mesures dels botons i camps de text.
public class Calculadora extends JFrame {
    private Float num1,num2;
    private int rnum1, rnum2, rnum3, rnum4, rnum5, rnum6, rnum7;
    private char op;
    private boolean booloscuro, tried;
    private JPanel panel;
    private final JLabel[] challengelabels = new JLabel[7];
    private final int[] posxlabels = {100, 100, 100, 100, 120, 3, 110};
    private final int[] posylabels = {187, 237, 287, 337, 120, 450, 450};
    private final int[] widthlabels = {100, 100, 100, 100, 300, 450, 300};
    private JTextField campcalc;
    private final JTextField[] camps = new JTextField[4];
    private final int posxcamps = 200;
    private final int[] posycamps = {187,237,287,337};
    private final JMenuItem[] menuitems = new JMenuItem[8];
    private final String[] menuitemsname = {"Estandar", "Challenge", "--------", "Modo claro", "Modo oscuro", "Modo dorado", "--------", "GitHub"};
    private JButton checkrf;
    private final JButton[] buttons = new JButton[19];
    private final String[] buttonsname = {".","AC","<-","+","7","8","9","-","4","5","6","*","1","2","3","/","00","0","="};
    private final int[] posx = {20,128,236,344,20,128,236,344,20,128,236,344,20,128,236,344,20,128,246};
    private final int[] posy = {105,105,105,105,210,210,210,210,315,315,315,315,420,420,420,420,525,525,525};
    private final ActionListener[] actions = new ActionListener[19];
//Definim la mesura de la finestra i algunes característiques d'aquesta, com la icona del programa i el títol, i truquem a la funció iniciarComponents.
    public Calculadora(){
        setSize(465,695);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Calculadora");
        this.setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("/Img/calc.png"))).getImage());
        iniciarComponents();
    }
//Funció per cridar i executar totes les funcions del programa
    public void iniciarComponents(){
        colocarPanel();
        colocarQText();
        colocarBotons();
        colocarListeners();
        colocarMenu();
    }
//Definim les característiques del panel.
    public void colocarPanel(){
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.decode("#f0f0f0"));
        this.getContentPane().add(panel);
    }
//Definim el quadre de text principal de la calculadora, i ens assegurem de que no es podrà escriure més d'un punt i cap lletra, afegim colors i background.
    public void colocarQText(){
        campcalc = new JTextField();
        campcalc.setBounds(38,5,375,87);
        campcalc.setHorizontalAlignment(SwingConstants.RIGHT);
        campcalc.setFont(new Font("Arial",Font.PLAIN, 18));
        campcalc.setBackground(Color.decode("#e6e6e6"));
        campcalc.setForeground(Color.decode("#111111"));
        campcalc.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                final int keyCode = ke.getKeyCode();
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
                    campcalc.setEditable(true);
                }
                else if(keyCode == KeyEvent.VK_BACK_SPACE){
                    campcalc.setText(campcalc.getText().substring(0, campcalc.getText().length()));
                }
                else campcalc.setEditable(ke.getKeyChar() == '.');
                campcalc.setBackground(Color.WHITE);
            }
        });
        panel.add(campcalc);
    }
//Definim els botons donant-lis contingut amb l'array de buttonsname i afegim colors i background.
    public void colocarBotons(){
        final int amplada = 87;
        final int ampladaigual = 174;
        final int llargada = 87;
        for(int i = 0; i < 19; i++){
            buttons[i] = new JButton(buttonsname[i]);
            buttons[i].setFont(new Font("Arial",Font.PLAIN,20));
            buttons[i].setForeground(Color.decode("#111111"));
            if(i == 18){
                buttons[i].setBounds(posx[i],posy[i],ampladaigual,llargada);
                buttons[i].setBackground(Color.decode("#8abae0"));
                buttons[i].setForeground(Color.decode("#111111"));
            }
            else {
                buttons[i].setBounds(posx[i], posy[i], amplada, llargada);
                buttons[i].setBackground(Color.decode("#fafafa"));
            }
            panel.add(buttons[i]);
        }
    }
//A cada botó li posem un ActionListener, ara cada botó fara la seva funció.
    public void colocarListeners(){
        for(int i = 0; i < 19; i++) {
            switch(i) {
                case 0: actions[0] = e -> {
                    if(!campcalc.getText().contains(".")){
                        campcalc.setText(campcalc.getText() + ".");
                    }
                };
                case 1: actions[1] = e -> {
                    campcalc.setText("");
                    campcalc.setEditable(true);
                };
                case 2: actions[2] = e -> campcalc.setText(campcalc.getText().substring(0, campcalc.getText().length() - 1));
                case 3: actions[3] = e -> {
                    num1 = Float.parseFloat(campcalc.getText());
                    op = '+';
                    campcalc.setText("");
                };
                case 4: actions[4] = e -> campcalc.setText(campcalc.getText() + "7");
                case 5: actions[5] = e -> campcalc.setText(campcalc.getText() + "8");
                case 6: actions[6] = e -> campcalc.setText(campcalc.getText() + "9");
                case 7: actions[7] = e -> {
                    num1 = Float.parseFloat(campcalc.getText());
                    op = '-';
                    campcalc.setText("");
                };
                case 8: actions[8] = e -> campcalc.setText(campcalc.getText() + "4");
                case 9: actions[9] = e -> campcalc.setText(campcalc.getText() + "5");
                case 10: actions[10] = e -> campcalc.setText(campcalc.getText() + "6");
                case 11: actions[11] = e -> {
                    num1 = Float.parseFloat(campcalc.getText());
                    op = '*';
                    campcalc.setText("");
                };
                case 12: actions[12] = e -> campcalc.setText(campcalc.getText() + "1");
                case 13: actions[13] = e -> campcalc.setText(campcalc.getText() + "2");
                case 14: actions[14] = e -> campcalc.setText(campcalc.getText() + "3");
                case 15: actions[15] = e -> {
                    num1 = Float.parseFloat(campcalc.getText());
                    op = '/';
                    campcalc.setText("");
                };
                case 16: actions[16] = e -> campcalc.setText(campcalc.getText() + "00");
                case 17: actions[17] = e -> campcalc.setText(campcalc.getText() + "0");
                case 18: actions[18] = e->{
                    num2 = Float.parseFloat(campcalc.getText());

                    if(op == '+'){campcalc.setText(colocarZero(num1 + num2));}
                    else if(op == '-'){campcalc.setText(colocarZero(num1 - num2));}
                    else if(op == '*'){campcalc.setText(colocarZero(num1 * num2));}
                    else if(op == '/') {
                        if (num2 == 0) {
                            campcalc.setText("ERROR");
                            campcalc.setEditable(false);
                            campcalc.setBackground(Color.WHITE);
                        }
                        else{
                            campcalc.setText(colocarZero(num1 / num2));
                        }
                    }
                };
            }buttons[i].addActionListener(actions[i]);
        }
    }
//Mirem si el resultat de la operació que fem a la calculadora és un nombre amb residu (decimal) o no, en cas de que no no sortirà un .0 al nombre (4 en comptes de 4.0)
    public String colocarZero(float res){
        String retorn=Float.toString(res);
        if(res % 1 == 0){
            retorn=retorn.substring(0, retorn.length() - 2);
        }
        return retorn;
    }
//Coloquem el menú sencer d'opcions
    public void colocarMenu(){
        //Definim el JMenuBar, definim els MenuItems i el botó de comprovació.
        JMenuBar menubar = new JMenuBar();
        setJMenuBar(menubar);
        JMenu menu1 = new JMenu("Acerca de");
        menubar.add(menu1);

        for(int i = 0; i < menuitems.length; i++){
            menuitems[i] = new JMenuItem(menuitemsname[i]);
            if(i == 2 || i == 6){
                menuitems[i].setEnabled(false);
            }
            else if(i == 5){
                menuitems[i].setVisible(false);
            }
            menu1.add(menuitems[i]);
        }

        checkrf = new JButton("Comprobar");
        checkrf.setBounds(160, 400, 125, 25);
        checkrf.setVisible(false);
        panel.add(checkrf);

        ActionListener standard = new ActionListener() {
            //Re-afegim tots els botons i habilitem el quadre de text en cas de venir de l'opció challenge.
            @Override
            public void actionPerformed(ActionEvent e) {
                campcalc.setEnabled(true);
                for(int i = 0; i< 19; i++){
                    buttons[i].setVisible(true);
                }
                if(challengelabels[0] != null) {
                    checkrf.setVisible(false);
                    for (int i = 0; i < challengelabels.length; i++) {
                        challengelabels[i].setVisible(false);
                    }
                    campcalc.setText("");
                    for (int i = 0; i < camps.length; i++) {
                        camps[i].setVisible(false);
                    }
                }
            }
        };menuitems[0].addActionListener(standard);

        ActionListener claro = new ActionListener() {
            //Posem tota la interfície en modo claro, és a dir, colors suaus entre gris i blanc.
            @Override
            public void actionPerformed(ActionEvent e) {
                booloscuro = false;
                panel.setBackground(Color.decode("#f0f0f0"));
                campcalc.setBackground(Color.decode("#e6e6e6"));
                campcalc.setForeground(Color.decode("#111111"));
                for(int i = 0; i < 19; i++){
                    if (i == 18){
                        buttons[i].setBackground(Color.decode("#8abae0"));
                    }
                    else{
                        buttons[i].setBackground(Color.decode("#fafafa"));
                    }
                    buttons[i].setForeground(Color.decode("#111111"));
                }
                if(challengelabels[0] != null) {
                    checkrf.setForeground(Color.decode("#111111"));
                    checkrf.setBackground(Color.decode("#8abae0"));
                    for(int i = 0; i < challengelabels.length; i++){
                        challengelabels[i].setForeground(Color.decode("#111111"));
                    }
                    if (!tried) {
                        for (int i = 0; i < camps.length; i++) {
                            camps[i].setBackground(Color.decode("#e6e6e6"));
                            camps[i].setForeground(Color.decode("#111111"));
                        }
                    }
                }
            }
        };menuitems[3].addActionListener(claro);

        ActionListener oscuro = new ActionListener() {
            //Posem tota la interfície en modo oscuro, és a dir, colors suaus entre gris i negre.
            @Override
            public void actionPerformed(ActionEvent e) {
                booloscuro = true;
                panel.setBackground(Color.decode("#333333"));
                campcalc.setBackground(Color.decode("#565656"));
                campcalc.setForeground(Color.decode("#ffffff"));
                for(int i = 0; i < 19; i++){
                    if(i == 18){
                        buttons[i].setBackground(Color.decode("#325978"));
                    }
                    else {
                        buttons[i].setBackground(Color.decode("#111111"));
                    }
                    buttons[i].setForeground(Color.decode("#ffffff"));
                }
                if(challengelabels[0] != null) {
                    checkrf.setForeground(Color.decode("#ffffff"));
                    checkrf.setBackground(Color.decode("#325978"));
                    for (int i = 0; i < challengelabels.length; i++) {
                        challengelabels[i].setForeground(Color.decode("#ffffff"));
                    }
                    if (!tried) {
                        for (int i = 0; i < camps.length; i++) {
                            camps[i].setBackground(Color.decode("#565656"));
                            camps[i].setForeground(Color.decode("#ffffff"));
                        }
                    }
                }
            }
        };menuitems[4].addActionListener(oscuro);

        ActionListener challenge = new ActionListener() {
            //Amaguem tota la interfície bàsica de la calculadora menys el quadre de text principal, que només l'amaguem i ensenyem la nova interfície del challenge. Cal destacar que només es pot entrar una vegada a aquesta opció.
            @Override
            public void actionPerformed(ActionEvent e) {
                final int labelheight = 25;
                final int ampladacamps = 100;
                final int alturacamps = 25;
                colocarRandom();
                final String[] challengelabelstext = {rnum1 + " + " + rnum2 + " = ?", rnum3 + " - " + rnum4 + " = ?", rnum5 + " * " + rnum6 + " = ?", rnum7 + " / " + 2 + " = ?", "Solo tienes una oportunidad!", "Enhorabuena! Has desbloqueado la versión dorada", "Vaya, no lo has conseguido..."};

                campcalc.setEnabled(false);
                campcalc.setText("");
                menuitems[1].setEnabled(false);
                checkrf.setVisible(true);
                for(int i = 0; i < 19; i++){
                    buttons[i].setVisible(false);
                }

                for(int i = 0; i < challengelabels.length; i++){
                    challengelabels[i] = new JLabel(challengelabelstext[i]);
                    challengelabels[i].setFont(new Font("Arial",Font.BOLD, 18));
                    challengelabels[i].setBounds(posxlabels[i], posylabels[i], widthlabels[i], labelheight);
                    challengelabels[i].setVisible(true);
                    panel.add(challengelabels[i]);
                    if(i == 5 || i == 6){
                        challengelabels[i].setVisible(false);
                    }
                }

                for(int i = 0; i < camps.length; i++){
                    camps[i] = new JTextField();
                    camps[i].setFont(new Font("Arial", Font.PLAIN, 18));
                    camps[i].setBounds(posxcamps, posycamps[i],ampladacamps,alturacamps);
                    camps[i].setVisible(true);
                    int finalI = i;
                    camps[i].addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyPressed(KeyEvent ke) {
                            final int keyCode = ke.getKeyCode();
                            if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
                                camps[finalI].setEditable(true);
                            }
                            else if(keyCode == KeyEvent.VK_BACK_SPACE){
                                camps[finalI].setText(camps[finalI].getText().substring(0, camps[finalI].getText().length()));
                            }
                            else camps[finalI].setEditable(ke.getKeyChar() == '.');
                        }
                    });panel.add(camps[i]);
                }

                if(booloscuro){
                    checkrf.setForeground(Color.decode("#ffffff"));
                    checkrf.setBackground(Color.decode("#325978"));
                    for(int i = 0; i < challengelabels.length; i++){
                        challengelabels[i].setForeground(Color.decode("#ffffff"));
                    }
                    for(int i = 0; i < camps.length; i++){
                        camps[i].setBackground(Color.decode("#565656"));
                        camps[i].setForeground(Color.decode("#ffffff"));
                    }
                }
                if(!booloscuro){
                    checkrf.setForeground(Color.decode("#111111"));
                    checkrf.setBackground(Color.decode("#8abae0"));
                    for(int i = 0; i < challengelabels.length; i++){
                        challengelabels[i].setForeground(Color.decode("#111111"));
                    }
                    for(int i = 0; i < camps.length; i++){
                        camps[i].setBackground(Color.decode("#e6e6e6"));
                        camps[i].setForeground(Color.decode("#111111"));
                    }
                }
            }
        };menuitems[1].addActionListener(challenge);

        ActionListener check = new ActionListener() {
            //Comprovem els resultats del challenge, en cas d'estar tot correcte habilitem el modo dorado, una nova "skin" per la calculadora, en cas contrari, no pasa res.
            @Override
            public void actionPerformed(ActionEvent e) {
                final double[] chop = {rnum1 + rnum2, rnum3 - rnum4, rnum5 * rnum6, rnum7 / 2.0};
                int counter = 0;
                checkrf.setEnabled(false);
                for(int i = 0; i < camps.length; i++){
                    if(camps[i].getText().equals("")){
                        camps[i].setBackground(Color.decode("#a2231d"));
                    }
                    else if(Float.parseFloat(camps[i].getText()) == chop[i]){
                        camps[i].setBackground(Color.decode("#008000"));
                        counter++;
                    }
                    else{
                        camps[i].setBackground(Color.decode("#a2231d"));
                    }
                    camps[i].setEnabled(false);
                }
                if(counter == 4){
                    menuitems[5].setVisible(true);
                    challengelabels[5].setVisible(true);
                }
                else{
                    challengelabels[6].setVisible(true);
                }
                tried = true;
            }
        };checkrf.addActionListener(check);

        ActionListener gold = new ActionListener() {
            //Si hem fet bé el challenge, podrem accedir al modo daurat de la calculadora, amb colors daurats en comptes de clars o obscurs.
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.setBackground(Color.decode("#ffbf00"));
                campcalc.setBackground(Color.decode("#ffcf40"));
                checkrf.setBackground(Color.decode("#265999"));
                for(int i = 0; i < 19; i++){
                    if(i == 18){
                        buttons[i].setBackground(Color.decode("#4488BF"));
                    }
                    else {
                        buttons[i].setBackground(Color.decode("#bf9b30"));
                    }
                    buttons[i].setForeground(Color.decode("#111111"));
                }
            }
        };menuitems[5].addActionListener(gold);

        ActionListener git = new ActionListener() {
            //Redirigim a l'usuari al github on ha estat penjat el projecte d'aquesta calculadora.
            @Override
            public void actionPerformed(ActionEvent e) {
                String myurl = "https://github.com/Javi2323J/ProjecteCalculadora";
                try {
                    Desktop.getDesktop().browse(java.net.URI.create(myurl));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        };menuitems[7].addActionListener(git);
    }
//Assegurem que les operacions no sempre seran iguals, dins d'un rang de nombres per no fer el challenge massa complicat.
    public void colocarRandom(){
        Random rand = new Random();
        int r = 0;
        r = rand.nextInt(1,10);
        rnum1 = r;
        r = rand.nextInt(1,10);
        rnum2 = r;
        r = rand.nextInt(10,20);
        rnum3 = r;
        r = rand.nextInt(1,10);
        rnum4 = r;
        r = rand.nextInt(1,5);
        rnum5 = r;
        r = rand.nextInt(10, 20);
        rnum6 = r;
        r = rand.nextInt(10,50);
        rnum7 = r;
    }
}