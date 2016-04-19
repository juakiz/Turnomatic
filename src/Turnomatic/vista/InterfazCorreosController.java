package Turnomatic.vista;

import Turnomatic.MainApp;
import Turnomatic.modelo.Turno;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public class InterfazCorreosController {
    @FXML
    private TableView<Turno> turnoTable;
    @FXML
    private TableColumn<Turno, String> numeroColumn;
    @FXML
    private TableColumn<Turno, String> servicioColumn;

    @FXML
    private Label numero;
    @FXML
    private Label servicio;


    // Reference to the main application.
    private MainApp mainApp;



    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        numeroColumn.setCellValueFactory(cellData -> cellData.getValue().numeroProperty());
        servicioColumn.setCellValueFactory(cellData -> cellData.getValue().servicioProperty());
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        turnoTable.setItems(mainApp.getColaData());
    }
}