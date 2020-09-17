package net.hackathon;

//import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class Player {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private int age;
    private String fieldPosition;
    private double weight;
    private double height;

    public Player() {
    }


    public Player(String firstName, String lastName, String email, int age, String fieldPosition, double weight, double height) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.fieldPosition = fieldPosition;
        this.weight = weight;
        this.height = height;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPosition() {
        return fieldPosition;
    }

    public void setPosition(String fieldPosition) {
        this.fieldPosition = fieldPosition;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getId() {
        return id;
    }
}