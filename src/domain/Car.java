import java.util.Date;

public class car implements Comparable <car> {
    private int year, ownerId;
    private String manufacturer;
    private String model;
    private Date RegistrationDate;
    private Date ExpirationDate;
    private String InsuranceCompany;
    private String Color;
    private String Plate;


    public car(int year, String manufacturer, String model, Date registrationDate, Date expirationDate, String insuranceCompany, String color, String plate, int ownerId) {
        this.year = year;
        this.manufacturer = manufacturer;
        this.model = model;
        RegistrationDate = registrationDate;
        ExpirationDate = expirationDate;
        InsuranceCompany = insuranceCompany;
        Color = color;
        Plate = plate;
        this.ownerId = ownerId;
    }

    @Override
    public String toString() {
        return "car{" +
                "year=" + year +
                ", ownerId=" + ownerId +
                ", manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", RegistrationDate=" + RegistrationDate +
                ", ExpirationDate=" + ExpirationDate +
                ", InsuranceCompany='" + InsuranceCompany + '\'' +
                ", Color='" + Color + '\'' +
                ", Plate='" + Plate + '\'' +
                '}'+"\n";
    }

    @Override
    public int compareTo(car o) {
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

    public Date getRegistrationDate() {
        return RegistrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        RegistrationDate = registrationDate;
    }

    public Date getExpirationDate() {
        return ExpirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        ExpirationDate = expirationDate;
    }

    public String getInsuranceCompany() {
        return InsuranceCompany;
    }

    public void setInsuranceCompany(String insuranceCompany) {
        InsuranceCompany = insuranceCompany;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public String getPlate() {
        return Plate;
    }

    public void setPlate(String plate) {
        Plate = plate;
    }

}
