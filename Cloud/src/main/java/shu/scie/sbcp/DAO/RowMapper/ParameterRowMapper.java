package shu.scie.sbcp.DAO.RowMapper;

import org.springframework.jdbc.core.RowMapper;
import shu.scie.sbcp.domain.Parameter;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Thorn on 2016/7/1.
 */
public class ParameterRowMapper implements RowMapper {
    public Parameter mapRow(ResultSet rs,int rowNum)throws SQLException{
        Parameter parameter=new Parameter();
        parameter.setId(rs.getInt("id"));
        parameter.setAddTime(rs.getTimestamp("addtime"));
        parameter.setBodyTemperature(rs.getFloat("bt"));
        parameter.setDiastolicPressure(rs.getFloat("dbp"));
        parameter.setSystolicPressure(rs.getFloat("sbp"));
        parameter.setAveragePressure(rs.getFloat("abp"));
        parameter.setBloodOxygen(rs.getFloat("bo"));
        parameter.setBloodGlucose(rs.getFloat("bg"));
        parameter.setHeartRate(rs.getFloat("hr"));
        parameter.setDanger(rs.getBoolean("danger"));
        return parameter;
    }
}
