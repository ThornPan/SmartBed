package shu.scie.sbcp.domain;

import java.sql.Timestamp;

/**
 * Created by Thorn on 2016/6/23.
 */
public class MedicalHistory {
    private int id;
    private Timestamp addTime;
    private String content;

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

    public void setContent(String content){
        this.content=content;
    }
    public String getContent(){
        return content;
    }
}
