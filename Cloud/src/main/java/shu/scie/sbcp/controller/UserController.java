package shu.scie.sbcp.controller;

import org.hibernate.service.jdbc.connections.internal.DatasourceConnectionProviderImpl;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import shu.scie.sbcp.DAO.DataService;
import shu.scie.sbcp.domain.Admin;
import shu.scie.sbcp.domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Thorn on 2016/6/23.
 */
@Controller
public class UserController {
    @RequestMapping(value = "/getUserInfo",method = RequestMethod.POST)
    public void getUserInfo(HttpSession session, HttpServletRequest request, HttpServletResponse response)
    throws ServletException,IOException{
        int id=(Integer) session.getAttribute("id");
        JSONObject jsonObject=new JSONObject();

        try {
            User user= DataService.getUserInfo(id);
            jsonObject.put("id",user.getId());
            jsonObject.put("name",user.getName());
            jsonObject.put("age",user.getAge());
            jsonObject.put("sex",user.getSex());
        }catch (Exception e){
            e.printStackTrace();
        }

        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter outs = response.getWriter();
        outs.write(jsonObject.toString());
        outs.flush();
        outs.close();
    }

    @RequestMapping(value = "/getMedicalHistoryList",method = RequestMethod.POST)
    public void getMedicalHistoryList(){

    }
}
