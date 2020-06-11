package ch2.q4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class  ch2 extends Application {

    TextArea textArea;
    Stage stage3;

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException, IOException {

        Stage stage4 = new Stage();
        GridPane pane4 = new GridPane();
        pane4.setAlignment(Pos.CENTER);
        pane4.setPadding(new Insets(15, 15, 15, 15));
        pane4.setHgap(5);
        pane4.setVgap(5);
        pane4.add(new Label("User Name: "), 0, 1);
        TextField name = new TextField();
        pane4.add(name, 1, 1);
        pane4.add(new Label("Password: "), 0, 2);
        PasswordField password = new PasswordField();
        pane4.add(password, 1, 2);
        Button loginButton = new Button("Sign in");
        Button exitButton = new Button("Exit");
        HBox hBox = new HBox(10, loginButton, exitButton);
        hBox.alignmentProperty().setValue(Pos.CENTER_RIGHT);
        pane4.add(hBox, 1, 3);

        String filepath = "password.txt";
        File file = new File(filepath);
        file.createNewFile();
        Scanner scanner = new Scanner(file);
        loginButton.setOnAction((ActionEvent event) -> {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains(name.getText()) && line.contains(md5Java(password.getText()))) {
                    GridPane pane5 = new GridPane();
                    pane5.setAlignment(Pos.CENTER);
                    pane5.setPadding(new Insets(15, 15, 15, 15));
                    pane5.setHgap(5);
                    pane5.setVgap(15);
                    Button addStudent = new Button("Add Student");
                    pane5.add(addStudent, 0, 0);
                    Button viewStudent = new Button("View Student");
                    pane5.add(viewStudent, 0, 1);
                    Scene scene5 = new Scene(pane5, 300, 300);
                     scene5.getStylesheets().add(Ch2Q4.class.getResource("style.css").toExternalForm());
                    stage4.setTitle("Options Page");
                    stage4.setScene(scene5);
                }
            }
        });

        exitButton.setOnAction((event) -> {
            stage4.close();
        });

        Scene scene4 = new Scene(pane4, 300, 300);
        stage4.setTitle("Login Page");
        stage4.setScene(scene4);
        stage4.setResizable(false);
         scene4.getStylesheets().add(ch2.class.getResource("style.css").toExternalForm());
        stage4.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public static String md5Java(String message) {
        String digest = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(message.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder(2 * hash.length);
            for (byte b : hash) {
                sb.append(String.format("%02x", b & 0xff));
            }
            digest = sb.toString();
        } catch (UnsupportedEncodingException ex) {

        } catch (NoSuchAlgorithmException ex) {

        }
        return digest;
    }
}
