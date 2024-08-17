package com.example.demo1;

import com.example.demo1.DB_Management.DBMangment;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainDeposit {

    @FXML
    private TextField MoneyToD;
    @FXML
    private Label sMessage;
    @FXML
    private Label fMessage;



    DBMangment db=new DBMangment();



    String user=SharedDataModel.getMyVariable();

    @FXML
    private ImageView myI;



    public void displayImageOnStart() {
        Image image = new Image("file:C:/Users/oem/Desktop/Summer 23/Testing/Project/demo1/img/BKK.png");
        myI.setImage(image);
    }

    public void initialize(){
        displayImageOnStart();
        fMessage.setVisible(false);
        sMessage.setVisible(false);
    }
    @FXML
    public void Deposit(ActionEvent event){

        Double money;
        try {
            money =Double.parseDouble(MoneyToD.getText());
            if(db.deposit(user, money)!=-1) {
                fMessage.setVisible(false);
                sMessage.setVisible(true);
                sMessage.setText("Money deposited successfully");
            }
            else {
                sMessage.setVisible(false);
                fMessage.setVisible(true);
                fMessage.setText("Deposit failed successfully");
            }
        }catch (Exception e){
            System.out.println("Noooooooo!!! D " + e.getMessage());
            sMessage.setVisible(false);
            fMessage.setVisible(true);
            fMessage.setText("Deposit failed successfully");
        }


    }



    public void switchToMainMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Main_Menu.fxml"));
        Parent root = loader.load();
        MainMenu controller = loader.getController();
        controller.Start();


        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
