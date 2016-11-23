import javax.swing.*;

//Classe permetant de gérer le thread relatif au clic, en dehors du main.
//On utilise donc un SwingWorker pour éviter que l'IHM freeze quand on lance les clics.
public class Clicking extends SwingWorker<Void, Object> {
    @Override
    protected Void doInBackground() throws Exception {
        Bot.clickBot();
        return null;
    }

}
