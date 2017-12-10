package org.cypmaster.utils;

/**
 * Created by Ciprian at 12/10/2017
 */
public class RangeFilter {

    private int begin;
    private int end;
    private boolean selected;

    public RangeFilter(int begin, int end, boolean selected) {
        this.begin = begin;
        this.end = end;
        this.selected = selected;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public int getBegin() {
        return begin;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "RangeFilter{" +
                "begin=" + begin +
                ", end=" + end +
                ", selected=" + selected +
                '}';
    }
}
