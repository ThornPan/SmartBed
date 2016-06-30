package shu.scie.sbcp.service;

import shu.scie.sbcp.domain.Admin;
import shu.scie.sbcp.domain.User;

import java.util.List;

/**
 * Created by Thorn on 2016/6/30.
 */
public interface AdminService {
    public Admin getAdminInfo(int id);
    public List<User> getUserList();
}
