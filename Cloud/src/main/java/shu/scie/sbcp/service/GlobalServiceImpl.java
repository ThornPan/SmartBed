package shu.scie.sbcp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shu.scie.sbcp.DAO.GlobalDao;

/**
 * Created by Thorn on 2016/6/29.
 */
@Service
public class GlobalServiceImpl implements GlobalService {
    @Autowired
    private GlobalDao globalDao;

    public boolean checkAccount(String type,int id,String pw){
        if(globalDao.getUserName(type,id)!=null){
            if(pw.equals(globalDao.getUserPw(type,id))){
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }

    public void userLogin(int id){
        globalDao.userLogin(id);
    }

    public void userLogout(int id){
        globalDao.userLogout(id);
    }


}
