package shu.scie.sbcp.DAO.RowMapper;

import org.springframework.jdbc.core.RowMapper;
import shu.scie.sbcp.domain.MedicineRecord;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Thorn on 2016/7/30.
 */
public class MedicineRowMapper implements RowMapper {
    public MedicineRecord mapRow(ResultSet rs,int rowNum)throws SQLException{
        MedicineRecord medicineRecord = new MedicineRecord();
        medicineRecord.setId(rs.getInt("id"));
        medicineRecord.setAddTime(rs.getTimestamp("addtime"));
        medicineRecord.setMedicine(rs.getString("medicine"));
        medicineRecord.setDosage(rs.getInt("dosage"));
        return medicineRecord;
    }
}
