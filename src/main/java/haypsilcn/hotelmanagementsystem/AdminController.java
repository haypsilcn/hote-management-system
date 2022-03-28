package haypsilcn.hotelmanagementsystem;

import haypsilcn.hotelmanagementsystem.customer.BookingCustomer;
import haypsilcn.hotelmanagementsystem.customer.Customer;
import haypsilcn.hotelmanagementsystem.database.*;
import haypsilcn.hotelmanagementsystem.exceptions.AdminAlreadyExists;
import haypsilcn.hotelmanagementsystem.exceptions.EmployeeAlreadyExist;
import haypsilcn.hotelmanagementsystem.exceptions.RoomAlreadyExist;
import haypsilcn.hotelmanagementsystem.hotel.Employee;
import haypsilcn.hotelmanagementsystem.hotel.Room;
import haypsilcn.hotelmanagementsystem.login.Admin;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
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

    @FXML
    public Button newRoomButton;
    @FXML
    public Button searchButton;
    @FXML
    public Button newAdminButton;
    @FXML
    public Button newEmployeeButton;
    @FXML
    public Button reload;
    @FXML
    public Button doubleRoomButton;
    @FXML
    public Button deluxeRoomButton;
    @FXML
    public Button luxuryRoomButton;
    @FXML
    public Button singleRoomButton;

    @FXML
    public PasswordField passwordField;
    @FXML
    public PasswordField confirmPass;

    @FXML
    public ComboBox<Integer> singleRoomNr;
    @FXML
    public ComboBox<Integer> doubleRoomNr;
    @FXML
    public ComboBox<Integer> deluxeRoomNr;
    @FXML
    public ComboBox<Integer> luxuryRoomNr;
    @FXML
    public ComboBox<String> roomType;
    @FXML
    public ComboBox<String> employeeGender;
    @FXML
    public ComboBox<String> employeeDepartment;
    @FXML
    public ComboBox<String> singleGender;
    @FXML
    public ComboBox<String> doubleGender1;
    @FXML
    public ComboBox<String> doubleKidGender;
    @FXML
    public ComboBox<String> doubleGender2;
    @FXML
    public ComboBox<String> deluxeGender1;
    @FXML
    public ComboBox<String> deluxeGender2;
    @FXML
    public ComboBox<String> deluxeKidGender;
    @FXML
    public ComboBox<String> deluxeGender3;
    @FXML
    public ComboBox<String> deluxeGender4;
    @FXML
    public ComboBox<String> luxuryGender1;
    @FXML
    public ComboBox<String> luxuryGender2;
    @FXML
    public ComboBox<String> luxuryKidGender;
    @FXML
    public ComboBox<String> luxuryGender3;
    @FXML
    public ComboBox<String> luxuryGender4;


    @FXML
    public DatePicker employeeDoB;
    @FXML
    public DatePicker singleDoB;
    @FXML
    public DatePicker singleCheckIn;
    @FXML
    public DatePicker singleCheckOut;
    @FXML
    public DatePicker doubleDoB1;
    @FXML
    public DatePicker doubleCheckIn;
    @FXML
    public DatePicker doubleCheckOut;
    @FXML
    public DatePicker doubleDoB2;
    @FXML
    public DatePicker doubleKidDoB;
    @FXML
    public DatePicker deluxeDoB1;
    @FXML
    public DatePicker deluxeCheckIn;
    @FXML
    public DatePicker deluxeCheckOut;
    @FXML
    public DatePicker deluxeDoB2;
    @FXML
    public DatePicker deluxeKidDoB;
    @FXML
    public DatePicker deluxeDoB3;
    @FXML
    public DatePicker deluxeDoB4;
    @FXML
    public DatePicker luxuryDoB1;
    @FXML
    public DatePicker luxuryCheckIn;
    @FXML
    public DatePicker luxuryCheckOut;
    @FXML
    public DatePicker luxuryDoB2;

    @FXML
    public DatePicker luxuryKidDoB;
    @FXML
    public DatePicker luxuryDoB3;
    @FXML
    public DatePicker luxuryDoB4;

    @FXML
    public CheckBox deluxeKidTickBox;
    @FXML
    public CheckBox doubleKidTickBox;
    @FXML
    public CheckBox luxuryKidTickBox;

    @FXML
    public Text welcomeText;
    @FXML
    public Parent root;
    @FXML
    public MenuBar menuBar;

    @FXML
    public TabPane tabPane;
    @FXML
    public TabPane addTabPane;

    @FXML
    public Tab newAdminTab;
    @FXML
    public Tab newRoomTab;
    @FXML
    public Tab checkInTab;
    @FXML
    public Tab newEmployeeTab;
    @FXML
    public Tab singleRoomTab;
    @FXML
    public Tab doubleRoomTab;
    @FXML
    public Tab deluxeRoomTab;
    @FXML
    public Tab luxuryRoomTab;

    @FXML
    public TextField search;
    @FXML
    public TextField newUsernameTextField;
    @FXML
    public TextField roomNrTextField;
    @FXML
    public TextField employeeFirstName;
    @FXML
    public TextField employeeLastName;
    @FXML
    public TextField singleLastName;
    @FXML
    public TextField doubleFirstName1;
    @FXML
    public TextField doubleLastName1;
    @FXML
    public TextField doubleKidFirstName;
    @FXML
    public TextField doubleKidLastName;
    @FXML
    public TextField doubleFirstName2;
    @FXML
    public TextField doubleLastName2;
    @FXML
    public TextField deluxeFirstName1;
    @FXML
    public TextField deluxeLastName1;
    @FXML
    public TextField deluxeFirstName2;
    @FXML
    public TextField deluxeLastName2;
    @FXML
    public TextField deluxeKidFirstName;
    @FXML
    public TextField deluxeKidLastName;
    @FXML
    public TextField deluxeFirstName3;
    @FXML
    public TextField deluxeLastName3;
    @FXML
    public TextField deluxeFirstName4;
    @FXML
    public TextField deluxeLastName4;
    @FXML
    public TextField luxuryFirstName1;
    @FXML
    public TextField luxuryLastName1;
    @FXML
    public TextField luxuryFirstName2;
    @FXML
    public TextField luxuryLastName2;
    @FXML
    public TextField luxuryKidFirstName;
    @FXML
    public TextField luxuryKidLastName;
    @FXML
    public TextField luxuryFirstName3;
    @FXML
    public TextField luxuryLastName3;
    @FXML
    public TextField luxuryFirstName4;
    @FXML
    public TextField luxuryLastName4;
    @FXML
    public TextField singleFirstName;

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
    private TableView<Customer> customerTable;
    private TableView<Employee> employeeTable;
    private TableView<BookingCustomer> bookingCustomerTable;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");

    private final ObservableList<Admin> adminObservableList = FXCollections.observableArrayList();
    private final ObservableList<Room> roomObservableList = FXCollections.observableArrayList();
    private final ObservableList<Customer> customerObservableList = FXCollections.observableArrayList();
    private final ObservableList<Employee> employeeObservableList = FXCollections.observableArrayList();


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
        Tab customerTab = new Tab("customer", getCustomerTable());
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
        checkInTab.setStyle("-fx-font-size: 15px");
        newRoomTab.setStyle("-fx-font-size: 15px");

        setNewEmployeeTab();
        setNewRoomTab();
        setCheckInTab();

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

        newRoomButton.setOnAction(event -> {
            try {
                createRoom();
            } catch (RoomAlreadyExist e) {
                System.out.println(e);
                alert.setContentText("Room <<" + roomNrTextField.getText() + ">> is already exists!");
                alert.showAndWait();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            roomNrTextField.clear();
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
        reloadRoomTable();

        return roomTable;
    }

    /**
     * Show all customer info and their room number
     * @return
     * @throws SQLException
     */
    private TableView<Customer> getCustomerTable() throws SQLException {

        customerTable = new TableView<>();
        customerTable.setFixedCellSize(30);

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

        customerTable.getColumns().addAll(id, firstName, lastName, birthday, gender, room, checkin, checkout);
        reloadCustomerTable();

        return customerTable;
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

    private void setNewEmployeeTab() {
        setDatePicker(employeeDoB);
        setComboBoxGender(employeeGender);

        // set up combo box department
        employeeDepartment.getItems().addAll("Manager", "Reception", "Driver", "Housekeeper");
        employeeDepartment.setValue("Reception");
        employeeDepartment.setStyle("-fx-font-size: 15px");
    }

    private void setNewRoomTab() {
        // set default value for combo box type of room
        roomType.getItems().addAll("Single", "Double", "Deluxe", "Luxury");
        roomType.setValue("Double");
        roomType.setStyle("-fx-font-size: 15px");

        // room number only accepts positive integers 0-9 with length of 3 digits
        roomNrTextField.textProperty().addListener(((observableValue, oldValue, newValue) -> {
            if (!newValue.matches("\\d{0,3}"))
                roomNrTextField.setText(oldValue);
        }));
    }

    private void setCheckInTab() throws SQLException {
        setSingleRoomTab();
        setDoubleRoomTab();
        setDeluxeRoomTab();
        setLuxuryRoomTab();

    }

    private void setSingleRoomTab() throws SQLException {
        singleRoomTab.setStyle("-fx-font-size: 15px");

        setDatePicker(singleDoB);
        setDatePicker(singleCheckIn);
        setDatePicker(singleCheckOut);
        setComboBoxGender(singleGender);

        resultSet = roomDB.getEmptyRoom("Single");
        while (resultSet.next())
            singleRoomNr.getItems().add(resultSet.getInt(1));
        // set the default value = smallest empty room number
        singleRoomNr.setValue(singleRoomNr.getItems().get(0));
        singleRoomNr.setStyle("-fx-font-size: 15px");
    }
    
    private void setDoubleRoomTab() throws SQLException {
        doubleRoomTab.setStyle("-fx-font-size: 15px");

        setCheckBoxAction(doubleKidTickBox, doubleKidFirstName, doubleKidLastName, doubleKidDoB, doubleKidGender);
        setDatePicker(doubleDoB1);
        setDatePicker(doubleDoB2);
        setDatePicker(doubleCheckIn);
        setDatePicker(doubleCheckOut);

        setComboBoxGender(doubleGender1);
        setComboBoxGender(doubleGender2);

        resultSet = roomDB.getEmptyRoom("Double");
        while (resultSet.next())
            doubleRoomNr.getItems().add(resultSet.getInt(1));
        // set the default value = smallest empty room number
        doubleRoomNr.setValue(doubleRoomNr.getItems().get(0));
        doubleRoomNr.setStyle("-fx-font-size: 15px");
    }

    private void setDeluxeRoomTab() throws SQLException {
        deluxeRoomTab.setStyle("-fx-font-size: 15px");

        setCheckBoxAction(deluxeKidTickBox, deluxeKidFirstName, deluxeKidLastName, deluxeKidDoB, deluxeKidGender);
        setDatePicker(deluxeDoB1);
        setDatePicker(deluxeDoB2);
        setDatePicker(deluxeDoB3);
        setDatePicker(deluxeDoB4);
        setDatePicker(deluxeCheckIn);
        setDatePicker(deluxeCheckOut);

        setComboBoxGender(deluxeGender1);
        setComboBoxGender(deluxeGender2);
        setComboBoxGender(deluxeGender3);
        setComboBoxGender(deluxeGender4);

        resultSet = roomDB.getEmptyRoom("Deluxe");
        while (resultSet.next())
            deluxeRoomNr.getItems().add(resultSet.getInt(1));
        // set the default value = smallest empty room number
        deluxeRoomNr.setValue(doubleRoomNr.getItems().get(0));
        deluxeRoomNr.setStyle("-fx-font-size: 15px");
    }

    private void setLuxuryRoomTab() throws SQLException {
        luxuryRoomTab.setStyle("-fx-font-size: 15px");

        setCheckBoxAction(luxuryKidTickBox, luxuryKidFirstName, luxuryKidLastName, luxuryKidDoB, luxuryKidGender);
        setDatePicker(luxuryDoB1);
        setDatePicker(luxuryDoB2);
        setDatePicker(luxuryDoB3);
        setDatePicker(luxuryDoB4);
        setDatePicker(luxuryCheckIn);
        setDatePicker(luxuryCheckOut);

        setComboBoxGender(luxuryGender1);
        setComboBoxGender(luxuryGender2);
        setComboBoxGender(luxuryGender3);
        setComboBoxGender(luxuryGender4);

        resultSet = roomDB.getEmptyRoom("Luxury");
        while (resultSet.next())
            luxuryRoomNr.getItems().add(resultSet.getInt(1));
        // set the default value = smallest empty room number
        luxuryRoomNr.setValue(luxuryRoomNr.getItems().get(0));
        luxuryRoomNr.setStyle("-fx-font-size: 15px");
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

    private void createEmployee() throws SQLException, EmployeeAlreadyExist, DateTimeParseException {
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

    private void createRoom() throws RoomAlreadyExist, SQLException {
        if (!roomNrTextField.getText().isEmpty()) {
            Room newRoom = new Room(Integer.parseInt(roomNrTextField.getText()), roomType.getValue());
            if (newRoom.getNumber() < 100) {
                alert.setContentText("Room can only start with 100!");
                alert.showAndWait();
            } else
                if (roomDB.createRoom(newRoom))
                    reloadRoomTable();
                else {
                    alert.setContentText("Room <<" + newRoom.getNumber() + ">> is already exist!");
                    alert.showAndWait();
                }
        } else {
            alert.setContentText("Please insert room number!");
            alert.showAndWait();
        }
    }

    private void singleRoomCheckin() {
        if (!singleFirstName.getText().isEmpty() && !singleLastName.getText().isEmpty()) {

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

    private void reloadRoomTable() throws SQLException {
        roomObservableList.clear();
        resultSet = roomDB.emptyRoom();
        while (resultSet.next()) {
            roomObservableList.add(new Room(resultSet.getInt(1), resultSet.getString(2)));
            roomTable.setItems(roomObservableList);
        }
    }

    private void reloadCustomerTable() throws SQLException {
        customerObservableList.clear();
        resultSet = customerDB.showAll();
        while (resultSet.next()) {
            customerObservableList.add(new Customer(
                    resultSet.getInt(1), // customer id
                    resultSet.getString(2), // first name
                    resultSet.getString(3), // last name
                    resultSet.getString(4), // birthday
                    resultSet.getString(5), // gender
                    resultSet.getInt(6), // room number
                    resultSet.getString(7), // check in
                    resultSet.getString(8) // check out
            ));
            customerTable.setItems(customerObservableList);        
        }
    }

    private void showAlert(String message) {
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void setComboBoxGender(ComboBox<String> comboBoxGender) {
        comboBoxGender.getItems().addAll("F", "M");
        comboBoxGender.setValue("F");
        comboBoxGender.setStyle("-fx-font-size: 15px");
    }

    private void setDatePicker(DatePicker datePicker) {
        datePicker.setValue(LocalDate.now());
        datePicker.setStyle("-fx-font-size: 15px");
    }

    private void setCheckBoxAction(CheckBox checkBox, TextField firstName, TextField lastName, DatePicker dob, ComboBox<String> gender) {

        /*
         * Property description:
         * Determines whether the user toggling the CheckBox should cycle through all three states: checked, unchecked, and undefined.
         * If true then all three states will be cycled through;
         * if false then only checked and unchecked will be cycled.
         */
        checkBox.setAllowIndeterminate(false);
        checkBox.setSelected(false); // checkbox default as unchecked

        firstName.setDisable(true);
        lastName.setDisable(true);
        dob.setDisable(true);
        gender.setDisable(true);

        // trigger the action when toggling the checkbox
        checkBox.selectedProperty().addListener((observableValue, unchecked, checked) -> {
            if (unchecked) {
                firstName.setDisable(true);
                lastName.setDisable(true);
                dob.setDisable(true);
                gender.setDisable(true);
            } else {
                firstName.setDisable(false);
                lastName.setDisable(false);
                dob.setDisable(false);
                gender.setDisable(false);
                setDatePicker(dob);
                setComboBoxGender(gender);
            }
        });

    }
}
