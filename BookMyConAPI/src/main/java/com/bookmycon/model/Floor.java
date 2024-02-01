package com.bookmycon.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Builder
@Table(name="floor")

public class Floor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    @Column(name="name")
    private String name;
    @Column(name="location")
    private String location;
    @Column(name="type")
    private String type;
    @Column(name="amenities")
    private String amenities;
    @Column(name="capacity")
    private int capacity;
    @Column(name="availability")
    private boolean availability;

     public Floor(){

     }
    public Floor(int id, String name, String location, String type, String amenities, int capacity, boolean availability) {
        super();

        this.name = name;
        this.location = location;
        this.type = type;
        this.amenities = amenities;
        this.capacity = capacity;
        this.availability = availability;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAmenities() {
        return amenities;
    }

    public void setAmenities(String amenities) {
        this.amenities = amenities;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }




}

