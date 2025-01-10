package controller.dashboard;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class DashboardFormController {

    @FXML
    public AnchorPane paneCustomerManagement;
    @FXML
    public AnchorPane paneItemManagement;
    @FXML
    public AnchorPane paneOrderManagement;
    @FXML
    public Tab tabItemManagement;
    @FXML
    public Tab tabOrderManagement;
    @FXML
    public Tab tabCustomerManagement;
    @FXML
    public TabPane tabPane;

    @FXML
    void chCustomerManagement(Event event) throws IOException {
        URL resource = this.getClass().getResource("/view/customer_view_form.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        paneCustomerManagement.getChildren().clear();
        paneCustomerManagement.getChildren().add(load);
    }

    @FXML
    void chItemManagement(Event event) throws IOException {
        loadPanes("/view/item_form.fxml",paneItemManagement);
    }

    @FXML
    void chOrderManagement(Event event) {

    }

    void loadPanes(String fxmlPath, AnchorPane pane) throws IOException {
        URL resource = this.getClass().getResource(fxmlPath);
        assert resource != null;
        Parent form = FXMLLoader.load(resource);
        pane.getChildren().clear();
        pane.getChildren().add(form);
    }

}
