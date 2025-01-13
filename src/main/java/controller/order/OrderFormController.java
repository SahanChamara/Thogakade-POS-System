package controller.order;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import io.github.palexdev.mfxcore.controls.Label;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import model.Item;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.ResourceBundle;

public class OrderFormController implements Initializable {

    @FXML
    public AnchorPane paneOrderForm;
    @FXML
    public Label lblTime;
    @FXML
    private TableColumn colDescription;

    @FXML
    private TableColumn colItemCode;

    @FXML
    private TableColumn colQty;

    @FXML
    private TableColumn colTotal;

    @FXML
    private TableColumn colUnitPrice;

    @FXML
    private JFXComboBox comboCustomerId;

    @FXML
    private JFXComboBox comboItemCode;

    @FXML
    private Label lblCustomerName;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblDescription;

    @FXML
    private Label lblOrderId;

    @FXML
    private Label lblQtyOnHand;

    @FXML
    private Label lblTotal;

    @FXML
    private Label lblUnitPrice;

    @FXML
    private TableView tblOrder;

    @FXML
    private JFXTextField txtQty;

    @FXML
    void btnAddNewCustomerOnAction(ActionEvent event) {

    }

    @FXML
    void btnAddToOrderOnAction(ActionEvent event) {

    }

    @FXML
    void btnCanselOnAction(ActionEvent event) {

    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {

    }

    @FXML
    void btnRemoveOrderAction(ActionEvent event) {

    }

    @FXML
    public void comboCusStateChange(ActionEvent actionEvent) {
        setCustomerName();
    }

    void setOrderId(){
        lblOrderId.setText(OrderController.getInstance().getOrderId());
    }

    void setTime() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, e -> {
                    LocalTime localTime = LocalTime.now();
                    lblTime.setText(localTime.getHour() + " : " + localTime.getMinute() + " : " + localTime.getSecond());
                }),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    void loadCustomerId(){
        ObservableList<String> customerIdObservableList = FXCollections.observableArrayList();
        customerIdObservableList.addAll(OrderController.getInstance().getCustomerId());
        comboCustomerId.setItems(customerIdObservableList);
    }

    void setCustomerName(){
        lblCustomerName.setText(OrderController.getInstance().searchCustomerName(comboCustomerId.getSelectionModel().getSelectedItem().toString()).getName());
    }

    void loadItemCodes(){
        ObservableList<String> itemCodesObservableList = FXCollections.observableArrayList();
        for (Item item : OrderController.getInstance().getItemCode()) {
            itemCodesObservableList.add(item.getCode());
        }
        comboItemCode.setItems(itemCodesObservableList);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // set Date
        lblDate.setText(new SimpleDateFormat("yyyy/MM/dd").format(new Date()));

        // set Time
        setTime();

        // set Order ID
        setOrderId();

        // set Customer iDs to Combo Box
        loadCustomerId();

        // set Item Codes to Combo Box
        loadItemCodes();
    }
}
