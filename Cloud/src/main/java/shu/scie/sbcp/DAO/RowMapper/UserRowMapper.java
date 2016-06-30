package shu.scie.sbcp.DAO.RowMapper;

import org.springframework.jdbc.core.RowMapper;
import shu.scie.sbcp.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Thorn on 2016/6/30.
 */
public class UserRowMapper implements RowMapper {
    public User mapRow(ResultSet rs,int rowNum)throws SQLException{
        User user=new User();
        user.setId(rs.getInt("id"));
        user.setName(rs.getString("name"));
        user.setAge(rs.getInt("age"));
        user.setSex(rs.getString("sex"));
        return user;
    }
}
