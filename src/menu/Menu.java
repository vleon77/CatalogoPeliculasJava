package menu;

import com.peliculas.negocio.*;

import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        int opcion;
        Scanner consola = new Scanner(System.in);
        ICatalogoPeliculas catalogoPeliculas = new CatalogoPeliculasImpl();
        System.out.println("Bienvenido, Elija una opción del Menu:");
        do {
            System.out.println("1 - Iniciar catálogo de películas");
            System.out.println("2 - Agregar película");
            System.out.println("3 - Listar películas");
            System.out.println("4 - Buscar película");
            System.out.println("0 - Salir");

            opcion = Integer.parseInt(consola.nextLine());

            switch (opcion) {
                case 1 -> catalogoPeliculas.iniciarArchivo();
                case 2 -> {
                    String nombrePelicula;
                    System.out.println("Intoduce el nombre de la pelicula");
                    nombrePelicula = consola.nextLine();
                    catalogoPeliculas.agregarPelicula(nombrePelicula);
                }
                case 3 -> catalogoPeliculas.listarPeliculas();
                case 4 -> {
                    String nombrePelicula;
                    System.out.println("Introduzca el nombre de la película que desea buscar: ");
                    nombrePelicula = consola.nextLine();
                    catalogoPeliculas.buscarPelicula( nombrePelicula);
                }
                case 0 -> System.out.println("Hasta Pronto!");

                default -> System.out.println("Opción no reconocida");
            }
        }while (opcion != 0);
    }
}
