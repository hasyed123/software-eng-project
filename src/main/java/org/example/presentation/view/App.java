package org.example.presentation.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        // Create a border pane for the window
        Pane root = new Pane();

        Rectangle rectangle = new Rectangle();
        rectangle.setX(425);
        rectangle.setY(100);
        rectangle.setWidth(350.0);
        rectangle.setHeight(450.0);
        rectangle.setFill(Color.GREY);
        root.getChildren().add(rectangle);

        // Create a box for the login form
        VBox loginBox = new VBox(10);
        loginBox.setPadding(new Insets(20));

        // Add a title to the login form
        Text title = new Text("Algoma University Login");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        title.setFill(Color.WHITE);
        title.setLayoutX(480);
        title.setLayoutY(150);
        root.getChildren().add(title);

        // Add an email field to the login form
        TextField emailField = new TextField();
        emailField.setPromptText("Email");
        emailField.setPrefSize(220,20);
        HBox box = new HBox(5);
        box.setPadding(new Insets(275, 20 , 20, 470));
        box.getChildren().add(emailField);
        loginBox.getChildren().add(box);

        // Add a password field to the login form
        TextField passwordField = new TextField();
        passwordField.setPromptText("Password");
        passwordField.setPrefSize(220,20);
        HBox box2 = new HBox(5);
        box2.setPadding(new Insets(5, 10 , 10, 470));
        box2.getChildren().add(passwordField);
        loginBox.getChildren().add(box2);

        // Add a login button to the login form
        Button loginButton = new Button("Login");
        loginButton.setPrefSize(80,20);
        loginButton.setLayoutX(560);
        loginButton.setLayoutY(480);

        loginButton.setOnAction(event -> {

        });
        root.getChildren().add(loginButton);
        root.getChildren().add(loginBox);

        // Create a scene and show the window
        Scene scene = new Scene(root, 1200, 700);
        primaryStage.setTitle("AlgomaU Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
