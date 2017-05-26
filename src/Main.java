import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.fxml.*;
import java.io.*;

public class Main extends Application {
    private FXMLLoader loader;

    public Main() {
        this.loader = new FXMLLoader(getClass().getResource("Calculator.fxml"));
    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = loader.load();
        Scene scene = new Scene(root, 300, 275);
        stage.setTitle("hello example title dont forget to replace.");
        stage.setScene(scene);
        stage.show();
    }
}
