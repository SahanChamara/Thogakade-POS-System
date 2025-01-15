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
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import model.Item;
import model.OrderTable;

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
    public TableView tblOrders;
    @FXML
    public TableColumn colItemCode;
    @FXML
    public TableColumn colDesc;
    @FXML
    public TableColumn colQty;
    @FXML
    public TableColumn colUnitPrice;
    @FXML
    public TableColumn colTotal;

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
    private JFXTextField txtQty;

    private ObservableList<OrderTable> orderTableObservableList = FXCollections.observableArrayList();

    @FXML
    void btnAddNewCustomerOnAction(ActionEvent event) {

    }

    @FXML
    void btnAddToOrderOnAction(ActionEvent event) {
        try {
            if (checkQtyOnHand()) {
                comboCustomerId.setEditable(false);
                colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
                colDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
                colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
                colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
                colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
                tblOrders.setItems(orderTableObservableList);

                Double totalPrice = (Integer.parseInt(txtQty.getText()) * Double.parseDouble(lblUnitPrice.getText()));
                OrderTable orderTable = new OrderTable(comboItemCode.getSelectionModel().getSelectedItem().toString(), lblDescription.getText(), Integer.parseInt(txtQty.getText()), Double.parseDouble(lblUnitPrice.getText()), totalPrice);

                int existIndex = orderTableObservableList.indexOf(orderTable);

                if (existIndex != -1) {
                    OrderTable orderTableExist = orderTableObservableList.get(existIndex);
                    orderTableObservableList.set(existIndex, new OrderTable(comboItemCode.getSelectionModel().getSelectedItem().toString(),
                            lblDescription.getText(),
                            Integer.parseInt(txtQty.getText()) + orderTableExist.getQty(),
                            Double.parseDouble(lblUnitPrice.getText()),
                            totalPrice + orderTableExist.getTotal())
                    );
                } else {
                    orderTableObservableList.add(orderTable);
                }
                txtQty.clear();
                calculateTotal();
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Enough Stock...").show();
            }

        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.INFORMATION, "Please Enter Qty...").show();
        }
    }

    void calculateTotal() {
        double netTotal = 0.0;
        for (OrderTable orderTable : orderTableObservableList) {
            lblTotal.setText(String.valueOf(netTotal += orderTable.getTotal()));
        }
    }

    boolean checkQtyOnHand() {
        return Integer.parseInt(txtQty.getText()) <= Integer.parseInt(lblQtyOnHand.getText());
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {
        comboCustomerId.setEditable(true);

    }

    @FXML
    void btnRemoveOrderAction(ActionEvent event) {

    }

    @FXML
    public void comboCusStateChange(ActionEvent actionEvent) {
        setCustomerName();
    }

    void setOrderId() {
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

    void loadCustomerId() {
        ObservableList<String> customerIdObservableList = FXCollections.observableArrayList();
        customerIdObservableList.addAll(OrderController.getInstance().getCustomerId());
        comboCustomerId.setItems(customerIdObservableList);
    }

    void setCustomerName() {
        lblCustomerName.setText(OrderController.getInstance().searchCustomerName(comboCustomerId.getSelectionModel().getSelectedItem().toString()).getName());
    }

    void loadItemCodes() {
        ObservableList<String> itemCodesObservableList = FXCollections.observableArrayList();
        for (Item item : OrderController.getInstance().getItemCode()) {
            itemCodesObservableList.add(item.getCode());
        }
        comboItemCode.setItems(itemCodesObservableList);
    }


    @FXML
    public void comboItemStateChange(ActionEvent actionEvent) {
        for (Item loadItemDetail : OrderController.getInstance().loadItemDetails(comboItemCode.getSelectionModel().getSelectedItem().toString())) {
            lblDescription.setText(loadItemDetail.getDescription());
            lblUnitPrice.setText(String.valueOf(loadItemDetail.getUnitPrice()));
            lblQtyOnHand.setText(String.valueOf(loadItemDetail.getQtyOnHand()));
        }
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
