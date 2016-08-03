package shu.scie.sbcp.service;

/**
 * Created by Thorn on 2016/6/30.
 */
public interface GlobalService {
    public boolean checkAccount(String type,int id,String pw);
    public void userLogin(int id);
    public void userLogout(int id);
    boolean checkFamilyID(String id);
    void insertFamily(String id,String name,String pw,String phone);
    boolean checkFamilyAccount(String userId,String userPw);
}
