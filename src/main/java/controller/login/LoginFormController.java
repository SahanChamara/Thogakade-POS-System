package controller.login;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class LoginFormController {

    @FXML
    private AnchorPane paneSignIn;

    @FXML
    private AnchorPane paneSignUp;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtNewEmail;

    @FXML
    private JFXTextField txtNewPassword;

    @FXML
    private JFXTextField txtNewUserName;

    @FXML
    private JFXTextField txtPassword;

    @FXML
    void btnCreateNewAccountOnAction(ActionEvent event) {
        paneSignIn.setVisible(false);
        paneSignUp.toFront();
    }

    @FXML
    void btnSignInOnAction(ActionEvent event) {

    }

    @FXML
    void btnSignUpOnAction(ActionEvent event) {

    }

}
