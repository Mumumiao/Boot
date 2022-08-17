package com.mySpring.Web;

import com.mySpring.boot.ResponseEntility;
import com.mySpring.boot.ResponseFactory;
import com.mySpring.boot.Userright;
import com.mySpring.service.UserrightSeverce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/userright")
@Transactional(rollbackFor =Exception.class)
public class UserrightConsole {
    @Autowired
    private UserrightSeverce userrightSeverce;
    @GetMapping("/list")
    public ResponseEntility seleall() {
        List<Userright> userrights = userrightSeverce.getAll();
        List<Userright> fauserright = new ArrayList<>();
        userrights.forEach(i -> {
            List<Userright> chuserright = new ArrayList<>();
            if (i.getFa() == 0) {
                fauserright.add(i);
                System.out.println(i.getName());
                userrights.forEach(j -> {
                    if (j.getFa() == i.getId()) {
                        chuserright.add(j);
                        System.out.println(j.getName());
                        i.setUserrights(chuserright);
                    }
                });
            }
        });
        return ResponseFactory.getSuResponseEntility(fauserright);

    }

}
