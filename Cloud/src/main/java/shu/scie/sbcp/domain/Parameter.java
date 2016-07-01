package shu.scie.sbcp.domain;

import com.mysql.jdbc.BlobFromLocator;
import jdk.nashorn.internal.ir.ReturnNode;

import java.sql.Timestamp;

/**
 * Created by Thorn on 2016/7/1.
 */
public class Parameter {
    private int id;
    private Timestamp addTime;
    private float bodyTemperature;
    private float diastolicPressure;
    private float systolicPressure;
    private float averagePressure;
    private float bloodOxygen;
    private float bloodGlucose;
    private float heartRate;
    private boolean danger;

    public void setId(int id){
        this.id=id;
    }
    public int getId(){
        return id;
    }

    public void setAddTime(Timestamp addTime){
        this.addTime=addTime;
    }
    public Timestamp getAddTime(){
        return addTime;
    }

    public void setBodyTemperature(float bodyTemperature){
        this.bodyTemperature=bodyTemperature;
    }
    public float getBodyTemperature(){
        return bodyTemperature;
    }

    public void setDiastolicPressure(float diastolicPressure){
        this.diastolicPressure=diastolicPressure;
    }
    public float getDiastolicPressure(){
        return diastolicPressure;
    }

    public void setSystolicPressure(float systolicPressure){
        this.systolicPressure=systolicPressure;
    }
    public float getSystolicPressure(){
        return systolicPressure;
    }

    public void setAveragePressure(float averagePressure){
        this.averagePressure=averagePressure;
    }
    public float getAveragePressure(){
        return averagePressure;
    }

    public void setBloodOxygen(float bloodOxygen){
        this.bloodOxygen=bloodOxygen;
    }
    public float getBloodOxygen(){
        return bloodOxygen;
    }

    public void setBloodGlucose(float bloodGlucose){
        this.bloodGlucose=bloodGlucose;
    }
    public float getBloodGlucose(){
        return bloodGlucose;
    }

    public void setHeartRate(float heartRate){
        this.heartRate=heartRate;
    }
    public float getHeartRate(){
        return heartRate;
    }

    public void setDanger(boolean danger){
        this.danger=danger;
    }
    public boolean isDanger(){
        return danger;
    }
}
