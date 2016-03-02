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

            System.out.println("--------------JUGADORES--------------");
            System.out.println("1 - Crear jugador");
            System.out.println("2 - Aumentar caracteristicas");
            System.out.println("3 - Traspasar");
            System.out.println("4 - Retirar");

            System.out.println("----------------EQUIPO---------------");
            System.out.println("5 - Crear equipo");
            System.out.println("6 - Cambiar entrenador");
            System.out.println("7 - Cambiar liga");

            System.out.println("--------------CONSULTAS--------------");
            System.out.println("8 - Consultar jugadores de un equipo");
            System.out.println("9 - Consultar Jugadores de dos equipos solicitados (SODA).");
            System.out.println("10 - Consultar jugadores de un equipo con Fuerza menor o igual que 5.");
            System.out.println("11 - Consultar Jugadores pertenecientes a una Liga.");
            System.out.println("12 - Consultar características de un jugador.");
            System.out.println("13 - Consultar jugadores que entrena un entrenador.");
            System.out.println("14 - Equipos de una liga en concreto");

            System.out.println("0 - Sortir ");

            opcio = teclat.nextInt();

            switch (opcio){

                case 0:

                break;

                case 1:
                    // Crear jugador
                    crearJugador();
                break;

                case 2:
                    // Aumentar caracteristicas jugador
                    aumentarCaracteristicas();
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
                    // Crear equipo
                    crearEquipo();
                break;

                case 6:
                    // Cambiar entrenador
                    cambiarEntrenadorDeEquipo();
                break;

                case 7:
                    // Cambiar equipo de liga
                    cambiarEquipoDeLiga();
                break;

                case 8:
                    // Consultar jugadores de un equipo
                    System.out.println("Introduce el nombre del equipo del que quieres ver los jugadores");
                        String nombreEquipo = teclat.next();

                    imprimirJugadoresDeEquipo(nombreEquipo);
                break;

                case 9:
                    // Consultar Jugadores de dos equipos solicitados (SODA)
                    cambiarEquipoDeLiga();
                break;

                case 10:
                    // Consultar jugadores de un equipo con Fuerza menor o igual que 5.
                    jugadoresDeEquipoFueraMenorCinco();
                break;

                case 11:
                    // Consultar Jugadores pertenecientes a una Liga.

                break;

                case 12:
                    // Consultar características de un jugador.
                    caracteristicasDeUnJugador();
                break;

                case 13:
                    // Consultar jugadores que entrena un entrenador
                    jugadoresDeUnEntrenador();
                break;

                case 14:
                    // Cambiar equipo de liga
                    cambiarEquipoDeLiga();
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

        // Le asignamos las caracteristicas al jugador
        jugador.setCaracteristicasJugador(caracteristicasJugador);

        // Conectamos a la base de datos, anyadimos los datos y cerramos la conexion
        conectarBBBDD();
        database.store(jugador);
        desconectarBBDD("Se ha añadido un jugador correctamente");
    }

    public static void aumentarCaracteristicas() {

        // Teclado
        Scanner teclat = new Scanner(System.in);

        // Pedmis el DNI del jugador y el valor
        System.out.println("Introduce el DNI del jugador (String)");
            String DNI = teclat.nextLine();
        System.out.println("En que valor quieres aumentar las cracteristicas? (Byte)");
            Byte puntosAumentar = teclat.nextByte();

        // Conectamos con la base de datos
        conectarBBBDD();

        // Preparamos el objeto para hacer la queryByExample
        Jugador byExample = new Jugador();
        byExample.setDNI(DNI);

        ObjectSet jugadores = database.queryByExample(new Jugador());
        Jugador selected = null;

        // Recorremos todos los jugadores con el for hasta que encontremos al que le queremos subir las habilidades
        for (int iterador = 0; iterador < jugadores.size(); iterador++) {

            selected = (Jugador) jugadores.get(iterador);

            if (selected.getDNI().equals(DNI)) {

                // Los bytes no se pueden sumar de manera tradicional con el operador "+"
                // es necesario castarlos a su tipo de datos

                Caracteristicas caracteristicas = ((Jugador) jugadores.get(iterador)).getCaracteristicasJugador();
                caracteristicas.setPenalti((byte) (caracteristicas.getPenalti() + puntosAumentar));
                caracteristicas.setPase((byte) (caracteristicas.getPase() + puntosAumentar));
                caracteristicas.setVelocidad((byte) (caracteristicas.getVelocidad() + puntosAumentar));
                caracteristicas.setFuerza((byte) (caracteristicas.getFuerza() + puntosAumentar));
                caracteristicas.setAgilidad((byte) (caracteristicas.getAgilidad() + puntosAumentar));
                selected.setCaracteristicasJugador(caracteristicas);
                database.delete(jugadores.get(iterador));

                // cerramos la conexión con la base de datos
                desconectarBBDD(null);
                break;
            }
        }

        // Conectamos a la base de datos, anyadimos los datos y cerramos la conexion
        conectarBBBDD();
        database.store(selected);
        desconectarBBDD("Se han aumentado las caracteristicas" + puntosAumentar + " puntos");
    }

    public static void reitrarJugador(){

        // Retirar jugador
        Scanner teclat = new Scanner(System.in);

        // Pedimos introducir el DNI
        System.out.println("Introduce el DNI del jugador a retirar (eliminará el jugador)");
            String DNI = teclat.nextLine();

        // Preparamos el objeto para hacer la queryByExample
        Jugador byExample = new Jugador();
        byExample.setDNI(DNI);

        // Conectamos a la base de datos
        conectarBBBDD();

        // Preparamos el objeto para hacer la queryByExample
        ObjectSet jugadores = database.queryByExample(new Jugador());

        // Buscamos el jugador con nuestro DNI
        for (int iterador = 0; iterador < jugadores.size(); iterador++) {

            Jugador jugadorIteracion = (Jugador) jugadores.get(iterador);

            if (jugadorIteracion.getDNI().equals(DNI)){
                // database.delete(jugadorIteracion.getCaracteristicasJugador().getClass());
                database.delete(jugadorIteracion);
            }
        }

        // Cerramos la conexion con la base de datos
        desconectarBBDD(null);
    }

    public static void traspasarJugador() {

        // Traspasar jugador
        Scanner teclat = new Scanner(System.in);

        // Pedimos el DNI del jugador y el equipo al que va a ir
        System.out.println("Introduce el DNI del jugador a traspasar (String)");
            String DNI = teclat.nextLine();

        System.out.println("Introduce el nombre del equipo al que va a ser traspasado (String)");
            String nombreEquipo = teclat.nextLine();

        // Conectamos la BBDD
        conectarBBBDD();

        // Preparamos nuestro objeto para hacer la queryByExample
        Jugador jugadorIteracion = null;
        ObjectSet jugadores = database.queryByExample(new Jugador());

        // Con un for buscamos el equipo
        for (int iterador = 0; iterador < jugadores.size(); iterador++) {

            jugadorIteracion = (Jugador) jugadores.get(iterador);

            // Añadimos el jugador a la BBDD
            if (jugadorIteracion.getDNI().equals(DNI)) {
                // Una vez encontrado el jugador, cortamos el bucle
                break;
            }
        }

        // Preparamos el objeto para hacer la queryByExample
        Equipo equipoIteracion = null;
        ObjectSet equipos = database.queryByExample(new Equipo());

        // Buscamos el equipo al que irá nuestro jugador
        for (int iterador = 0; iterador < equipos.size(); iterador++) {

            equipoIteracion = (Equipo) equipos.get(iterador);

            // Eliminamos el antiguo equipo y lo volvemos a anyadir actualizado
            if (equipoIteracion.getNombre().equals(nombreEquipo)) {
                database.delete(equipoIteracion);
                desconectarBBDD(null);
                equipoIteracion.anyadirJugador(jugadorIteracion);
                break;
            }
        }

        // Traspasamos el jugador
        conectarBBBDD();
        database.store(equipoIteracion);
        desconectarBBDD("Se ha traspasado el jugador " + jugadorIteracion.getNombre() + " al equipo " + equipoIteracion.getNombre());
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
            String nombreLiga = teclat.next();
            liga.setNombre(nombreLiga);

        System.out.println("Introduce la categoria de la liga (Byte)");
            Byte categoriaLiga = teclat.nextByte();
            liga.setCategoria(categoriaLiga);

        System.out.println("Introduce el nombre del patrocinador de la liga (String)");
            String patrocinadorLiga = teclat.next();
            liga.setPatrocinador(patrocinadorLiga);

        // Nombre del equipo

        System.out.println("Introduce nombre del equipo (String)");
            String nombreEquipo = teclat.next();
            equipo.setNombre(nombreEquipo);

        System.out.println("Introduce el nombre del estadio (String)");
            String nombreEstadio = teclat.next();
            equipo.setEstadio(nombreEstadio);

        // Entrenador del equipo

        System.out.println("Introduce el nombre del entrenador del equipo (String)");
            String nombreEntrenador = teclat.next();
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

    public static void cambiarEntrenadorDeEquipo()  {

        // Teclat
        Scanner teclat = new Scanner(System.in);

        // Pedimos el nombre del equipo y del entrenador
        System.out.println("Introduce el nombre del equipo al que le quieres cambiar el entrenador (String)");
            String nombreEquipo = teclat.next();
        System.out.println("Introduce el nombre del nuevo entrenador(String)");
            String nombreEntrenador = teclat.next();

        // Conectamos a la base de datos
        conectarBBBDD();

        // Preparamos el objeto para la queryByExample
        ObjectSet equipos = database.queryByExample(new Equipo());
        Equipo equipoIteracion = null;

        // Recorremos todos los equipos hasta encontrar el que queremos
        for (int iterador = 0; iterador < equipos.size(); iterador++) {

            equipoIteracion = (Equipo) equipos.get(iterador);

            if (equipoIteracion.getNombre().equals(nombreEquipo)) {
                Entrenador entrenador = ((Equipo) equipos.get(iterador)).getEntrenadorEquipo();
                entrenador.setNombre(nombreEntrenador);
                database.delete(equipos.get(iterador));

                // Cerramos la conexion con la BBDD
                desconectarBBDD(null);
                break;
            }
        }

        // Y guardamos los cambios en la BBDD
        conectarBBBDD();
        database.store(equipoIteracion);
        desconectarBBDD(nombreEntrenador + " es el nuevo entrenador de " + nombreEquipo);
    }

    public static void cambiarEquipoDeLiga() {

        // Teclado
        Scanner teclat = new Scanner(System.in);

        // PEdimos el nombre del equipo y el nombre de la liga
        System.out.println("Introduce el nombre equipo (String)");
            String nombreEquipo = teclat.nextLine();

        System.out.println("Introduce el nombre de Liga (String)");
            String nombreLiga = teclat.nextLine();

        // Conectamos a la bbdd
        conectarBBBDD();

        // Preparamos el objeto para hacer la queryByExample
        ObjectSet equipos = database.queryByExample(new Equipo());
        Equipo equipoIteracion = null;

        // Recorremos todos los equipos hasta que encontremos el nuestro
        for (int iterador = 0; iterador < equipos.size(); iterador++) {

            equipoIteracion = (Equipo) equipos.get(iterador);

            if (equipoIteracion.getNombre().equals(nombreEquipo)) {

                Liga liga = ((Equipo) equipos.get(iterador)).getLiga();
                liga.setNombre(nombreLiga);
                database.delete(equipos.get(iterador));

                // Desconectamos de la BBDD
                desconectarBBDD(null);
                break;
            }
        }

        // Guardamos los cambios en la base de datos
        conectarBBBDD();
        database.store(equipoIteracion);
        desconectarBBDD("Se ha cambiado correctamente al " + equipoIteracion.getNombre() + " de Liga");
    }

    // Ligas

    public static void cambiarPatrocinadorLiga()  {

        // Teclado
        Scanner teclat = new Scanner(System.in);

        // Pedimos el nombre del equipo y del nuevo patrocinador
        System.out.println("Introduce el nombre del equipo al que le quieres cambiar el patrocinador (String)");
            String nombreEquipo = teclat.nextLine();

        System.out.println("Introduce el nombre del nuevo patrocinador (String)");
            String nombrePatrocinador = teclat.nextLine();

        // Conectamos a la BBDD
        conectarBBBDD();

        // Preparamos el objeto para la queryByExample
        ObjectSet equipos = database.queryByExample(new Equipo());
        Equipo equipoIteracion = null;

        // Recorremos todos los equipos hasta que encontramos el que buscamos
        for (int iterador = 0; iterador < equipos.size(); iterador++) {

            equipoIteracion = (Equipo) equipos.get(iterador);

            if (equipoIteracion.getNombre().equals(nombreEquipo)) {

                Liga liga = ((Equipo) equipos.get(iterador)).getLiga();
                liga.setPatrocinador(nombrePatrocinador);
                database.delete(equipos.get(iterador));

                // Desconectamos la BBDD
                desconectarBBDD(null);
                break;
            }
        }

        // Guardamos los cambios en la BBDD y cerramos la conexión
        conectarBBBDD();
        database.store(equipoIteracion);
        desconectarBBDD("Se ha cambiado el patrocinador correctamente por " +  nombrePatrocinador);
    }

    // Querys

    public static void caracteristicasDeUnJugador()  {

        // Características que tiene un jugador

        // Teclado
        Scanner teclat = new Scanner(System.in);

        // Pedimos el nombre del jugador
        System.out.println("Nombre del jugador Jugador (String)");
            String nombreJugador = teclat.nextLine();

        // Conectamos a la bbdd
        conectarBBBDD();

        // Objeto para la queryByExample
        ObjectSet jugadores = database.queryByExample(new Jugador());

        // Y mostramos las características del jugador buscandolo con un for

        System.out.println();
        System.out.println("Caracteristicas jugador " + nombreJugador);
        System.out.println("--------------------------------------------------------");

        for (int iterador = 0; iterador < jugadores.size(); iterador++)  {

            Jugador jugadorIteracion = (Jugador) jugadores.get(iterador);

            if (jugadorIteracion.getNombre().equals(nombreJugador)) {
                System.out.println(jugadorIteracion.getCaracteristicasJugador().toString());
                break;
            }
        }
    }

    public static void jugadoresDeUnEntrenador()  {

        // Jugadores que entrena un entrenador

        // Teclat
        Scanner teclat = new Scanner(System.in);

        // Pedimos por teclado el nombre del entrenador
        System.out.println("Introduce el nombre del entrenador (String)");
            String nombreEntrenador = teclat.nextLine();

        // Objeto para la queryByExample
        ObjectSet equipos = database.queryByExample(new Equipo());

        // Conectamos a la BBDD
        conectarBBBDD();

        // Y imprimimos todos los jugadores del entrenador con el for

        System.out.println();
        System.out.println("Jugadores del entrenador: " +  nombreEntrenador);
        System.out.println("--------------------------------------------------------");

        for (int iterador = 0; iterador < equipos.size(); iterador++) {

            Equipo equipoIteracion = (Equipo) equipos.get(iterador);

            if (equipoIteracion.getEntrenadorEquipo().getNombre().equals(nombreEntrenador)) {
                for (int iteradorJugadores = 0; iteradorJugadores < equipoIteracion.getListaJugadores().size(); iteradorJugadores++) {
                    System.out.println(equipoIteracion.getListaJugadores().get(iteradorJugadores).toString());
                }
                break;
            }
        }
    }

    public static void jugadoresDeEquipoFueraMenorCinco() {

        // Jugadores de un equipo que tengan una fuerza menor o igual que cinco

        // Teclado
        Scanner teclat = new Scanner(System.in);

        // Introducimos el nombre del equipo por teclado
        System.out.println("Introduce el nombre del equipo (String)");
            String nombreEquipo = teclat.nextLine();

        // Conectamos a la BBDD
        conectarBBBDD();

        // Objeto para la queryByExample
        ObjectSet equipos = database.queryByExample(new Equipo());
        Equipo equipoIteracion = null;

        // Buscamos nuestro equipo con el for
        for (int iterador = 0; iterador < equipos.size(); iterador++) {

            equipoIteracion = (Equipo) equipos.get(iterador);

            if (equipoIteracion.getNombre().equals(nombreEquipo)) {
                // Una vez encontrado el equipo cortamos el bucle
                break;
            }
        }

        // E imprimimos todos sus jugadores con el nuevo for

        System.out.println();
        System.out.println("Jugadores en el equipo: " +  nombreEquipo + " con una fuerza igual o menor que 5");
        System.out.println("--------------------------------------------------------");

        for (int iterador = 0; iterador < equipoIteracion.getListaJugadores().size(); iterador++) {
            if (equipoIteracion.getListaJugadores().get(iterador).getCaracteristicasJugador().getFuerza() <= 5) {
                System.out.println(equipoIteracion.getListaJugadores().get(iterador).toString());
            }
        }

        // Cerramos la conexión a la BBDD
        database.close();
    }

    public static void jugadoresDeDosEquipos() {

        // Imprime los jugadores de dos equipos

        Scanner teclat = new Scanner(System.in);

        // Introducimos los dos equipos
        System.out.println("Nombre equipo 1 (String)");
            String equipo1 = teclat.nextLine();
        System.out.println("Nombre equipo 2 (String)");
            String equipo2 = teclat.nextLine();

        // Mostramos los jugadores
        imprimirJugadoresDeEquipo(equipo1);
        imprimirJugadoresDeEquipo(equipo2);
    }

    public static void imprimirJugadoresDeEquipo(String nombreEquipo)  {

        // Imprime todos los jugadores de un equipo concreto

        // Conectamos a la BBDD
        conectarBBBDD();

        // Objeto para la queryByExample
        ObjectSet equipos = database.queryByExample(new Equipo());
        Equipo equipoIteracion = null;

        // Buscamos el equipo que queremos con el for
        for (int iterador = 0; iterador < equipos.size(); iterador++) {

            equipoIteracion = (Equipo) equipos.get(iterador);

            if (equipoIteracion.getNombre().equals(nombreEquipo)) {
                // Una vez encontrado el equipo cortamos el bucle
                break;
            }
        }

        // Imprimimos todos los jugadores del equipo con el for

        System.out.println();
        System.out.println("Jugadores en el equipo: " +  nombreEquipo);
        System.out.println("--------------------------------------------------------");

        for (int iterador = 0; iterador < equipoIteracion.getListaJugadores().size(); iterador++) {
            System.out.println("Jugador " + (iterador + 1) + ": " + equipoIteracion.getListaJugadores().get(iterador).toString());
        }

        // Cerramos la conexión con la BBDD
        database.close();
    }

    //Métodos para la BBDD

    public static void conectarBBBDD() {

        // Archivo de la BBDD
        File archivoBBDD = new File("database.data");

        // Si no existe lo creamos, si existe, lo asignamos
        if (!archivoBBDD.exists()) {
            try   {
                archivoBBDD.createNewFile();
                database = Db4o.openFile("database.data");

            } catch (IOException e) {}
        }
        else {
            database = Db4o.openFile("database.data");
        }
    }

    public static void desconectarBBDD(String mensaje) {
        // Hacemos commit y cerramos la conexión con la bbdd
        database.commit();
        database.close();

        // Si tenemos texto como parametro, lo mostramos
        if (mensaje != null && !mensaje.equalsIgnoreCase("")){
            System.out.println(mensaje);
            System.out.println();
        }
    }

}