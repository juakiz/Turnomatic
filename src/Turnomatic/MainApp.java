package Turnomatic;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.prefs.Preferences;

import Turnomatic.modelo.Turno;
import Turnomatic.vista.ConfigWindowController;
import Turnomatic.vista.InterfazCorreosController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    /**
     * Los datos como listas observables
     */
    private ObservableList<Turno> colaData = FXCollections.observableArrayList();
    private ObservableList<String> servicios = FXCollections.observableArrayList();
    private ObservableList<String> empleados = FXCollections.observableArrayList();
    private static ArrayList<String> log=new ArrayList<>();

    /**
     * Constructor que añade datos de testeo iniciales
     *
    public MainApp() {
        //Add some sample data
        colaData.add(new Turno("Pagar"));
        colaData.add(new Turno("Enviar"));
        colaData.add(new Turno("Recibir"));
        servicios.add("Pagar");
        servicios.add("Enviar");
        servicios.add("Recibir");


    }

    /**
     * Devuelve datos como observable list.
     */
    public ObservableList<Turno> getColaData() {
        return colaData;
    }
    public ObservableList<String> getServicios() {
        return servicios;
    }
    public ObservableList<String> getEmpleados() {
        return empleados;
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Turnomatic");

        initRootLayout();

        muestraInterfazCorreos();
    }

    /**
     * Inicializa el root layout.
     */
    public void initRootLayout() {
        try {
            // Lo carga del fxml.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("vista/RootLayout.fxml"));
            rootLayout = loader.load();

            // Muestra la escena conteniendo el root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Muestra la InterfazCorreos dentro de la root layout.
     */
    public void muestraInterfazCorreos() {
        try {
            // Carga InterfazCorreos.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("vista/InterfazCorreos.fxml"));
            AnchorPane personOverview = loader.load();

            // Establece InterfazCorreos en el centro de root layout.
            rootLayout.setCenter(personOverview);

            // Da al controlador acceso al main.
            InterfazCorreosController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodos para agregar y eliminar turnos a la tabla y agregarlos a las mesas
     */
    public Turno getTurnoColaData (){
        return colaData.get(0);
    }
    public void setTurnoColaData (Turno turno){
        colaData.add(turno);
    }
    public void delTurnoColaData (){
        colaData.remove(0);
    }
    public boolean isEmptyTurnoColaData(){
        if(colaData.size()<=0) {
            return true;
        } else {
            return false;
        }
    }

    //public void setServicios (String servicios){ this.servicios.add(servicios);}

    /**
     * Devuelve el Stage principal
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Abre una pantalla emergente para que el usuario rellene la configuración de la oficina.
     * Devuelve true si le da a OK.
     *
     */
    public boolean muestraConfigWindow() {
        try {
            //Carga el archivo fxml y crea una nueva Stage para la pantalla emergente.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("vista/ConfigWindow.fxml"));
            AnchorPane page = loader.load();

            // Crea el dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Configuracion de Nueva Oficina");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            //Establece los servicios en el controlador
            ConfigWindowController controller = loader.getController();
            controller.setConfigWindow(dialogStage);

            // Muestra el dialogo y espera a q el usuario lo cierre
            dialogStage.showAndWait();


            servicios.addAll(controller.getServicios());
            empleados.addAll(controller.getEmpleados());


            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

