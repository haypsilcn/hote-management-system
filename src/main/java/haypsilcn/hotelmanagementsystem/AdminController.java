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
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

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
    public Button singleRoomButton;
    @FXML
    public Button reservationButton;
    @FXML
    public Button checkOutButton;

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
    public ComboBox<String> reservationGender;
    @FXML
    public ComboBox<Integer> singleQuantity;
    @FXML
    public ComboBox<Integer> doubleQuantity;
    @FXML
    public ComboBox<Integer> deluxeQuantity;
    @FXML
    public ComboBox<Integer> checkOutRoom;

    @FXML
    public DatePicker employeeDoB;
    @FXML
    public DatePicker singleDoB;
    @FXML
    public DatePicker singleCheckOut;
    @FXML
    public DatePicker doubleDoB1;
    @FXML
    public DatePicker doubleCheckOut;
    @FXML
    public DatePicker doubleDoB2;
    @FXML
    public DatePicker doubleKidDoB;
    @FXML
    public DatePicker deluxeDoB1;
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
    public DatePicker reservationDoB;

    @FXML
    public CheckBox deluxeKidTickBox;
    @FXML
    public CheckBox doubleKidTickBox;
    @FXML
    public CheckBox singleCheckBox;
    @FXML
    public CheckBox doubleCheckBox;
    @FXML
    public CheckBox deluxeCheckBox;
    @FXML
    public CheckBox luxuryCheckBox;
    @FXML
    public CheckBox doubleSecondCustomer;
    @FXML
    public CheckBox deluxeSecondCustomer;
    @FXML
    public CheckBox deluxeThirdCustomer;
    @FXML
    public CheckBox deluxeFourthCustomer;

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
    public Tab reservationTab;
    @FXML
    public Tab checkOutTab;

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
    public TextField singleFirstName;
    @FXML
    public TextField reservationFirstName;
    @FXML
    public TextField reservationLastName;
    @FXML
    public DatePicker reservationCheckIn;
    @FXML
    public DatePicker reservationCheckOut;

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
    private final ObservableList<BookingCustomer> bookingObservableList = FXCollections.observableArrayList();

    private boolean success1 = false;
    private boolean success2 = false;
    private boolean success3 = false;
    private boolean success4 = false;
    private boolean success5 = false;
    private boolean secondCustomer = true;
    private boolean thirdCustomer = true;
    private boolean fourthCustomer = true;
    private boolean extraPerson = true;

    private Customer customer1 = null;
    private Customer customer2 = null;
    private Customer customer3 = null;
    private Customer customer4 = null;
    private Customer customer5 = null;


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
        setNewEmployeeTab();
        setNewRoomTab();
        setCheckInTab();
        setReservationTab();
        setCheckOutTab();

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
                showAlert("Employee <<" + employeeFirstName.getText() + ", " + employeeLastName.getText() + ">> is already exists!");
            } catch (DateTimeParseException e) {
                System.out.println(e);
                showAlert("DoB format is: d/M/yyyy. Please check again!");
            }

            tabPane.getSelectionModel().select(employeeTab);
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
            tabPane.getSelectionModel().select(emptyRoomTab);
        });

        reservationButton.setOnAction(event -> {
            try {
                if (createReservation()) {
                    tabPane.getSelectionModel().select(bookingTab);
                    reservationFirstName.clear();
                    reservationLastName.clear();
                    singleCheckBox.setSelected(false);
                    doubleCheckBox.setSelected(false);
                    deluxeCheckBox.setSelected(false);
                    luxuryCheckBox.setSelected(false);
                    setBirthdayDatePicker(reservationDoB, 18);
                    setCheckInOutDatePicker(reservationCheckIn, reservationCheckOut);
                    setComboBoxGender(reservationGender);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        /*
        After checking in for the current selected room, removes it from the combo box
        And update this room to the checkout combo box in checkout tab
        Then set new selected room = new first room in the list
         */
        singleRoomButton.setOnAction(event -> {
            try {
                if (singleRoomCheckin()) {
                    singleRoomNr.getItems().remove(singleRoomNr.getValue());
                    if (singleRoomNr.getItems().isEmpty()) {
                        disableCustomerInfo(singleFirstName, singleLastName, singleDoB, singleGender, true);
                        singleCheckOut.setDisable(true);
                        singleRoomNr.setDisable(true);
                        singleRoomButton.setDisable(true);
                    } else
                        singleRoomNr.setValue(singleRoomNr.getItems().get(0));

                    updateCheckoutTab(singleCheckOut);
                    singleFirstName.clear();
                    singleLastName.clear();
                    setComboBoxGender(singleGender);
                    setBirthdayDatePicker(singleDoB, 18);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (DateTimeParseException e) {
                System.out.println(e);
                showAlert("Date format is: d/M/yyyy. Please check again!");
            }
            tabPane.getSelectionModel().select(customerTab);
        });

        doubleRoomButton.setOnAction(event -> {
            try {
                if (doubleRoomCheckin()) {
                    doubleRoomNr.getItems().remove(doubleRoomNr.getValue());
                    if (doubleRoomNr.getItems().isEmpty()) {
                        disableCustomerInfo(doubleFirstName1, doubleLastName1, doubleDoB1, doubleGender1, true);
                        disableTab(doubleSecondCustomer, doubleCheckOut, doubleRoomNr, doubleRoomButton, true);
                    } else
                        doubleRoomNr.setValue(doubleRoomNr.getItems().get(0));

                    updateCheckoutTab(doubleCheckOut);

                    doubleFirstName1.clear();
                    doubleLastName1.clear();
                    setComboBoxGender(doubleGender1);
                    setBirthdayDatePicker(doubleDoB1, 18);

                    resetAfterCheckin(doubleSecondCustomer, doubleFirstName2, doubleLastName2, doubleDoB2, doubleGender2);
                    resetAfterCheckinExtra(doubleKidTickBox, doubleKidFirstName, doubleKidLastName, doubleKidDoB, doubleKidGender);


                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (DateTimeParseException e) {
                System.out.println(e);
                showAlert("Date format is: d/M/yyyy. Please check again!");
            }
            tabPane.getSelectionModel().select(customerTab);
        });

        deluxeRoomButton.setOnAction(event -> {
            try {
                if (deluxeRoomCheckin()) {
                    deluxeRoomNr.getItems().remove(deluxeRoomNr.getValue());
                    if (deluxeRoomNr.getItems().isEmpty()) {
                        disableCustomerInfo(deluxeFirstName1, deluxeLastName1, deluxeDoB1, deluxeGender1, true);
                        disableTab(deluxeSecondCustomer, deluxeCheckOut, deluxeRoomNr, deluxeRoomButton, true);
                    } else
                        deluxeRoomNr.setValue(deluxeRoomNr.getItems().get(0));

                    updateCheckoutTab(deluxeCheckOut);

                    deluxeFirstName1.clear();
                    deluxeLastName1.clear();
                    setComboBoxGender(deluxeGender1);
                    setBirthdayDatePicker(deluxeDoB1, 18);

                    resetAfterCheckin(deluxeSecondCustomer, deluxeFirstName2, deluxeLastName2, deluxeDoB2, deluxeGender2);
                    resetAfterCheckin(deluxeThirdCustomer, deluxeFirstName3, deluxeLastName3, deluxeDoB3, deluxeGender3);
                    resetAfterCheckin(deluxeFourthCustomer, deluxeFirstName4, deluxeLastName4, deluxeDoB4, deluxeGender4);
                    resetAfterCheckinExtra(deluxeKidTickBox, deluxeKidFirstName, deluxeKidLastName, deluxeKidDoB, deluxeKidGender);


                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (DateTimeParseException e) {
                System.out.println(e);
                showAlert("Date format is: d/M/yyyy. Please check again!");
            }
            tabPane.getSelectionModel().select(customerTab);
        });

        /*
        Reload customer table then remove the current selected value in combo box
        Then check of after remove the current value, whether the combobox is empty -> disable
        Otherwise, set new selected value = next value
         */
        checkOutButton.setOnAction(event -> {
            try {
                if (!checkOutRoom.isDisable()) {
                    roomDB.checkOut(checkOutRoom.getValue());
                    tabPane.getSelectionModel().select(customerTab);
                    reloadCustomerTable();
                } else
                    showAlert("No room to check out!");


                if (!singleRoomNr.getItems().isEmpty())
                    singleRoomNr.getItems().clear();
                resultSet = roomDB.getEmptyRoom("Single");
                while (resultSet.next())
                    singleRoomNr.getItems().add(resultSet.getInt(1));
                singleRoomNr.setValue(singleRoomNr.getItems().get(0));
                disableCustomerInfo(singleFirstName, singleLastName, singleDoB, singleGender, false);
                singleCheckOut.setDisable(false);
                singleRoomNr.setDisable(false);
                singleRoomButton.setDisable(false);

                if (!doubleRoomNr.getItems().isEmpty())
                    doubleRoomNr.getItems().clear();
                resultSet = roomDB.getEmptyRoom("Double");
                while (resultSet.next())
                    doubleRoomNr.getItems().add(resultSet.getInt(1));
                doubleRoomNr.setValue(doubleRoomNr.getItems().get(0));
                disableCustomerInfo(doubleFirstName1, doubleLastName1, doubleDoB1, doubleGender1, false);
                disableTab(doubleSecondCustomer, doubleCheckOut, doubleRoomNr, doubleRoomButton, false);

                if (!deluxeRoomNr.getItems().isEmpty())
                    deluxeRoomNr.getItems().clear();
                resultSet = roomDB.getEmptyRoom("Deluxe");
                while (resultSet.next())
                    deluxeRoomNr.getItems().add(resultSet.getInt(1));
                deluxeRoomNr.setValue(deluxeRoomNr.getItems().get(0));
                disableCustomerInfo(deluxeFirstName1, deluxeLastName1, deluxeDoB1, deluxeGender1, false);
                disableTab(deluxeSecondCustomer, deluxeCheckOut, deluxeRoomNr, deluxeRoomButton, false);

            } catch (SQLException e) {
                e.printStackTrace();
            }
            checkOutRoom.getItems().remove(checkOutRoom.getValue());
            if (checkOutRoom.getItems().isEmpty())
                checkOutRoom.setDisable(true);
            else
                checkOutRoom.setValue(checkOutRoom.getItems().get(0));

            tabPane.getSelectionModel().select(customerTab);
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
        TableColumn<BookingCustomer, Integer> roomAmount = new TableColumn<>("roomAmount");
        TableColumn<BookingCustomer, String> checkin = new TableColumn<>("checkin");
        TableColumn<BookingCustomer, String> checkout = new TableColumn<>("checkout");

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        dob.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        roomType.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        roomAmount.setCellValueFactory(new PropertyValueFactory<>("roomAmount"));
        checkin.setCellValueFactory(new PropertyValueFactory<>("checkin"));
        checkout.setCellValueFactory(new PropertyValueFactory<>("checkout"));

        bookingCustomerTable.getColumns().addAll(id, firstName, lastName, dob, gender, roomType, roomAmount, checkin, checkout);
        reloadBookingTable();

        return bookingCustomerTable;
    }

    private void setNewEmployeeTab() {
        newEmployeeTab.setStyle("-fx-font-size: 15px");

        setBirthdayDatePicker(employeeDoB, 18);
        setComboBoxGender(employeeGender);

        // set up combo box department
        employeeDepartment.getItems().addAll("Manager", "Reception", "Driver", "Housekeeper");
        employeeDepartment.setValue("Reception");
        employeeDepartment.setStyle("-fx-font-size: 15px");
    }

    private void setNewRoomTab() {
        newRoomTab.setStyle("-fx-font-size: 15px");

        // set default value for combo box type of room
        roomType.getItems().addAll("Single", "Double", "Deluxe");
        roomType.setValue("Double");
        roomType.setStyle("-fx-font-size: 15px");

        // room number only accepts positive integers 0-9 with length of 3 digits
        roomNrTextField.textProperty().addListener(((observableValue, oldValue, newValue) -> {
            if (!newValue.matches("\\d{0,3}"))
                roomNrTextField.setText(oldValue);
        }));
    }

    private void setReservationTab() {
        reservationTab.setStyle("-fx-font-size: 15px");

        setBirthdayDatePicker(reservationDoB, 18);
        setComboBoxGender(reservationGender);
        setCheckInOutDatePicker(reservationCheckIn, reservationCheckOut);

        setEmptyRoomReservation(singleCheckBox, doubleCheckBox, deluxeCheckBox, luxuryCheckBox, singleQuantity,"Single");
        setEmptyRoomReservation(doubleCheckBox, singleCheckBox, deluxeCheckBox, luxuryCheckBox, doubleQuantity,"Double");
        setEmptyRoomReservation(deluxeCheckBox, singleCheckBox, doubleCheckBox, luxuryCheckBox, deluxeQuantity,"Deluxe");

    }

    private void setCheckOutTab() throws SQLException {
        checkOutTab.setStyle("-fx-font-size: 15px");
        checkOutRoom.setStyle("-fx-font-size: 15px");

        resultSet = roomDB.getCheckOutRoom();
        if (!roomDB.getCheckOutRoom().next())
            checkOutRoom.setDisable(true);
        else {
            while (resultSet.next())
                checkOutRoom.getItems().add(resultSet.getInt(1));
            checkOutRoom.setValue(checkOutRoom.getItems().get(0));
        }

        checkOutRoom.getItems().sorted();

    }

    private void setCheckInTab() throws SQLException {
        checkInTab.setStyle("-fx-font-size: 15px");
        setSingleRoomTab();
        setDoubleRoomTab();
        setDeluxeRoomTab();
    }

    private void setSingleRoomTab() throws SQLException {
        singleRoomTab.setStyle("-fx-font-size: 15px");

        setBirthdayDatePicker(singleDoB, 18);
        setCheckOutDate(singleCheckOut);
        setComboBoxGender(singleGender);

        resultSet = roomDB.getEmptyRoom("Single");
        while (resultSet.next())
            singleRoomNr.getItems().add(resultSet.getInt(1));
        if (singleRoomNr.getItems().isEmpty()) {
            disableCustomerInfo(singleFirstName, singleLastName, singleDoB, singleGender, true);
            singleRoomNr.setDisable(true);
            singleCheckOut.setDisable(true);
            singleRoomButton.setDisable(true);
        } else {
            // set the default value = smallest empty room number
            singleRoomNr.setValue(singleRoomNr.getItems().get(0));
            singleRoomNr.setStyle("-fx-font-size: 15px");
        }
    }
    
    private void setDoubleRoomTab() throws SQLException {
        doubleRoomTab.setStyle("-fx-font-size: 15px");

        setBirthdayDatePicker(doubleDoB1, 18);
        setComboBoxGender(doubleGender1);

        /*
         * Property description:
         * Determines whether the user toggling the CheckBox should cycle through all three states: checked, unchecked, and undefined.
         * If true then all three states will be cycled through;
         * if false then only checked and unchecked will be cycled.
         */
        doubleSecondCustomer.setAllowIndeterminate(false);
        doubleSecondCustomer.setSelected(false); // checkbox default as checked
        setBirthdayDatePicker(doubleDoB2, 0);
        setComboBoxGender(doubleGender2);
        disableCustomerInfo(doubleFirstName2, doubleLastName2, doubleDoB2, doubleGender2, true);
        disableCustomerInfo(doubleKidFirstName, doubleKidLastName, doubleKidDoB, doubleKidGender, true);
        doubleKidTickBox.setDisable(true);


        // trigger action when toggling the checkbox
        doubleSecondCustomer.selectedProperty().addListener((observableValue, unchecked1, checked1) -> {
            if (unchecked1) {
                doubleKidTickBox.setDisable(true);
                disableCustomerInfo(doubleFirstName2, doubleLastName2, doubleDoB2, doubleGender2, true);
                disableCustomerInfo(doubleKidFirstName, doubleKidLastName, doubleKidDoB, doubleKidGender, true);
            } else {
                disableCustomerInfo(doubleFirstName2, doubleLastName2, doubleDoB2, doubleGender2, false);
                disableCustomerInfo(doubleKidFirstName, doubleKidLastName, doubleKidDoB, doubleKidGender, !doubleKidTickBox.isSelected());
                doubleKidTickBox.setDisable(false);

                doubleKidTickBox.selectedProperty().addListener((observableValue1, unchecked2, checked2) ->
                        disableCustomerInfo(doubleKidFirstName, doubleKidLastName, doubleKidDoB, doubleKidGender, unchecked2));
            }
        });

        setCheckOutDate(doubleCheckOut);

        resultSet = roomDB.getEmptyRoom("Double");
        while (resultSet.next())
            doubleRoomNr.getItems().add(resultSet.getInt(1));
        if (doubleRoomNr.getItems().isEmpty()) {
            disableCustomerInfo(doubleFirstName1, doubleLastName1, doubleDoB1, doubleGender1, true);
            disableTab(doubleSecondCustomer, doubleCheckOut, doubleRoomNr, doubleRoomButton, true);
        } else {
            // set the default value = smallest empty room number
            doubleRoomNr.setValue(doubleRoomNr.getItems().get(0));
            doubleRoomNr.setStyle("-fx-font-size: 15px");
        }

    }

    private void setDeluxeRoomTab() throws SQLException {
        deluxeRoomTab.setStyle("-fx-font-size: 15px");

        setBirthdayDatePicker(deluxeDoB1, 18);
        setBirthdayDatePicker(deluxeDoB2, 0);
        setBirthdayDatePicker(deluxeDoB3, 0);
        setBirthdayDatePicker(deluxeDoB4,0);
        setExtraPersonBirthdayDatePicker(deluxeKidDoB);

        setComboBoxGender(deluxeGender1);
        setComboBoxGender(deluxeGender2);
        setComboBoxGender(deluxeGender3);
        setComboBoxGender(deluxeGender4);
        setComboBoxGender(deluxeKidGender);

        /*
         * Property description:
         * Determines whether the user toggling the CheckBox should cycle through all three states: checked, unchecked, and undefined.
         * If true then all three states will be cycled through;
         * if false then only checked and unchecked will be cycled.
         */
        deluxeSecondCustomer.setAllowIndeterminate(false);
        deluxeThirdCustomer.setAllowIndeterminate(false);
        deluxeFourthCustomer.setAllowIndeterminate(false);
        deluxeKidTickBox.setAllowIndeterminate(false);

        deluxeSecondCustomer.setSelected(false);
        deluxeThirdCustomer.setSelected(false);
        deluxeFourthCustomer.setSelected(false);
        deluxeKidTickBox.setSelected(false);

        deluxeThirdCustomer.setDisable(true);
        deluxeFourthCustomer.setDisable(true);
        deluxeKidTickBox.setDisable(true);

        disableCustomerInfo(deluxeFirstName2, deluxeLastName2, deluxeDoB2, deluxeGender2, true);
        disableCustomerInfo(deluxeFirstName3, deluxeLastName3, deluxeDoB3, deluxeGender3, true);
        disableCustomerInfo(deluxeFirstName4, deluxeLastName4, deluxeDoB4, deluxeGender4, true);
        disableCustomerInfo(deluxeKidFirstName, deluxeKidLastName, deluxeKidDoB, deluxeKidGender, true);

        deluxeSecondCustomer.selectedProperty().addListener((observableValue, unchecked2, checked2) -> {
            if (unchecked2) {
                deluxeThirdCustomer.setDisable(true);
                deluxeFourthCustomer.setDisable(true);
                deluxeKidTickBox.setDisable(true);

                disableCustomerInfo(deluxeFirstName2, deluxeLastName2, deluxeDoB2, deluxeGender2, true);
                disableCustomerInfo(deluxeFirstName3, deluxeLastName3, deluxeDoB3, deluxeGender3, true);
                disableCustomerInfo(deluxeFirstName4, deluxeLastName4, deluxeDoB4, deluxeGender4, true);
                disableCustomerInfo(deluxeKidFirstName, deluxeKidLastName, deluxeKidDoB, deluxeKidGender, true);
            } else {
                disableCustomerInfo(deluxeFirstName2, deluxeLastName2, deluxeDoB2, deluxeGender2, false);
                deluxeThirdCustomer.setDisable(false);
                deluxeFourthCustomer.setDisable(!deluxeThirdCustomer.isSelected());
                deluxeKidTickBox.setDisable(!deluxeFourthCustomer.isSelected());

                disableCustomerInfo(deluxeFirstName3, deluxeLastName3, deluxeDoB3, deluxeGender3, !deluxeThirdCustomer.isSelected());
                disableCustomerInfo(deluxeFirstName4, deluxeLastName4, deluxeDoB4, deluxeGender4, !deluxeFourthCustomer.isSelected());
                disableCustomerInfo(deluxeKidFirstName, deluxeKidLastName, deluxeKidDoB, deluxeKidGender, !deluxeKidTickBox.isSelected());

                deluxeThirdCustomer.selectedProperty().addListener((observableValue1, unchecked3, checked3) -> {
                    if (unchecked3) {
                        deluxeFourthCustomer.setDisable(true);
                        deluxeKidTickBox.setDisable(true);

                        disableCustomerInfo(deluxeFirstName3, deluxeLastName3, deluxeDoB3, deluxeGender3, true);
                        disableCustomerInfo(deluxeFirstName4, deluxeLastName4, deluxeDoB4, deluxeGender4, true);
                        disableCustomerInfo(deluxeKidFirstName, deluxeKidLastName, deluxeKidDoB, deluxeKidGender, true);
                    } else {
                        disableCustomerInfo(deluxeFirstName3, deluxeLastName3, deluxeDoB3, deluxeGender3, false);
                        deluxeFourthCustomer.setDisable(false);
                        deluxeKidTickBox.setDisable(!deluxeFourthCustomer.isSelected());

                        disableCustomerInfo(deluxeFirstName4, deluxeLastName4, deluxeDoB4, deluxeGender4, !deluxeFourthCustomer.isSelected());
                        disableCustomerInfo(deluxeKidFirstName, deluxeKidLastName, deluxeKidDoB, deluxeKidGender, !deluxeKidTickBox.isSelected());

                        deluxeFourthCustomer.selectedProperty().addListener((observableValue2, unchecked4, checked4) -> {
                            if (unchecked4) {
                                deluxeKidTickBox.setDisable(true);

                                disableCustomerInfo(deluxeFirstName4, deluxeLastName4, deluxeDoB4, deluxeGender4, true);
                                disableCustomerInfo(deluxeKidFirstName, deluxeKidLastName, deluxeKidDoB, deluxeKidGender, true);
                            } else {
                                disableCustomerInfo(deluxeFirstName4, deluxeLastName4, deluxeDoB4, deluxeGender4, false);
                                deluxeKidTickBox.setDisable(false);

                                disableCustomerInfo(deluxeKidFirstName, deluxeKidLastName, deluxeKidDoB, deluxeKidGender, !deluxeKidTickBox.isSelected());

                                deluxeKidTickBox.selectedProperty().addListener((observableValue3, uncheck, checked) ->
                                        disableCustomerInfo(deluxeKidFirstName, deluxeKidLastName, deluxeKidDoB, deluxeKidGender, uncheck));
                            }
                        });
                    }
                });
            }
        });

        setCheckOutDate(deluxeCheckOut);

        resultSet = roomDB.getEmptyRoom("Deluxe");
        while (resultSet.next())
            deluxeRoomNr.getItems().add(resultSet.getInt(1));
        if (deluxeRoomNr.getItems().isEmpty()) {
            disableCustomerInfo(deluxeFirstName1, deluxeLastName1, deluxeDoB1, deluxeGender1, true);
            disableTab(deluxeSecondCustomer, deluxeCheckOut, deluxeRoomNr, deluxeRoomButton, true);
        } else {
            // set the default value = smallest empty room number
            deluxeRoomNr.setValue(deluxeRoomNr.getItems().get(0));
            deluxeRoomNr.setStyle("-fx-font-size: 15px");
        }

    }

    private boolean createUser(Admin admin) throws SQLException, AdminAlreadyExists {
        if (!newUsernameTextField.getText().isEmpty() && !passwordField.getText().isEmpty() && !confirmPass.getText().isEmpty()) {
            if (!passwordField.getText().equals(confirmPass.getText())) {
                alert.setContentText("Confirm password not match!");
                alert.showAndWait();
            } else {
                Admin newAdmin = new Admin(newUsernameTextField.getText(), passwordField.getText());
                if (adminDB.createAdmin(newAdmin)) {
                    reloadAdminTable(admin);
                    return true;
                }
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
        return false;
    }

    private boolean createEmployee() throws SQLException, EmployeeAlreadyExist {
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
                return true;
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
        return false;
    }

    private boolean createRoom() throws RoomAlreadyExist, SQLException {
        if (!roomNrTextField.getText().isEmpty()) {
            Room newRoom = new Room(Integer.parseInt(roomNrTextField.getText()), roomType.getValue());
            if (newRoom.getNumber() < 100) {
                alert.setContentText("Room can only start with 100!");
                alert.showAndWait();
            } else
                if (roomDB.createRoom(newRoom)) {
                    reloadRoomTable();
                    return true;
                }
                else {
                    alert.setContentText("Room <<" + newRoom.getNumber() + ">> is already exist!");
                    alert.showAndWait();
                }
        } else {
            alert.setContentText("Please insert room number!");
            alert.showAndWait();
        }
        return false;
    }

    private boolean createReservation() throws SQLException {
        boolean succeed = false;
        if (!reservationFirstName.getText().isEmpty() && !reservationLastName.getText().isEmpty()) {
            BookingCustomer newBookingCustomer = new BookingCustomer(
                    reservationFirstName.getText(),
                    reservationLastName.getText(),
                    LocalDate.parse(reservationDoB.getEditor().getText(), formatter).toString(),
                    reservationGender.getValue(),
                    LocalDate.parse(reservationCheckIn.getEditor().getText(), formatter).toString(),
                    LocalDate.parse(reservationCheckOut.getEditor().getText(), formatter).toString()
                    );
            if (!singleQuantity.isDisable()) {
                if (singleQuantity.getValue() == null) {
                    showAlert("Please choose at least 1 single room or uncheck it");
                } else {
                    newBookingCustomer.setRoomType("Single");
                    newBookingCustomer.setRoomAmount(singleQuantity.getValue());
                    if (roomDB.makeReservation(newBookingCustomer)) {
                        succeed = true;
                        reloadBookingTable();
                    }
                }
            }
            if (!doubleQuantity.isDisable()) {
                if (doubleQuantity.getValue() == null)
                    showAlert("Please choose at least 1 double room or uncheck it");
                else {
                    newBookingCustomer.setRoomType("Double");
                    newBookingCustomer.setRoomAmount(doubleQuantity.getValue());
                    if (roomDB.makeReservation(newBookingCustomer)) {
                        succeed = true;
                        reloadBookingTable();
                    }
                }
            }
            if (!deluxeQuantity.isDisable()) {
                if (deluxeQuantity.getValue() == null)
                    showAlert("Please choose at least 1 deluxe room or uncheck it");
                else {
                    newBookingCustomer.setRoomType("Deluxe");
                    newBookingCustomer.setRoomAmount(deluxeQuantity.getValue());
                    if (roomDB.makeReservation(newBookingCustomer)) {
                        succeed = true;
                        reloadBookingTable();
                    }
                }
            }

            if (singleCheckBox.isSelected() && singleQuantity.isDisable())
                showAlert("Single room is not available for this period. Please choose other room type.");
            else if (doubleCheckBox.isSelected() && doubleQuantity.isDisable())
                showAlert("Double room is not available for this period. Please choose other room type.");
            else if (deluxeCheckBox.isSelected() && deluxeQuantity.isDisable())
                showAlert("Deluxe room is not available for this period. Please choose other room type.");

        } else if (reservationFirstName.getText().isEmpty())
            showAlert("Please insert customer first name");
        else if (reservationLastName.getText().isEmpty())
            showAlert("Please insert customer last name");
        return succeed;
    }

    /**
     * TODO
     */
    private void checkOut() {

    }

    private boolean singleRoomCheckin() throws SQLException {
        if (getValidCustomer(singleFirstName, singleLastName, singleDoB, "", 18, 100)) {
            Customer newCustomer = new Customer(
                    singleFirstName.getText(),
                    singleLastName.getText(),
                    LocalDate.parse(singleDoB.getEditor().getText(), formatter).toString(),
                    singleGender.getValue(),
                    LocalDate.now().toString(),
                    LocalDate.parse(singleCheckOut.getEditor().getText(), formatter).toString(),
                    singleRoomNr.getValue()
            );
            roomDB.checkIn(newCustomer);
            reloadCustomerTable();
            return true;
        } else
            return false;
    }

    private boolean doubleRoomCheckin() throws SQLException {
        if (!getLocalDateText(doubleCheckOut).isBefore(LocalDate.now().plusDays(1))) {
            if (getValidCustomer(doubleFirstName1, doubleLastName1, doubleDoB1, "1st", 18, 100)) {
                customer1 = new Customer(doubleFirstName1.getText(),
                        doubleLastName1.getText(),
                        LocalDate.parse(doubleDoB1.getEditor().getText(), formatter).toString(),
                        doubleGender1.getValue(),
                        LocalDate.now().toString(),
                        LocalDate.parse(doubleCheckOut.getEditor().getText(), formatter).toString(),
                        doubleRoomNr.getValue());
                success1 = true;
            }

            if (doubleSecondCustomer.isSelected()) {
                if (getValidCustomer(doubleFirstName2, doubleLastName2, doubleDoB2, "2nd", 0, 100)) {
                    customer2 = new Customer(doubleFirstName2.getText(),
                            doubleLastName2.getText(),
                            LocalDate.parse(doubleDoB2.getEditor().getText(), formatter).toString(),
                            doubleGender2.getValue(),
                            LocalDate.now().toString(),
                            LocalDate.parse(doubleCheckOut.getEditor().getText(), formatter).toString(),
                            doubleRoomNr.getValue());
                    success2 = true;
                }
            } else
                secondCustomer = false;

            if (doubleKidTickBox.isSelected() && !doubleKidTickBox.isDisable()) {
                if (getValidCustomer(doubleKidFirstName, doubleKidLastName, doubleKidDoB, "3rd", 0, 12)) {
                    customer3 = new Customer(doubleKidFirstName.getText(),
                            doubleKidLastName.getText(),
                            LocalDate.parse(doubleKidDoB.getEditor().getText(), formatter).toString(),
                            doubleKidGender.getValue(),
                            LocalDate.now().toString(),
                            LocalDate.parse(doubleCheckOut.getEditor().getText(), formatter).toString(),
                            doubleRoomNr.getValue());
                    success3 = true;
                }
            } else
                extraPerson = false;
        } else
            showAlert("Checkout date must be at least tomorrow!");

        if (success1 && success2 && success3) {
            roomDB.checkIn(customer1);
            roomDB.checkIn(customer2);
            roomDB.checkIn(customer3);
            reloadCustomerTable();
            return true;
        } else if (success1 && success2 && !extraPerson) {
            roomDB.checkIn(customer1);
            roomDB.checkIn(customer2);
            reloadCustomerTable();
            return true;
        } else if (success1 && !secondCustomer) {
            roomDB.checkIn(customer1);
            reloadCustomerTable();
            return true;
        } else
            return false;
    }

    private boolean deluxeRoomCheckin() throws SQLException {
        if (!getLocalDateText(deluxeCheckOut).isBefore(LocalDate.now().plusDays(1))) {
            if (getValidCustomer(deluxeFirstName1, deluxeLastName1, deluxeDoB1, "1st", 18, 100)) {
                customer1 = new Customer(deluxeFirstName1.getText(),
                        deluxeLastName1.getText(),
                        LocalDate.parse(deluxeDoB1.getEditor().getText(), formatter).toString(),
                        deluxeGender1.getValue(),
                        LocalDate.now().toString(),
                        LocalDate.parse(deluxeCheckOut.getEditor().getText(), formatter).toString(),
                        deluxeRoomNr.getValue());
                success1 = true;
            }

            if (deluxeSecondCustomer.isSelected()) {
                if (getValidCustomer(deluxeFirstName2, deluxeLastName2, deluxeDoB2, "2nd", 0, 100)) {
                    customer2 = new Customer(deluxeFirstName2.getText(),
                            deluxeLastName2.getText(),
                            LocalDate.parse(deluxeDoB2.getEditor().getText(), formatter).toString(),
                            deluxeGender2.getValue(),
                            LocalDate.now().toString(),
                            LocalDate.parse(deluxeCheckOut.getEditor().getText(), formatter).toString(),
                            deluxeRoomNr.getValue());
                    success2 = true;
                }
            } else
                secondCustomer = false;

            if (deluxeThirdCustomer.isSelected()) {
                if (getValidCustomer(deluxeFirstName3, deluxeLastName3, deluxeDoB3, "3rd", 0, 100)) {
                    customer3 = new Customer(deluxeFirstName3.getText(),
                            deluxeLastName3.getText(),
                            LocalDate.parse(deluxeDoB3.getEditor().getText(), formatter).toString(),
                            deluxeGender3.getValue(),
                            LocalDate.now().toString(),
                            LocalDate.parse(deluxeCheckOut.getEditor().getText(), formatter).toString(),
                            deluxeRoomNr.getValue());
                    success3 = true;
                }
            } else
                thirdCustomer = false;

            if (deluxeFourthCustomer.isSelected()) {
                if (getValidCustomer(deluxeFirstName4, deluxeLastName4, deluxeDoB4, "4th", 0, 100)) {
                    customer4 = new Customer(deluxeFirstName4.getText(),
                            deluxeLastName4.getText(),
                            LocalDate.parse(deluxeDoB4.getEditor().getText(), formatter).toString(),
                            deluxeGender4.getValue(),
                            LocalDate.now().toString(),
                            LocalDate.parse(deluxeCheckOut.getEditor().getText(), formatter).toString(),
                            deluxeRoomNr.getValue());
                    success4 = true;
                }
            } else
                fourthCustomer = false;

            if (!deluxeKidTickBox.isDisable() && deluxeKidTickBox.isSelected()) {
                if (getValidCustomer(deluxeKidFirstName, deluxeKidLastName, deluxeKidDoB, "5th", 0, 12)) {
                    customer5 = new Customer(deluxeKidFirstName.getText(),
                            deluxeKidLastName.getText(),
                            LocalDate.parse(deluxeKidDoB.getEditor().getText(), formatter).toString(),
                            deluxeKidGender.getValue(),
                            LocalDate.now().toString(),
                            LocalDate.parse(deluxeCheckOut.getEditor().getText(), formatter).toString(),
                            deluxeRoomNr.getValue());
                    success5 = true;
                }
            } else
                extraPerson = false;
        } else
            showAlert("Checkout date must be at least tomorrow!");

        if (success1 && success2 && success3 && success4 && success5) {
            roomDB.checkIn(customer1);
            roomDB.checkIn(customer2);
            roomDB.checkIn(customer3);
            roomDB.checkIn(customer4);
            roomDB.checkIn(customer5);
            reloadCustomerTable();
            return true;
        } else if (success1 && success2 && success3 && success4 && !extraPerson) {
            roomDB.checkIn(customer1);
            roomDB.checkIn(customer2);
            roomDB.checkIn(customer3);
            roomDB.checkIn(customer4);
            reloadCustomerTable();
            return true;
        } else if (success1 && success2 && success3 && !fourthCustomer) {
            roomDB.checkIn(customer1);
            roomDB.checkIn(customer2);
            roomDB.checkIn(customer3);
            reloadCustomerTable();
            return true;
        } else if (success1 && success2 && !thirdCustomer) {
            roomDB.checkIn(customer1);
            roomDB.checkIn(customer2);
            reloadCustomerTable();
            return true;
        } else if (success1 && !secondCustomer) {
            roomDB.checkIn(customer1);
            reloadCustomerTable();
            return true;
        } else
            return false;
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

    private void reloadBookingTable() throws SQLException {
        bookingObservableList.clear();
        resultSet = customerDB.showAllBooking();
        while (resultSet.next()) {
            bookingObservableList.add(new BookingCustomer(
                    resultSet.getInt(1), // id
                    resultSet.getString(2), // first name
                    resultSet.getString(3), // last name
                    resultSet.getString(4), // dob
                    resultSet.getString(5), // gender
                    resultSet.getString(6), // room type
                    resultSet.getInt(7), // room amount
                    resultSet.getString(8), // check in
                    resultSet.getString(9) // check out
            ));
            bookingCustomerTable.setItems(bookingObservableList);
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

    /**
     * Customer must be in between 110 and 'minimumAge' yo
     * In Double, Deluxe and Luxury room at least on customer > 18 yo
     * @param datePicker
     */
    private void setBirthdayDatePicker(DatePicker datePicker, int minimumAge) {
        datePicker.setStyle("-fx-font-size: 15px");

        datePicker.setValue(LocalDate.now().minusYears(minimumAge));

        datePicker.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(empty || date.compareTo(datePicker.getValue()) > 0 || date.compareTo(LocalDate.now().minusYears(100)) < 0);
            }
        });
    }

    /**
     * Kid must be in range between 12 and 0 yo
     * @param datePicker
     */
    private void setExtraPersonBirthdayDatePicker(DatePicker datePicker) {
        datePicker.setStyle("-fx-font-size: 15px");

        datePicker.setValue(LocalDate.now().minusYears(12).plusDays(1));

        datePicker.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(empty || date.compareTo(datePicker.getValue()) < 0 || date.compareTo(LocalDate.now()) > 0);
            }
        });
    }

    /**
     * Checkin date by default is today date
     * Checkout date by default is tomorrow and always is after at least 1 day after checkin date
     * If the new checkin date is after the current checkin date, then checkout = new checkin + 1 day
     * If the new checkin date is before the current checkin date, then checkout doesn't change
     * @param checkInDate
     * @param checkOutDate
     */
    private void setCheckInOutDatePicker(DatePicker checkInDate, DatePicker checkOutDate) {
        checkInDate.setStyle("-fx-font-size: 15px");
        checkOutDate.setStyle("-fx-font-size: 15px");

        checkInDate.setValue(LocalDate.now());
        checkOutDate.setValue(checkInDate.getValue().plusDays(1));

        AtomicReference<LocalDate> currentSelectedDate = new AtomicReference<>(checkInDate.getValue());

        checkInDate.setOnAction(event -> {
            if (checkInDate.getValue().isAfter(currentSelectedDate.get())) {
                checkOutDate.setValue(checkInDate.getValue().plusDays(1));
                currentSelectedDate.set(checkInDate.getValue());
            }
            else
                checkOutDate.setValue(checkOutDate.getValue());
        });

        checkInDate.setDayCellFactory(datePicker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(empty || date.compareTo(LocalDate.now()) < 0);
            }
        });

        checkOutDate.setDayCellFactory(datePicker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                long period = ChronoUnit.DAYS.between(checkInDate.getValue(), date);
                setTooltip(new Tooltip("Booking to stay for " + period + " days"));
                setDisable(empty || date.compareTo(checkInDate.getValue()) < 1);
            }
        });
    }

    private void setCheckOutDate(DatePicker checkOutDate) {
        checkOutDate.setStyle("-fx-font-size: 15px");
        checkOutDate.setValue(LocalDate.now().plusDays(1));

        checkOutDate.setDayCellFactory(datePicker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                long period = ChronoUnit.DAYS.between(LocalDate.now(), date);
                setTooltip(new Tooltip("Booking to stay for " + period + " days"));
                setDisable(empty || date.compareTo(LocalDate.now()) < 1);
            }
        });
    }

    private void disableCustomerInfo(TextField firstName, TextField lastName, DatePicker dob, ComboBox<String> gender, boolean disable) {
        firstName.setDisable(disable);
        lastName.setDisable(disable);
        dob.setDisable(disable);
        gender.setDisable(disable);
    }

    private void disableTab(CheckBox checkBox, DatePicker date, ComboBox<Integer> room, Button button, boolean disable) {
        checkBox.setDisable(disable);
        date.setDisable(disable);
        room.setDisable(disable);
        button.setDisable(disable);
    }

    private void setEmptyRoomReservation(CheckBox checkBox1, CheckBox checkBox2, CheckBox checkBox3, CheckBox checkBox4, ComboBox<Integer> roomAmount, String roomType) {

        /*
         * Property description:
         * Determines whether the user toggling the CheckBox should cycle through all three states: checked, unchecked, and undefined.
         * If true then all three states will be cycled through;
         * if false then only checked and unchecked will be cycled.
         */
        checkBox1.setAllowIndeterminate(false);
        checkBox1.setSelected(false); // by default is unselected
        roomAmount.setDisable(true);


        checkBox1.selectedProperty().addListener((observableValue, uncheck, checked) -> {
            try {
                int emptyRoom = roomDB.getAmountEmptyRoom(roomType, reservationCheckIn.getValue(), reservationCheckOut.getValue());
                int i = 1;
                if (emptyRoom != 0) {
                    while (i <= emptyRoom)
                        roomAmount.getItems().add(i++);
                    roomAmount.getItems().sort(Comparator.naturalOrder());
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (checked) {
                roomAmount.setDisable(roomAmount.getItems().isEmpty());
                reservationCheckIn.setDisable(true);
                reservationCheckOut.setDisable(true);
            } else {
                roomAmount.getItems().clear();
                roomAmount.setValue(null);
                roomAmount.setDisable(true);
                if (checkBox2.isSelected() || checkBox3.isSelected() || checkBox4.isSelected()) {
                    reservationCheckIn.setDisable(true);
                    reservationCheckOut.setDisable(true);
                } else if (!checkBox2.isSelected() && !checkBox3.isSelected() && !checkBox4.isSelected()) {
                    reservationCheckIn.setDisable(false);
                    reservationCheckOut.setDisable(false);
                }
            }
        } );
    }

    private void updateCheckoutTab(DatePicker checkoutDate) throws SQLException {
        checkoutDate.setValue(LocalDate.now().plusDays(1));

        if (checkOutRoom.isDisable())
            checkOutRoom.setDisable(false);
        if (!checkOutRoom.getItems().isEmpty())
            checkOutRoom.getItems().clear();

        resultSet = roomDB.getCheckOutRoom();
        while (resultSet.next())
            checkOutRoom.getItems().add(resultSet.getInt(1));
        checkOutRoom.getItems().sort(Comparator.naturalOrder());
        checkOutRoom.setValue(checkOutRoom.getItems().get(0));
    }

    private void resetAfterCheckin(CheckBox checkBox, TextField firstName, TextField lastName, DatePicker dob, ComboBox<String> gender) {
        checkBox.setSelected(false);
        firstName.clear();
        lastName.clear();
        setBirthdayDatePicker(dob, 0);
        setComboBoxGender(gender);
        disableCustomerInfo(firstName, lastName, dob, gender, true);
    }

    private void resetAfterCheckinExtra(CheckBox checkBox, TextField firstName, TextField lastName, DatePicker dob, ComboBox<String> gender) {
        checkBox.setSelected(false);
        firstName.clear();
        lastName.clear();
        dob.getEditor().clear();
        gender.getItems().clear();
        disableCustomerInfo(firstName, lastName, dob, gender, true);
    }

    private LocalDate getLocalDateText(DatePicker datePicker) {
        return LocalDate.parse(datePicker.getEditor().getText(), formatter);
    }

    private boolean getValidCustomer(TextField firstName, TextField lastName, DatePicker dob, String message, int minAge, int maxAge) {
        if (firstName.getText().isEmpty())
            showAlert("Please insert " + message + " customer first name!");
        else if (lastName.getText().isEmpty())
            showAlert("Please insert " + message + " customer last name!");
        else
            if (getLocalDateText(dob).isAfter(LocalDate.now().minusYears(minAge)) || getLocalDateText(dob).isBefore(LocalDate.now().minusYears(maxAge)))
                showAlert(message + " customer's age must between " + minAge + " and " + maxAge + " years old!");
            else
                return true;

        return false;
    }
}
