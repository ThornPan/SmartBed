package shu.scie.sbcp.domain;

import java.sql.Timestamp;

/**
 * Created by Thorn on 2016/6/23.
 */
public class MedicalHistory {
    private int id;
    private Timestamp date;
    private String content;

    public void setId(int id){
        this.id=id;
    }
    public int getId(){
        return id;
    }

    public void setDate(Timestamp date){
        this.date=date;
    }
    public Timestamp getDate(){
        return date;
    }

    public void setContent(String content){
        this.content=content;
    }
    public String getContent(){
        return content;
    }
}
