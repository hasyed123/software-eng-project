package org.example.presentation.view;

import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.HibernateModule;
import org.example.business.CemsService;
import javafx.fxml.*;

import java.util.Objects;

public class App extends Application {
    public static Injector injector = Guice.createInjector(new HibernateModule());
    CemsService service = new CemsService(injector);
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("LoginWindow.fxml")));
        primaryStage.setTitle("App");
        primaryStage.setScene(new Scene(root, 1000, 1000));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
