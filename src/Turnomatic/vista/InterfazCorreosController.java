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

import java.io.*;
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


    //Contenedor de las mesas
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

    }

    /**
     * Es llamado por el main para obtener una referencia de si misma.
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
    private void handlePedirTurno(ActionEvent event) {
        if (ServiciosBox.getValue() != null && ServiciosBox.getValue().length() != 0) {
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
    private void handleNuevaOficina(ActionEvent event) {

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
            if (mainApp.getEmpleados().size() >= 1) empleado1.setText(mainApp.getEmpleados().get(0));
            else fila1.setVisible(false);
            if (mainApp.getEmpleados().size() >= 2) empleado2.setText(mainApp.getEmpleados().get(1));
            else fila2.setVisible(false);
            if (mainApp.getEmpleados().size() >= 3) empleado3.setText(mainApp.getEmpleados().get(2));
            else fila3.setVisible(false);
            if (mainApp.getEmpleados().size() >= 4) empleado4.setText(mainApp.getEmpleados().get(3));
            else fila4.setVisible(false);
            if (mainApp.getEmpleados().size() >= 5) empleado5.setText(mainApp.getEmpleados().get(4));
            else fila5.setVisible(false);

        }

    }

    /**
     * Controla los botones que liberan las mesas
     */
    @FXML
    private void handleLiberar(ActionEvent event) {

        //Controla que la cola no este vacia
        if (!mainApp.isEmptyTurnoColaData()) {

            //Establece la etiqueta del turno actual
            turnoActual.setText(mainApp.getTurnoColaData().getNumero());

            //Administra la solicitud de turno dependiendo del boton pulsado
            solicitaTurno(event,liberar1,turno1,mainApp,servicio1);
            solicitaTurno(event,liberar2,turno2,mainApp,servicio2);
            solicitaTurno(event,liberar3,turno3,mainApp,servicio3);
            solicitaTurno(event,liberar4,turno4,mainApp,servicio4);
            solicitaTurno(event,liberar5,turno5,mainApp,servicio5);

        } else {
            // Cola vacia
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Cola vacía");
            alert.setHeaderText(null);
            alert.setContentText("No quedan turnos, puedes ir a tomarte un café.");
            alert.showAndWait();
        }
    }

    //Administra la solicitud de turno dependiendo del boton pulsado
    public static void solicitaTurno(ActionEvent event, Button liberar, Label turno, MainApp mainApp, Label servicio){
        if (event.getSource() == liberar) {
            //Añade el turno al log
            try(BufferedWriter bw=new BufferedWriter(new FileWriter("/home/juakiz/log.txt",true))){
                bw.newLine();
                bw.write("Turno: "+mainApp.getTurnoColaData().getNumero()+
                        " - Servicio: "+mainApp.getTurnoColaData().getServicio());
            }catch(IOException e){
                System.out.println("Error E/S: "+e);
            }

            turno.setText(mainApp.getTurnoColaData().getNumero());
            servicio.setText(mainApp.getTurnoColaData().getServicio());
            mainApp.delTurnoColaData();

        }
    }

    /**
     * Controla los botones que bloquean las mesas
     */
    @FXML
    private void handleBloquear(ActionEvent event) {

        bloqueoMesa(event,bloquear1,turno1,servicio1,empleado1,liberar1);
        bloqueoMesa(event,bloquear2,turno2,servicio2,empleado2,liberar2);
        bloqueoMesa(event,bloquear3,turno3,servicio3,empleado3,liberar3);
        bloqueoMesa(event,bloquear4,turno4,servicio4,empleado4,liberar4);
        bloqueoMesa(event,bloquear5,turno5,servicio5,empleado5,liberar5);

    }


    //Administra el bloqueo de la mesa dependiendo del boton pulsado
    public static void bloqueoMesa(ActionEvent event, ToggleButton bloquear, Label turno, Label servicio,
                                   Label empleado, Button liberar) {

        if (event.getSource() == bloquear) {
            if (bloquear.isSelected()) {
                turno.setText("-");
                servicio.setText("-");
                turno.setDisable(true);
                servicio.setDisable(true);
                empleado.setDisable(true);
                liberar.setDisable(true);
            } else {
                turno.setDisable(false);
                servicio.setDisable(false);
                empleado.setDisable(false);
                liberar.setDisable(false);
            }
        }
    }

}