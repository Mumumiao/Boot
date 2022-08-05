package com.mySpring.Web;

import com.mySpring.boot.ResponseEntility;
import com.mySpring.boot.ResponseFactory;
import com.mySpring.boot.User;
import com.mySpring.service.UserSeverce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@Transactional(rollbackFor =Exception.class)
public class LoginConsole {
    @Autowired
    private UserSeverce userSeverce;

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntility<String> login( String account, String password, HttpSession session, Model model) {
        System.out.println(account);
        System.out.println(password);
        User user = userSeverce.login(account, password);
        if(user==null){
            model.addAttribute("msg","密码或账号错误");
            return ResponseFactory.getDeResponseEntility("密码或账号错误");
        }else{
            session.setAttribute("user",user);
            return ResponseFactory.getSuResponseEntility("登陆成功");
        }


    }

    @RequestMapping("/tologin")
    @ResponseBody
    public String tologin() {
        return "login";
    }
}
