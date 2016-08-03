package shu.scie.sbcp.DAO;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import shu.scie.sbcp.DAO.RowMapper.FamilyRowMapper;
import shu.scie.sbcp.DAO.RowMapper.UserRowMapper;
import shu.scie.sbcp.domain.Family;
import shu.scie.sbcp.domain.User;

import java.util.List;

/**
 * Created by Thorn on 2016/8/1.
 */
@Repository
public class JdbcFamilyDao extends JdbcDaoSupport implements FamilyDao {
    public Family getFamilyInfo(String id){
        String sql = "select * from family where id = '" + id + "'";
        System.out.println(sql);
        Family family = (Family) getJdbcTemplate().queryForObject(sql,new FamilyRowMapper());
        return family;
    }

    public List<User> getFamilyUserList(String id){
        String sql = "SELECT * from user where id in (SELECT userid from relation where relativeid = '"+id+"') order by id desc";
        List<User> list = (List<User>) getJdbcTemplate().query(sql,new UserRowMapper());
        return list;
    }

    public void bind(int id,String family){
        String sql = "INSERT into relation (userid,relativeid) values(?,?)";
        getJdbcTemplate().update(sql,new Object[]{id,family});
    }

    public String getUserName(String type,int id){
        String sql="select name from "+type+" where id = ?";
        String name = null;
        try {
            name = (String)getJdbcTemplate().queryForObject(sql,new Object[]{id},String.class);
        } catch (DataAccessException e) {
        }
        return name;
    }

    public String getUserPw(String type,int id){
        String sql="select pw from "+type+" where id="+id;
        String pw=(String)getJdbcTemplate().queryForObject(sql,String.class);
        return pw;
    }
}
