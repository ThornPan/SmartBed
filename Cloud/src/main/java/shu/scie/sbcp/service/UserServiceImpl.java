package shu.scie.sbcp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shu.scie.sbcp.DAO.UserDao;
import shu.scie.sbcp.domain.MedicalHistory;
import shu.scie.sbcp.domain.Parameter;
import shu.scie.sbcp.domain.User;

import java.util.List;

/**
 * Created by Thorn on 2016/6/30.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    public User getUserInfo(int id){
        return userDao.getUserInfo(id);
    }

    public List<MedicalHistory> getMedicalHistoryList(int id){
        return userDao.getMedicalHistoryList(id);
    }

    public List<Parameter> getParameterList(int id){
        return userDao.getParameterList(id);
    }
}
