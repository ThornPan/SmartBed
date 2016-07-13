package shu.scie.sbcp.service;

import org.json.JSONException;
import org.json.JSONObject;
import shu.scie.sbcp.domain.MedicalHistory;
import shu.scie.sbcp.domain.Parameter;
import shu.scie.sbcp.domain.User;

import java.util.List;

/**
 * Created by Thorn on 2016/6/30.
 */
public interface UserService {
    public User getUserInfo(int id);
    public List<MedicalHistory> getMedicalHistoryList(int id);
    public List<Parameter> getParameterList(int id);
    public void userLeaveBed(int id,long addTime)throws JSONException;
    public void sendLeaveBedMessage(String name,String type,String nums,String time)throws JSONException;
    public void uploadParameter(JSONObject jsonParameter)throws JSONException;
    public void uploadTurnRecord(JSONObject jsonTurnRecord)throws JSONException;
    public void uploadToiletRecord(JSONObject jsonToiletRecord)throws JSONException;
    public void uploadMedicineRecord(JSONObject jsonMedicineRecord)throws JSONException;
    public void updateUserInfo(JSONObject jsonUserInfo)throws JSONException;
}
