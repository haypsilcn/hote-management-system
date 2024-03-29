package haypsilcn.hotelmanagementsystem.customer;

public class BookingCustomer {
    private int id;
    private String firstName;
    private String lastName;
    private String birthday;
    private String gender;
    private String roomType;
    private String checkin;
    private String checkout;
    private int roomAmount;

    public BookingCustomer(String firstName, String lastName, String birthday, String gender, String checkin, String checkout) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.gender = gender;
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public BookingCustomer(int id, String firstName, String lastName, String birthday, String gender, String roomType, int roomAmount, String checkin, String checkout) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.gender = gender;
        this.roomType = roomType;
        this.roomAmount = roomAmount;
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    public int getRoomAmount() {
        return roomAmount;
    }

    public void setRoomAmount(int roomAmount) {
        this.roomAmount = roomAmount;
    }

    @Override
    public String toString() {
        return "BookingCustomer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday='" + birthday + '\'' +
                ", gender='" + gender + '\'' +
                ", roomType='" + roomType + '\'' +
                ", checkin='" + checkin + '\'' +
                ", checkout='" + checkout + '\'' +
                '}';
    }
}
