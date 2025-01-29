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
import service.custom.impl.ItemServiceImpl;

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
        if(ItemServiceImpl.getInstance().addItem(new Item(txtItemCode.getText(),txtDescription.getText(),Double.parseDouble(txtPrice.getText()),Integer.parseInt(txtQty.getText())))){
            loadTable();
            new Alert(Alert.AlertType.INFORMATION,"item Added Successful").show();
        }
    }

    @FXML
    void btnDeleteItemOnAction(ActionEvent event) {
        if(ItemServiceImpl.getInstance().addItem(new Item(txtItemCode.getText(),txtDescription.getText(),Double.parseDouble(txtPrice.getText()),Integer.parseInt(txtQty.getText())))){
            new Alert(Alert.AlertType.INFORMATION,"item Deleted Successful").show();
            loadTable();
        }else {
            new Alert(Alert.AlertType.INFORMATION,"item Deleted Failed").show();
        }
    }

    @FXML
    void btnSearchItemOnAction(ActionEvent event) {
        for (Item item : ItemServiceImpl.getInstance().searchItem(txtItemCode.getText())){
            txtDescription.setText(item.getDescription());
            txtPrice.setText(String.valueOf(item.getUnitPrice()));
            txtQty.setText(String.valueOf(item.getQtyOnHand()));
        }
    }

    @FXML
    void btnUpdateItemOnAction(ActionEvent event) {
        if(ItemServiceImpl.getInstance().updateItem(new Item(txtItemCode.getText(),txtDescription.getText(),Double.parseDouble(txtPrice.getText()),Integer.parseInt(txtQty.getText())))){
            new Alert(Alert.AlertType.INFORMATION,"item Update Successful").show();
            loadTable();
        }else {
            new Alert(Alert.AlertType.INFORMATION,"item Update Failed").show();
        }
    }

    void loadTable(){
        ObservableList<Item> itemObservableList = FXCollections.observableArrayList();
        itemObservableList.addAll(ItemServiceImpl.getInstance().getAllItems());
        tblItem.setItems(itemObservableList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));

        txtItemCode.setText(String.format("P%03d",Integer.parseInt(ItemServiceImpl.getInstance().generateId().substring(1))+1));
        loadTable();
    }
}
