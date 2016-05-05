package Turnomatic.modelo;

/**
 * Created by juakiz on 6/04/16.
 */

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model turno.
 *
 */
public class Turno {

    private final StringProperty numero;
    private final StringProperty servicio;
    //Contador para autoincrementar el número de turno.
    private static int contador=1;

    /**
     * Constructor, getter y setter.
     *
     */
    public Turno(String servicio) {
        this.numero = new SimpleStringProperty(Integer.toString(contador));
        this.servicio = new SimpleStringProperty(servicio);
        //maneja el autoincremento del turno
        contador++;
    }

    public String getNumero() {
        return numero.get();
    }

    public StringProperty numeroProperty() {
        return numero;
    }

    public void setNumero() {
        this.numero.set(Integer.toString(contador));
        contador++;
    }

    public String getServicio() {
        return servicio.get();
    }

    public StringProperty servicioProperty() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio.set(servicio);
    }
}