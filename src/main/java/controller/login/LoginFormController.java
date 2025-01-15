package controller.login;

import animatefx.animation.FadeIn;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.User;

import java.io.IOException;

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
    void btnSignInOnAction(ActionEvent event) throws IOException {
        if(LoginController.getInstance().loginUser(new User(null,null,txtEmail.getText(),txtPassword.getText()))){
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml")));
            scene.getStylesheets().add(getClass().getResource("/css/dashboard.css").toExternalForm());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        }else{
            new Alert(Alert.AlertType.INFORMATION,"Email or Password Invalid").show();
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
