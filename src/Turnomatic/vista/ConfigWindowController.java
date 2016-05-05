package Turnomatic.vista;

        import Turnomatic.modelo.Config;
        import javafx.fxml.FXML;
        import javafx.scene.control.Alert;
        import javafx.scene.control.TextField;
        import javafx.stage.Stage;

        import java.util.ArrayList;

/**
 * Pantalla de dialogo que edita la oficina.
 *
 */
public class ConfigWindowController {

    @FXML
    private TextField mesa1;
    @FXML
    private TextField mesa2;
    @FXML
    private TextField mesa3;
    @FXML
    private TextField mesa4;
    @FXML
    private TextField mesa5;
    @FXML
    private TextField servicio1;
    @FXML
    private TextField servicio2;
    @FXML
    private TextField servicio3;
    @FXML
    private TextField servicio4;
    @FXML
    private TextField servicio5;

    private Stage configWindow;

    private boolean okClicked = false;

/*
    //Instancio la clase Config para ajustar la configuración de la oficina.
    Config oficina=new Config();

    public ArrayList<String> getServicios() {
        return oficina.getServicios();
    }
*/
    ArrayList<String> empleados = new ArrayList<>();
    ArrayList<String> servicios = new ArrayList<>();

    public ArrayList<String> getEmpleados() {
        return empleados;
    }

    public ArrayList<String> getServicios() {
        return servicios;
    }

    /**
     * Esto inicializa la clase controlador, todos los metodos dentro ajustan la vista inicial.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Establece el Stage de esta ventana.
     *
     */
    public void setConfigWindow(Stage stage) {
        this.configWindow = stage;
    }

    /**
     * Co una booleana se controla si dio ok o no..
     *
     */

    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Es llamado cuando le das a aceptar.
     */

    @FXML
    private void handleAceptar() {

            if (mesa1.getText() != null && mesa1.getText().length() != 0) {
                empleados.add(mesa1.getText());
            }
            if (mesa2.getText() != null && mesa2.getText().length() != 0) {
                empleados.add(mesa2.getText());
            }
            if (mesa3.getText() != null && mesa3.getText().length() != 0) {
                empleados.add(mesa3.getText());
            }
            if (mesa4.getText() != null && mesa4.getText().length() != 0) {
                empleados.add(mesa4.getText());
            }
            if (mesa5.getText() != null && mesa5.getText().length() != 0) {
                empleados.add(mesa5.getText());
            }
            if (servicio1.getText() != null && servicio1.getText().length() != 0) {
                servicios.add(servicio1.getText());
            }
            if (servicio2.getText() != null && servicio2.getText().length() != 0) {
                servicios.add(servicio2.getText());
            }
            if (servicio3.getText() != null && servicio3.getText().length() != 0) {
                servicios.add(servicio3.getText());
            }
            if (servicio4.getText() != null && servicio4.getText().length() != 0) {
                servicios.add(servicio4.getText());
            }
            if (servicio5.getText() != null && servicio5.getText().length() != 0) {
                servicios.add(servicio5.getText());
            }


        if (empleados.size() != 0 && servicios.size() != 0) {

            okClicked = true;
            configWindow.close();

        } else {
            // No se eligieron empleados y/o servicios.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ninguna seleccion");
            alert.setHeaderText(null);
            alert.setContentText("Has de seleccionar al menos un empleado y un servicio.");
            alert.showAndWait();

        }

    }

    /**
     * Es llamado cuando le da a cancelar.
     */
    @FXML
    private void handleCancelar() {
        configWindow.close();
    }

}