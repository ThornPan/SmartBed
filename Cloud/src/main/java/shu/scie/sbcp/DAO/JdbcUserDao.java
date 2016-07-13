package shu.scie.sbcp.DAO;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import shu.scie.sbcp.DAO.RowMapper.MedicalHistoryRowMapper;
import shu.scie.sbcp.DAO.RowMapper.ParameterRowMapper;
import shu.scie.sbcp.DAO.RowMapper.UserRowMapper;
import shu.scie.sbcp.domain.*;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Thorn on 2016/6/30.
 */
@Repository
public class JdbcUserDao extends JdbcDaoSupport implements UserDao {
    public User getUserInfo(int id){
        String sql="select id,name,age,sex,status from user where id="+id;
        User user=(User)getJdbcTemplate().queryForObject(sql,new UserRowMapper());
        return user;
    }

    public List<MedicalHistory> getMedicalHistoryList(int id){
        String sql="select * from medicalhistory where id="+id+" order by addtime desc";
        List<MedicalHistory> list=(List<MedicalHistory>)getJdbcTemplate().query(sql,new MedicalHistoryRowMapper());
        return list;
    }

    public List<Parameter> getParameterList(int id){
        String sql="select * from pararecord where id ="+id+" order by addtime desc";
        List<Parameter> list=(List<Parameter>)getJdbcTemplate().query(sql,new ParameterRowMapper());
        return list;
    }

    public void userLeaveBed(int id,long addTime){
        String sql="update user set status=2 where id="+id;
        getJdbcTemplate().update(sql);
        String sql2="insert into alarmrecord (id,addtime,type) values (?,?,'leavebed')";
        getJdbcTemplate().update(sql2,id,new Timestamp(addTime));
    }

    public String getUserFamilyNum(int id){
        String nums="";
        String sql="select phone from family where id in (select relativeid from relation where userid = "+id+")";
        List<String> numList=getJdbcTemplate().queryForList(sql,String.class);
        if(numList.size()!=0){
            nums=numList.get(0);
            for (int n=1;n<numList.size();n++){
                nums=nums+","+numList.get(n);
            }
        }
        return nums;
    }

    public void saveParameter(Parameter parameter){
        String sql="insert into pararecord (id,addtime,bt,hr,danger) values (?,?,?,?,?)";
        getJdbcTemplate().update(sql,
                parameter.getId(),
                parameter.getAddTime(),
                parameter.getBodyTemperature(),
                /*parameter.getSystolicPressure(),
                parameter.getDiastolicPressure(),
                parameter.getAveragePressure(),
                parameter.getBloodOxygen(),
                parameter.getBloodGlucose(),*/
                parameter.getHeartRate(),
                parameter.isDanger());
    }

    public void saveTurnRecord(TurnRecord turnRecord){
        String sql="insert into turnrecord values(?,?)";
        getJdbcTemplate().update(sql,turnRecord.getId(),turnRecord.getAddTime());
    }

    public void saveToiletRecord(ToiletRecord toiletRecord){
        String sql="insert into toiletrecord values(?,?)";
        getJdbcTemplate().update(sql,toiletRecord.getId(),toiletRecord.getAddTime());
    }

    public void saveMedicineRecord(MedicineRecord medicineRecord){
        String sql="insert into medicinerecord values(?,?,?,?)";
        getJdbcTemplate().update(sql,
                medicineRecord.getId(),
                medicineRecord.getAddTime(),
                medicineRecord.getMedicine(),
                medicineRecord.getDosage());
    }

    public void updateUserInfo(User user){
        String sql="update user set name = ? , age = ? , sex = ? where id = ?";
        getJdbcTemplate().update(sql,user.getName(),user.getAge(),user.getSex(),user.getId());
    }
}
