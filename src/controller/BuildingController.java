package controller;

import au.edu.uts.ap.javafx.*;
import model.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import java.text.DecimalFormat;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
/**
 * The controller for building.fxml.
 */
public class BuildingController extends Controller<Building> {
    //@FXML private ListView<Lift> liftsLv;
    @FXML private Button callLiftBt;
    @FXML private Button viewLiftBt;
    @FXML private Button peopleBt;
    @FXML private Button exitBt;
    @FXML private Slider delaySl;
    @FXML private Text timeTxt;
    @FXML private TableColumn<Lift,String> numberTc;
    @FXML private TableColumn<Lift,String> levelTc;//~~~~
    @FXML private TableColumn<Lift,String> PassengersTc;
    @FXML private TableColumn<Lift,String> WaitingTc;
    @FXML private TableView<Lift> liftsTv;

    @FXML public final Building getBuilding() { return model; }

    @FXML private void initialize() {
        // Start up the building. Don't forget to also shutdown the building
        // when the user clicks the "Exit" button.
        getBuilding().startup();
        liftsTv.getSelectionModel().selectedItemProperty().addListener( 
                (observable, oldLift, newLift)->viewLiftBt.setDisable(newLift==null));
        delaySl.valueProperty().addListener((o,oldV,newV) -> model.setDelay(getDelay()));
        levelTc.setCellValueFactory(celldata -> celldata.getValue().levelVisualProperty());
        numberTc.setCellValueFactory(cellData -> cellData.getValue().NumberProperty().asString("Lift %d"));
        PassengersTc.setCellValueFactory(cellData -> cellData.getValue().PassengersSizeProperty().asString("%d"));
        WaitingTc.setCellValueFactory(cellData -> cellData.getValue().QueueSizeProperty().asString("%d"));
        levelTc.setCellFactory(column -> {
            return new TableCell<Lift,String>(){
                @Override
                protected void updateItem(String item,boolean empty){
                    super.updateItem(item,empty);
                    if(item!=null||empty){
                        setStyle("-fx-font-family:monospace");
                    }
                    setText(item);
                }
            };
        });
        liftsTv.setColumnResizePolicy( TableView.CONSTRAINED_RESIZE_POLICY );
    }
    
    private int getDelay() {
        return (int)delaySl.getValue();
    }
    
    private Lift getSelectedLift(){
        return liftsTv.getSelectionModel().getSelectedItem();
    }
    
    /**
     * This accessor method returns the selected number on the delay slider.
     *
     * @return the the selected delay
     */
    
    @FXML private void handleViewLift(ActionEvent event) throws Exception{
        ViewLoader.showStage(getSelectedLift(),"/view/lift.fxml","Lift",new Stage());
    }
        
    @FXML private void handleExit(ActionEvent event) throws Exception{
        getBuilding().shutdown();
        stage.close();
    }
    
    @FXML private void handlePeople(ActionEvent event) throws Exception{
        ViewLoader.showStage(getBuilding(),"/view/people.fxml","Person",new Stage());}
    
    @FXML private void handleCallLift(ActionEvent event) throws Exception{
        ViewLoader.showStage(getBuilding(),"/view/call_lift.fxml","Call Lift",new Stage());
    }

}
