package com.peliculas.negocio;

import com.peliculas.datos.*;
import com.peliculas.domain.Pelicula;
import com.peliculas.excepciones.AccesoDatosEx;
import com.peliculas.excepciones.LecturaDatosEx;

public class CatalogoPeliculasImpl implements ICatalogoPeliculas {
    private final  IAccesoDatos datos;

    public CatalogoPeliculasImpl(){
        this.datos = new AccesoDatosImpl();
    }

    @Override
    public void agregarPelicula(String nombrePelicula) {
        Pelicula pelicula = new Pelicula(nombrePelicula);
         boolean anexar;
        try {
            anexar =datos.existe(NOMBRE_RECURSO);
            datos.escribir(pelicula,NOMBRE_RECURSO,anexar);
        } catch (AccesoDatosEx accesoDatosEx) {
            System.out.println("Error de Acceso de datos");
            accesoDatosEx.printStackTrace();
        }
    }

    @Override
    public void listarPeliculas() {
        try {
            var peliculas = this.datos.listar(NOMBRE_RECURSO);
            for (var pelicula: peliculas){
                System.out.println(pelicula);

            }
        } catch (LecturaDatosEx lecturaDatosEx) {
            System.out.println("Error al leer los datos");
            lecturaDatosEx.printStackTrace();
        }
    }

    @Override
    public void buscarPelicula(String buscar) {
        String resultado = null;
        try {
            resultado = datos.buscar(NOMBRE_RECURSO,buscar);
        } catch (LecturaDatosEx lecturaDatosEx) {
            System.out.println("Error al leer los datos");
            lecturaDatosEx.printStackTrace();
        }
        System.out.println("resultado = " + resultado);
    }

    @Override
    public void iniciarArchivo() {
        try {
            if(this.datos.existe(NOMBRE_RECURSO)){
                datos.borrar(NOMBRE_RECURSO);
            }
            datos.crear(NOMBRE_RECURSO);
        } catch (AccesoDatosEx accesoDatosEx) {
            System.out.println("Error al iniciar el catálogo de películas");
            accesoDatosEx.printStackTrace();
        }
    }
}
