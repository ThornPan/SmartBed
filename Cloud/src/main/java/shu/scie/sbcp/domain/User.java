package shu.scie.sbcp.domain;

/**
 * Created by Thorn on 2016/6/16.
 */
public class User {
    private int identity;
    private String name;
    private int age;
    private String sex;
    private boolean status;

    public void setIdentify(int identify){
        this.identity=identify;
    }
    public int getIdentify(){
        return identity;
    }

    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }

    public void setAge(int age){
        this.age=age;
    }
    public int getAge(){
        return age;
    }

    public void setSex(String sex){
        this.sex=sex;
    }
    public String getSex(){
        return sex;
    }

    public void setStatus(boolean status){
        this.status=status;
    }
    public boolean getStatus(){
        return status;
    }
}
