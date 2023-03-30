package org.example.presentation.view;

import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.HibernateModule;
import org.example.business.CemsService;
import org.example.model.Club;
import org.example.model.User;


import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
public class LoginController  implements Initializable{

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button loginButton;

    @FXML
    private Label titleLabel;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField emailField;

    CemsService service = App.service;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                User user = service.tryCredentials(emailField.getText(), passwordField.getText());
                if (user != null){
                    Club club = service.getClubIfPresident(user.getId());
                    if(club == null) navigateToMemberPortal(event, user.getId());
                    else navigateToPresidentPortal(event, club.getName());
                }
                else{
                    titleLabel.setText("Incorrect");
                }
            }
        });
    }

    public void navigateToMemberPortal(ActionEvent event, String username) {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getClassLoader().getResource("memberPortal.fxml")));
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        MemberPortalController memberPortalController = loader.getController();
        memberPortalController.setUsername(username);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void navigateToPresidentPortal(ActionEvent event, String clubName) {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getClassLoader().getResource("presidentPortal.fxml")));
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        PresidentPortalController presidentPortalController = loader.getController();
        presidentPortalController.setClubName(clubName);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void loginUser(ActionEvent event, String username, String password){
        //changeScene()
    }
}