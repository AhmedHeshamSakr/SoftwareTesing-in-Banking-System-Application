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

public class MainTransfer {

    @FXML
    private TextField MoneyToT;
    @FXML
    private TextField To;
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
        fMessage.setVisible(false);
        sMessage.setVisible(false);
    }
    public void TransferMoney(ActionEvent event){
        Double money3;
        String ToP=To.getText();

        try {
            money3 = Double.parseDouble(MoneyToT.getText());
            if(db.transferMoney(user ,ToP, money3)!=-1 && db.updateBalance(ToP, money3)!=-1) {


                fMessage.setVisible(false);
                sMessage.setVisible(true);
                sMessage.setText("Money transfered successfully");
            }
            else {
                sMessage.setVisible(false);
                fMessage.setVisible(true);
                fMessage.setText("Transfer failed successfully");
            }
        }catch (Exception e){
            System.out.println("Noooooooo!!! T" + e.getMessage());
            sMessage.setVisible(false);
            fMessage.setVisible(true);
            fMessage.setText("Transfer failed successfully");
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

