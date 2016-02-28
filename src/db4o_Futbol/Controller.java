package db4o_Futbol;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by 46465442z on 19/02/16.
 */
public class Controller {

    private static ObjectContainer database;

    public static void main(String[] args) {

        //Controler que gestiona la BBDD
        // Menú
        Scanner teclat = new Scanner(System.in);

        int opcio = 1;

        while (opcio != 0){

            System.out.println("\n");
            System.out.println("MENU:");

            System.out.println("---------------LIGA------------------");
            System.out.println("1 - Crear liga");
            System.out.println("2 - Anyadir equipo");

            System.out.println("----------------EQUIPO---------------");
            System.out.println("5 - Crear equipo");
            System.out.println("6 - Anyadir entrenador");
            System.out.println("7 - Anyadir jugadores");
            System.out.println("8 - An");

            System.out.println("--------------JUGADORES--------------");
            System.out.println("1 - Crear jugador");
            System.out.println("2 - Retirar un jugador");
            System.out.println("3 - Traspasar un jugador");
            System.out.println("4 - Crear características");
            System.out.println("---------------EQUIPO----------------");

            System.out.println("--------------CONSULTAS--------------");
            System.out.println("8 - Consultar jugadores de un equipo");
            System.out.println("9 - Consultar Jugadores de dos equipos solicitados (SODA).");
            System.out.println("10 - Consultar jugadores de un equipo con Fuerza menor o igual que 5.");
            System.out.println("11 - Consultar Jugadores pertenecientes a una Liga.");
            System.out.println("12 - Consultar características de un jugador.");
            System.out.println("6 - Consultar jugadores que entrena un entrenador.");
            System.out.println("7 - Equipos de una liga en concreto");

            System.out.println("0 - Sortir ");

            opcio = teclat.nextInt();

            switch (opcio){

                case 0:

                break;

                case 1:

                break;

                case 2:

                break;

                case 3:
                    // Traspasar jugador
                    traspasarJugador();
                break;

                case 4:
                    // Retirar jugador
                    reitrarJugador();

                break;

                case 5:

                break;

                case 6:

                break;

                case 7:

                break;
            }
        }
    }

    // Jugadores

    public static void crearJugador() {

        // Abrimos el teclado
        Scanner teclat = new Scanner(System.in);

        // Objetos
        Jugador jugador = new Jugador();
        Caracteristicas caracteristicasJugador = new Caracteristicas();

        // Pedimos los datos del jugador

        System.out.println("Introduce el DNI del jugador (String)");
            String dni = teclat.nextLine();
            jugador.setDNI(dni);

        System.out.println("Introduce el nombre del jugador (String)");
            String nombre = teclat.nextLine();
            jugador.setNombre(nombre);

        System.out.println("Introduce el apellido del jugador (String)");
            String apellido = teclat.nextLine();
            jugador.setApellido(apellido);

        System.out.println("Introduce la altura del jugador (Double)");
            double altura = teclat.nextDouble();
            jugador.setAltura(altura);

        // Pedimos las características

        System.out.println("Introduce la agilidad del jugador (Byte)");
            Byte agilidad = teclat.nextByte();
            caracteristicasJugador.setAgilidad(agilidad);

        System.out.println("Introduce la fuerza del jugador (Byte)");
            Byte fuerza = teclat.nextByte();
            caracteristicasJugador.setFuerza(fuerza);

        System.out.println("Introduce la velocidad del jugador (Byte)");
            Byte velocidad = teclat.nextByte();
            caracteristicasJugador.setVelocidad(velocidad);

        System.out.println("Introduce el pase del jugador (Byte)");
            Byte pase = teclat.nextByte();
            caracteristicasJugador.setPase(pase);

        System.out.println("Introduce el penalti del jugador (Byte)");
            Byte penalti = teclat.nextByte();
            caracteristicasJugador.setPenalti(penalti);

        jugador.setCaracteristicasJugador(caracteristicasJugador);

        conectarBBBDD();
        database.store(jugador);
        desconectarBBDD("Se ha añadido un jugador correctamente");
    }

    public static void reitrarJugador(){

        // Retirar jugador
        Scanner teclat = new Scanner(System.in);

        // Pedimos introducir el DNI
        System.out.println("Introduce el DNI del jugador a retirar (eliminará el jugador)");
            String DNI = teclat.nextLine();

        // Hacemos una queryByExample
        Jugador byExample = new Jugador();
        byExample.setDNI(DNI);

        ObjectSet jugadores = database.queryByExample(new Jugador());

        // Buscamos el jugador con nuestro DNI
        for (int iterador = 0; iterador < jugadores.size(); iterador++) {

            Jugador jugadorIteracion = (Jugador) jugadores.get(iterador);

            if (jugadorIteracion.getDNI().equals(DNI)){
                jugadorIteracion.SeRetira(database);
            }
        }

        database.commit();
        database.close();
    }

