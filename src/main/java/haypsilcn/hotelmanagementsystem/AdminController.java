package haypsilcn.hotelmanagementsystem;

import haypsilcn.hotelmanagementsystem.customer.BookingCustomer;
import haypsilcn.hotelmanagementsystem.customer.Customer;
import haypsilcn.hotelmanagementsystem.database.*;
import haypsilcn.hotelmanagementsystem.exceptions.AdminAlreadyExists;
import haypsilcn.hotelmanagementsystem.exceptions.EmployeeAlreadyExist;
import haypsilcn.hotelmanagementsystem.hotel.Employee;
import haypsilcn.hotelmanagementsystem.hotel.Room;
import haypsilcn.hotelmanagementsystem.login.Admin;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class AdminController {

    public TabPane tabPane;
    public TextField search;
    public Button searchButton;
    public TabPane addTabPane;
    public Tab newAdminTab;
    public TextField newUsernameTextField;
    public PasswordField passwordField;
    public PasswordField confirmPass;
    public Button newAdminButton;
    public Tab newRoomTab;
    public TextField roomTextField;
    public ComboBox roomType;
    public Button newRoomButton;
    public Tab newCustomerTab;
    public TextField customerFirstName;
    public TextField customerLastName;
    public DatePicker customerDoB;
    public ComboBox customerGender;
    public TextField customerRoom;
    public DatePicker customerCheckin;
    public DatePicker customerCheckout;
    public Button newCustomerButton;
    public Tab newEmployeeTab;
    public TextField employeeFirstName;
    public TextField employeeLastName;
    public DatePicker employeeDoB;
    public ComboBox<String> employeeGender;
    public ComboBox<String> employeeDepartment;
    public Button newEmployeeButton;
    public Button reload;

    private Stage stage;
    private Scene scene;

    private AdminDB adminDB;
    private CustomerDB customerDB;
    private RoomDB roomDB;
    private EmployeeDB employeeDB;
    private ResultSet resultSet;

    private Alert alert;
    private TableView<Admin> adminTable;
    private TableView<Room> roomTable;
    private TableView<Customer> customerRoomTable;
    private TableView<Employee> employeeTable;
    private TableView<BookingCustomer> bookingCustomerTable;

    private final ObservableList<Admin> adminObservableList = FXCollections.observableArrayList();
    private final ObservableList<Room> roomObservableList = FXCollections.observableArrayList();
    private final ObservableList<Customer> customerObservableList = FXCollections.observableArrayList();
    private final ObservableList<Employee> employeeObservableList = FXCollections.observableArrayList();
    private final ObservableList<BookingCustomer> bookingCustomerObservableList = FXCollections.observableArrayList();

    public Text welcomeText;
    public Parent root;
    public MenuBar menuBar;

    public void setupAdmin(Admin admin) throws SQLException {
        try {
            adminDB = new AdminDB();
            customerDB = new CustomerDB();
            roomDB = new RoomDB();
            employeeDB  = new EmployeeDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        alert = new Alert(Alert.AlertType.ERROR);
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
        Tab customerTab = new Tab("customer", getCustomerRoomTable());
        Tab emptyRoomTab = new Tab("room", getRoomTable());
        Tab employeeTab = new Tab("employee", getEmployeeTable());
        Tab bookingTab = new Tab("booking", getBookingCustomerTable());

        adminTab.setStyle("-fx-font-size: 15px");
        customerTab.setStyle("-fx-font-size: 15px");
        emptyRoomTab.setStyle("-fx-font-size: 15px");
        employeeTab.setStyle("-fx-font-size: 15px");
        bookingTab.setStyle("-fx-font-size: 15px");

        tabPane.getTabs().addAll(adminTab, customerTab, emptyRoomTab, employeeTab, bookingTab);


        newAdminTab.setStyle("-fx-font-size: 15px");
        newEmployeeTab.setStyle("-fx-font-size: 15px");
        newCustomerTab.setStyle("-fx-font-size: 15px");
        newRoomTab.setStyle("-fx-font-size: 15px");

        setNewEmployeeTab();


        newAdminButton.setOnAction(event -> {
            try {
                createUser(admin);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (AdminAlreadyExists e) {
                System.out.println(e);
                alert.setContentText("<<" + newUsernameTextField.getText() + ">> is already exist!");
                alert.showAndWait();
            }
            newUsernameTextField.clear();
            passwordField.clear();
            confirmPass.clear();
        });

        newEmployeeButton.setOnAction(event -> {
            try {
                createEmployee();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (EmployeeAlreadyExist e) {
                System.out.println(e);
                alert.setContentText("Employee <<" + employeeFirstName.getText() + ", " + employeeLastName.getText() + ">> is already exists!");
                alert.showAndWait();
            } catch (DateTimeParseException e) {
                System.out.println(e);
                alert.setContentText("DoB format is: d/M/yyyy. Please check again!");
                alert.showAndWait();
            }
        });

    }


    /**
     * Show all admin user from database except currently logged in admin
     * @param admin
     * @return
     * @throws SQLException
     */
    private TableView<Admin> getAdminTable(Admin admin) throws SQLException {

        adminTable = new TableView<>();
        adminTable.setEditable(false);
        adminTable.setFixedCellSize(30);

        TableColumn<Admin, String> username = new TableColumn<>("username");
        TableColumn<Admin, String> password = new TableColumn<>("password");

        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        password.setCellValueFactory(new PropertyValueFactory<>("password"));

        adminTable.getColumns().addAll(username, password);
        reloadAdminTable(admin);

        return adminTable;
    }

    /**
     * Show all the empty room
     * @return
     * @throws SQLException
     */
    private TableView<Room> getRoomTable() throws SQLException {

        roomTable = new TableView<>();
        roomTable.setFixedCellSize(30);
        roomTable.setEditable(false);
        TableColumn<Room, String> roomNr = new TableColumn<>("roomNr");
        TableColumn<Room, String> type = new TableColumn<>("type");
        roomNr.setCellValueFactory(new PropertyValueFactory<>("number"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));

        roomTable.getColumns().addAll(roomNr, type);
        resultSet = roomDB.emptyRoom();
        while (resultSet.next())
            roomTable.getItems().add(new Room(resultSet.getInt(1), resultSet.getString(2)));
        return roomTable;
    }

    /**
     * Show all customer info and their room number
     * @return
     * @throws SQLException
     */
    private TableView<Customer> getCustomerRoomTable() throws SQLException {

        customerRoomTable = new TableView<>();
        customerRoomTable.setFixedCellSize(30);

        TableColumn<Customer, Integer> id = new TableColumn<>("id");
        TableColumn<Customer, String> firstName = new TableColumn<>("firstname");
        TableColumn<Customer, String> lastName = new TableColumn<>("lastname");
        TableColumn<Customer, String> birthday = new TableColumn<>("birthday");
        TableColumn<Customer, String> gender = new TableColumn<>("gender");
        TableColumn<Customer, Integer> room = new TableColumn<>("room");
        TableColumn<Customer, String> checkin = new TableColumn<>("checkin");
        TableColumn<Customer, String> checkout = new TableColumn<>("checkout");

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        birthday.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        room.setCellValueFactory(new PropertyValueFactory<>("room"));
        checkin.setCellValueFactory(new PropertyValueFactory<>("checkin"));
        checkout.setCellValueFactory(new PropertyValueFactory<>("checkout"));

        customerRoomTable.getColumns().addAll(id, firstName, lastName, birthday, gender, room, checkin, checkout);
        resultSet = customerDB.showAll();
        while (resultSet.next())
            customerRoomTable.getItems().add(new Customer(
                    resultSet.getInt(1), // id
                    resultSet.getString(2), // first name
                    resultSet.getString(3), // last name
                    resultSet.getString(4), // birthday
                    resultSet.getString(5), // gender
                    resultSet.getInt(6), //room
                    resultSet.getString(7), // check in
                    resultSet.getString(8) // check out
            ));
        return customerRoomTable;

    }

    /**
     * Show all employee info
     * @return
     * @throws SQLException
     */
    private TableView<Employee> getEmployeeTable() throws SQLException {

        employeeTable = new TableView<>();
        employeeTable.setFixedCellSize(30);

        TableColumn<Employee, Integer> id = new TableColumn<>("id");
        TableColumn<Employee, String> firstName = new TableColumn<>("firstname");
        TableColumn<Employee, String> lastName = new TableColumn<>("lastname");
        TableColumn<Employee, String> dob = new TableColumn<>("dob");
        TableColumn<Employee, String> gender = new TableColumn<>("gender");
        TableColumn<Employee, String> department = new TableColumn<>("department");

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        dob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        department.setCellValueFactory(new PropertyValueFactory<>("department"));

        employeeTable.getColumns().addAll(id, firstName, lastName, dob, gender, department);
        reloadEmployeeTable();

        return employeeTable;
    }

    /**
     * Show all booking info
     * @return
     * @throws SQLException
     */
    private TableView<BookingCustomer> getBookingCustomerTable() throws SQLException {

        bookingCustomerTable = new TableView<>();
        bookingCustomerTable.setFixedCellSize(30);

        TableColumn<BookingCustomer, Integer> id = new TableColumn<>("id");
        TableColumn<BookingCustomer, String> firstName = new TableColumn<>("firstname");
        TableColumn<BookingCustomer, String> lastName = new TableColumn<>("lastname");
        TableColumn<BookingCustomer, String> dob = new TableColumn<>("dob");
        TableColumn<BookingCustomer, String> gender = new TableColumn<>("gender");
        TableColumn<BookingCustomer, String> roomType = new TableColumn<>("roomType");
        TableColumn<BookingCustomer, String> checkin = new TableColumn<>("checkin");
        TableColumn<BookingCustomer, String> checkout = new TableColumn<>("checkout");

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        dob.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        roomType.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        checkin.setCellValueFactory(new PropertyValueFactory<>("checkin"));
        checkout.setCellValueFactory(new PropertyValueFactory<>("checkout"));

        bookingCustomerTable.getColumns().addAll(id, firstName, lastName, dob, gender, roomType, checkin, checkout);
        resultSet = customerDB.showBooking();
        while (resultSet.next())
            bookingCustomerTable.getItems().add(new BookingCustomer(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8)
            ));

        return bookingCustomerTable;
    }

    private void createUser(Admin admin) throws SQLException, AdminAlreadyExists {
        if (!newUsernameTextField.getText().isEmpty() && !passwordField.getText().isEmpty() && !confirmPass.getText().isEmpty()) {
            if (!passwordField.getText().equals(confirmPass.getText())) {
                alert.setContentText("Confirm password not match!");
                alert.showAndWait();
            } else {
                Admin newAdmin = new Admin(newUsernameTextField.getText(), passwordField.getText());
                if (adminDB.createAdmin(newAdmin))
                    reloadAdminTable(admin);
                else {
                    alert.setContentText("<<" + newAdmin.getUsername() + ">> is already exist!");
                    alert.showAndWait();
                }
            }
        } else if (newUsernameTextField.getText().isEmpty()) {
            alert.setContentText("Please insert username!");
            alert.showAndWait();
        } else if (passwordField.getText().isEmpty()) {
            alert.setContentText("Pleaser insert password");
            alert.showAndWait();
        } else if (confirmPass.getText().isEmpty()) {
            alert.setContentText("Please confirm your password");
            alert.showAndWait();
        }
    }

    private void setNewEmployeeTab() {
        // set up date picker dob
        employeeDoB.setValue(LocalDate.now());
        employeeDoB.setStyle("-fx-font-size: 15px");

        // set up combo box gender
        employeeGender.getItems().addAll("M", "F");
        employeeGender.setValue("M"); // default value for gender combo box
        employeeGender.setStyle("-fx-font-size: 15px");

        // set up combo box department
        employeeDepartment.getItems().addAll("Manager", "Reception", "Driver", "Housekeeper");
        employeeDepartment.setValue("Reception");
        employeeDepartment.setStyle("-fx-font-size: 15px");
    }

    private void createEmployee() throws SQLException, EmployeeAlreadyExist, DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        if (!employeeFirstName.getText().isEmpty() && !employeeLastName.getText().isEmpty()) {
            Employee newEmployee = new Employee(
                    employeeFirstName.getText(),
                    employeeLastName.getText(),
                    LocalDate.parse(employeeDoB.getEditor().getText(), formatter).toString(),
                    employeeGender.getValue(),
                    employeeDepartment.getValue()
            );
            if (employeeDB.createEmployee(newEmployee)) {
                reloadEmployeeTable();
                employeeFirstName.clear();
                employeeLastName.clear();
            } else {
                alert.setContentText("Employee <<" + newEmployee.getFirstName() + " " + newEmployee.getLastName() + ">> is already exists!");
                alert.showAndWait();
            }
        } else if (employeeFirstName.getText().isEmpty()) {
            alert.setContentText("Please insert employee first name!");
            alert.showAndWait();
        } else if (employeeLastName.getText().isEmpty()) {
            alert.setContentText("Please insert employee last name!");
            alert.showAndWait();
        }
    }

    private void reloadAdminTable(Admin admin) throws SQLException {
        adminObservableList.clear();
        resultSet = adminDB.showAllExcept(admin);
        while (resultSet.next()) {
            adminObservableList.add(new Admin(resultSet.getString(2), resultSet.getString(3)));
            adminTable.setItems(adminObservableList);
        }
    }
    
    private void reloadEmployeeTable() throws SQLException {
        employeeObservableList.clear();
        resultSet = employeeDB.showAll();
        while (resultSet.next()) {
            employeeObservableList.add(new Employee(
                    resultSet.getInt(1),
                    resultSet.getString(2), // first name
                    resultSet.getString(3), // last name
                    resultSet.getString(4), // dob
                    resultSet.getString(5), // gender
                    resultSet.getString(6) // department
            ));
            employeeTable.setItems(employeeObservableList);
        }
    }
}
