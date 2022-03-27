package haypsilcn.hotelmanagementsystem.customer;

import haypsilcn.hotelmanagementsystem.hotel.Room;

public class Customer {

    private String firstName;
    private String lastName;
    private String birthday;
    private String gender;
    private int room;
    private String checkin;
    private String checkout;
    private int id;

    public Customer(String firstName, String lastName, String birthday, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.gender = gender;
    }

    public Customer(int id, String firstName, String lastName, int room, String checkin, String checkout) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.room= room;
        this.checkin = checkin;
        this.checkout = checkout;
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

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
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

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
