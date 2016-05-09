package Turnomatic.vista;

import Turnomatic.MainApp;
import Turnomatic.modelo.Turno;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import org.omg.CORBA.SystemException;

import java.io.IOException;
import java.util.ArrayList;


public class InterfazCorreosController {
    @FXML
    private TableView<Turno> turnoTable;
    @FXML
    private TableColumn<Turno, String> numeroColumn;
    @FXML
    private TableColumn<Turno, String> servicioColumn;

    @FXML
    private ComboBox<String> ServiciosBox;

    @FXML
    private ObservableList<String> empleados;

    @FXML
    private Button botonNueva;
    @FXML
    private Button botonGuardar;

    @FXML
    private Label turnoActual;

    //Botones de liberar mesas
    @FXML
    private Button liberar1;
    @FXML
    private Button liberar2;
    @FXML
    private Button liberar3;
    @FXML
    private Button liberar4;
    @FXML
    private Button liberar5;

    //Botones de bloquear mesas
    @FXML
    private ToggleButton bloquear1;
    @FXML
    private ToggleButton bloquear2;
    @FXML
    private ToggleButton bloquear3;
    @FXML
    private ToggleButton bloquear4;
    @FXML
    private ToggleButton bloquear5;

    //Empleados
    @FXML
    private Label empleado1;
    @FXML
    private Label empleado2;
    @FXML
    private Label empleado3;
    @FXML
    private Label empleado4;
    @FXML
    private Label empleado5;


    //Mesas
    @FXML
    private GridPane fila1;
    @FXML
    private GridPane fila2;
    @FXML
    private GridPane fila3;
    @FXML
    private GridPane fila4;
    @FXML
    private GridPane fila5;

    //Etiquetas de turnos y servicios que estan siendo atendidos
    @FXML
    private Label turno1;
    @FXML
    private Label turno2;
    @FXML
    private Label turno3;
    @FXML
    private Label turno4;
    @FXML
    private Label turno5;

    @FXML
    private Label servicio1;
    @FXML
    private Label servicio2;
    @FXML
    private Label servicio3;
    @FXML
    private Label servicio4;
    @FXML
    private Label servicio5;

    // Referencia al main.
    private MainApp mainApp;



    /**
     * Esto inicializa la clase controlador, todos los metodos dentro ajustan la vista inicial.
     */
    @FXML
    private void initialize() {
        // Inicializa la tabla de la cola con dos columnas
        numeroColumn.setCellValueFactory(cellData -> cellData.getValue().numeroProperty());
        servicioColumn.setCellValueFactory(cellData -> cellData.getValue().servicioProperty());

        //Inicializa los empleados por defecto


    }

    /**
     * Es llamado por el main para obtener una referencia de si misma.
     *
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Añade los datos de las ObservableList a la Tabla y el ComboBox
        turnoTable.setItems(mainApp.getColaData());
        ServiciosBox.setItems(mainApp.getServicios());
    }

    /**
     * Método que se encarga de dar turno
     */


    @FXML
    private void handlePedirTurno(ActionEvent event){
        if(ServiciosBox.getValue() != null && ServiciosBox.getValue().length() != 0) {
            Turno turno = new Turno(ServiciosBox.getValue());
            mainApp.setTurnoColaData(turno);
        } else {
            // Ninguna seleccion.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Elige un servicio");
            alert.setHeaderText(null);
            alert.setContentText("Has de elegir algún servicio.");
            alert.showAndWait();
        }



    }

    /**
     * Es llamado cuando pinchas en nueva oficina.
     */
    @FXML
    private void handleNuevaOficina(ActionEvent event){

        boolean okClicked = mainApp.muestraConfigWindow();
        if (okClicked) {
            //Desactiva el boton nueva oficina
            botonNueva.setDisable(true);
            botonGuardar.setDisable(false);

            //Prepara el ComboBox
            ServiciosBox.setPromptText("Elige un servicio");

            //Activa las mesas, hace invisibles las vacias y establece los nombres de los empleados

            fila1.setDisable(false);
            fila2.setDisable(false);
            fila3.setDisable(false);
            fila4.setDisable(false);
            fila5.setDisable(false);
            if (mainApp.getEmpleados().size()>=1) empleado1.setText(mainApp.getEmpleados().get(0)); else fila1.setVisible(false);
            if (mainApp.getEmpleados().size()>=2) empleado2.setText(mainApp.getEmpleados().get(1)); else fila2.setVisible(false);
            if (mainApp.getEmpleados().size()>=3) empleado3.setText(mainApp.getEmpleados().get(2)); else fila3.setVisible(false);
            if (mainApp.getEmpleados().size()>=4) empleado4.setText(mainApp.getEmpleados().get(3)); else fila4.setVisible(false);
            if (mainApp.getEmpleados().size()>=5) empleado5.setText(mainApp.getEmpleados().get(4)); else fila5.setVisible(false);

            /*
            switch(mainApp.getEmpleados().size()){
                case 5:
                    empleado5.setText(mainApp.getEmpleados().get(4));
                    fila5.setVisible(true);
                case 4:
                    empleado4.setText(mainApp.getEmpleados().get(3));
                    fila4.setVisible(true);
                case 3:
                    empleado3.setText(mainApp.getEmpleados().get(2));
                    fila3.setVisible(true);
                case 2:
                    empleado2.setText(mainApp.getEmpleados().get(1));
                    fila2.setVisible(true);
                case 1:
                    empleado1.setText(mainApp.getEmpleados().get(0));
                    fila1.setVisible(true);
            }
            */
        }

    }

