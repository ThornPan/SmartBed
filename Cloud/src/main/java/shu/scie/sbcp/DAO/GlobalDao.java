package shu.scie.sbcp.DAO;

/**
 * Created by Thorn on 2016/6/29.
 */
public interface GlobalDao {
    public String getUserName(String type,int id);
    public String getUserPw(String type,int id);
    public void userLogin(int id);
    public void userLogout(int id);
}
