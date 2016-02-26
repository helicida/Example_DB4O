package db4o_Futbol;

import java.util.Scanner;

/**
 * Created by 46465442z on 19/02/16.
 */
public class Controller {

    public static void main(String[] args) {

        //Controler que gestiona la BBDD

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

                break;

                case 4:

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
}
