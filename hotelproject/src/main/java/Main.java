import view.login.loginMain.LogInForm;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
        public static void main(String[] args) {

                Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
                mongoLogger.setLevel(Level.SEVERE);
                LogInForm.runForm();

        }
}

