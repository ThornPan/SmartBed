package shu.scie.sbcp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shu.scie.sbcp.DAO.AdminDao;
import shu.scie.sbcp.domain.Admin;
import shu.scie.sbcp.domain.User;

import java.util.List;

/**
 * Created by Thorn on 2016/6/30.
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;

    public Admin getAdminInfo(int id){
        return adminDao.getAdminInfo(id);
    }

    public List<User> getUserList(){
        return adminDao.getUserList();
    }
}
