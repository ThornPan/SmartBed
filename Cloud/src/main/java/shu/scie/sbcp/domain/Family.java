package shu.scie.sbcp.domain;

/**
 * Created by Thorn on 2016/6/16.
 */
public class Family {
    private String id;
    private String name;
    private String phone;

    public void setId(String id){
        this.id=id;
    }
    public String getId(){
        return id;
    }

    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }

    public void setPhone(String phone){
        this.phone=phone;
    }
    public String getPhone(){
        return phone;
    }
}
