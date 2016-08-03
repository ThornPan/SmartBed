package shu.scie.sbcp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shu.scie.sbcp.DAO.FamilyDao;
import shu.scie.sbcp.domain.Family;
import shu.scie.sbcp.domain.User;

import java.util.List;

/**
 * Created by Thorn on 2016/8/1.
 */
@Service
public class FamilyServiceImpl implements FamilyService {
    @Autowired
    private FamilyDao familyDao;

    public Family getFamilyInfo(String id){
        return familyDao.getFamilyInfo(id);
    }

    public List<User> getFamilyUserList(String id){
        return familyDao.getFamilyUserList(id);
    }

    public void bind(int id,String family){
        familyDao.bind(id,family);
    }

    public boolean checkAccount(String type,int id,String pw){
        if(familyDao.getUserName(type,id)!=null){
            if(pw.equals(familyDao.getUserPw(type,id))){
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }
}
