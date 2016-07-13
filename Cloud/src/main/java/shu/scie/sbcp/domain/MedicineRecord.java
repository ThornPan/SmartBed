package shu.scie.sbcp.domain;

/**
 * Created by Thorn on 2016/7/13.
 */
public class MedicineRecord extends Record {
    private String medicine;
    private int dosage;

    public void setMedicine(String medicine){
        this.medicine=medicine;
    }
    public String getMedicine(){
        return medicine;
    }

    public void setDosage(int dosage){
        this.dosage=dosage;
    }
    public int getDosage(){
        return dosage;
    }
}
