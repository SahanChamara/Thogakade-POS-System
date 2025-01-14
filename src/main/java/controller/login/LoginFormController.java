package controller.login;

import animatefx.animation.FadeIn;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import model.User;

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
        new FadeIn(paneSignUp).play();
        paneSignUp.toFront();
    }

    @FXML
    void btnSignInOnAction(ActionEvent event) {
        if(LoginController.getInstance().loginUser(new User(null,null,txtEmail.getText(),txtPassword.getText()))){
            System.out.println("login success");
        }else{
            System.out.println("login failed");
        }

    }

    @FXML
    void btnSignUpOnAction(ActionEvent event) {
        if(LoginController.getInstance().registerUser(new User(null,txtNewUserName.getText(),txtNewEmail.getText(),txtNewPassword.getText()))){
            new Alert(Alert.AlertType.INFORMATION,"User Registered Successful");
        }else{
            new Alert(Alert.AlertType.INFORMATION,"User Registered Failed");
        }
    }

}
