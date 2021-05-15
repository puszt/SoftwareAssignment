package bishops;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.tinylog.Logger;

import java.io.File;

/**
 * Class to control the database.
 */
public class DatabaseController {
    /**
     * Returns the path to the user's home dir.
     * @return the path to the user's home dir.
     */
    public static String getUsersHomeDir() {
        String users_home = System.getProperty("user.home");
        return users_home.replace("\\", "/");
    }

    /**
     * Checks if database folder already exists, if not, then creates it.
     */
    public static void createDatabaseDir(){
        String myDirectory = ".BishopsDataBase";
        String path = getUsersHomeDir() + File.separator + myDirectory ;

        if (new File(path).mkdir()) {
            Logger.debug("Directory created");
        }else{
            Logger.debug("Directory already exists");
        }
    }

    /**
     * Returns the path to the database.
     * @return the path to the database.
     */
    public static String getDatabaseDirPath(){
        String myDirectory = ".BishopsDataBase";
        String path = getUsersHomeDir() + File.separator + myDirectory ;
        return path;
    }

    /**
     * Returns the JDBI uri.
     * @return the JDBI uri.
     */
    public static String getJdbiDatabasePath(){
        String filePath = "jdbc:h2:file:" + getDatabaseDirPath() + File.separator + "Highscores.mv.db";
        String jdbiPath = filePath.substring(0,filePath.length()-6);
        return jdbiPath;
    }

    /**
     * Creates the Highscores.mv.db file and Highscores table if it doesn't exist.
     */
    public static void createDatabase(){
        if (!checkForDatabase()){
            Jdbi jdbi = Jdbi.create(getJdbiDatabasePath());
            try(Handle handle = jdbi.open()){
                handle.execute("""
                                    CREATE TABLE HIGHSCORES(ID NUMBER PRIMARY KEY, NAME VARCHAR2(50), SCORE NUMBER)
                                    """);
            }
            Logger.debug("Database created");
        }else {
            Logger.debug("Database already exists");
        }
    }

    /**
     * Checks if Highscores.mv.db already exists.
     * @return if Highscores.mv.db already exists
     */
    public static boolean checkForDatabase(){
        String filePath = getDatabaseDirPath();
        String file = "Highscores.mv.db";
        return (new File(filePath,file).exists());
    }
}
