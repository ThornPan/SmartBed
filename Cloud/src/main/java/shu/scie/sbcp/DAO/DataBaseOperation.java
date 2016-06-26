package shu.scie.sbcp.DAO;

import org.json.JSONObject;
import shu.scie.sbcp.domain.Admin;
import shu.scie.sbcp.domain.MedicalHistory;
import shu.scie.sbcp.domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by Thorn on 2016/6/22.
 */
public class DataBaseOperation implements DataBaseMethods {
    private String dbURL;  //数据库地址
    private String userName; //数据库用户名
    private String userPwd;  //数据库密码
    private static Properties prop;
    private static final String CONFIGNAME = "/jdbc-config.properties";
    private Connection connection =null;

    public DataBaseOperation() throws Exception{


        prop = new Properties();
        //加载配置文件

        prop.load(DataBaseOperation.class.getClassLoader().getResourceAsStream(CONFIGNAME));
        //获取数据库驱动

        initConnection(prop);
    }

    private void initConnection(Properties prop)
    {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(prop.getProperty("CONNECTION_URL"),
                                                      prop.getProperty("CONNECTION_USERNAME"),
                                                      prop.getProperty("CONNECTION_PASSWORD"));
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void close(){
        closeConnection();
    }

    private void closeConnection()
    {
        if(connection!=null)
            try {
                connection.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    }

    public boolean isUserExist(String type,int id)throws SQLException{
        if(connection!=null){
            String sqlStr="select * from "+type+" where id="+id;
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(sqlStr);
            if(resultSet.next()){
                return true;
            }
        }
        return false;
    }

    public String getUserPw(String type,int id)throws SQLException{
        if(connection!=null){
            String sqlString="select pw from "+type+" where id="+id;
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(sqlString);
            if(resultSet.next()){
                String pw=resultSet.getString(1);
                return pw;
            }
        }
        return null;
    }

    public Admin getAdminInfo(int id)throws SQLException{
        Admin admin=new Admin();
        if(connection!=null){
            String sqlstr="select id,name from admin where id="+id;
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(sqlstr);
            if(resultSet.next()){
                admin.setId(resultSet.getInt(1));
                admin.setName(resultSet.getString(2));
            }
        }
        return admin;
    }

    public User getUserInfo(int id)throws SQLException{
        User user=new User();
        if(connection!=null){
            String sqlStr="select id,name,age,sex from user where id="+id;
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(sqlStr);
            if(resultSet.next()){
                user.setId(resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setAge(resultSet.getInt(3));
                user.setSex(resultSet.getString(4));
            }
        }
        return user;
    }

    public List<User> getUserList()throws SQLException{
        List<User> list=new ArrayList<User>();
        if(connection!=null){
            String sqlStr="select id,name,age,sex from user order by id desc";
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(sqlStr);
            while (resultSet.next()){
                User user=new User();
                user.setId(resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setAge(resultSet.getInt(3));
                user.setSex(resultSet.getString(4));
                list.add(user);
            }
        }
        return list;
    }

    public List<MedicalHistory> getMedicalHistoryList(int id)throws SQLException{
        List<MedicalHistory> list=new ArrayList<MedicalHistory>();
        if(connection!=null){
            String sqlStr="select * from medicalhistory where id="+id+"order by adddate desc";
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(sqlStr);
            while (resultSet.next()){
                MedicalHistory medicalHistory=new MedicalHistory();
                medicalHistory.setId(resultSet.getInt(1));
                medicalHistory.setDate(resultSet.getTimestamp(2));
                medicalHistory.setContent(resultSet.getString(3));
                list.add(medicalHistory);
            }
        }
        return list;
    }


}
