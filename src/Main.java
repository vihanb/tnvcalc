import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.fxml.*;
import java.io.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.event.*;

import expr.Expr;
import parser.Parser;
import token.Token;
import tokenizer.Tokenizer;
import java.util.*;


public class Main extends Application {
    private FXMLLoader loader;
    private Label output;
    private Label input;

    private String pendingInput = "";

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

        this.pendingInput = "";

        scene.getRoot().applyCss();

        this.input = (Label) scene.lookup("#input");

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
        System.out.println(str);
        System.out.println(this.input);
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
        Expr ast = new Parser(tokens).parse(0);
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
