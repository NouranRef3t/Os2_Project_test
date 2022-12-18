package bbfx;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.Semaphore;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.DialogPane;

public class FXMLDocumentController implements Initializable {

    private DialogPane dialog;

    @FXML
    public void Exit(ActionEvent evnt) {
        System.exit(0);
    }

    @FXML
    private Label queueSize;

    List<Integer> queue = new ArrayList<Integer>();
    int size = 5;

    @FXML
    public void produce(ActionEvent evnt) {
        ticketsLeft(evnt);
        Producer producer = new Producer(queue, size);
        Thread pt = new Thread(producer);
        pt.start();
        if (queue.size() == size) {
            overflowAlert(evnt);
        }
    }

    @FXML
    public void consume(ActionEvent evnt) {
        ticketsLeft(evnt);
        Consumer consumer = new Consumer(queue);
        Thread ct = new Thread(consumer);
        ct.start();
        if (queue.isEmpty()) {
            underflowAlert(evnt);
        }
    }

    @FXML
    private Label tcktlft;

    @FXML
    public void ticketsLeft(ActionEvent evnt) {
        tcktlft.setText(Integer.toString(queue.size()));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void newWindow(ActionEvent evnt) throws IOException {
        Stage secondStage = (Stage) ((Node) evnt.getSource()).getScene().getWindow();
        Parent secondRoot = FXMLLoader.load(getClass().getResource("cinmaTheater.fxml"));
        Scene secondScene = new Scene(secondRoot);
        secondStage.setScene(secondScene);
        secondStage.show();
    }

    @FXML
    public void overflowAlert(ActionEvent evnt) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("OverrFlow");
        alert.setHeaderText("Overflow,");
        alert.setContentText("Number of tickets more than needed");
        dialog = alert.getDialogPane();
        dialog.getStylesheets().add(getClass().getResource("CSS.css").toString());
        dialog.getStyleClass().add("dialog");
        alert.show();
    }

    @FXML
    public void underflowAlert(ActionEvent evnt) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("UnderFlow");
        alert.setHeaderText("Underflow,");
        alert.setContentText("There are no tickets left");
        dialog = alert.getDialogPane();
        dialog.getStylesheets().add(getClass().getResource("CSS.css").toString());
        dialog.getStyleClass().add("dialog");
        alert.show();
    }
}
