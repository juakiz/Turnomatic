package Turnomatic.modelo;

import java.util.ArrayList;

/**
 * Created by juakiz on 3/05/16.
 */
public class Config {

    ArrayList<String> empleados = new ArrayList<>();
    ArrayList<String> servicios = new ArrayList<>();


    public ArrayList<String> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(ArrayList<String> empleados) { this.empleados = empleados; }

    public ArrayList<String> getServicios() {
        return servicios;
    }

    public void setServicios(ArrayList<String> servicios) {
        this.servicios = servicios;
    }
}
