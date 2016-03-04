package db4o_Futbol;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;

import java.io.File;
import java.io.IOException;
import java.util.List;
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
            System.out.println(" 8 - Jugadores de un equipo solicitado");
            System.out.println(" 9 - Jugadores de dos equipos solicitados utilizando una consulta SODA");
            System.out.println(" 10 - Los jugadores de un equipo que tenga una Fuerza menor o igual que 5");
            System.out.println(" 11 - Equipos y jugadores pertenecientes a una Liga");
            System.out.println(" 12 - Características de un jugador dado");
            System.out.println(" 13 - Jugadores que pertenece a un entrenador dado");
            System.out.println(" 14 - Visualizar equipos");

            System.out.println("// RANDOM");
            System.out.println("-----------------------------------------");
            System.out.println("15 - Añadir jugador a un equipo");

            System.out.println("0 - Sortir ");

            opcio = teclat.nextInt();

            switch (opcio){

                case 0:

                break;

                case 1:
                    // Crear jugador
                    crearJugador(database);
                break;

                case 2:
                    // Aumentar caracteristicas jugador
                    aumentarCaracteristicas(database);
                break;

                case 3:
                    // Traspasar jugador
                    traspasarJugador(database);
                break;

                case 4:
                    // Retirar jugador
                    reitrarJugador(database);
                break;

                case 5:
                    // Crear equipo
                    crearEquipo(database);
                break;

                case 6:
                    // Cambiar entrenador
                    cambiarEntrenadorDeEquipo(database);
                break;

                case 7:
                    // Cambiar equipo de liga
                    cambiarEquipoDeLiga(database);
                break;

                case 8:
                    // Consultar jugadores de un equipo
                    System.out.println("Introduce el nombre del equipo del que quieres ver los jugadores");
                        String nombreEquipo = teclat.next();

                    imprimirJugadoresDeEquipo(database, nombreEquipo);
                break;

                case 9:
                    // Consultar Jugadores de dos equipos solicitados (SODA)
                    jugadoresDeDosEquiposSODA(database);
                break;

                case 10:
                    // Consultar jugadores de un equipo con Fuerza menor o igual que 5.
                    jugadoresDeEquipoFueraMenorCinco(database);
                break;

                case 11:
                    // Consultar Jugadores pertenecientes a una Liga.
                    jugadoresEnLiga(database);
                break;

                case 12:
                    // Consultar características de un jugador.
                    caracteristicasDeUnJugador(database);
                break;

                case 13:
                    // Consultar jugadores que entrena un entrenador
                    jugadoresDeUnEntrenador(database);
                break;

                case 14:
                    // Jugadores y equipos de una liga
                    visualizarEquipos(database);
                break;

                case 15:
                    // Anyadir jugador a un equipo
                    anyadirJugadorAUnEquipo(database);
                break;
            }
        }
    }

    // Jugadores

    public static void crearJugador(ObjectContainer database) {

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

        System.out.println("Introduce la agilidad del jugador (int)");
            int agilidad = teclat.nextInt();
            caracteristicasJugador.setAgilidad(agilidad);

        System.out.println("Introduce la fuerza del jugador (int)");
            int fuerza = teclat.nextInt();
            caracteristicasJugador.setFuerza(fuerza);

        System.out.println("Introduce la velocidad del jugador (int)");
            int velocidad = teclat.nextInt();
            caracteristicasJugador.setVelocidad(velocidad);

        System.out.println("Introduce el pase del jugador (int)");
            int pase = teclat.nextInt();
            caracteristicasJugador.setPase(pase);

        System.out.println("Introduce el penalti del jugador (int)");
            int penalti = teclat.nextInt();
            caracteristicasJugador.setPenalti(penalti);

        // Le asignamos las caracteristicas al jugador
        jugador.setCaracteristicasJugador(caracteristicasJugador);

        // Conectamos a la base de datos, anyadimos los datos y cerramos la conexion

        database.store(jugador);

    }

    public static void aumentarCaracteristicas(ObjectContainer database) {

        // Teclado
        Scanner teclat = new Scanner(System.in);

        // Pedmis el DNI del jugador y el valor
        System.out.println("Introduce el DNI del jugador (String)");
            String DNI = teclat.nextLine();
        System.out.println("En que valor quieres aumentar las cracteristicas? (int)");
            int puntosAumentar = teclat.nextInt();

        // Preparamos el objeto para hacer la queryByExample
        Jugador byExample = new Jugador();
        byExample.setDNI(DNI);

        ObjectSet jugadores = database.queryByExample(new Jugador());
        Jugador selected = null;

        // Recorremos todos los jugadores con el for hasta que encontremos al que le queremos subir las habilidades
        for (int iterador = 0; iterador < jugadores.size(); iterador++) {

            selected = (Jugador) jugadores.get(iterador);

            if (selected.getDNI().equals(DNI)) {

                // Los ints no se pueden sumar de manera tradicional con el operador "+"
                // es necesario castarlos a su tipo de datos

                Caracteristicas caracteristicas = ((Jugador) jugadores.get(iterador)).getCaracteristicasJugador();
                caracteristicas.setPenalti((int) (caracteristicas.getPenalti() + puntosAumentar));
                caracteristicas.setPase((int) (caracteristicas.getPase() + puntosAumentar));
                caracteristicas.setVelocidad((int) (caracteristicas.getVelocidad() + puntosAumentar));
                caracteristicas.setFuerza((int) (caracteristicas.getFuerza() + puntosAumentar));
                caracteristicas.setAgilidad((int) (caracteristicas.getAgilidad() + puntosAumentar));
                selected.setCaracteristicasJugador(caracteristicas);
                database.delete(jugadores.get(iterador));

                break;
            }
        }

        database.store(selected);
    }

    public static void reitrarJugador(ObjectContainer database){

        // Retirar jugador
        Scanner teclat = new Scanner(System.in);

        // Pedimos introducir el DNI
        System.out.println("Introduce el DNI del jugador a retirar (eliminará el jugador)");
            String DNI = teclat.nextLine();

        // Preparamos el objeto para hacer la queryByExample
        Jugador byExample = new Jugador();
        byExample.setDNI(DNI);

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
    }

    public static void traspasarJugador(ObjectContainer database) {

        // Traspasar jugador
        Scanner teclat = new Scanner(System.in);

        // Pedimos el DNI del jugador y el equipo al que va a ir
        System.out.println("Introduce el DNI del jugador a traspasar (String)");
            String DNI = teclat.nextLine();

        System.out.println("Introduce el nombre del equipo al que va a ser traspasado (String)");
            String nombreEquipo = teclat.nextLine();

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
                equipoIteracion.anyadirJugador(jugadorIteracion);
                break;
            }
        }

        // Traspasamos el jugador
        database.store(equipoIteracion);
    }

    // Equipo

    public static void crearEquipo(ObjectContainer database) {

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

        System.out.println("Introduce la categoria de la liga (int)");
            int categoriaLiga = teclat.nextInt();
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

        System.out.println("Introduce los años de experiencia del entrenador (int)");
            int anyoExperiencia = teclat.nextInt();
            entrenador.setAnyoExperiencia(anyoExperiencia);

        // Le asignamos al equipo su liga y su entrenador
        equipo.setEntrenadorEquipo(entrenador);
        equipo.setLiga(liga);

        // Añadimos el Equipo a la BBDD
        database.store(equipo);
    }

    public static void anyadirJugadorAUnEquipo(ObjectContainer database){

        // Teclat
        Scanner teclat = new Scanner(System.in);

        // Pedimos el nombre del equipo y del entrenador
        System.out.println("Introduce el nombre del equipo al que le quieres anyadir un jugador (String)");
        String nombreEquipo = teclat.next();
        System.out.println("Introduce el DNI del jugador(String)");
        String DNI = teclat.next();

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
                equipoIteracion.anyadirJugador(jugadorIteracion);
                break;
            }
        }
    }

    public static void cambiarEntrenadorDeEquipo(ObjectContainer database)  {

        // Teclat
        Scanner teclat = new Scanner(System.in);

        // Pedimos el nombre del equipo y del entrenador
        System.out.println("Introduce el nombre del equipo al que le quieres cambiar el entrenador (String)");
            String nombreEquipo = teclat.next();
        System.out.println("Introduce el nombre del nuevo entrenador(String)");
            String nombreEntrenador = teclat.next();

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
                break;
            }
        }

        // Y guardamos los cambios en la BBDD
        database.store(equipoIteracion);
    }

    public static void cambiarEquipoDeLiga(ObjectContainer database) {

        // Teclado
        Scanner teclat = new Scanner(System.in);

        // PEdimos el nombre del equipo y el nombre de la liga
        System.out.println("Introduce el nombre equipo (String)");
            String nombreEquipo = teclat.nextLine();

        System.out.println("Introduce el nombre de Liga (String)");
            String nombreLiga = teclat.nextLine();

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
                break;
            }
        }

        database.store(equipoIteracion);
    }

    // Ligas

    private static void jugadoresEnLiga(ObjectContainer db) {

        // Teclado
        Scanner teclat = new Scanner(System.in);

        System.out.println("Introduce el nombre de la liga: (String)");
            String liga = teclat.nextLine();

        ObjectSet<Liga> result = db.queryByExample(new Liga(liga, 0, null));

        // Si no hay resultado hacemos saltar la excepción
        if(!result.hasNext()){

            try {
                throw new Exception("No se ha encontrado la liga");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else{
            // Creo un list que lleno con los equipos (ponemos cero porque debería ser el primer resultado)
            List<Equipo> equipos = result.get(0).getEquipos();

            // Si equipos está vacío saltamos otra excepción
            if(equipos.isEmpty()){
                try {
                    throw new Exception("No hay ningun equipo en esta liga");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else{
                // Y si hay resultados lo imprimimos
                for(int iterador = 0; iterador < equipos.size(); iterador++) {
                    System.out.println(equipos.get(iterador).toString());
                    iterador++;
                }
            }
        }

    }

    // Querys

    public static void caracteristicasDeUnJugador(ObjectContainer database)  {

        // Características que tiene un jugador

        // Teclado
        Scanner teclat = new Scanner(System.in);

        // Pedimos el nombre del jugador
        System.out.println("Nombre del jugador Jugador (String)");
            String nombreJugador = teclat.nextLine();

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

    public static void jugadoresDeUnEntrenador(ObjectContainer database)  {

        // Jugadores que entrena un entrenador

        // Teclat
        Scanner teclat = new Scanner(System.in);

        // Pedimos por teclado el nombre del entrenador
        System.out.println("Introduce el nombre del entrenador (String)");
            String nombreEntrenador = teclat.nextLine();

        // Objeto para la queryByExample
        ObjectSet equipos = database.queryByExample(new Equipo());

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

    public static void jugadoresDeEquipoFueraMenorCinco(ObjectContainer database) {

        // Jugadores de un equipo que tengan una fuerza menor o igual que cinco

        // Teclado
        Scanner teclat = new Scanner(System.in);

        // Introducimos el nombre del equipo por teclado
        System.out.println("Introduce el nombre del equipo (String)");
            String nombreEquipo = teclat.nextLine();

        // Objeto para la queryByExample
        ObjectSet<Equipo> equipos = database.queryByExample(new Equipo(nombreEquipo, null, null));
        Equipo equipoIteracion = null;

        // E imprimimos todos sus jugadores con el nuevo for
        System.out.println();
        System.out.println("Jugadores en el equipo: " +  nombreEquipo + " con una fuerza igual o menor que 5");
        System.out.println("-----------------------------------------------------------------------------------");

        // Si no hay resultados hacemos saltar la excepción
        if(!equipos.hasNext()){

            try {
                throw new Exception("No se ha encontrado el equipo o no tiene jugadores");
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        else{
            // Sacamos la lista de jugadores
            List<Jugador> jugadores = equipos.get(0).getListaJugadores();

            boolean encontrado = false;

            for(int iterador = 0; iterador < jugadores.size(); iterador++) {

                if(jugadores.get(iterador).getCaracteristicasJugador().getFuerza() <= 5){

                    encontrado = true;

                    System.out.print(jugadores.get(iterador).getNombre() + " fuerza: " + jugadores.get(iterador).getCaracteristicasJugador().getFuerza() + "\n");
                }
            }

            // Si no se ha enctrado nada lo imprimimos por pantalla
            if(!encontrado) {
                System.out.println("No hay ningun jugador que cumpla los parametros de la consulta");
            }
        }
    }

    public static void jugadoresDeDosEquiposSODA(ObjectContainer database){

        // Teclado
        Scanner teclat = new Scanner(System.in);

        // Pedimos el nombre de los equipos
         System.out.println("Introduce el nombre del equipo 1:");
            String equipo1 = teclat.nextLine();

         System.out.println("Introduce el nombre del equipo 2:");
            String equipo2 = teclat.nextLine();

         // Preparamos el queryByExample
         ObjectSet<Equipo> equipo1Os = database.queryByExample(new Equipo(equipo1, null, null));
         ObjectSet<Equipo> equipo2Os = database.queryByExample(new Equipo(equipo2, null, null));

        // Comprobamos si hay resultados
         if(equipo1Os.hasNext() && equipo2Os.hasNext()){

             // Imprimimos los jugadores

             Query query1 = database.query();
             query1.constrain(new Equipo(equipo1, null, null));

             ObjectSet<Equipo> result1 = query1.execute();
             imprimirJugadoresDeEquipo(database, result1.next().getNombre());

             Query query2 = database.query();
             query2.constrain(new Equipo(equipo2, null, null));

             ObjectSet<Equipo> result2 = query2.execute();
             imprimirJugadoresDeEquipo(database, result2.next().getNombre());

         }
         else {
             System.out.println("Los equipos no existen o no tienen jugadores");
         }
    }

    private static void visualizarEquipos(ObjectContainer db) {

        // Muestra todos los equipos

        ObjectSet<Equipo> equipos = db.queryByExample(Equipo.class);

        System.out.println();
        System.out.println("Equipos de la BBDD:");
        System.out.println("----------------------------");

        // Con un iterador recorremos todos los equipos y los vamos imprimiendo en pantalla

        int iterador = 0;

        while(equipos.hasNext()){
            System.out.println("- " + equipos.get(iterador).getNombre());
            iterador++;
        }

    }

    public static void imprimirJugadoresDeEquipo(ObjectContainer database, String nombreEquipo)  {

        // Imprime todos los jugadores de un equipo concreto

        // Objeto para la queryByExample
        ObjectSet equipos = database.queryByExample(new Equipo(nombreEquipo, null, null));
        Equipo equipoIteracion = null;

        // Imprimimos todos los jugadores del equipo con el for

        System.out.println();
        System.out.println("Jugadores en el equipo: " +  nombreEquipo);
        System.out.println("--------------------------------------------------------");

        // Buscamos el equipo que queremos con el for
        while (equipos.hasNext()) {
            System.out.println(equipos.next().toString());
        }
    }



    public static void anyadirEjemplos(ObjectContainer database){

        // Método auxiliar con algunos datos que se anyaden automaticamente a la BBDD

        // Jugadores predefinidos
        Jugador jugador1 = new Jugador("31237123q", "J1", "Apellido1", 2000);
        Jugador jugador2 = new Jugador("87654321A", "J2", "Apellido2", 1946);
        Jugador jugador3 = new Jugador("13123123e", "J3", "Apellido3", 1934);
        Jugador jugador4 = new Jugador("1241234132d", "J4", "Apellido4", 1958);
        Jugador jugador5 = new Jugador("23123123r", "J5", "Apellido5", 1976);

        // Entrenadores predefinidos
        Entrenador entrenadorBarsa = new Entrenador("Guardiola", (int) 20);
        Entrenador entrenadorAtleti = new Entrenador("Mourinho", (int) 3);

        // Equipos predefinidos
        Equipo equipo1 = new Equipo("Barça", "Camp Nou", entrenadorBarsa);
        Equipo equipo2 = new Equipo("Atleti", "Calderon", entrenadorAtleti);

        // Ligas predefinidas
        Liga liga1 = new Liga("LFP", 1, "Burdel La Jamelga");
        Liga liga2 = new Liga("2a Division", 2, "Apple");

        //Caracteristicas de los jugadores

        Caracteristicas carJugador1 = new Caracteristicas(6, 3, 6, 6, 6);
        Caracteristicas carJugador2 = new Caracteristicas(4, 4, 2, 1, 9);

        jugador1.setCaracteristicasJugador(carJugador1);
        jugador2.setCaracteristicasJugador(carJugador2);
        jugador3.setCaracteristicasJugador(carJugador1);
        jugador4.setCaracteristicasJugador(carJugador2);
        jugador5.setCaracteristicasJugador(carJugador1);

        jugador1.getCaracteristicasJugador().setFuerza(6);
        jugador2.getCaracteristicasJugador().setFuerza(6);

        // Anyadimos los jugadores a equipos

        equipo1.anyadirJugador(jugador1);
        equipo1.anyadirJugador(jugador2);

        equipo1.anyadirJugador(jugador3);
        equipo2.anyadirJugador(jugador4);
        equipo2.anyadirJugador(jugador5);

        // Anyadimos los equipos a ligas

        liga1.anyadirEqupio(equipo1);
        liga1.anyadirEqupio(equipo2);

        database.store(liga1);
        database.commit();

    }

    // Otros metodos que no he implementado en el menú

    public static void cambiarPatrocinadorLiga(ObjectContainer database)  {

        // Teclado
        Scanner teclat = new Scanner(System.in);

        // Pedimos el nombre del equipo y del nuevo patrocinador
        System.out.println("Introduce el nombre del equipo al que le quieres cambiar el patrocinador (String)");
        String nombreEquipo = teclat.nextLine();

        System.out.println("Introduce el nombre del nuevo patrocinador (String)");
        String nombrePatrocinador = teclat.nextLine();

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
                break;
            }
        }

        database.store(equipoIteracion);
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

    public static void jugadoresDeDosEquipos(ObjectContainer database) {

        // Imprime los jugadores de dos equipos

        Scanner teclat = new Scanner(System.in);

        // Introducimos los dos equipos
        System.out.println("Nombre equipo 1 (String)");
        String equipo1 = teclat.nextLine();
        System.out.println("Nombre equipo 2 (String)");
        String equipo2 = teclat.nextLine();

        // Mostramos los jugadores
        imprimirJugadoresDeEquipo(database, equipo1);
        imprimirJugadoresDeEquipo(database, equipo2);
    }

}