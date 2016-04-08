package db40_guerra;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import db4o_Futbol.*;

import java.util.Scanner;

/**
 * Created by 46465442z on 19/02/16.
 */
public class Controller {

    private static ObjectContainer database;

    public static void main(String[] args) {

        // Instanciamos nuesto object container a traves de la clase auxiliar para hacer consultas
        database = DatabaseConn.getInstance();

        // Anyadimos los equipos ya hecho
       anyadirEjemplos(database);

        //Controler que gestiona la BBDD
        
        // Menú
        Scanner teclat = new Scanner(System.in);

        int opcio = 1;

        while (opcio != 0){

            System.out.println("\n");
            System.out.println("MENU:");

            System.out.println("--------------Menu--------------");
            System.out.println("1 - Crear grupo armado");
            System.out.println("2 - Crear conflicto");
            System.out.println("3 - Asignar grupo armado a conflicto");
            System.out.println("4 - Ver los participantes de un conflicto");
            System.out.println("5 - Grupos armados con mas de 300 bajas");

            System.out.println("0 - Sortir ");

            opcio = teclat.nextInt();

            switch (opcio){

                case 0:

                break;

                case 1:
                    // Crear un grupoArmado
                    crearGrupoArmado(database);
                break;

                case 2:
                    // Aumentar caracteristicas jugador
                    crearConflicto(database);
                break;

                case 3:
                    // Aumentar caracteristicas jugador
                    anyadirGrupoArmadoAUnConflicto(database);
                break;

                case 4:
                    imprimirParticipesConflicto(database);
                break;

                case 5:
                    gruposArmados300Bajas(database);
                break;
            }
        }
    }

    // GrupoArmado

    public static void crearGrupoArmado(ObjectContainer database) {

        // Abrimos el teclado
        Scanner teclat = new Scanner(System.in);

        // Objetos
        GrupoArmado grupoArmado = new GrupoArmado();

        // Pedimos los datos del grupoArmado
        System.out.println("Introduce el codigo del grupo armado (int)");
            int codigo = teclat.nextInt();

        if(comprobarClavePrimaria(codigo, false)){

            grupoArmado.setCodigo(codigo);

            System.out.println("Introduce el nombre del grupo armado (String)");
            String nombre = teclat.next();
            grupoArmado.setNombre(nombre);

            System.out.println("Introduce las bajas del grupo armado (int)");
            int bajas = teclat.nextInt();
            grupoArmado.setBajas(bajas);


            // Conectamos a la base de datos, anyadimos los datos y cerramos la conexion
            database.store(grupoArmado);
        }
    }

    // Conflicto

    public static void crearConflicto(ObjectContainer database) {

        // Abrimos el teclado
        Scanner teclat = new Scanner(System.in);

        // Objetos
        Conflicto conflicto = new Conflicto();

        // Pedimos los datos del Conflicto

        System.out.println("Introduce el codigo del conflicto (int)");
            int codigo = teclat.nextInt();

        if(comprobarClavePrimaria(codigo, true)) {

            conflicto.setCodigo(codigo);

            System.out.println("Introduce el nombre del conflicto (String)");
            String nombre = teclat.next();
            conflicto.setNombre(nombre);

            System.out.println("Introduce las zona del conflicto (String)");
            String zona = teclat.next();
            conflicto.setZona(zona);

            System.out.println("Introduce el numero de heridos en el conflicto (int)");
            int heridos = teclat.nextInt();
            conflicto.setHeridos(heridos);

            // Conectamos a la base de datos, anyadimos los datos y cerramos la conexion
            database.store(conflicto);
        }
    }

    public static void anyadirGrupoArmadoAUnConflicto(ObjectContainer database){

        // Teclat
        Scanner teclat = new Scanner(System.in);

        // Pedimos el nombre del equipo y del entrenador
        System.out.println("Introduce el codigo del conflicto al que le quieres anyadir un grupo armado (int)");
            int codigoConflicto = teclat.nextInt();

        System.out.println("Introduce el codigo del grupo armado(int)");
            int codigoGrupoArmado = teclat.nextInt();

        // Preparamos nuestro objeto para hacer la queryByExample
        GrupoArmado grupoArmadoIteracion = null;
        ObjectSet grupoArmado = database.queryByExample(new GrupoArmado());

        // Con un for buscamos el equipo
        for (int iterador = 0; iterador < grupoArmado.size(); iterador++) {

            grupoArmadoIteracion = (GrupoArmado) grupoArmado.get(iterador);

            // Añadimos el jugador a la BBDD
            if (grupoArmadoIteracion.getCodigo() == codigoGrupoArmado) {
                // Una vez encontrado el jugador, cortamos el bucle
                break;
            }
        }

        // Preparamos el objeto para hacer la queryByExample
        Conflicto conflictoIteracion = null;
        ObjectSet conflictos = database.queryByExample(new Equipo());

        // Buscamos el equipo al que irá nuestro jugador
        for (int iterador = 0; iterador < conflictos.size(); iterador++) {

            conflictoIteracion = (Conflicto) conflictos.get(iterador);

            // Eliminamos el antiguo equipo y lo volvemos a anyadir actualizado
            if (conflictoIteracion.getCodigo() == codigoConflicto) {
                conflictoIteracion.anyadirGrupoArmado(grupoArmadoIteracion);
                break;
            }
        }
    }

