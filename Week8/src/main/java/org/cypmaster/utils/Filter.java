package org.cypmaster.utils;

/**
 * Created by Ciprian at 12/9/2017
 */
public class Filter {

    private String value;
    private boolean selected;

    public Filter(String value, boolean selected) {
        this.value = value;
        this.selected = selected;
    }

    public String getValue() {
        return value;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void setValue(String value) {


        this.value = value;
    }

    @Override
    public String toString() {
        return "Filter{" +
                "value='" + value + '\'' +
                ", selected=" + selected +
                '}';
    }


}