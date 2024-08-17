package com.example.demo1;


import com.example.demo1.DB_Management.DBMangment;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.controlsfx.control.tableview2.TableColumn2;

import java.io.IOException;
import java.util.Objects;



public class MainHistory {
    String user=SharedDataModel.getMyVariable();
    DBMangment db=new DBMangment();



    @FXML
    private TableView<Transactions> table_view;
    @FXML
    private TableColumn<Transactions, String> transaction_id_coloumn;
    @FXML
    private TableColumn<Transactions, String> transaction_type_coloumn;
    @FXML
    private TableColumn<Transactions, String> amount_coloumn;
    @FXML
    private TableColumn<Transactions, String> timestamp_coloumn;
    @FXML
    private ImageView myI;



    public void displayImageOnStart() {
        Image image = new Image("file:C:/Users/oem/Desktop/Summer 23/Testing/Project/demo1/img/BKK.png");
        myI.setImage(image);
    }
    
    public void initalize(){
        displayImageOnStart();
        transaction_id_coloumn.setCellValueFactory(new PropertyValueFactory<Transactions, String>("transaction_ID"));
        transaction_type_coloumn.setCellValueFactory(new PropertyValueFactory<Transactions, String>("transaction_type"));
        amount_coloumn.setCellValueFactory(new PropertyValueFactory<Transactions, String>("amount"));
        timestamp_coloumn.setCellValueFactory(new PropertyValueFactory<Transactions, String>("timestamp"));

        table_view.setItems(Transactions.transaction_list);
    }

    public void displayTransactions(){
        try {
            db.retrieveAndDisplayTransactions(user);
            initalize();
        }
        catch(Exception e)
        {
            System.out.println(e);
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
