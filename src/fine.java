import java.util.Date;

public class fine {
    private int FineId;
    private String afm;
    private Date FineDate;
    private double ammount;
    private String plate;


    public fine(int fineId, String afm, Date fineDate, double ammount, String plate) {
        FineId = fineId;
        this.afm = afm;
        FineDate = fineDate;
        this.ammount = ammount;
        this.plate = plate;
    }

    public int getFineId() {
        return FineId;
    }

    public void setFineId(int fineId) {
        FineId = fineId;
    }

    public String getAfm() {
        return afm;
    }

    public void setAfm(String afm) {
        this.afm = afm;
    }

    public Date getFineDate() {
        return FineDate;
    }

    public void setFineDate(Date fineDate) {
        FineDate = fineDate;
    }

    public double getAmmount() {
        return ammount;
    }

    public void setAmmount(double ammount) {
        this.ammount = ammount;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

}
