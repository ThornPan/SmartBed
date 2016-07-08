package shu.scie.sbcp.DAO;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import shu.scie.sbcp.DAO.RowMapper.MedicalHistoryRowMapper;
import shu.scie.sbcp.DAO.RowMapper.ParameterRowMapper;
import shu.scie.sbcp.DAO.RowMapper.UserRowMapper;
import shu.scie.sbcp.domain.MedicalHistory;
import shu.scie.sbcp.domain.Parameter;
import shu.scie.sbcp.domain.User;

import java.util.List;

/**
 * Created by Thorn on 2016/6/30.
 */
@Repository
public class JdbcUserDao extends JdbcDaoSupport implements UserDao {
    public User getUserInfo(int id){
        String sql="select id,name,age,sex from user where id="+id;
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

    public void userLeaveBed(int id){
        String sql="update user set status=2 where id="+id;
        getJdbcTemplate().update(sql);
    }
}
