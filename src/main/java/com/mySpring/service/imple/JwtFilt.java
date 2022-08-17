package com.mySpring.service.imple;


//
//@Slf4j
//@ResponseBody
//public class JwtFilt implements Filter {
//    @Autowired
//    private Jwtutil jwtutil;
//
//    @Override
//    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) req;
//        HttpServletResponse response = (HttpServletResponse) res;
//        ObjectMapper mapper = new ObjectMapper();
//        //登录的话通过
//        log.info("进入jwt过滤器");
//        if (request.getRequestURI().equals("/MyBoot/user/login")||request.getRequestURI().equals("/MyBoot/login")||request.getRequestURI().equals("/MyBoot/Emp/picf")||request.getRequestURI().endsWith(".jpg")||request.getRequestURI().equals("/MyBoot/login")) {
//            log.info("通过"+request.getRequestURI());
//            chain.doFilter(request, response);
//            return;
//        } else {
//            log.info("jwt开始验证"+request.getHeader("jwt"));
//            try {
//                if (jwtutil.verifyJWT(request.getHeader("jwt"))) {
//                    log.info("jwt通过验证");
//                    chain.doFilter(request, response);
//                }else{
//
//                    String json = mapper.writeValueAsString(ResponseFactory.getInResponseEntility("jwt有问题"));
//                    response.setContentType("application/json;charset=utf-8");
//                    response.getWriter().print(json);
//                    response.getWriter().close();
//                }
//            }catch(Exception e){
//                e.printStackTrace();
//                String json = mapper.writeValueAsString(ResponseFactory.getInResponseEntility("jwt有问题"));
//                response.setContentType("application/json;charset=utf-8");
//                response.getWriter().print(json);
//                response.getWriter().close();
//            }
//
//
//        }
//
//
//    }
//}
