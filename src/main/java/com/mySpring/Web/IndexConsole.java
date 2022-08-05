package com.mySpring.Web;


import com.mySpring.boot.Menu;
import com.mySpring.boot.User;
import com.mySpring.service.UserSeverce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@Transactional(rollbackFor =Exception.class)
public class IndexConsole {
    @Autowired
    private UserSeverce userSeverce;

    @RequestMapping("/index")
    public String index(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<Menu> menus = userSeverce.getMenu(user.getId());
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
        model.addAttribute("famenu", famenu);
        return "index";
    }
}
