package domain;

import java.time.LocalDate;
import java.util.Date;

public class Car implements Comparable <Car> {
    private int year, ownerId;
    private String manufacturer;
    private String model;
    private LocalDate registrationDate;
    private LocalDate expirationDate;
    private String insuranceCompany;
    private String color;
    private String plate;

    public Car() {

    }


    public Car(int year, String manufacturer, String model, LocalDate registrationDate, LocalDate expirationDate, String insuranceCompany, String color, String plate, int ownerId) {
        this.year = year;
        this.manufacturer = manufacturer;
        this.model = model;
        this.registrationDate = registrationDate;
        this.expirationDate = expirationDate;
        this.insuranceCompany = insuranceCompany;
        this.color = color;
        this.plate = plate;
        this.ownerId = ownerId;
    }

    @Override
    public String toString() {
        return "Car{" +
                "year=" + year +
                ", ownerId=" + ownerId +
                ", manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", registrationDate=" + registrationDate +
                ", expirationDate=" + expirationDate +
                ", insuranceCompany='" + insuranceCompany + '\'' +
                ", color='" + color + '\'' +
                ", plate='" + plate + '\'' +
                '}'+"\n";
    }

    @Override
    public int compareTo(Car o) {
        return (this.getPlate()).compareTo(o.getPlate());
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getInsuranceCompany() {
        return insuranceCompany;
    }

    public void setInsuranceCompany(String insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

}
