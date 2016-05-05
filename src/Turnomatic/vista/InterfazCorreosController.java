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
    private Button botonNueva;
    @FXML
    private Button botonTurno;

    @FXML
    private Label turnoActual;

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

    //Meto las mesas en un array
    ObservableList<GridPane> mesas = FXCollections.observableArrayList(fila1,fila2,fila3,fila4,fila5);

    @FXML
    private Turno turno;


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


        //funcionalidad de los botones
        //botonTurno.setOnAction(this::handlePedirTurno);

    }

    /**
     * Es llamado por el main para obtener una referencia de si misma.
     *
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Añade los datos de las ObservableList a la tabla y el combobox
        turnoTable.setItems(mainApp.getColaData());
        ServiciosBox.setItems(mainApp.getServicios());
    }

    /**
     * Método que se encarga de dar turno
     */


    @FXML
    private void handlePedirTurno(ActionEvent event){
        if(ServiciosBox.getValue() != null && ServiciosBox.getValue().length() != 0) {
            turno=new Turno(ServiciosBox.getValue());
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
            //Prepara el ComboBox
            ServiciosBox.setPromptText("Elige un servicio");
            ConfigWindowController temp = new ConfigWindowController();
            for(String aux: temp.getServicios()) {
                mainApp.setServicios(aux);
            }
            //Poner nombre del empleado a las mesas
            for(String aux: temp.getServicios()) {
                mainApp.setServicios(aux);
            }
            //Activar las mesas

            for(int i=0;i<temp.getEmpleados().size();i++) {
               mesas.get(i).setDisable(false);
            }
            System.out.println(mesas.size());
        }


    }




}