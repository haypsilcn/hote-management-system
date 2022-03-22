package haypsilcn.hotelmanagementsystem.hotel;

public class Employee {
    private String firstName;
    private String lastName;
    private String position;
    private int id;

    public Employee(String firstName, String lastName, String position, int id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
