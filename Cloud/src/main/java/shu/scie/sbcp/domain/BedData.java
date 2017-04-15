package shu.scie.sbcp.domain;

import java.sql.Timestamp;

/**
 * Created by Thorn on 2017/4/15.
 */
public class BedData {
    private int userId;
    private String weight;
    private String degree1;
    private String degree2;
    private String degree3;
    private String degree4;
    private String degree5;
    private Timestamp deltaTime;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getDegree1() {
        return degree1;
    }

    public void setDegree1(String degree1) {
        this.degree1 = degree1;
    }

    public String getDegree2() {
        return degree2;
    }

    public void setDegree2(String degree2) {
        this.degree2 = degree2;
    }

    public String getDegree3() {
        return degree3;
    }

    public void setDegree3(String degree3) {
        this.degree3 = degree3;
    }

    public String getDegree4() {
        return degree4;
    }

    public void setDegree4(String degree4) {
        this.degree4 = degree4;
    }

    public String getDegree5() {
        return degree5;
    }

    public void setDegree5(String degree5) {
        this.degree5 = degree5;
    }

    public Timestamp getDeltaTime() {
        return deltaTime;
    }

    public void setDeltaTime(Timestamp deltaTime) {
        this.deltaTime = deltaTime;
    }
}
