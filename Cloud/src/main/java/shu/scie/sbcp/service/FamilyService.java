package shu.scie.sbcp.service;

import shu.scie.sbcp.domain.Family;
import shu.scie.sbcp.domain.User;

import java.util.List;

/**
 * Created by Thorn on 2016/8/1.
 */
public interface FamilyService {
    Family getFamilyInfo(String id);
    List<User> getFamilyUserList(String id);
    void bind(int id,String family);
    boolean checkAccount(String type,int id,String pw);
}
