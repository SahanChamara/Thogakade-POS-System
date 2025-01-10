package controller.dashboard;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.net.URL;

public class DashboardFormController {

    @FXML
    private AnchorPane paneLoadForm;

    @FXML
    void btnAddCustomerFormOnAction(ActionEvent event) throws IOException {
        URL resource = this.getClass().getResource("/view/add_customer_view_form.fxml");
        assert resource!=null;

        Parent load = FXMLLoader.load(resource);
        this.paneLoadForm.getChildren().clear();
        this.paneLoadForm.getChildren().add(load);
    }

    @FXML
    void btnItemManageFromOnAction(ActionEvent event) {

    }

    @FXML
    void btnOrderPlaceFormOnAction(ActionEvent event) {

    }

}
