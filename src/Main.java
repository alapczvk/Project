import org.apache.log4j.Logger;
import projekt.GUI;

public class Main {
    //private static Logger logger= Logger.getLogger(Main.class); //w kazdej klasie tak robie zeby zbierac logi
    public static void main(String[] args) {
            javax.swing.SwingUtilities.invokeLater(GUI::createAndShowGUI);
        }
}


