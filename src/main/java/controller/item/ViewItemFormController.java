package controller.item;

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

public class ViewItemFormController implements Initializable {
    @FXML
    private TableColumn colDesc;

    @FXML
    private TableColumn colCode;

    @FXML
    private TableColumn colQtyOnHand;

    @FXML
    private TableColumn colUnitPrice;

    @FXML
    private TableView tblItem;

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        if(ItemController.getInstance().deleteItem((Item) tblItem.getSelectionModel().getSelectedItem())){
            new Alert(Alert.AlertType.INFORMATION,"Item Delete Successful").show();
            loadTable();
        }
    }

    @FXML
    void btnReloadOnAction(ActionEvent event) {
        loadTable();
    }

    private void loadTable() {
        ObservableList<Item> itemObservableList = FXCollections.observableArrayList();
        itemObservableList.addAll(ItemController.getInstance().getAllItems());
        tblItem.setItems(itemObservableList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));

        loadTable();
    }
}
