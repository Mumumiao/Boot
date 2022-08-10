package com.mySpring.Web;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.mySpring.boot.Config.Jwtutil;
import com.mySpring.boot.ResponseEntility;
import com.mySpring.boot.ResponseFactory;
import com.mySpring.boot.User;
import com.mySpring.service.UserSeverce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping ("/user")
@Transactional(rollbackFor =Exception.class)
public class LoginConsole {
    @Autowired
    private Jwtutil jwtutil;
    @Autowired
    private UserSeverce userSeverce;
    /*@CrossOrigin
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


    }*/

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntility login(@RequestBody  User u, HttpServletResponse response) {

        User user = userSeverce.login(u.getAccount(), u.getPassword());
        if(user==null){
            return ResponseFactory.getDeResponseEntility("密码或账号错误");
        }else{
            JWT jwt = JWTUtil.parseToken(jwtutil.createJwt(user.getRole().getId(),user.getId()));
            System.out.println("创建的jwt中存的id为"+jwt.getPayload("id"));
            System.out.println("创建的jwt中存的roleid为"+jwt.getPayload("roleid"));
            response.setHeader("jwt",jwtutil.createJwt(user.getRole().getId(),user.getId()));
            return ResponseFactory.getSuResponseEntility(user);
        }


    }

}
