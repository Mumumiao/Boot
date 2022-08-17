package com.mySpring.Web;


import com.mySpring.boot.Menu;
import com.mySpring.boot.ResponseEntility;
import com.mySpring.boot.ResponseFactory;
import com.mySpring.service.MenuSeverce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/menu")
@Transactional(rollbackFor =Exception.class)
public class MenuConsole {
    @Autowired
    private MenuSeverce menuSeverce;

    @GetMapping("/list")
    public ResponseEntility seleall() {
        List<Menu> menus = menuSeverce.getAll();
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
