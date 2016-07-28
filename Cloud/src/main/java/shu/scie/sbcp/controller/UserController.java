package shu.scie.sbcp.controller;

import com.sun.javafx.logging.PulseLogger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import shu.scie.sbcp.domain.MedicalHistory;
import shu.scie.sbcp.domain.Parameter;
import shu.scie.sbcp.domain.User;
import shu.scie.sbcp.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Thorn on 2016/6/23.
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    public int getUserIdPara(HttpSession session,String data){
        int id=0;
        if(data.equals("user")){
            id=(Integer) session.getAttribute("id");
        }else{
            String type=(String)session.getAttribute("type");
            if(type.equals("admin")){
                id=Integer.parseInt(data);
            }
        }
        return id;
    }

    @RequestMapping(value = "/getUserInfo",method = RequestMethod.POST)
    @ResponseBody
    public User getUserInfo(HttpSession session, @RequestParam String data){
        int id=getUserIdPara(session,data);
        User user=userService.getUserInfo(id);
        return user;
    }

    @RequestMapping(value = "/getMedicalHistoryList",method = RequestMethod.POST)
    @ResponseBody
    public List<MedicalHistory> getMedicalHistoryList(HttpSession session, @RequestParam String data){
        int id=getUserIdPara(session,data);
        List<MedicalHistory> list=userService.getMedicalHistoryList(id);
        return list;
    }

    @RequestMapping(value = "/getParameterList",method = RequestMethod.POST)
    @ResponseBody
    public List<Parameter> getParameterList(HttpSession session,@RequestParam String data){
        int id=getUserIdPara(session,data);
        List<Parameter> list=userService.getParameterList(id);
        return list;
    }

    @RequestMapping(value = "/userLeaveBed",method = RequestMethod.POST)
    @ResponseBody
    public String userLeaveBed(@RequestParam int userId,@RequestParam long addTime)throws JSONException{
        userService.userLeaveBed(userId,addTime);
        return "success";
    }

    @RequestMapping(value = "/uploadParameter",method = RequestMethod.POST)
    @ResponseBody
    public String uploadParameter(@RequestParam JSONObject jsonParameter)throws JSONException{
        userService.uploadParameter(jsonParameter);
        return "success";
    }

    @RequestMapping(value = "/uploadTurnRecord",method = RequestMethod.POST)
    @ResponseBody
    public String uploadTurnRecord(@RequestParam JSONObject jsonTurnRecord)throws JSONException{
        userService.uploadTurnRecord(jsonTurnRecord);
        return "success";
    }

    @RequestMapping(value = "/uploadToiletRecord",method = RequestMethod.POST)
    @ResponseBody
    public String uploadToiletRecord(@RequestParam JSONObject jsonToiletRecord)throws JSONException{
        userService.uploadToiletRecord(jsonToiletRecord);
        return "success";
    }

    @RequestMapping(value = "/uploadMedicineRecord",method = RequestMethod.POST)
    @ResponseBody
    public String uploadMedicineRecord(@RequestParam JSONObject jsonMedicineRecord)throws JSONException{
        userService.uploadMedicineRecord(jsonMedicineRecord);
        return "success";
    }

    @RequestMapping(value = "/updateUserInfo",method = RequestMethod.POST)
    @ResponseBody
    public String updateUserInfo(@RequestParam JSONObject jsonUserInfo)throws JSONException{
        System.out.println(jsonUserInfo.toString());
        userService.updateUserInfo(jsonUserInfo);
        return "success";

    }
}