    // Consultas

    public static void imprimirParticipesConflicto(ObjectContainer database)  {

        // Imprime todos los grupos armados involucrados en un conflicto

        // Teclat
        Scanner teclat = new Scanner(System.in);

        // Pedimos el nombre del equipo y del entrenador
        System.out.println("Introduce el codigo del conflicto del que quieras ver los grupos armados (int)");
            int codigoConflicto = teclat.nextInt();

        // Objeto para la queryByExample
        ObjectSet conflicto = database.queryByExample(new Conflicto(codigoConflicto));

        // Imprimimos todos los jugadores del equipo con el for

        System.out.println();
        System.out.println("Grupos armados participes en el conflicto: " +  codigoConflicto);
        System.out.println("--------------------------------------------------------");

        // Buscamos el equipo que queremos con el for
        while (conflicto.hasNext()) {
            System.out.println(conflicto.next().toString());
        }
    }

    public static void gruposArmados300Bajas(ObjectContainer database){

        // Imprime los grupos armados con más de 300 bajas

        // Teclat
        Scanner teclat = new Scanner(System.in);

        // Imprimimos todos los jugadores del equipo con el for

        System.out.println();
        System.out.println("Grupos armados con más de 300 bajas: ");
        System.out.println("--------------------------------------------------------");

        // Preparamos nuestro objeto para hacer la queryByExample
        GrupoArmado grupoArmadoIteracion = null;
        ObjectSet grupoArmado = database.queryByExample(new GrupoArmado());

        // Con un for buscamos el equipo
        for (int iterador = 0; iterador < grupoArmado.size(); iterador++) {

            grupoArmadoIteracion = (GrupoArmado) grupoArmado.get(iterador);

            // Añadimos el jugador a la BBDD
            if (grupoArmadoIteracion.getBajas() > 300) {
                System.out.println(grupoArmadoIteracion.toString());
            }
        }
    }

    public static boolean comprobarClavePrimaria(int valor, boolean tipo){

        // Método que gestiona que no se puedan añadir grupos y conflictos con la misma clave primaria

        // Si tipo = true comprobamos conflictos
        // Si tipo = false comprobamos gruposArmados

        if(!tipo){
            // Preparamos nuestro objeto para hacer la queryByExample
            GrupoArmado grupoArmadoIteracion = null;
            ObjectSet grupoArmado = database.queryByExample(new GrupoArmado());

            // Con un for buscamos el equipo
            for (int iterador = 0; iterador < grupoArmado.size(); iterador++) {

                grupoArmadoIteracion = (GrupoArmado) grupoArmado.get(iterador);

                // Añadimos el jugador a la BBDD
                if (grupoArmadoIteracion.getCodigo() == valor) {
                    System.out.println("La clave primaria introducida ya existe en otro registro");
                    return false;
                }
            }

            // Si no ha encontrado nada se puede usar
            return true;
        }
        else{
            // Preparamos nuestro objeto para hacer la queryByExample
            Conflicto conflictoIteracion = null;
            ObjectSet conflicto = database.queryByExample(new Conflicto());

            // Con un for buscamos el equipo
            for (int iterador = 0; iterador < conflicto.size(); iterador++) {

                conflictoIteracion = (Conflicto) conflicto.get(iterador);

                // Añadimos el jugador a la BBDD
                if (conflictoIteracion.getCodigo() == valor) {
                    System.out.println("La clave primaria introducida ya existe en otro registro");
                    return false;
                }
            }

            // Si no ha encontrado nada se puede usar
            return true;
        }
    }

    // Ejemplo para rellenar la bbdd automatica

    public static void anyadirEjemplos(ObjectContainer database){

        // Método auxiliar con algunos datos que se anyaden automaticamente a la BBDD

        // Conflictos predefinidos
        Conflicto conf1 = new Conflicto(1, "Guerra Siria", "Oriente Medio", 300000);

        // Combatientes predefinidos
        GrupoArmado comb1 = new GrupoArmado("ISIS", 1, 2000000);
        GrupoArmado comb2 = new GrupoArmado("Kurdos", 2, 200000);
        GrupoArmado comb3 = new GrupoArmado("Civiles", 3, 2);

        // Anyadimos los combatientes a los conflictos
        conf1.anyadirGrupoArmado(comb1);
        conf1.anyadirGrupoArmado(comb2);
        conf1.anyadirGrupoArmado(comb3);

        database.store(conf1);
        database.commit();
    }
}