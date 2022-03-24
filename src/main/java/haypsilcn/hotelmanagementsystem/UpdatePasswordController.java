package haypsilcn.hotelmanagementsystem;

import haypsilcn.hotelmanagementsystem.database.AdminDB;
import haypsilcn.hotelmanagementsystem.exceptions.AdminLoginAuthorize;
import haypsilcn.hotelmanagementsystem.exceptions.AdminNotFound;
import haypsilcn.hotelmanagementsystem.exceptions.AdminUpdatePasswordException;
import haypsilcn.hotelmanagementsystem.login.Admin;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class UpdatePasswordController {

    public AnchorPane root;
    public Button update;
    public PasswordField oldPassword;
    public PasswordField newPassword;
    public PasswordField confirmPassword;
    public Button reset;
    public MenuBar menuBar;
    private Stage stage;
    private Scene scene;
    private Admin adminUpdate;
    private AdminDB adminDB;

    public void setupPassword(Admin admin) {

        Admin currentAdmin = new Admin(admin.getUsername(), admin.getPassword());

        Alert alert = new Alert(Alert.AlertType.ERROR);
        try {
            adminDB = new AdminDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        // Setup Menu Bar
        ImageView arrImg = new ImageView("file:src/main/img/back-arrow.png");
        arrImg.setFitWidth(15);
        arrImg.setFitHeight(15);
        Label arrLabel = new Label();
        arrLabel.setGraphic(arrImg);
        arrLabel.setOnMouseClicked(event -> {
            try {
                previousController(currentAdmin, event);
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }

        });

        Menu logout = new Menu();
        logout.setGraphic(arrLabel);
        Menu edit = new Menu("Edit");
        Menu add = new Menu("Add");
        Menu sorting = new Menu("Sorting");
        Menu search = new Menu("Search");

        edit.setDisable(true);
        add.setDisable(true);
        sorting.setDisable(true);
        search.setDisable(true);

        menuBar.getMenus().addAll(logout, edit, add, sorting, search);

        reset.setOnMouseClicked(event -> {
            oldPassword.clear();
            newPassword.clear();
            confirmPassword.clear();
        });

        update.setOnMouseClicked(event -> {
            if (!oldPassword.getText().isEmpty() && !newPassword.getText().isEmpty() && !confirmPassword.getText().isEmpty()) {
                if (newPassword.getText().equals(confirmPassword.getText())) {
                    adminUpdate = new Admin(admin.getUsername(), oldPassword.getText());
                    try {
                        if (adminDB.updatePassword(adminUpdate, newPassword.getText())) {
                            previousController(adminUpdate, event);
                        } else {
                            alert.setContentText("Old password incorrect. Cannot update new password!");
                            alert.showAndWait();
                        }
                    } catch (SQLException | IOException e) {
                        e.printStackTrace();
                    } catch (AdminNotFound | AdminLoginAuthorize | AdminUpdatePasswordException e) {
                        System.out.println(e);
                    }
                } else {
                    alert.setContentText("Confirm password is not macht!");
                    alert.showAndWait();
                }
            } else if (oldPassword.getText().isEmpty()) {
                alert.setContentText("Please insert your old password!");
                alert.showAndWait();
            } else if (newPassword.getText().isEmpty()) {
                alert.setContentText("Please insert your new password");
                alert.showAndWait();
            } else if (confirmPassword.getText().isEmpty()) {
                alert.setContentText("Please confirm your new password");
                alert.showAndWait();
            }
        });
    }

    private void previousController(Admin admin, MouseEvent event) throws SQLException, IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("admin.fxml")));
        root = loader.load();

        AdminController adminController = loader.getController();
        adminController.setupAdmin(admin);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Admin");
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
