/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package mainpkg;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author tonoy
 */
public class StudentViewSceneController implements Initializable {

    @FXML
    private TextField nameTextField;
    @FXML
    private TextField idTextField;
    @FXML
    private TextField cgpaTextField;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private ComboBox<String> MajorCombobox;
    @FXML
    private RadioButton MaleRadioButton;
    @FXML
    private RadioButton femaleRadioButton;
    @FXML
    private RadioButton OtherRadioButton;
    @FXML
    private TableView<student> tableViewTextField;
    @FXML
    private TableColumn<student,String> idTableColumn;
    @FXML
    private TableColumn<student,String> nameTableColumn;
    @FXML
    private TableColumn<student,String> genderTableColumn;
    @FXML
    private TableColumn<student,String> universityTableColumn;
    @FXML
    private TableColumn<student,String> majorTableColumn;
    @FXML
    private TableColumn<student,String> cgpaTableColumn;
    @FXML
    private TableColumn<student,String> passwordTableColumn;
    @FXML
    private ComboBox<Integer> searchIDCombo;
    @FXML
    private Label detailsLabel;

    ArrayList<student> studentList;
    public static ArrayList<Integer> StudentId = new ArrayList<>();
    ToggleGroup tg;
    Alert unfilled = new Alert(Alert.AlertType.WARNING,"Please select Everything!!!!");
    Alert success = new Alert(Alert.AlertType.INFORMATION,"Add Successfully");
    Alert invalidInput = new Alert(Alert.AlertType.WARNING,"please input number !!!!");
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tg = new ToggleGroup();
        MaleRadioButton.setToggleGroup(tg);
        femaleRadioButton.setToggleGroup(tg);
        OtherRadioButton.setToggleGroup(tg);
        
        MajorCombobox.getItems().addAll("CSE","EEE","MIS","HRM");
        
        studentList = new ArrayList<>();
        nameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        idTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        genderTableColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        passwordTableColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        majorTableColumn.setCellValueFactory(new PropertyValueFactory<>("major"));
        cgpaTableColumn.setCellValueFactory(new PropertyValueFactory<>("cgpa"));
        universityTableColumn.setCellValueFactory(new PropertyValueFactory<>("uniName"));
        
    }    

    @FXML
    private void addStudentButton(ActionEvent event) {
        String gender = "";
        if(MaleRadioButton.isSelected())
            gender = "Male";
        else if(femaleRadioButton.isSelected())
            gender = "Female";
        else if (OtherRadioButton.isSelected())
            gender = " Other";
        
        if (gender.isEmpty()){
            unfilled.show();
            return;
        }
        Integer id;
//        validation Check


        try {
            id = Integer.parseInt(idTextField.getText());
        } catch (NumberFormatException e) {
            invalidInput.show();
            return;
        }
              
        
        
        studentList.add(new student(id,
                nameTextField.getText(),
                passwordTextField.getText(),
                Float.parseFloat(cgpaTextField.getText()),
                MajorCombobox.getValue(),
                gender));
        
        searchIDCombo.getItems().add(id);
        StudentId.add(id);
        
//        String idText = idTextField.getText();
//        if (idText.isEmpty()){
//            unfilled.show();
//            return;
//            
//        }
        
        boolean anyEmpty = false;
        for(student s : studentList){
            if ( s.getName().isEmpty()||
                    s.getCgpa()==0.0f||
                    s.getGender().isEmpty()||
                    s.getPassword().isEmpty()||
                    s.getMajor().isEmpty()){
                anyEmpty = true;
                break;
            }
        }
        if (anyEmpty){
            unfilled.show();
        }
        
        success.show();
        
        idTextField.clear();
        nameTextField.clear();
        passwordTextField.clear();
        cgpaTextField.clear();
         
    }

    @FXML
    private void viewStudentButton(ActionEvent event) {
        for (student s : studentList){
            tableViewTextField.getItems().add(s);
        }
    }

    @FXML
    private void viewDetailsButton(ActionEvent event) {
        Integer selectedID = searchIDCombo.getValue();
        
        for (student s : studentList){
            if(s.getId()== selectedID){
                detailsLabel.setText("ID : "+s.getId()+"\n Name : " + s.getName()+"\n Major : "+s.getMajor());
            }
        }
        
        
    }

    @FXML
    private void nextButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("newViewScene.fxml"));
        Parent parent = loader.load();

        
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();


        Scene nextScene = new Scene(parent);

        currentStage.setScene(nextScene);
        currentStage.show();
    }
    
}
