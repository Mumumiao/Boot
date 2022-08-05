package com.mySpring.Web;

import com.mySpring.boot.AddGroup;
import com.mySpring.boot.ResponseEntility;
import com.mySpring.boot.ResponseFactory;
import com.mySpring.boot.User;
import com.mySpring.service.UserSeverce;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
@Slf4j
@Transactional(rollbackFor =Exception.class)
public class UserConsole {
    @Autowired
    private UserSeverce userSeverce;
    @PostMapping("/add")
    public ResponseEntility<String> add(@RequestBody @Validated(AddGroup.class) User user){
     /*   if(result.hasFieldErrors()){
            Map<String,String> map=new HashMap<>();
            List<FieldError> errors = result.getFieldErrors();
            errors.forEach(e -> {
                log.info(e.getField(),e.getDefaultMessage());
                map.put(e.getField(),e.getDefaultMessage());
            });
            return new ResponseEntility(ReCode.not_found,"添加失败",map);

        }*/
        userSeverce.Add(user);
        return ResponseFactory.getSuResponseEntility("新创建的用户的id为"+user.getId());
    }
}
