/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package bbfx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CinmaTheaterController implements Initializable {

    @FXML
    private void newWindow(ActionEvent evnt) throws IOException {

        try {
            Stage firstStage = (Stage) ((Node) evnt.getSource()).getScene().getWindow();
            Parent firstRoot = FXMLLoader.load(getClass().getResource("/bbfx/FXMLDocument.fxml"));
            Scene firstScene = new Scene(firstRoot);
            firstStage.setScene(firstScene);
            firstStage.setTitle("Producer-Consumer problem");
            firstStage.show();

            Stage secondStage = new Stage();
            //secondStage.initModality(Modality.APPLICATION_MODAL);
            secondStage.initOwner(firstStage);
            Parent secondRoot = FXMLLoader.load(getClass().getResource("/bbfx/FXMLDocument.fxml"));
            Scene secondScene = new Scene(secondRoot);
            secondStage.setScene(secondScene);
            secondStage.setTitle("Producer-Consumer problem");
            secondStage.show();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
//        Stage sstage = new Stage();
// Parent root = FXMLLoader.load(getClass().getResource("/bbfx/cinmaTheater.fxml"));
//        Scene scene = new Scene(root);
//        sstage.setScene(scene);
//        sstage.setTitle("Producer-Consumer problem");
//        sstage.show();
    }

    @FXML
    public void Exit(ActionEvent evnt) {
        System.exit(0);
    }

    @FXML
    ComboBox<String> filmBox;
    ObservableList<String> filmList = FXCollections.observableArrayList(" Black panther", " Smile", " Doctor Strange");

    @FXML
    ComboBox<String> timeBox;
    ObservableList<String> timeList = FXCollections.observableArrayList(" 9-12 AM", " 12-3 PM", " 3-6 PM", " 6-9 PM", " 9-12 PM");

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        filmBox.setItems(filmList);
        timeBox.setItems(timeList);
    }

}
