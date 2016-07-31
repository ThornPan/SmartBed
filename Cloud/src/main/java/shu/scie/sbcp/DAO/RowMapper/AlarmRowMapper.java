package shu.scie.sbcp.DAO.RowMapper;

import org.springframework.jdbc.core.RowMapper;
import shu.scie.sbcp.domain.AlarmRecord;
import shu.scie.sbcp.domain.Parameter;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Thorn on 2016/7/30.
 */
public class AlarmRowMapper implements RowMapper {
    public AlarmRecord mapRow(ResultSet rs, int rowNum)throws SQLException {
        AlarmRecord alarmRecord = new AlarmRecord();
        alarmRecord.setId(rs.getInt("id"));
        alarmRecord.setAddTime(rs.getTimestamp("addtime"));
        alarmRecord.setType(rs.getString("type"));
        return alarmRecord;
    }
}
