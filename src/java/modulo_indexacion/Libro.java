/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modulo_indexacion;

import java.util.ArrayList;
import modulo_persistencia.GestorPersistenciaGeneral;

/**
 *
 * @author filardo
 */
public class Libro implements Comparable {

    private int id;
    private String descripcion; //puede ser el nombre del archivo, el titulo, etc
    private String ruta; // la ruta del archivo desde donde lo cargamos
    private String encoding;
    public static final String UTF = "UTF-8";

    public Libro(String descripcion, String ruta) {
        this.id = -1;
        this.descripcion = descripcion;
        this.ruta = ruta;
        this.encoding = UTF;
    }

    public Libro(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
        this.encoding = UTF;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void agregarPalabra() {
    } //agrega una palabra a la etructura de datos, en caso de estar repetida, busca la palabra y
    //le suma a la misma la frecuencia

    public Palabra[] mostrarPalabras() {
        return null;
    } //muestra todas las palabras del archivo

    //Agregar
    @Override
    public int compareTo(Object l) {
        Libro li = (Libro) l;
        return this.getDescripcion().compareTo(li.getDescripcion());
    }

    public Boolean comprobarExistencia() {
        GestorPersistenciaGeneral gpg = new GestorPersistenciaGeneral();
        return gpg.comprobarLibro(descripcion);
    }

    /**
     * Módulo del libro this. 
     * Se calcula con la raíz cuadrada de la sumatoria de los
     * cuadrados de la frecuencia de cada palabra del libro por
     * su frecuencia inversa.
     * @param al ArrayList de las palabras del Libro
     * @param N La cantidad de libros de la base
     * @return 
     */
    double calcularModulo(ArrayList<Palabra> al, int N) {

        double acum = 0;

        for (Palabra p : al) {

            int tf = p.getFrecuencia();
            double idf = p.calcularFrecuenciaInversa(N);

            acum += Math.pow((double) tf * idf, 2);
        }
        return Math.sqrt(acum);
    }

}
