package com.mySpring.Web;


import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.mySpring.boot.Config.Jwtutil;
import com.mySpring.boot.Menu;
import com.mySpring.boot.ResponseEntility;
import com.mySpring.boot.ResponseFactory;
import com.mySpring.service.UserSeverce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin
@Controller
@Transactional(rollbackFor =Exception.class)
public class IndexConsole {
    @Autowired
    private UserSeverce userSeverce;
    @Autowired
    private Jwtutil jwtutil;

    @RequestMapping("/index")
    @ResponseBody
    public ResponseEntility index(@RequestHeader String jwt) {
        System.out.println(jwt+"这是获取的请求头");
        int id=0;
        if(jwtutil.verifyJWT(jwt)){
            JWT jwtf = JWTUtil.parseToken(jwt);
            id= (int) jwtf.getPayload("id");
    }else {
            return ResponseFactory.getDeResponseEntility("令牌无效");
        }
        System.out.println("获取到的jwtid为"+id);
        List<Menu> menus = userSeverce.getMenu(id);
        List<Menu> famenu = new ArrayList<>();
        menus.forEach(i -> {
            List<Menu> chmenu = new ArrayList<>();
            if (i.getFa() == 0) {
                famenu.add(i);
                System.out.println(i.getName());
                menus.forEach(j -> {
                    if (j.getFa() == i.getId()) {
                        chmenu.add(j);
                        System.out.println(j.getName());
                        i.setMenuList(chmenu);
                    }
                });
            }
     });
        return ResponseFactory.getSuResponseEntility(famenu);
    }
}
