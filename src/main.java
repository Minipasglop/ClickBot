import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;

/**
 * Created by Junior on 01/10/2016.
 */
public class main extends JFrame{

    private JLabel label = new JLabel("Nombre de clics par seconde : 25");
    protected static int nbClics = 25;
    public static JLabel Texte = new JLabel("F4 pour Lancer / Couper");
    public static boolean botOn = false;
    protected static NumberFormat formatDureeBot;
    protected static JFormattedTextField tempsExec = new JFormattedTextField(formatDureeBot);
    private JPanel panGlobal = new JPanel();

    public main() {
        super("ClickBot By Minipasglop");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JSlider slide = new JSlider();
        slide.setMaximum(50);
        slide.setMinimum(5);
        slide.setValue(25);
        slide.setPaintTicks(true);
        slide.setPaintLabels(true);
        slide.setMinorTickSpacing(5);
        slide.setMajorTickSpacing(10);
        slide.addChangeListener(new ChangeListener(){
            public void stateChanged(ChangeEvent event){
                label.setText("Nombre de clics par seconde : " + ((JSlider)event.getSource()).getValue());
                nbClics = ((JSlider)event.getSource()).getValue();
            }
        });
        tempsExec.setValue(new Double(60));
        tempsExec.setColumns(15);
        panGlobal.setLayout(new GridLayout(0,1));
        JPanel panCurseur = new JPanel();
        panCurseur.setLayout(new BorderLayout());
        panCurseur.add(slide,BorderLayout.CENTER);
        panCurseur.add(label,BorderLayout.SOUTH);
        panGlobal.add(panCurseur);
        panGlobal.add(Texte);
        this.add(panGlobal);
        this.pack();
        this.setResizable(false);
        this.setVisible(true);
    }

    public static void ClickBot() {
        try {
            Robot robot = new Robot();
            while (botOn) {
                try {
                    Thread.sleep(1000 / nbClics);
                    robot.mousePress(InputEvent.BUTTON1_MASK);
                    robot.mouseRelease(InputEvent.BUTTON1_MASK);
                    }
                catch (InterruptedException ex) {
                }
            }
        }
        catch (AWTException e) {
        }
        botOn = false;
    }


    public static void main(String[] args) {
        main ClickBot = new main();
        ClickBot.addWindowListener(new KeyLogger());
    }
}
