package org.cypmaster.entities;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.jws.soap.SOAPBinding;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
@Cacheable
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<UserInput> userInput;

    public Category() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserInput> getUserInput() {
        return userInput;
    }

    public void setUserInput(List<UserInput> userInput) {
        this.userInput = userInput;
    }


    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
