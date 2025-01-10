package controller.item;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Item;

import java.net.URL;
import java.util.ResourceBundle;

public class ItemFormController implements Initializable {

    @FXML
    private Label lblId;

    @FXML
    private TextArea txtDescription;

    @FXML
    private TextField txtItemName;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtQty;

    @FXML
    void btnAddItemOnAction(ActionEvent event) {
        if(ItemController.getInstance().addItem(new Item(lblId.getText(),txtDescription.getText(),Double.parseDouble(txtPrice.getText()),Integer.parseInt(txtQty.getText())))){
            new Alert(Alert.AlertType.INFORMATION,"Item Added Successful...").show();
        }
    }

    void setItemId(){
        lblId.setText(String.format("P%03d",Integer.parseInt(ItemController.getInstance().generateId().substring(1))+1));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setItemId();
    }
}
