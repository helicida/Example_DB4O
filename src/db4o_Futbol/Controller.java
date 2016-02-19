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
            System.out.println("1 - Consultar jugadores de un equipo");
            System.out.println("2 - Consultar Jugadores de dos equipos solicitados (SODA).");
            System.out.println("3 - Consultar jugadores de un equipo con Fuerza menor o igual que 5.");
            System.out.println("4 - Consultar Jugadores pertenecientes a una Liga.");
            System.out.println("5 - Consultar características de un jugador.");
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

                default:

                break;
            }
        }
    }
}
