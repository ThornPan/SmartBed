package shu.scie.sbcp.DAO;

import shu.scie.sbcp.domain.Admin;
import shu.scie.sbcp.domain.User;

import java.util.List;

/**
 * Created by Thorn on 2016/6/30.
 */
public interface AdminDao {
    public Admin getAdminInfo(int id);
    public List<User> getUserList();
}
