/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomeNode;

import java.awt.Rectangle;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import au.edu.uts.ap.javafx.*;
import java.awt.Rectangle;
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
import javafx.scene.Node.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 *
 * @author 姜宜辰
 */


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class SpecialLiftNode extends HBox{
    
    Rectangle display;
    Paint paint;
    
    private Label label;
    private HBox[] hbox;
    
    public SpecialLiftNode(String text){
        setId("rectangle-item");
        initComponents();
    }
    
    public void initComponents(){
        display=new Rectangle(50,50);
        StackPane localStackPane = new StackPane();
            localStackPane.getStyleClass().add("container");
            Rectangle bgRect = new Rectangle();
            //bgRect.setFill(Color.TRANSPARENT);
            //bgRect.widthProperty().bind(rect.widthProperty().add(20.0d));
            //bgRect.heightProperty().bind(rect.heightProperty().add(20.0d));
            //localStackPane.getChildren().addAll(bgRect, rect);
            getChildren().add(localStackPane);
    }
}



/**
 *
 * @author 姜宜辰
 */

