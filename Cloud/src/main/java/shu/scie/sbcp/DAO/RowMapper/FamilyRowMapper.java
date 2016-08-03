package shu.scie.sbcp.DAO.RowMapper;

import org.springframework.jdbc.core.RowMapper;
import shu.scie.sbcp.domain.Family;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Thorn on 2016/8/1.
 */
public class FamilyRowMapper implements RowMapper {
    public Family mapRow(ResultSet rs,int rowNum)throws SQLException{
        Family family = new Family();
        family.setId(rs.getString("id"));
        family.setName(rs.getString("name"));
        family.setPhone(rs.getString("phone"));
        return family;
    }
}
