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

public class LoginPage {

    @FXML
    private Parent root;
    @FXML
    private TextField password;
    @FXML
    private TextField UserName;
    @FXML
    private Button Login;
    @FXML
    public String  MyUser;

    @FXML
    private Label sMessage;
    @FXML
    private Label fMessage;
    DBMangment db = new DBMangment();

    @FXML
    private ImageView myI;



    public void displayImageOnStart() {
        Image image = new Image("file:C:/Users/oem/Desktop/Summer 23/Testing/Project/demo1/img/BKK.png");
        myI.setImage(image);
    }

    public LoginPage() {
        SharedDataModel sharedDataModel = new SharedDataModel();
    }

    public void updateVariable(String value) {
        SharedDataModel.setMyVariable(value);
    }


    public void initialize(){
        displayImageOnStart();
        fMessage.setVisible(false);
        sMessage.setVisible(false);
    }

    @FXML
    public void Login(ActionEvent event) {
        MyUser=UserName.getText();
        try {
            if (db.logIn(MyUser, password.getText()) != -1) {
                updateVariable(MyUser);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Main_Menu.fxml"));
                Parent root = loader.load();
                MainMenu controller = loader.getController();
                controller.Start();

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                fMessage.setVisible(false);
                sMessage.setVisible(true);
                sMessage.setText("Login successfully");
            }
            else {
                sMessage.setVisible(false);
                fMessage.setVisible(true);
                fMessage.setText("Incorrect username or password");
            }
        } catch (Exception e) {
            System.out.println("Noooooooo!!!" + e.getMessage());

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