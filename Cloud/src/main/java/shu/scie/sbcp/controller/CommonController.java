package shu.scie.sbcp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import shu.scie.sbcp.DAO.DataService;

import javax.persistence.Id;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * Created by Thorn on 2016/6/22.
 */
@Controller
public class CommonController {
    @RequestMapping(value = "/test")
    public String test(){
        System.out.println("test");
        return "Test";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public void login(HttpSession session, HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        int userId=Integer.parseInt(request.getParameter("id"));
        String userPw=request.getParameter("pw");
        String userType=request.getParameter("type");

        response.setContentType("charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter outs = response.getWriter();

        try {
            if(DataService.checkAccount(userType,userId,userPw)){
                session.setAttribute("id",userId);
                outs.write("success");
            }else{
                outs.write("fail");
            }
        }catch (Exception e){
            e.printStackTrace();
            outs.write("fail");
        }
        outs.flush();
        outs.close();
    }

    @RequestMapping(value = "/logout",method = RequestMethod.POST)
    public void logout(HttpSession session,HttpServletRequest request,HttpServletResponse response)
    throws IOException{
        session.removeAttribute("id");
        response.setContentType("charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter outs = response.getWriter();
        outs.write("success");
        outs.flush();
        outs.close();
    }
}
