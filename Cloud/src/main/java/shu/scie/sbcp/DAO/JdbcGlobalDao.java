package shu.scie.sbcp.DAO;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

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
}
