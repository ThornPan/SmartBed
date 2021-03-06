package shu.scie.sbcp.service;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shu.scie.sbcp.DAO.UserDao;
import shu.scie.sbcp.domain.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * Created by Thorn on 2016/6/30.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    private final String taobao_url="http://gw.api.taobao.com/router/rest";
    private final String SmsFreeSignName="智能床测试";
    private final String taobao_appKey="23401872";
    private final String taobao_secret="25ce2755d67d2acb58d22ec0b76bcf24";
    private final String leaveBedType="SMS_12166021";


    public User getUserInfo(int id){
        return userDao.getUserInfo(id);
    }

    public List<MedicalHistory> getMedicalHistoryList(int id){
        return userDao.getMedicalHistoryList(id);
    }

    /*public List<Parameter> getParameterList(int id){
        return userDao.getParameterList(id);
    }*/

    public JSONObject getParameter(int id,int curPage,int pageSize)throws JSONException{
        JSONObject jsonObject = new JSONObject();
        List<Parameter> list = userDao.getParameterList(id,curPage,pageSize);
        jsonObject.put("recordCount",list.size());
        JSONArray jsonArray = new JSONArray();
        int i = (curPage-1)*pageSize;
        while (i < curPage*pageSize && i<list.size()){
            JSONObject object = new JSONObject();
            object.put("danger",list.get(i).isDanger());
            object.put("bodyTemperature",String.valueOf(list.get(i).getBodyTemperature()));
            object.put("diastolicPressure",String.valueOf(list.get(i).getDiastolicPressure()));
            object.put("systolicPressure",String.valueOf(list.get(i).getSystolicPressure()));
            object.put("averagePressure",String.valueOf(list.get(i).getAveragePressure()));
            object.put("bloodOxygen",String.valueOf(list.get(i).getBloodOxygen()));
            object.put("bloodGlucose",String.valueOf(list.get(i).getBloodGlucose()));
            object.put("heartRate",String.valueOf(list.get(i).getHeartRate()));
            object.put("addTime",String.valueOf(list.get(i).getAddTime()));
            jsonArray.put(object);
            i++;
        }
        jsonObject.put("list",jsonArray);
        //System.out.println(jsonObject.toString());
        return  jsonObject;
    }

    public JSONObject getAlarmList(int id,int curPage,int pageSize)throws JSONException{
        JSONObject jsonObject = new JSONObject();
        List<AlarmRecord> list = userDao.getAlarmList(id,curPage,pageSize);
        jsonObject.put("recordCount",list.size());
        JSONArray jsonArray = new JSONArray();
        int i = (curPage-1)*pageSize;
        while (i < curPage*pageSize && i<list.size()){
            JSONObject object = new JSONObject();
            object.put("addTime",list.get(i).getAddTime());
            object.put("type",list.get(i).getType());
            jsonArray.put(object);
            i++;
        }
        jsonObject.put("list",jsonArray);
        return jsonObject;
    }

    public List<TurnRecord> getTurnList(int id){
        return userDao.getTurnList(id);
    }

    public List<MedicineRecord> getMedicineList(int id){
        return userDao.getMedicineList(id);
    }

    public List<ToiletRecord> getToiletList(int id){
        return userDao.getToiletList(id);
    }

    public void userLeaveBed(int id,long addTime)throws JSONException{
        userDao.userLeaveBed(id,addTime);
        String nums=userDao.getUserFamilyNum(id);
        if(!nums.equals("")){
            String name=userDao.getUserInfo(id).getName();
            String time=(new Timestamp(addTime)).toString().substring(5,19);
            sendLeaveBedMessage(name,leaveBedType,nums,time);
        }
    }

    public void uploadParameter(JSONObject jsonObject)throws JSONException{
        Parameter parameter=new Parameter();
        parameter.setId(jsonObject.getInt("userId"));
        parameter.setAddTime(new Timestamp(jsonObject.getLong("addTime")));
        parameter.setBodyTemperature(Float.parseFloat(jsonObject.getString("bt")));
        //parameter.setSystolicPressure(Float.parseFloat(jsonObject.getString("sbp")));
        //parameter.setDiastolicPressure(Float.parseFloat(jsonObject.getString("dbp")));
        //parameter.setAveragePressure(Float.parseFloat(jsonObject.getString("abp")));
        //parameter.setBloodOxygen(Float.parseFloat(jsonObject.getString("bo")));
        //parameter.setBloodGlucose(Float.parseFloat(jsonObject.getString("bg")));
        parameter.setHeartRate(Float.parseFloat(jsonObject.getString("hr")));
        parameter.setDanger(jsonObject.getBoolean("danger"));
        userDao.saveParameter(parameter);
    }

    public void uploadTurnRecord(JSONObject jsonTurnRecord)throws JSONException{
        TurnRecord turnRecord=new TurnRecord();
        turnRecord.setId(jsonTurnRecord.getInt("userId"));
        turnRecord.setAddTime(new Timestamp(jsonTurnRecord.getLong("addTime")));
        userDao.saveTurnRecord(turnRecord);
    }

    public void uploadToiletRecord(JSONObject jsonToiletRecord)throws JSONException{
        ToiletRecord toiletRecord=new ToiletRecord();
        toiletRecord.setId(jsonToiletRecord.getInt("userId"));
        toiletRecord.setAddTime(new Timestamp(jsonToiletRecord.getLong("addTime")));
        userDao.saveToiletRecord(toiletRecord);
    }

    public void uploadMedicineRecord(JSONObject jsonMedicineRecord)throws JSONException{
        MedicineRecord medicineRecord=new MedicineRecord();
        medicineRecord.setId(jsonMedicineRecord.getInt("userId"));
        medicineRecord.setAddTime(new Timestamp(jsonMedicineRecord.getLong("addTime")));
        medicineRecord.setMedicine(jsonMedicineRecord.getString("medicine"));
        medicineRecord.setDosage(jsonMedicineRecord.getInt("dosage"));
        userDao.saveMedicineRecord(medicineRecord);
    }

    public void updateUserInfo(JSONObject jsonUserInfo)throws JSONException{
        User user=new User();
        user.setId(jsonUserInfo.getInt("userId"));
        user.setName(jsonUserInfo.getString("name"));
        user.setAge(jsonUserInfo.getInt("age"));
        user.setSex(jsonUserInfo.getString("sex"));
        userDao.updateUserInfo(user);
    }

    public void sendLeaveBedMessage(String name,String type,String nums,String time)throws JSONException{
        TaobaoClient client = new DefaultTaobaoClient(taobao_url, taobao_appKey, taobao_secret);

        JSONObject jsonObject=new JSONObject();
        jsonObject.put("name",name);
        jsonObject.put("time",time);
        System.out.print(jsonObject.toString());
        System.out.println(nums);

        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        req.setExtend("");
        req.setSmsType("normal");
        req.setSmsFreeSignName(SmsFreeSignName);
        req.setSmsParamString(jsonObject.toString());
        req.setRecNum(nums);
        req.setSmsTemplateCode(type);
        try {
            AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
            System.out.println(rsp.getBody());
        }catch (ApiException e){
            e.printStackTrace();
            System.out.println("message fail");
        }
    }

    public boolean checkRelation(int id,String family){
        return userDao.checkRelation(id,family);
    }

    public String uploadBedData(BedData bedData) {
        try {
            userDao.saveBedData(bedData);
            return "success";
        } catch (Exception e){
            return e.getMessage();
        }
    }

    public BedData getBedData(int userId) {
        return userDao.getBedData(userId);
    }
}
