package com.bookmycon.model;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name="piechart")
public class PieChart {
    @Column(name="name")
    private String name;

    @Column(name="capacity")
    private int capacity;


    public PieChart(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
