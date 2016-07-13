package shu.scie.sbcp.DAO;

import shu.scie.sbcp.domain.*;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Thorn on 2016/6/30.
 */
public interface UserDao {
    public User getUserInfo(int id);
    public List<MedicalHistory> getMedicalHistoryList(int id);
    public List<Parameter> getParameterList(int id);
    public void userLeaveBed(int id,long addTime);
    public void saveParameter(Parameter parameter);
    public void saveTurnRecord(TurnRecord turnRecord);
    public void saveToiletRecord(ToiletRecord toiletRecord);
    public void saveMedicineRecord(MedicineRecord medicineRecord);
    public void updateUserInfo(User user);
    public String getUserFamilyNum(int id);
}
