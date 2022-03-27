package haypsilcn.hotelmanagementsystem;

import haypsilcn.hotelmanagementsystem.customer.Customer;
import haypsilcn.hotelmanagementsystem.database.AdminDB;
import haypsilcn.hotelmanagementsystem.database.CustomerDB;
import haypsilcn.hotelmanagementsystem.database.Database;
import haypsilcn.hotelmanagementsystem.database.RoomDB;
import haypsilcn.hotelmanagementsystem.hotel.Room;
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

    public TabPane tabPane;
    public TextField search;
    public Button searchButton;
    private Stage stage;
    private Scene scene;
    private AdminDB adminDB;
    private CustomerDB customerDB;
    private RoomDB roomDB;
    private TableView<Admin> adminTable;
    private TableView<Customer> customerTable;
    private TableView<Room> roomTable;
    private TableView<Customer> customerRoomTable;

    public Text welcomeText;
    public Parent root;
    public MenuBar menuBar;

    public void setupAdmin(Admin admin) throws SQLException {
        try {
            adminDB = new AdminDB();
            customerDB = new CustomerDB();
            roomDB = new RoomDB();
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


        Tab adminTab = new Tab("admin", getAdminTable(admin));
        Tab customerTab = new Tab("customer", getCustomerTable());
        Tab emptyRoomTab = new Tab("room", getRoomTable());
        Tab cusRoomTab = new Tab("customer_room", getCustomerRoomTable());

        adminTab.setStyle("-fx-font-size: 15px");

        tabPane.getTabs().addAll(adminTab, customerTab, emptyRoomTab, cusRoomTab);
    }


    private TableView<Customer> getCustomerTable() throws SQLException {

        customerTable = new TableView<>();
        customerTable.setEditable(false);
        customerTable.setFixedCellSize(30);
        TableColumn<Customer, String> firstName = new TableColumn<>("firstname");
        TableColumn<Customer, String> lastName = new TableColumn<>("lastname");
        TableColumn<Customer, String> birthday = new TableColumn<>("birthday");
        TableColumn<Customer, String> gender = new TableColumn<>("gender");

        firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        birthday.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        gender.setCellValueFactory(new PropertyValueFactory<>("gender"));

        customerTable.getColumns().addAll(firstName, lastName, birthday, gender);
        ResultSet customerResultSet = customerDB.showAll();
        while (customerResultSet.next())
            customerTable.getItems().add(new Customer(customerResultSet.getString(2), customerResultSet.getString(3), customerResultSet.getString(4), customerResultSet.getString(5)));

        return customerTable;
    }

    private TableView<Admin> getAdminTable(Admin admin) throws SQLException {

        adminTable = new TableView<>();
        adminTable.setEditable(true);
        adminTable.setFixedCellSize(30);
        TableColumn<Admin, String> username = new TableColumn<>("username");
        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        TableColumn<Admin, String> password = new TableColumn<>("password");
        password.setCellValueFactory(new PropertyValueFactory<>("password"));
        adminTable.getColumns().addAll(username, password);
        ResultSet adminResultSet = adminDB.showAllExcept(admin);
        while (adminResultSet.next())
            adminTable.getItems().add(new Admin(adminResultSet.getString(2), adminResultSet.getString(3)));

        return adminTable;
    }

    private TableView<Room> getRoomTable() throws SQLException {

        roomTable = new TableView<>();
        roomTable.setFixedCellSize(30);
        roomTable.setEditable(false);
        TableColumn<Room, String> roomNr = new TableColumn<>("roomNr");
        TableColumn<Room, String> type = new TableColumn<>("type");
        roomNr.setCellValueFactory(new PropertyValueFactory<>("number"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));

        roomTable.getColumns().addAll(roomNr, type);
        ResultSet roomResultSet = roomDB.emptyRoom();
        while (roomResultSet.next())
            roomTable.getItems().add(new Room(roomResultSet.getInt(1), roomResultSet.getString(2)));
        return roomTable;
    }

    private TableView<Customer> getCustomerRoomTable() throws SQLException {
        customerRoomTable = new TableView<>();
        customerRoomTable.setFixedCellSize(30);

        TableColumn<Customer, Integer> id = new TableColumn<>("id");
        TableColumn<Customer, String> firstName = new TableColumn<>("firstname");
        TableColumn<Customer, String> lastName = new TableColumn<>("lastname");
        TableColumn<Customer, Room> room = new TableColumn<>("room");
        TableColumn<Customer, String> checkin = new TableColumn<>("checkin");
        TableColumn<Customer, String> checkout = new TableColumn<>("checkout");

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        room.setCellValueFactory(new PropertyValueFactory<>("room"));
        checkin.setCellValueFactory(new PropertyValueFactory<>("checkin"));
        checkout.setCellValueFactory(new PropertyValueFactory<>("checkout"));

        customerRoomTable.getColumns().addAll(id, firstName, lastName, room, checkin, checkout);
        ResultSet resultSet = roomDB.customerRoom();
        while (resultSet.next())
            customerRoomTable.getItems().add(new Customer(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            ));
        return customerRoomTable;

    }
}
