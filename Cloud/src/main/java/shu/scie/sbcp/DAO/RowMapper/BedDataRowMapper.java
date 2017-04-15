package shu.scie.sbcp.DAO.RowMapper;

import org.springframework.jdbc.core.RowMapper;
import shu.scie.sbcp.domain.BedData;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Thorn on 2017/4/15.
 */
public class BedDataRowMapper implements RowMapper {
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        BedData bedData = new BedData();
        bedData.setUserId(resultSet.getInt("user_id"));
        bedData.setWeight(resultSet.getString("weight"));
        bedData.setDegree1(resultSet.getString("degree_1"));
        bedData.setDegree2(resultSet.getString("degree_2"));
        bedData.setDegree3(resultSet.getString("degree_3"));
        bedData.setDegree4(resultSet.getString("degree_4"));
        bedData.setDegree5(resultSet.getString("degree_5"));
        bedData.setDeltaTime(resultSet.getTimestamp("delta_time"));
        return bedData;
    }
}
