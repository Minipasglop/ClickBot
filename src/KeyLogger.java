import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

//Classe appellée KeyLogger se basant sur la librairie jnativehook
//Ce n'est pas un KeyLogger en réalité, ca permets juste de lancer / couper le Bot quand on appuie sur F4
//Cependant, on peut pousser le concept plus loin.

public class KeyLogger implements NativeKeyListener, WindowListener
{
    public void nativeKeyPressed(NativeKeyEvent e) {
        if(e.getKeyCode() == NativeKeyEvent.VC_F4 && Bot.botOn) { //Si la touche pressée correspond à F4 et que le bot tourne, on l'arrete
            Bot.botOn = false;
            System.out.println("Stop");
        }
        else if(e.getKeyCode() == NativeKeyEvent.VC_F4 && ! Bot.botOn) { //Si la touche presssée correspond à F4 et que le bot ne tourne pas, on le lance.
            Bot.botOn = true;
            new Clicking().execute();
        }
    }//nativeKeyPressed()

    public void nativeKeyReleased(NativeKeyEvent e) {
    }

    public void nativeKeyTyped(NativeKeyEvent e) {
    }


    //Méthode permettant d'ajouter le "KeyLogger" à la fenetre et qu'il puisse donc agir.
    @Override
    public void windowOpened(WindowEvent e) {
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ev) {
            ev.printStackTrace();
        }
        GlobalScreen.addNativeKeyListener(this);
    }//windowOpened()

    @Override
    public void windowClosing(WindowEvent e) {
    }

    //Méthode permettant de supprimer le "KeyLogger" de la fenetre a sa fermeture.
    public void windowClosed(WindowEvent e) {
        // Clean up the native hook.
        try {
            GlobalScreen.unregisterNativeHook();
        }
        catch (NativeHookException ex) {
            ex.printStackTrace();
        }
        System.runFinalization();
        System.exit(0);
    }//windowClosing()

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }
}
