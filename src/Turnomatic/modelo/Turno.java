package Turnomatic.modelo;

/**
 * Created by juakiz on 6/04/16.
 */

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model class for a Person.
 *
 * @author Marco Jakob
 */
public class Turno {

    private final StringProperty numero;
    private final StringProperty servicio;


    /**
     * Constructor with some initial data.
     *
     */
    public Turno(String numero, String servicio) {
        this.numero = new SimpleStringProperty(numero);
        this.servicio = new SimpleStringProperty(servicio);
    }

    public String getNumero() {
        return numero.get();
    }

    public StringProperty numeroProperty() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero.set(numero);
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