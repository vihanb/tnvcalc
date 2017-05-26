import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.fxml.*;
import java.io.*;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("/Calculator.fmxl"));
        Scene scene = new Scene(root, 300, 275);
        stage.setTitle("hello example title dont forget to replace.");
        stage.setScene(scene);
        stage.show();
    }
}
