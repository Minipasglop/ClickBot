import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;

//Classe globale ( éxécutée ), incluant construction de l'IHM.

public class Bot extends JFrame{

    private final JLabel label = new JLabel("Nombre de clics par seconde : 25");
    protected static int nbClics = 25;
    public static JLabel texte = new JLabel("F4 pour Lancer / Couper");
    public static boolean botOn = false;

    /* On construit l'IHM, en y ajoutant le curseur, puis un listener de type ChangeListener(), qui modifie la donnée membre nbClics.
        On ajoute du texte, puis dans panneaux, puis on condtruit l'IHM qu'on rends visible.
     */

    public Bot() {
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
        JPanel panCurseur = new JPanel();
        panCurseur.setLayout(new BorderLayout());
        panCurseur.add(slide,BorderLayout.CENTER);
        panCurseur.add(label,BorderLayout.SOUTH);
        JPanel panGlobal = new JPanel();
        panGlobal.add(panCurseur);
        panGlobal.add(texte);
        this.add(panGlobal);
        this.pack();
        this.setResizable(false);
        this.setVisible(true);
    }//Bot(), constructeur

    //Le corps même du bot, c'est ce qui va générer les clics quand il sera appelé dans le thread invoqué par la classe Clicking
    public static void clickBot() {
        try {
            Robot robot = new Robot();
            while (botOn) {
                try {
                    Thread.sleep(1000 / nbClics);
                    robot.mousePress(InputEvent.BUTTON1_MASK);
                    robot.mouseRelease(InputEvent.BUTTON1_MASK);
                    }
                catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
        catch (AWTException e) {
            e.printStackTrace();
        }
        botOn = false;
    }//clickBot()

    //Notre main, ce qui est éxécuté.
    public static void main(String[] args) {
        Bot ClickBot = new Bot();
        ClickBot.addWindowListener(new KeyLogger());
    }//main
}
