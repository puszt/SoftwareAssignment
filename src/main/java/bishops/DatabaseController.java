package bishops;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.tinylog.Logger;

import java.io.File;

public class DatabaseController {
    public static String getUsersHomeDir() {
        String users_home = System.getProperty("user.home");
        return users_home.replace("\\", "/"); // to support all platforms.
    }
    public static void createDatabaseDir(){
        String myDirectory = ".BishopsDataBase"; // user Folder Name
        String path = getUsersHomeDir() + File.separator + myDirectory ;

        if (new File(path).mkdir()) {
            Logger.debug("Directory created");
        }else{
            Logger.debug("Directory already exists");
        }
    }

    public static String getDatabaseDirPath(){
        String myDirectory = ".BishopsDataBase"; // user Folder Name
        String path = getUsersHomeDir() + File.separator + myDirectory ;
        return path;
    }

    public static String getJdbiDatabasePath(){
        String filePath = "jdbc:h2:file:" + getDatabaseDirPath() + File.separator + "Highscores.mv.db";
        String jdbiPath = filePath.substring(0,filePath.length()-6);
        return jdbiPath;
    }

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

    public static boolean checkForDatabase(){
        String filePath = getDatabaseDirPath();
        String file = "Highscores.mv.db";
        return (new File(filePath,file).exists());
    }
}
