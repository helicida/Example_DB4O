package db4o_Futbol;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;

// CLASE que se ha buscado por internet
// Garantiza que solamente hay una instancia abierta con la base de datos
// Evitando problemas

public class DatabaseConn {

    private static DatabaseConn INSTANCE = null;
    private final String ruta = "database.data";    // Ruta del archivo de la base de datos
    private static ObjectContainer database;        // Object Container que apunta a la BBDD

    private synchronized static void createInstance(){
        if(INSTANCE == null){
            INSTANCE = new DatabaseConn();
            INSTANCE.performConnection();
        }
    }

    private void performConnection() {
        EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
        database = Db4oEmbedded.openFile(config, ruta);
    }

    public static ObjectContainer getInstance(){
        if(INSTANCE == null){
            createInstance();
        }

        return database;
    }
}