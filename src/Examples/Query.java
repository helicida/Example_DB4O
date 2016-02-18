package Examples;

/**
 * Created by 46465442z on 19/01/16.
 */
import com.db4o.*;

public class Query {

    public static void main(String[] args) throws Exception {

        ObjectContainer db = null;

        try {

            db = Db4o.openFile("sergiBBDD.data");

            Person brian = new Person("Brian", "Goetz", 39);
            Person jason = new Person("Jason", "Hunter", 35);
            Person brians = new Person("Brian", "Sletten", 38);
            Person david = new Person("David", "Geary", 55);
            Person glenn = new Person("Glenn", "Vanderberg", 40);
            Person neal = new Person("Neal", "Ford", 39);

            db.store(brian);
            db.store(jason);
            db.store(brians);
            db.store(david);
            db.store(glenn);
            db.store(neal);

            db.commit();

            // Find all the Brians
            ObjectSet brianSearch = db.queryByExample(new Person("Brian", null, 0));

            while (brianSearch.hasNext()){
                System.out.println(brianSearch.next());
            }
        }
        finally  {
            if (db != null)
                db.close();
        }
    }
}