package com.example.demo1;



import com.example.demo1.DB_Management.DBMangment;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SignUpPage {

    @FXML
    private Parent root;
    @FXML
    private TextField name;

    @FXML
    private TextField nID;
    @FXML
    private TextField userName;
    @FXML
    private TextField phone;
    @FXML
    private TextField password;
    @FXML
    private Button SignUp;
    @FXML

    DBMangment db=new DBMangment();
    @FXML
    private ImageView myI;

    @FXML
    private Label fMessage;



    public void displayImageOnStart() {
        Image image = new Image("file:C:/Users/oem/Desktop/Summer 23/Testing/Project/demo1/img/BKK.png");
        myI.setImage(image);
    }
    public void initalize(){

        displayImageOnStart();
        fMessage.setVisible(false);
    }


    public void SignUp(ActionEvent event ){
        String fullName=userName.getText();
        String pass=password.getText();
        String Name=name.getText();
        String NID=nID.getText();
        String Phone=phone.getText();

        try {
            if(db.signUp(fullName,pass,Name,NID,Phone)!=-1)
            {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Login_page.fxml")));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            else {

                fMessage.setVisible(true);
                fMessage.setText("Failed");
            }
        }
        catch (Exception e){
            System.out.println("Noooooooo!!!"+e.getMessage());
            fMessage.setVisible(true);
            fMessage.setText("Failed");
        }

    }

    public void switchToMain(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hello-view.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}
