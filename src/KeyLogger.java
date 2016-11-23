import org.jnativehook.GlobalScreen;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Created by Junior on 03/10/2016.
 */

public class KeyLogger implements NativeKeyListener, WindowListener
{
    public void nativeKeyPressed(NativeKeyEvent e) {
        if(e.getKeyCode() == NativeKeyEvent.VC_F4 && main.botOn) {
            main.botOn = false;
            System.out.println("Stop");
        }
        else if(e.getKeyCode() == NativeKeyEvent.VC_F4 && ! main.botOn) {
            main.botOn = true;
            new Clicking().execute();
        }
    }

    public void nativeKeyReleased(NativeKeyEvent e) {
    }

    public void nativeKeyTyped(NativeKeyEvent e) {
    }

    @Override
    public void windowOpened(WindowEvent e) {
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ev) {
            ev.printStackTrace();
        }
        GlobalScreen.addNativeKeyListener(this);
    }

    @Override
    public void windowClosing(WindowEvent e) {
    }

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
    }

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
