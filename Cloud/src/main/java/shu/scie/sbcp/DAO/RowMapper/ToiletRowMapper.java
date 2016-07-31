package shu.scie.sbcp.DAO.RowMapper;

import org.springframework.jdbc.core.RowMapper;
import shu.scie.sbcp.domain.ToiletRecord;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Thorn on 2016/7/30.
 */
public class ToiletRowMapper implements RowMapper {
    public ToiletRecord mapRow(ResultSet rs,int rowNum)throws SQLException{
        ToiletRecord toiletRecord = new ToiletRecord();
        toiletRecord.setId(rs.getInt("id"));
        toiletRecord.setAddTime(rs.getTimestamp("addtime"));
        return toiletRecord;
    }
}
