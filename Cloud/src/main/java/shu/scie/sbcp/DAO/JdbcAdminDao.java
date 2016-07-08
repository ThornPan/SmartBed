package shu.scie.sbcp.DAO;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import shu.scie.sbcp.DAO.RowMapper.UserRowMapper;
import shu.scie.sbcp.domain.Admin;
import shu.scie.sbcp.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Thorn on 2016/6/30.
 */
@Repository
public class JdbcAdminDao extends JdbcDaoSupport implements AdminDao {

    public class AdminRowMapper implements RowMapper<Admin> {
        public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
            Admin admin = new Admin();
            admin.setId(rs.getInt("id"));
            admin.setName(rs.getString("name"));
            return admin;
        }
    }


    public Admin getAdminInfo(int id){
        String sql="select * from admin where id="+id;
        Admin admin=(Admin) getJdbcTemplate().queryForObject(sql,new AdminRowMapper());
        return admin;
    }

    public List<User> getUserList(){
        String sql="select id,name,age,sex,status from user order by id desc";
        List<User> users=(List<User>)getJdbcTemplate().query(sql,new UserRowMapper());
        return users;
    }
}
