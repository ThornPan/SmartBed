package shu.scie.sbcp.service;

import org.json.JSONException;
import org.json.JSONObject;
import shu.scie.sbcp.domain.*;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by Thorn on 2016/6/30.
 */
public interface UserService {
    User getUserInfo(int id);
    List<MedicalHistory> getMedicalHistoryList(int id);
    //List<Parameter> getParameterList(int id);
    JSONObject getParameter(int id,int curPage,int pageSize) throws JSONException;
    //List<AlarmRecord> getAlarmList(int id);
    JSONObject getAlarmList(int id,int curPage,int pageSize) throws JSONException;
    List<TurnRecord> getTurnList(int id);
    List<MedicineRecord> getMedicineList(int id);
    List<ToiletRecord> getToiletList(int id);
    void userLeaveBed(int id,long addTime)throws JSONException;
    void sendLeaveBedMessage(String name,String type,String nums,String time)throws JSONException;
    void uploadParameter(JSONObject jsonParameter)throws JSONException;
    void uploadTurnRecord(JSONObject jsonTurnRecord)throws JSONException;
    void uploadToiletRecord(JSONObject jsonToiletRecord)throws JSONException;
    void uploadMedicineRecord(JSONObject jsonMedicineRecord)throws JSONException;
    void updateUserInfo(JSONObject jsonUserInfo)throws JSONException;
    boolean checkRelation(int id,String family);
}
