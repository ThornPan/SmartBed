package shu.scie.sbcp.DAO;

import org.json.JSONArray;
import org.json.JSONObject;
import shu.scie.sbcp.domain.Admin;
import shu.scie.sbcp.domain.MedicalHistory;
import shu.scie.sbcp.domain.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thorn on 2016/6/22.
 */
public class DataService {
    public static boolean checkAccount(String type,int id,String pw)throws Exception {
        DataBaseOperation dataBaseOperation=new DataBaseOperation();
        if(dataBaseOperation.isUserExist(type,id)){
            if(pw.equals(dataBaseOperation.getUserPw(type,id))) {
                dataBaseOperation.close();
                return true;
            }else {
                dataBaseOperation.close();
                return false;
            }
        }
        dataBaseOperation.close();
        return false;
    }

    public static Admin getAdminInfo(int id)throws Exception{
        DataBaseOperation dataBaseOperation=new DataBaseOperation();
        Admin admin=dataBaseOperation.getAdminInfo(id);
        dataBaseOperation.close();
        return admin;
    }

    public static User getUserInfo(int id)throws Exception{
        DataBaseOperation dataBaseOperation=new DataBaseOperation();
        User user=dataBaseOperation.getUserInfo(id);
        dataBaseOperation.close();
        return user;
    }

    public static JSONObject getUserList()throws Exception{
        JSONObject json=new JSONObject();
        DataBaseOperation dataBaseOperation=new DataBaseOperation();
        List<User> list=dataBaseOperation.getUserList();
        int count=list.size();
        json.put("count",count);
        JSONArray jsonArray=new JSONArray();
        for(User user:list){
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("id",user.getId());
            jsonObject.put("name",user.getName());
            jsonObject.put("age",user.getAge());
            jsonObject.put("sex",user.getSex());
            jsonArray.put(jsonObject);
        }
        json.put("user",jsonArray);
        dataBaseOperation.close();
        return json;
    }

    public JSONObject getMedicalHistoryList(int id)throws Exception{
        JSONObject json=new JSONObject();
        DataBaseOperation dataBaseOperation=new DataBaseOperation();
        List<MedicalHistory> list=dataBaseOperation.getMedicalHistoryList(id);
        int count=list.size();
        json.put("count",count);
        JSONArray jsonArray=new JSONArray();
        for(MedicalHistory medicalHistory:list){
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("id",medicalHistory.getId());
            jsonObject.put("date",medicalHistory.getDate());
            jsonObject.put("content",medicalHistory.getContent());
            jsonArray.put(jsonObject);
        }
        json.put("medicalhistory",jsonArray);
        dataBaseOperation.close();
        return json;
    }
}
