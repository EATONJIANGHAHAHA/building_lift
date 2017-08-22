/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import au.edu.uts.ap.javafx.*;
import model.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
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
 *
 * @author 姜宜辰
 */
public class PersonController extends Controller<Building>{
    public final Building getBuilding() { return model; }
    
    @FXML private TableView<Person> peopleTv;
    @FXML private TableColumn<Person,String> idClm;
    @FXML private TableColumn<Person,String> nameClm;
    @FXML private TableColumn<Person,String> levelClm;
    @FXML private TableColumn<Person,String> destinationClm;
    @FXML private TableColumn<Person,String> aboardClm;
    @FXML private Button closeBtn;

    @FXML private void initialize() {
        idClm.setCellValueFactory(cellData -> cellData.getValue().idProperty().asString());
        nameClm.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        levelClm.setCellValueFactory(cellData -> cellData.getValue().levelProperty().asString("Level %d"));
        destinationClm.setCellValueFactory(cellData -> cellData.getValue().destinationProperty().asString("Level %d"));
        aboardClm.setCellValueFactory(cellData -> cellData.getValue().aboardStatusProperty());
        peopleTv.setColumnResizePolicy( TableView.CONSTRAINED_RESIZE_POLICY );
    }
    
    @FXML private void handleClose(ActionEvent event) throws Exception{
        stage.close();
    }
}