package controller.customer;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;
import service.ServiceFactory;
import service.custom.CustomerService;
import service.custom.impl.CustomerServiceImpl;
import utill.ServiceType;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class CustomerFormController implements Initializable {

    @FXML
    private TableColumn colAddress;

    @FXML
    private TableColumn colId;

    @FXML
    private TableColumn colName;

    @FXML
    private TableColumn colSalary;

    @FXML
    private TableView tblCustomer;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtSalary;

    CustomerService customerService = ServiceFactory.getInstance().getServiceType(ServiceType.CUSTOMER);

    // Add Customer
    @FXML
    void btnAddCustomerOnAction(ActionEvent event) {
        try {
            if (customerService.addCustomer(new Customer(txtId.getText(), txtName.getText(), txtAddress.getText(), Double.parseDouble(txtSalary.getText())))) {
                new Alert(Alert.AlertType.INFORMATION, "Customer Added Successful").show();
                txtName.clear();
                txtAddress.clear();
                txtSalary.clear();
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Customer Added Failed").show();
            }
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.WARNING, "Please Fill the All Empty TEXT Fields..").show();
        }
    }

    // Delete Customer
    @FXML
    void btnDeleteCustomerOnAction(ActionEvent event) {

        Optional<ButtonType> result = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure Want to Delete it?.", ButtonType.YES, ButtonType.NO).showAndWait();
        ButtonType buttonType = result.orElse(ButtonType.NO);
        if (buttonType == ButtonType.YES) {
            if (customerService.deleteCustomer(txtId.getText())) {
                new Alert(Alert.AlertType.INFORMATION, "Customer Delete Successful").show();
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Customer Delete Failed").show();
            }
        }
        loadTable();

    }

    // Search Customer
    @FXML
    void btnSearchCustomerOnAction(ActionEvent event) {
        Customer customer = customerService.searchCustomer(txtId.getText());
        txtName.setText(customer.getName());
        txtAddress.setText(customer.getAddress());
        txtSalary.setText(String.valueOf(customer.getSalary()));
    }

    // Update Customer
    @FXML
    void btnUpdateCustomerOnAction(ActionEvent event) {
        if (customerService.updateCustomer(new Customer(txtId.getText(), txtName.getText(), txtAddress.getText(), Double.parseDouble(txtSalary.getText())))) {
            new Alert(Alert.AlertType.INFORMATION, "Customer Update Successful").show();
        } else {
            new Alert(Alert.AlertType.INFORMATION, "Customer Update Failed").show();
        }
    }

    // load the customer table
    private void loadTable() {
        ObservableList<Customer> customerObservableList = FXCollections.observableArrayList();
        for (Customer customer : customerService.getAllCustomer()) {
            customerObservableList.add(customer);
        }
        tblCustomer.setItems(customerObservableList);
    }

    // generate Customer ID
    private void generateCusId() {
        String cusId = customerService.generateId();
        int id = Integer.parseInt(cusId.substring(1));
        txtId.setText(String.format("C%03d", id + 1));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));

        generateCusId();
        loadTable();

        // get selected row in table
        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                setTextOnTheTable((Customer) newValue);
            }
        });
    }

    // ID text Field Action
    public void txtIdOnAction(ActionEvent actionEvent) {
        btnAddCustomerOnAction(actionEvent);
    }

    // set selected row in table
    private void setTextOnTheTable(Customer newValue) {
        txtId.setText(newValue.getId());
        txtName.setText(newValue.getName());
        txtAddress.setText(newValue.getAddress());
        txtSalary.setText(String.valueOf(newValue.getSalary()));
    }
}
