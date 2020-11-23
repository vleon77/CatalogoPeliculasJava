package com.peliculas.datos;

import com.peliculas.domain.Pelicula;
import com.peliculas.excepciones.*;


import java.io.*;
import java.util.*;

public class  AccesoDatosImpl implements IAccesoDatos {
    @Override
    public boolean existe(String nombreRecurso) throws AccesoDatosEx {
        File archivo = new File(nombreRecurso);
        return archivo.exists();
    }

    @Override
    public  List<Pelicula> listar(String nombreRecurso) throws LecturaDatosEx {
        List<Pelicula> peliculas =  new ArrayList<>();
        File archivo = new File(nombreRecurso);
        try {
            BufferedReader entrada = new BufferedReader(new FileReader(archivo));
            var lectura = entrada.readLine();
            while(lectura != null){
                Pelicula pelicula = new Pelicula(lectura);
                peliculas.add(pelicula);
                lectura = entrada.readLine();
            }
            entrada.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new LecturaDatosEx("Excepción al listar peliculas " + e.getMessage() );
        } catch (IOException e) {
            e.printStackTrace();
            throw new LecturaDatosEx("Excepción al listar peliculas " + e.getMessage() );

        }
        return peliculas;
    }

    @Override
    public void escribir(Pelicula pelicula, String nombreArchivo, boolean anexar) throws EscrituraDatosEx {
        File archivo = new File(nombreArchivo);
        try {
           PrintWriter salida= new PrintWriter(new FileWriter(archivo, anexar));
           salida.println(pelicula.getNombre());
           salida.close();
            System.out.println("Se ha escrito información al archivo: " + pelicula.getNombre());
        } catch (IOException e) {
            e.printStackTrace();
            throw new EscrituraDatosEx("Excepción al escribir pelicula" + e.getMessage() );
        }
    }

    @Override
    public String buscar(String nombreArchivo, String buscar) throws LecturaDatosEx  {
        File archivo = new File(nombreArchivo);
        String resultado = null;
        try {
            BufferedReader entrada = new BufferedReader( new FileReader(archivo));
            String lectura = entrada.readLine();
            int indice = 1;
            while (lectura != null){
                if(buscar != null && buscar.equalsIgnoreCase(lectura)){
                    resultado = "Pelicula " + lectura + " encontrada en el indice " + indice;
                    break;
                }
                lectura = entrada.readLine();
                indice ++;

            }
            entrada.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new LecturaDatosEx("Excepción al buscar pelicula" + e.getMessage() );
        } catch (IOException e) {
            e.printStackTrace();
            throw new LecturaDatosEx("Excepción al buscar pelicula" + e.getMessage() );
        }
        return resultado;
    }

    @Override
    public void crear(String nombreArchivo) throws AccesoDatosEx {
        File archivo = new File(nombreArchivo);
        try {
            PrintWriter salida = new PrintWriter(new FileWriter(archivo));
            salida.close();
            System.out.println("Se ha creado correctamente el fichero");
        } catch (IOException e) {
            e.printStackTrace();
            throw new AccesoDatosEx("Excepción al crear archivo" + e.getMessage());
        }
    }

    @Override
    public void borrar(String nombreRecurso) {
        File archivo = new File(nombreRecurso);
        if(archivo.exists()){
            archivo.delete();
            System.out.println("Se ha borrado el archivo");
        }
    }
}
