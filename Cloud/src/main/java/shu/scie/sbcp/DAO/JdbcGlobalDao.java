package shu.scie.sbcp.DAO;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import shu.scie.sbcp.domain.Family;
import sun.invoke.empty.Empty;

/**
 * Created by Thorn on 2016/6/29.
 */
@Repository
public class JdbcGlobalDao extends JdbcDaoSupport implements GlobalDao {
    public String getUserName(String type,int id){
        String sql="select name from "+type+" where id = ?";
        String name = (String)getJdbcTemplate().queryForObject(sql,new Object[]{id},String.class);
        return name;
    }

    public String getUserPw(String type,int id){
        String sql="select pw from "+type+" where id="+id;
        String pw=(String)getJdbcTemplate().queryForObject(sql,String.class);
        return pw;
    }

    public void userLogin(int id){
        String sql="update user set status=1 where id="+id;
        getJdbcTemplate().update(sql);
    }

    public void userLogout(int id){
        String sql="update user set status=0 where id="+id;
        getJdbcTemplate().update(sql);
    }

    public boolean checkFamilyID(String id){
        String sql = "select id from family where id = '"+id+"'";
        String checkId = null;
        try {
            checkId = (String) getJdbcTemplate().queryForObject(sql,String.class);
        }catch (EmptyResultDataAccessException e){

        }
        if(checkId == null){
            return false;
        } else {
            return true;
        }
    }

    public void insertFamily(String id,String name,String pw,String phone){
        String sql = "insert into family values(?,?,?,?)";
        getJdbcTemplate().update(sql,new Object[]{id,name,pw,phone});
    }
}
