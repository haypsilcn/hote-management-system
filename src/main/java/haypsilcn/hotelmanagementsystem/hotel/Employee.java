package haypsilcn.hotelmanagementsystem.hotel;

public class Employee {
    private String firstName;
    private String lastName;
    private String department;
    private String dob;
    private String gender;
    private int id;

    public Employee(int id, String firstName, String lastName, String dob, String gender, String department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.dob = dob;
        this.gender = gender;
        this.id = id;
    }

    public Employee(String firstName, String lastName, String dob, String gender, String department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.dob = dob;
        this.gender = gender;
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

    public String getdepartment() {
        return department;
    }

    public void setdepartment(String department) {
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
