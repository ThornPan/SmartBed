package shu.scie.sbcp.domain;

/**
 * Created by Thorn on 2016/7/30.
 */
public class AlarmRecord extends Record {
    private String type;

    public void setType(String type){
        this.type = type;
    }

    public String getType(){
        return type;
    }
}
