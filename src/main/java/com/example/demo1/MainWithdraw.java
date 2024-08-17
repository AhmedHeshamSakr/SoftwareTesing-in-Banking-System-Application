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

public class MainWithdraw {

    @FXML
    private TextField MoneyTow;
    @FXML
    private Label sMessage;
    @FXML
    private Label fMessage;

    String user=SharedDataModel.getMyVariable();
    DBMangment db=new DBMangment();
    @FXML
    private ImageView myI;



    public void displayImageOnStart() {
        Image image = new Image("file:C:/Users/oem/Desktop/Summer 23/Testing/Project/demo1/img/BKK.png");
        myI.setImage(image);
    }


    public void initialize(){
        displayImageOnStart();
        sMessage.setVisible(false);
        fMessage.setVisible(false);
    }
    public void Withdraw(ActionEvent event){
        Double money2;
        try {
            money2=Double.parseDouble(MoneyTow.getText()) ;
            if(db.withdraw(user,money2)!=-1) {
                fMessage.setVisible(false);
                sMessage.setVisible(true);
                sMessage.setText("Money withdrawn successfully");
            }
            else {
                sMessage.setVisible(false);
                fMessage.setVisible(true);
                fMessage.setText("Withdrawal failed successfully");
            }
        }catch (Exception e){
            System.out.println("Noooooooo!!! W " + e.getMessage());
            fMessage.setText("Withdrawal failed successfully");
            sMessage.setVisible(false);
            fMessage.setVisible(true);
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
