package shu.scie.sbcp.domain;

/**
 * Created by Thorn on 2016/6/16.
 */
public class User {
    private int id;
    private String name;
    private int age;
    private String sex;
    private int status;

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

    public void setStatus(int status){
        this.status=status;
    }
    public int getStatus(){
        return status;
    }
}
