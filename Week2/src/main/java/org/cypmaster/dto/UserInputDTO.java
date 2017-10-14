package org.cypmaster.dto;

public class UserInputDTO {

    private final String name;
    private final String value;
    private final String category;

    public UserInputDTO(String name, String value, String category) {
        this.name = name;
        this.value = value;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "UserInputDTO{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
