package shu.scie.sbcp.domain;

/**
 * Created by Thorn on 2016/6/16.
 */
public class Family {
    private int identity;
    private String name;
    private String phone;

    public void setIdentity(int identity){
        this.identity=identity;
    }
    public int getIdentity(){
        return identity;
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
