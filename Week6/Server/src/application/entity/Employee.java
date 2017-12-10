package application.entity;

/**
 * Created by SilviuG on 28.11.2017.
 */
public class Employee {
    private Integer id;
    private String name;
    private String email;
    private String country;

    public Employee(Integer id) {
        this.id = id;
    }

    public Employee(String name, String email, String country) {
        this.name = name;
        this.email = email;
        this.country = country;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
