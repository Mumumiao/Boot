package com.mySpring.service.imple;


//
//@Setter
//@Getter
//public class RightFilt implements Filter {
//    Boolean judge = true;
//    @Autowired
//    private UserMapper userMapper;
//    @Autowired
//    private RoleMapper roleMapper;
//    @Autowired
//    private Jwtutil jwtutil;
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        System.out.println("进入过滤器");
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
//        judge = true;
//       /* ServletContext context = request.getServletContext();
//        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(context);
//        UserMapper userMapper = ctx.getBean("userMapper", UserMapper.class);
//        RoleMapper roleMapper = ctx.getBean("roleMapper", RoleMapper.class);*/
//        HttpServletRequest req = (HttpServletRequest) request;
//        HttpServletResponse resp = (HttpServletResponse) response;
//        if (req.getRequestURI().equals("/MyBoot/user/login")||req.getRequestURI().endsWith(".jpg")||req.getRequestURI().equals("/MyBoot/Emp/picf")||req.getRequestURI().equals("/MyBoot/login")) {
//            chain.doFilter(req, resp);
//            return;
//        }
//        JWT jwtf = JWTUtil.parseToken(req.getHeader("jwt"));
//        User user=userMapper.getById((int)jwtf.getPayload("id"));
//        System.out.println("用户idwei"+(int)jwtf.getPayload("id"));
//        ObjectMapper mapper = new ObjectMapper();
//        if (user == null) {
//            String json = mapper.writeValueAsString(ResponseFactory.getInResponseEntility("请重新你登录"));
//            response.setContentType("application/json;charset=utf-8");
//            response.getWriter().print(json);
//            response.getWriter().close();
//            System.out.println("进入权利过滤器，但用户为空");
//        } else {
//            System.out.println("请求网址为" + req.getRequestURI());
//            User userf = userMapper.getById(user.getId());
//            Role role = userf.getRole();
//            Role rolef = roleMapper.getRightById(role.getId());
//            List<Userright> userrights = rolef.getUserrights();
//            userrights.forEach(i -> {
//                System.out.println("输出权力");
//                System.out.println(i.getUrl());
//                System.out.println(i.getId());
//                if (req.getRequestURI().startsWith(i.getUrl())) {
//                    judge = false;
//                    try {
//                        chain.doFilter(request, response);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    } catch (ServletException e) {
//                        e.printStackTrace();
//                    }
//
//                }
//
//            });
//            System.out.println("没有权限");
//            if (judge) {
//                judge = true;
//                try {
//                    String json = mapper.writeValueAsString(ResponseFactory.getInResponseEntility("没有该权限"));
//                    response.setContentType("application/json;charset=utf-8");
//                    response.getWriter().print(json);
//                    response.getWriter().close();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//
//        }
//
//
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//}
//
//
