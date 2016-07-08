package shu.scie.sbcp.service;

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
    public void userLeaveBed(int id);
}
