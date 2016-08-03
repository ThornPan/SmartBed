package shu.scie.sbcp.DAO;

import shu.scie.sbcp.domain.Family;
import shu.scie.sbcp.domain.User;

import java.util.List;

/**
 * Created by Thorn on 2016/8/1.
 */
public interface FamilyDao {
    Family getFamilyInfo(String id);
    List<User> getFamilyUserList(String id);
    void bind(int id,String family);
    String getUserName(String type,int id);
    String getUserPw(String type,int id);
}
