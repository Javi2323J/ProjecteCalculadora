package ProjecteCalculadora;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Calculadora extends JFrame {
    private Float num1,num2;
    private char op;
    private JPanel panel;
    private JTextField camp1;
    private final JButton[] buttons = new JButton[19];
    private final String[] buttonsname = {".","AC","<-","+","7","8","9","-","4","5","6","*","1","2","3","/","00","0","="};
    private final int[] posx = {20,128,236,344,20,128,236,344,20,128,236,344,20,128,236,344,20,128,246};
    private final int[] posy = {105,105,105,105,210,210,210,210,315,315,315,315,420,420,420,420,525,525,525};
    private final ActionListener[] actions = new ActionListener[19];

    public Calculadora(){
        setSize(465,675);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(311,452));
        setTitle("Calculadora");
        iniciarComponents();
    }
    public void iniciarComponents(){
        colocarPanel();
        colocarQText();
        colocarBotons();
        colocarListeners();
    }

    public void colocarPanel(){
        panel = new JPanel();
        panel.setLayout(null);
        this.getContentPane().add(panel);
    }

    public void colocarQText(){
        camp1 = new JTextField();
        camp1.setBounds(45,5,375,67);
        camp1.setHorizontalAlignment(SwingConstants.RIGHT);
        camp1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
                    camp1.setEditable(true);
                } else camp1.setEditable(ke.getKeyChar() == '.');
                camp1.setBackground(Color.WHITE);
            }
        });
        panel.add(camp1);
    }
    public void colocarBotons(){
        final int amplada = 87;
        final int ampladaigual = 174;
        final int llargada = 87;
        for(int i = 0; i < 19; i++){
            if(i == 18){
                buttons[i] = new JButton(buttonsname[i]);
                buttons[i].setBounds(posx[i],posy[i],ampladaigual,llargada);
                buttons[i].setFont(new Font("Arial",Font.PLAIN,20));
                panel.add(buttons[i]);
            }
            else {
                buttons[i] = new JButton(buttonsname[i]);
                buttons[i].setBounds(posx[i], posy[i], amplada, llargada);
                buttons[i].setFont(new Font("Arial", Font.PLAIN, 20));
                panel.add(buttons[i]);
            }
        }
    }

    public void colocarListeners(){
        for(int i = 0; i < 19; i++) {
            switch(i) {
                case 0: actions[0] = e -> {
                    if(!camp1.getText().contains(".")){
                        camp1.setText(camp1.getText() + ".");
                    }
                };
                case 1: actions[1] = e -> {
                    camp1.setText("");
                    camp1.setEditable(true);
                };
                case 2: actions[2] = e -> camp1.setText(camp1.getText().substring(0, camp1.getText().length() - 1));
                case 3: actions[3] = e -> {
                    num1 = Float.parseFloat(camp1.getText());
                    op = '+';
                    camp1.setText("");
                };
                case 4: actions[4] = e -> camp1.setText(camp1.getText() + "7");
                case 5: actions[5] = e -> camp1.setText(camp1.getText() + "8");
                case 6: actions[6] = e -> camp1.setText(camp1.getText() + "9");
                case 7: actions[7] = e -> {
                    num1 = Float.parseFloat(camp1.getText());
                    op = '-';
                    camp1.setText("");
                };
                case 8: actions[8] = e -> camp1.setText(camp1.getText() + "4");
                case 9: actions[9] = e -> camp1.setText(camp1.getText() + "5");
                case 10: actions[10] = e -> camp1.setText(camp1.getText() + "6");
                case 11: actions[11] = e -> {
                    num1 = Float.parseFloat(camp1.getText());
                    op = '*';
                    camp1.setText("");
                };
                case 12: actions[12] = e -> camp1.setText(camp1.getText() + "1");
                case 13: actions[13] = e -> camp1.setText(camp1.getText() + "2");
                case 14: actions[14] = e -> camp1.setText(camp1.getText() + "3");
                case 15: actions[15] = e -> {
                    num1 = Float.parseFloat(camp1.getText());
                    op = '/';
                    camp1.setText("");
                };
                case 16: actions[16] = e -> camp1.setText(camp1.getText() + "00");
                case 17: actions[17] = e -> camp1.setText(camp1.getText() + "0");
                case 18: actions[18] = e->{
                    num2 = Float.parseFloat(camp1.getText());

                    if(op == '+'){camp1.setText(colocarZero(num1 + num2));}
                    else if(op == '-'){camp1.setText(colocarZero(num1 - num2));}
                    else if(op == '*'){camp1.setText(colocarZero(num1 * num2));}
                    else if(op == '/') {
                        if (num2 == 0) {
                            camp1.setText("ERROR");
                            camp1.setEditable(false);
                            camp1.setBackground(Color.WHITE);
                        }
                        else{
                            camp1.setText(colocarZero(num1 / num2));
                        }
                    }
                };
            }buttons[i].addActionListener(actions[i]);
        }
    }
    public String colocarZero(float res){
        String retorn=Float.toString(res);
        if(res % 1 == 0){
            retorn=retorn.substring(0, retorn.length() - 2);
        }

        return retorn;
    }
}