    public static void traspasarJugador() {

        // Traspasar jugador
        Scanner teclat = new Scanner(System.in);

        System.out.println("Introduce DNI JUGADOR a traspasar");
            String DNI = teclat.nextLine();

        System.out.println("Introduce NOMBRE EQUIPO a donde traspasa");
            String nombreEquipo = teclat.nextLine();

        conectarBBBDD();
        Jugador selectedJugador = null;
        ObjectSet jugadores = database.queryByExample(new Jugador());

        for (int iterador = 0; iterador < jugadores.size(); iterador++) {
            selectedJugador = (Jugador) jugadores.get(iterador);
            // Añadimos el jugador a la BBDD
            if (selectedJugador.getDNI().equals(DNI)) {
                break;
            }
        }

        Equipo selectedEquipo = null;
        ObjectSet equipos = database.queryByExample(new Equipo());

        for (int iterador = 0; iterador < equipos.size(); iterador++) {

            selectedEquipo = (Equipo) equipos.get(iterador);

            if (selectedEquipo.getNombre().equals(nombreEquipo)) {
                database.delete(selectedEquipo);
                database.commit();
                database.close();

                selectedEquipo.anyadirJugador(selectedJugador);
                break;
            }
        }

        // Traspasamos el jugador
        conectarBBBDD();
        database.store(selectedEquipo);
        desconectarBBDD("Se ha traspasado el jugador " + selectedJugador.getNombre() + " al equipo " + selectedEquipo.getNombre());
    }

    // Equipo

    public static void crearEquipo() {

        // Abrimos el teclado
        Scanner teclat = new Scanner(System.in);

        // Objetos
        Liga liga = new Liga();
        Equipo equipo = new Equipo();
        Entrenador entrenador = new Entrenador();

        // Liga del equipo

        System.out.println("Introduce el nombre de la liga del equipo (String)");
            String nombreLiga = teclat.nextLine();
            liga.setNombre(nombreLiga);

        System.out.println("Introduce la categoria de la liga (String)");
            Byte categoriaLiga = teclat.nextByte();
            liga.setCategoria(categoriaLiga);

        System.out.println("Introduce el nombre del patrocinador de la liga (String)");
            String patrocinadorLiga = teclat.nextLine();
            liga.setPatrocinador(patrocinadorLiga);

        // Nombre del equipo

        System.out.println("Introduce nombre del equipo (String)");
            String nombreEquipo = teclat.nextLine();
            equipo.setNombre(nombreEquipo);

        System.out.println("Introduce el nombre del estadio (String)");
            String nombreEstadio = teclat.nextLine();
            equipo.setEstadio(nombreEstadio);

        // Entrenador del equipo

        System.out.println("Introduce el nombre del entrenador del equipo (String)");
            String nombreEntrenador = teclat.nextLine();
            entrenador.setNombre(nombreEntrenador);

        System.out.println("Introduce los años de experiencia del entrenador (Byte)");
            Byte anyoExperiencia = teclat.nextByte();
            entrenador.setAnyoExperiencia(anyoExperiencia);

        // Le asignamos al equipo su liga y su entrenador
        equipo.setEntrenadorEquipo(entrenador);
        equipo.setLiga(liga);

        // Añadimos el Equipo a la BBDD
        conectarBBBDD();
        database.store(equipo);
        desconectarBBDD("Se ha guardado un equipo correctamente");
    }

    // Querys

    public static void jugadoresDeUnEntrenador()  {

        // Jugadores que entrena un entrenador

        Scanner teclat = new Scanner(System.in);

        System.out.println("Introduce el nombre del entrenador (String)");
            String nombreEntrenador = teclat.nextLine();

        ObjectSet equipos = database.queryByExample(new Equipo());

        conectarBBBDD();

        System.out.println();
        System.out.println("Jugadores del entrenador: " +  nombreEntrenador);
        System.out.println("--------------------------------------------------------");

        for (int iterador = 0; iterador < equipos.size(); iterador++) {

            Equipo selected = (Equipo) equipos.get(iterador);

            if (selected.getEntrenadorEquipo().getNombre().equals(nombreEntrenador)) {
                for (int iteradorJugadores = 0; iteradorJugadores < selected.getListaJugadores().size(); iteradorJugadores++) {
                    System.out.println(selected.getListaJugadores().get(iteradorJugadores).toString());
                }
                break;
            }
        }
    }

    //Métodos para la BBDD

    public static void conectarBBBDD() {
        File archivoBBDD = new File("C:\\Users\\uRi\\IdeaProjects\\Programacion\\database.data");

        if (!archivoBBDD.exists()) {
            try
            {
                archivoBBDD.createNewFile();
                database = Db4o.openFile("database.data");

            } catch (IOException e) {}
        }
        else {
            database = Db4o.openFile("database.data");
        }
    }

    public static void desconectarBBDD(String mensaje) {
        database.commit();
        database.close();

        if (mensaje != null && !mensaje.equalsIgnoreCase("")){
            System.out.println(mensaje);
        }
    }

}
