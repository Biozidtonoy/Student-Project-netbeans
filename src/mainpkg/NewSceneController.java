/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package mainpkg;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author tonoy
 */
public class NewSceneController implements Initializable {

    @FXML
    private TextField newSceneIDTxtField;
    
    ArrayList<Integer> StudentId = new ArrayList<>();
    Alert invalidInput = new Alert(Alert.AlertType.WARNING,"please input number !!!!");
    Alert incorrect = new Alert(Alert.AlertType.WARNING,"invalid ID !!!!");

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void backButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("studentViewScene.fxml"));
        Parent parent = loader.load();

        
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();


        Scene nextScene = new Scene(parent);

        currentStage.setScene(nextScene);
        currentStage.show();
    }

    @FXML
    private void addButton(ActionEvent event) {
        try{
           int enteredId = Integer.parseInt(newSceneIDTxtField.getText());
         ArrayList<Integer> StudentId = StudentViewSceneController.StudentId;
         if (StudentId.contains(enteredId)){
             
         }else{
             incorrect.show();
         }
        }catch(NumberFormatException e) {
            invalidInput.show();
            return;
        }
            
        
        
        
    }
    
}
