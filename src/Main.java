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
    private Tokenizer tokenizer;
    private Parser parser;
    private FXMLLoader loader;

    @FXML private Label output;
    @FXML private Label input;

    private String pendingInput = "";

    public Main() {
        this.tokenizer = new Tokenizer();
        this.parser = new Parser();

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

        Main self = this;
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent key) {
                key.consume();
                if (key.getCode() == KeyCode.ENTER) {
                    self.submitCalc();
                } else if (key.getCode() == KeyCode.BACK_SPACE) {
                    self.setInput(self.pendingInput.substring(0, self.pendingInput.length() - 1));
                } else {
                    self.appendInput(key.getText());
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
        
        Queue<Token> tokens = this.tokenizer.feed(toSubmit);
        String res = null;
        if (tokens == null) {
            res = "Format error";
        }
        this.parser.setTokens(tokens);
        Expr ast = this.parser.parse(0);
        if (ast == null) {
            res = "Syntax error";
        }
        double value = ast.eval();
        if (Double.isNaN(value)) {
            res = "Math error";
        }
        this.parser.setPreviousAnswer(value);
        if (res != null)
            this.output.setText(res);
        else
            this.output.setText(Double.toString(value));
    }
}
