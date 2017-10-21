package org.cypmaster.entities;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Table(name = "userInput")
public class UserInput {

    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "value")
    private String value;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Category category;


    public UserInput() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "UserInput{" +
                "key='" + name + '\'' +
                ", value='" + value + '\'' +
                ", category=" + category +
                '}';
    }
}
