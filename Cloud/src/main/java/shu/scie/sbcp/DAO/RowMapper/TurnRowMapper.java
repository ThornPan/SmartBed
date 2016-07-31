package shu.scie.sbcp.DAO.RowMapper;

import org.springframework.jdbc.core.RowMapper;
import shu.scie.sbcp.domain.TurnRecord;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Thorn on 2016/7/30.
 */
public class TurnRowMapper implements RowMapper {
    public TurnRecord mapRow(ResultSet rs,int rowNum)throws SQLException{
        TurnRecord turnRecord = new TurnRecord();
        turnRecord.setId(rs.getInt("id"));
        turnRecord.setAddTime(rs.getTimestamp("addtime"));
        return turnRecord;
    }
}
