package domain;

import java.util.Date;

public class Fine {
    private int fineId;
    private String afm;
    private Date fineDate;
    private double ammount;
    private String plate;

    public Fine(int fineId, String afm, Date fineDate, double ammount, String plate) {
        this.fineId = fineId;
        this.afm = afm;
        this.fineDate = fineDate;
        this.ammount = ammount;
        this.plate = plate;
    }

    public int getFineId() {
        return fineId;
    }

    public void setFineId(int fineId) {
        this.fineId = fineId;
    }

    public String getAfm() {
        return afm;
    }

    public void setAfm(String afm) {
        this.afm = afm;
    }

    public Date getFineDate() {
        return fineDate;
    }

    public void setFineDate(Date fineDate) {
        this.fineDate = fineDate;
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
