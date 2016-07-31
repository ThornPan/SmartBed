package shu.scie.sbcp.DAO;

import shu.scie.sbcp.domain.Family;

/**
 * Created by Thorn on 2016/6/29.
 */
public interface GlobalDao {
    String getUserName(String type,int id);
    String getUserPw(String type,int id);
    void userLogin(int id);
    void userLogout(int id);
    boolean checkFamilyID(String id);
    void insertFamily(String id,String name,String pw,String phone);
}
