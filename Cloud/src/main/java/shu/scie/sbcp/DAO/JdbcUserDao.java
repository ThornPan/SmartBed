package shu.scie.sbcp.DAO;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.w3c.dom.ls.LSException;
import shu.scie.sbcp.DAO.RowMapper.*;
import shu.scie.sbcp.domain.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

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

    public List<Parameter> getParameterList(int id,int curPage,int pageSize){
        String sql="select * from pararecord where id ="+id+" order by addtime desc";
        List<Parameter> list=(List<Parameter>)getJdbcTemplate().query(sql,new ParameterRowMapper());
        return list;
    }

    public List<AlarmRecord> getAlarmList(int id,int curPage,int pageSize){
        String sql = "select * from alarmrecord where id ="+id+" order by addtime desc";
        List<AlarmRecord> list = (List<AlarmRecord>)getJdbcTemplate().query(sql,new AlarmRowMapper());
        return list;
    }

    public List<TurnRecord> getTurnList(int id){
        String sql = "select * from turnrecord where id ="+id+" order by addtime desc";
        List<TurnRecord> list = (List<TurnRecord>)getJdbcTemplate().query(sql,new TurnRowMapper());
        return list;
    }

    public List<MedicineRecord> getMedicineList(int id){
        String sql = "select * from medicinerecord where id ="+id+" order by addtime desc";
        List<MedicineRecord> list = (List<MedicineRecord>)getJdbcTemplate().query(sql,new MedicineRowMapper());
        return list;
    }

    public List<ToiletRecord> getToiletList(int id){
        String sql = "select * from toiletrecord where id ="+id+" order by addtime desc";
        List<ToiletRecord> list = (List<ToiletRecord>)getJdbcTemplate().query(sql,new ToiletRowMapper());
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

    public boolean checkRelation(int id,String family){
        int n = 0;
        String sql = "select id from relation where userid = " + id + " and relativeid = " + family;
        try {
            n = getJdbcTemplate().queryForObject(sql,Integer.class);
        }catch (EmptyResultDataAccessException e){

        }
        if(n == 0){
            return false;
        }
        return true;
    }

    public void saveBedData(BedData bedData) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String checkSql = "select * from bed_data where user_id = " + bedData.getUserId();
        System.out.println(checkSql);
        List<BedData> persistenceData = getJdbcTemplate().query(checkSql, new BedDataRowMapper());
        if (persistenceData.isEmpty()){
            String insertSql = "insert into bed_data values(?,?,?,?,?,?,?,?)";
            getJdbcTemplate().update(insertSql,
                    bedData.getUserId(),
                    bedData.getWeight(),
                    bedData.getDegree1(),
                    bedData.getDegree2(),
                    bedData.getDegree3(),
                    bedData.getDegree4(),
                    bedData.getDegree5(),
                    timestamp);
        } else {
            String updateSql = "update bed_data set weight = ?, degree_1 = ?, degree_2 = ?, " +
                    "degree_3 = ?, degree_4 = ?, degree_5 = ?, delta_time = ? where user_id = ?";
            getJdbcTemplate().update(updateSql,
                    bedData.getWeight(),
                    bedData.getDegree1(),
                    bedData.getDegree2(),
                    bedData.getDegree3(),
                    bedData.getDegree4(),
                    bedData.getDegree5(),
                    timestamp,
                    bedData.getUserId());
        }
    }

    public BedData getBedData(int userId) {
        String checkSql = "select * from bed_data where user_id = " + userId;
        BedData bedData = (BedData) getJdbcTemplate().queryForObject(checkSql, new BedDataRowMapper());
        return bedData;
    }
}
