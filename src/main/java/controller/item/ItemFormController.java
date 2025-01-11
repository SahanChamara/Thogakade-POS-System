package controller.item;

import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import model.Item;

import java.net.URL;
import java.util.ResourceBundle;

public class ItemFormController implements Initializable {

    @FXML
    private MFXTableColumn colDescription;

    @FXML
    private MFXTableColumn colItemCode;

    @FXML
    private MFXTableColumn colQty;

    @FXML
    private MFXTableColumn colUnitPrice;

    @FXML
    private MFXTableView tblItem;

    @FXML
    private MFXTextField txtDescription;

    @FXML
    private MFXTextField txtItemCode;

    @FXML
    private MFXTextField txtPrice;

    @FXML
    private MFXTextField txtQty;

    @FXML
    void btnAddItemOnAction(ActionEvent event) {
        if(ItemController.getInstance().addItem(new Item(txtItemCode.getText(),txtDescription.getText(),Double.parseDouble(txtPrice.getText()),Integer.parseInt(txtQty.getText())))){

        }

    }

    @FXML
    void btnDeleteItemOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchItemOnACtion(ActionEvent event) {

    }

    @FXML
    void btnUpdateItemOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtItemCode.setText(String.format("P%03d",Integer.parseInt(ItemController.getInstance().generateId().substring(1))+1));
    }
}
