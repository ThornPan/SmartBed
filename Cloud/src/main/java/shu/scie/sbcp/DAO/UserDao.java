package shu.scie.sbcp.DAO;

import shu.scie.sbcp.domain.*;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Thorn on 2016/6/30.
 */
public interface UserDao {
    User getUserInfo(int id);
    List<MedicalHistory> getMedicalHistoryList(int id);
    List<Parameter> getParameterList(int id);
    List<AlarmRecord> getAlarmList(int id);
    List<TurnRecord> getTurnList(int id);
    List<MedicineRecord> getMedicineList(int id);
    List<ToiletRecord> getToiletList(int id);
    void userLeaveBed(int id,long addTime);
    void saveParameter(Parameter parameter);
    void saveTurnRecord(TurnRecord turnRecord);
    void saveToiletRecord(ToiletRecord toiletRecord);
    void saveMedicineRecord(MedicineRecord medicineRecord);
    void updateUserInfo(User user);
    String getUserFamilyNum(int id);
}
