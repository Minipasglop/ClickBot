import javax.swing.*;

/**
 * Created by Junior on 02/10/2016.
 */
public class Clicking extends SwingWorker<Void, Object> {
    @Override
    protected Void doInBackground() throws Exception {
        main.ClickBot();
        return null;
    }

}
