package controller.item;

import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Item;

import java.net.URL;
import java.util.ResourceBundle;

public class ItemFormController implements Initializable {
    @FXML
    private TableColumn colItemCode;

    @FXML
    private TableColumn colDescription;

    @FXML
    private TableColumn colQtyOnHand;

    @FXML
    private TableColumn colUnitPrice;

    @FXML
    private TableView tblItem;
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
            loadTable();
            new Alert(Alert.AlertType.INFORMATION,"item Added Successful").show();
        }
    }

    @FXML
    void btnDeleteItemOnAction(ActionEvent event) {
        if(ItemController.getInstance().addItem(new Item(txtItemCode.getText(),txtDescription.getText(),Double.parseDouble(txtPrice.getText()),Integer.parseInt(txtQty.getText())))){
            new Alert(Alert.AlertType.INFORMATION,"item Deleted Successful").show();
        }
    }

    @FXML
    void btnSearchItemOnACtion(ActionEvent event) {

    }

    @FXML
    void btnUpdateItemOnAction(ActionEvent event) {

    }

    void loadTable(){
        ObservableList<Item> itemObservableList = FXCollections.observableArrayList();
        itemObservableList.addAll(ItemController.getInstance().getAllItems());
        tblItem.setItems(itemObservableList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));

        txtItemCode.setText(String.format("P%03d",Integer.parseInt(ItemController.getInstance().generateId().substring(1))+1));
        loadTable();
    }
}
