package shu.scie.sbcp.DAO.RowMapper;

import org.springframework.jdbc.core.RowMapper;
import shu.scie.sbcp.domain.MedicalHistory;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Thorn on 2016/6/30.
 */
public class MedicalHistoryRowMapper implements RowMapper {
    public MedicalHistory mapRow(ResultSet rs,int rowNum)throws SQLException{
        MedicalHistory medicalHistory=new MedicalHistory();
        medicalHistory.setId(rs.getInt("id"));
        medicalHistory.setAddTime(rs.getTimestamp("addTime"));
        medicalHistory.setContent(rs.getString("content"));
        return medicalHistory;
    }
}
