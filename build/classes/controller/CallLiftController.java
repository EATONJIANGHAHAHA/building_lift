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
public class CallLiftController extends Controller<Building> {
    @FXML private TextField levelTf;
    @FXML private Text ExceptionTxt;
    @FXML private ComboBox<Person> personCb;
    
    @FXML public final Building getBuilding() { return model; }
    @FXML public void initialize(){
        ExceptionTxt.setText("");
    }
    
    @FXML private void handleCancel() throws Exception{
        stage.close();
    }
    
    private int getLevel() throws NoContentException,UnacceptedInputException, NoSuchLiftException{
        String s="";
        s=levelTf.getText();
        if(s.equals(""))
            throw new NoContentException();
        else if(s.equals("0")||s.equals("7")||s.equals("8")||s.equals("9"))
            throw new NoSuchLiftException();
        else if(s.equals("1")||s.equals("1")||s.equals("2")||s.equals("3")||s.equals("4")||s.equals("5")||s.equals("6"))
            return Integer.parseInt(s);
        else 
            throw new UnacceptedInputException();
        //return Integer.parseInt(s);
    }
    
    private Person getPerson() throws NoSelectedPersonException{
        Person PError;
        PError = personCb.getSelectionModel().getSelectedItem();
        if(PError==null)
            throw new NoSelectedPersonException();
        return PError;
    }
    
    @FXML private void handleCall() throws Exception{
        try{
        model.call(getPerson(),getLevel());
        stage.close();//try
        }catch(NoSelectedPersonException e){
            ExceptionTxt.setText("You must select a caller");
        }catch(NoContentException e){
            ExceptionTxt.setText("You must type the Destination");
        }catch(NumberFormatException e){
            ExceptionTxt.setText("Destination must be an integer");
        }catch(NoSuchLiftException e){
            ExceptionTxt.setText("No suitable lift found");
        }catch(UnacceptedInputException e){
            ExceptionTxt.setText("Destination must be an integer");
        }
//catch ;
        
    }
}
