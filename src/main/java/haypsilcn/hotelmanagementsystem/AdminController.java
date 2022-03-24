package haypsilcn.hotelmanagementsystem;

import haypsilcn.hotelmanagementsystem.database.AdminDB;
import haypsilcn.hotelmanagementsystem.database.Database;
import haypsilcn.hotelmanagementsystem.login.Admin;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.StringBufferInputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class AdminController {
    public TableView adminTable;
    private Stage stage;
    private Scene scene;
    private AdminDB adminDB;

    public Text welcomeText;
    public Parent root;
    public MenuBar menuBar;

    public void setupAdmin(Admin admin) throws SQLException {
        try {
            adminDB = new AdminDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        welcomeText.setText("Welcome, " + admin.getUsername());

        // Setup Menu Bar
        ImageView logoutIcon = new ImageView("file:src/main/img/logout.png");
        logoutIcon.setFitWidth(20);
        logoutIcon.setFitHeight(20);
        Label logoutLabel = new Label();
        logoutLabel.setGraphic(logoutIcon);
        logoutLabel.setOnMouseClicked(event -> {
            System.out.println("Logout as <<" + admin.getUsername() + ">>");
            try {
                FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("main.fxml")));
                root = loader.load();
                Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                stage.setTitle("Hotel Manager System");
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        Menu logout = new Menu();
        logout.setGraphic(logoutLabel);
        Menu edit = new Menu("Edit");
        Menu add = new Menu("Add");
        Menu sorting = new Menu("Sorting");
        Menu search = new Menu("Search");

        if (admin.getUsername().equals("admin")) {
            MenuItem editPass = new MenuItem("Edit Password");
            MenuItem editUser = new MenuItem("Edit Admin");
            edit.getItems().addAll(editPass, editUser);

            MenuItem addEmployee = new MenuItem("New Employee");
            MenuItem addAdmin = new MenuItem("New Admin");
            MenuItem addRoom = new MenuItem("New Room");
            add.getItems().addAll(addAdmin, addEmployee, addRoom);

            editPass.setOnAction(event -> {
                stage = (Stage) root.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("update-password.fxml")));
                try {
                    root = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                UpdatePasswordController updatePasswordController = loader.getController();
                updatePasswordController.setupPassword(admin);

                scene = new Scene(root);
                stage.setTitle("Update Password");
                stage.setScene(scene);
                stage.show();
            });
        }

        menuBar.getMenus().addAll(logout, edit, add, sorting, search);

        adminTable.setEditable(true);
        TableColumn<Admin, String> username = new TableColumn<>("username");
        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        TableColumn<Admin, String> password = new TableColumn<>("password");
        password.setCellValueFactory(new PropertyValueFactory<>("password"));
        adminTable.getColumns().addAll(username, password);
        ResultSet resultSet = adminDB.showAllExcept(admin);
        while (resultSet.next()) {
            adminTable.getItems().add(new Admin(resultSet.getString(2), resultSet.getString(3)));
        }
    }

}
