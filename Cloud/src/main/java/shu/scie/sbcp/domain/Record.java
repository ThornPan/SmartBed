package shu.scie.sbcp.domain;

import java.sql.Timestamp;

/**
 * Created by Thorn on 2016/7/13.
 */
public class Record {
    private int id;
    private Timestamp addTime;

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
}
