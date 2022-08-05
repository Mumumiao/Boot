package com.mySpring.service.imple;



import com.mySpring.boot.Role;
import com.mySpring.boot.User;
import com.mySpring.boot.Userright;
import com.mySpring.mapper.RoleMapper;
import com.mySpring.mapper.UserMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Setter
@Getter
public class RightFilt implements Filter {
    Boolean judge = true;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("进入过滤器");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        judge = true;
       /* ServletContext context = request.getServletContext();
        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(context);
        UserMapper userMapper = ctx.getBean("userMapper", UserMapper.class);
        RoleMapper roleMapper = ctx.getBean("roleMapper", RoleMapper.class);*/
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            try {
                req.getRequestDispatcher("/tologin").forward(req, resp);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("进入过滤器，但用户为空");
        } else {

            System.out.println("请求网址为" + req.getRequestURI());
            User userf = userMapper.getById(user.getId());
            Role role = userf.getRole();
            Role rolef = roleMapper.getRightById(role.getId());
            List<Userright> userrights = rolef.getUserrights();
            userrights.forEach(i -> {
                System.out.println("输出权力");
                System.out.println(i.getUrl());
                System.out.println(i.getId());
                if (req.getRequestURI().startsWith(i.getUrl())) {
                    judge = false;
                    try {
                        chain.doFilter(request, response);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ServletException e) {
                        e.printStackTrace();
                    }

                }

            });
            System.out.println("判断条件" + judge);
            if (judge) {
                judge = true;
                try {
                    resp.sendRedirect("/index");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }


    }

    @Override
    public void destroy() {

    }
}


