package haypsilcn.hotelmanagementsystem;

import haypsilcn.hotelmanagementsystem.database.AdminDB;
import haypsilcn.hotelmanagementsystem.database.Database;
import haypsilcn.hotelmanagementsystem.exceptions.AdminLoginAuthorize;
import haypsilcn.hotelmanagementsystem.exceptions.AdminNotFound;
import haypsilcn.hotelmanagementsystem.login.Admin;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainController implements Initializable, Database {

    private Stage stage;
    private Scene scene;
    private Admin admin;
    private AdminDB adminDB;

    public Label usernameLabel;
    public Label passwordLabel;
    public TextField usernameTextField;
    public PasswordField passwordFeld;
    public Button reset;
    public GridPane gridPane;
    public MenuBar menuBar;
    public Button login;
    public Parent root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        try {
            adminDB = new AdminDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Alert alert = new Alert(Alert.AlertType.ERROR);
        setMenuBar(menuBar);
        menuBar.setDisable(true);

        login.setOnMouseClicked(event -> {
            if (!usernameTextField.getText().isEmpty() && !passwordFeld.getText().isEmpty()) {
                admin = new Admin(usernameTextField.getText(), passwordFeld.getText());
                try {
                    if (adminDB.loginAuthorize(admin)) {
                        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("admin.fxml")));
                        root = loader.load();

                        AdminController adminController = loader.getController();
                        adminController.setupAdmin(admin);

                        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setTitle("Admin");
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    } else {
                        alert.setContentText("Username and Password not macht!");
                        alert.showAndWait();
                    }
                } catch (SQLException | AdminLoginAuthorize | AdminNotFound | IOException e) {
                    e.printStackTrace();
                }
            }
            else {
                if (usernameTextField.getText().isEmpty() && passwordFeld.getText().isEmpty())
                    alert.setContentText("Please log in to continue!");
                else if (passwordFeld.getText().isEmpty())
                    alert.setContentText("Please insert your password!");
                else if (usernameTextField.getText().isEmpty())
                    alert.setContentText("Please insert your username!");
                alert.showAndWait();
            }
        });

        reset.setOnMouseClicked(event -> {
            usernameTextField.clear();
            passwordFeld.clear();
        });
    }

    private void setMenuBar(MenuBar menuBar) {
        this.menuBar = menuBar;

        ImageView logoutIcon = new ImageView("file:src/main/img/logout.png");
        logoutIcon.setFitWidth(20);
        logoutIcon.setFitHeight(20);
        Label logoutLabel = new Label();
        logoutLabel.setGraphic(logoutIcon);

        Menu logout = new Menu();
        logout.setGraphic(logoutLabel);
        Menu edit = new Menu("Edit");
        Menu add = new Menu("Add");
        Menu sorting = new Menu("Sorting");
        Menu search = new Menu("Search");

        menuBar.getMenus().addAll(logout, edit, add, sorting, search);
    }
}