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
public class LiftController extends Controller<Lift>{
     public final Lift getLift() { return model; }
     @FXML private Text liftStatusTxt;
     
     
     @FXML private void initialize(){
          
     }
     
     @FXML private void handleClose(ActionEvent event) throws Exception{
        stage.close();
    }
}
