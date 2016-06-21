package shu.scie.sbcp.domain;


/**
 * Created by Thorn on 2016/6/16.
 */
public class Admin {
    private int id;
    private String name;

    public void setId(int id){
        this.id=id;
    }
    public int getId(){
        return id;
    }

    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }
}