    /**
     * Controla los botones que liberan las mesas
     */
    @FXML
    private void handleLiberar(ActionEvent event){

        if(!mainApp.isEmptyTurnoColaData()) {

            //Establece la etiqueta del turno actual
            turnoActual.setText(mainApp.getTurnoColaData().getNumero());

            //Administra la solicitud de turno dependiendo del boton pulsado
            if (event.getSource() == liberar1) {
                turno1.setText(mainApp.getTurnoColaData().getNumero());
                servicio1.setText(mainApp.getTurnoColaData().getServicio());
                mainApp.delTurnoColaData();
            }

            if (event.getSource() == liberar2) {
                turno2.setText(mainApp.getTurnoColaData().getNumero());
                servicio2.setText(mainApp.getTurnoColaData().getServicio());
                mainApp.delTurnoColaData();
            }

            if (event.getSource() == liberar3) {
                turno3.setText(mainApp.getTurnoColaData().getNumero());
                servicio3.setText(mainApp.getTurnoColaData().getServicio());
                mainApp.delTurnoColaData();
            }

            if (event.getSource() == liberar4) {
                turno4.setText(mainApp.getTurnoColaData().getNumero());
                servicio4.setText(mainApp.getTurnoColaData().getServicio());
                mainApp.delTurnoColaData();
            }

            if (event.getSource() == liberar5) {
                turno5.setText(mainApp.getTurnoColaData().getNumero());
                servicio5.setText(mainApp.getTurnoColaData().getServicio());
                mainApp.delTurnoColaData();
            }
        } else {
            // Cola vacia.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Cola vacía");
            alert.setHeaderText(null);
            alert.setContentText("No quedan turnos, puedes ir a tomarte un café.");
            alert.showAndWait();
        }
    }

    /**
     * Controla los botones que bloquean las mesas
     */
    @FXML
    private void handleBloquear(ActionEvent event) {

        //Administra el bloqueo de la mesa dependiendo del boton pulsado
        if (event.getSource() == bloquear1) {
            if(bloquear1.isSelected()){
                turno1.setText("-");
                servicio1.setText("-");
                turno1.setDisable(true);
                servicio1.setDisable(true);
                empleado1.setDisable(true);
                liberar1.setDisable(true);
            } else {
                turno1.setDisable(false);
                servicio1.setDisable(false);
                empleado1.setDisable(false);
                liberar1.setDisable(false);
            }
        }

        if (event.getSource() == bloquear2) {
            if(bloquear2.isSelected()){
                turno2.setText("-");
                servicio2.setText("-");
                turno2.setDisable(true);
                servicio2.setDisable(true);
                empleado2.setDisable(true);
                liberar2.setDisable(true);
            } else {
                turno2.setDisable(false);
                servicio2.setDisable(false);
                empleado2.setDisable(false);
                liberar2.setDisable(false);
            }
        }

        if (event.getSource() == bloquear3) {
            if(bloquear3.isSelected()){
                turno3.setText("-");
                servicio3.setText("-");
                turno3.setDisable(true);
                servicio3.setDisable(true);
                empleado3.setDisable(true);
                liberar3.setDisable(true);
            } else {
                turno3.setDisable(false);
                servicio3.setDisable(false);
                empleado3.setDisable(false);
                liberar3.setDisable(false);
            }
        }

        if (event.getSource() == bloquear4) {
            if(bloquear4.isSelected()){
                turno4.setText("-");
                servicio4.setText("-");
                turno4.setDisable(true);
                servicio4.setDisable(true);
                empleado4.setDisable(true);
                liberar4.setDisable(true);
            } else {
                turno4.setDisable(false);
                servicio4.setDisable(false);
                empleado4.setDisable(false);
                liberar4.setDisable(false);
            }
        }

        if (event.getSource() == bloquear5) {
            if(bloquear5.isSelected()){
                turno5.setText("-");
                servicio5.setText("-");
                turno5.setDisable(true);
                servicio5.setDisable(true);
                empleado5.setDisable(true);
                liberar5.setDisable(true);
            } else {
                turno5.setDisable(false);
                servicio5.setDisable(false);
                empleado5.setDisable(false);
                liberar5.setDisable(false);
            }
        }

    }

}