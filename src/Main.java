import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.fxml.*;
import java.io.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.event.*;
import javafx.scene.paint.*;

import expr.Expr;
import parser.Parser;
import token.Token;
import tokenizer.Tokenizer;
import java.util.*;


public class Main extends Application {
    private FXMLLoader loader;

    @FXML private Label output;
    @FXML private Label input;

    private String pendingInput = "";

    public Main() {
        this.loader = new FXMLLoader(getClass().getResource("Calculator.fxml"));
    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = loader.load();
        Scene scene = new Scene(root, 404, 381);
        stage.setResizable(false);
        stage.setTitle("hello example title dont forget to replace.");
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();

        this.pendingInput = "";

        scene.getRoot().applyCss();

        Label input = (Label) scene.lookup("#input");
        Label output = (Label) scene.lookup("#output");

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent key) {
                if (key.getCode() == KeyCode.ENTER) {
                    Main.this.submitCalc();
                } else {
                    Main.this.appendInput(key.getText());
                }
            }
        });
    }

    @FXML
    private void didClickCalcButton(ActionEvent event) {
        Button src = (Button) event.getSource();
        String btn = src.getText();
        switch (btn) {
            case "C": this.setInput(""); break;
            case "=": this.submitCalc(); break;
            default: this.appendInput(btn);
        }
    }

    private void appendInput(String str) {
        this.setInput(this.pendingInput + str);
    }

    private void setInput(String str) {
        this.pendingInput = str;
        this.input.setText(str);
    }

    private void submitCalc() {
        String toSubmit = this.pendingInput;
        this.pendingInput = "";
        
        Tokenizer tokenizer = new Tokenizer();
        Queue<Token> tokens = tokenizer.feed(toSubmit);
        String res = null;
        if (tokens == null) {
            res = "Format error";
        }
        Main.parser.setTokens(tokens);
        Expr ast = Main.parser.parse(0);
        if (ast == null) {
            res = "Syntax error";
        }
        double value = ast.eval();
        if (Double.isNaN(value)) {
            res = "Math error";
        }

        if (res != null)
            this.output.setText(res);
        else
            this.output.setText(Double.toString(value));
    }
}
