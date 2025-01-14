import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Starter extends Application {
    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage stage) throws Exception {
//        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml")));
//        scene.getStylesheets().add(getClass().getResource("/css/dashboard.css").toExternalForm());
//        stage.setScene(scene);
//        stage.show();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/login_form.fxml"))));
        stage.show();
    }
}
