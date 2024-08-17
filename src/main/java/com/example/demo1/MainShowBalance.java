package com.example.demo1;


import com.example.demo1.DB_Management.DBMangment;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainShowBalance {

    @FXML
    public Label ShowB;

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
    }

    @FXML
    public void ShowBalance()
    {
        double balance = 0;
        try {
            balance = db.retrieveBalance(user);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        ShowB.setText("Your Balance is : " + balance);
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
