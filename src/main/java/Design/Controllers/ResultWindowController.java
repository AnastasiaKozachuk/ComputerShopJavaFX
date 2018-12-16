package Design.Controllers;


import java.net.URL;
import java.util.ResourceBundle;

import Design.SharedClasses;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;

public class ResultWindowController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label result;

    @FXML
    void initialize() {

        result.setText(SharedClasses.number.toString());
        result.setAlignment(Pos.CENTER);

    }
}