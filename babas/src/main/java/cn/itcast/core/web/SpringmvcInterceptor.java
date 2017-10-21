package cn.itcast.core.web;

import cn.itcast.common.web.session.SessionProvider;
import cn.itcast.core.bean.user.Buyer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 处理上下文
 * 处理Session
 * 全局
 *
 * @author lx
 */
public class SpringmvcInterceptor implements HandlerInterceptor {

    @Autowired
    private SessionProvider sessionProvider;

    private Integer adminId;
    //拦截 包括/buyer  的请求路径
    public static final String INTERCETOR_URL = "/buyer/";
    //返回路径
    public static final String RETURNURL = "returnUrl";

    //方法前   /buyer/
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        if (adminId != null) {
            Buyer b = new Buyer();
            b.setUsername("fbb2014");
            sessionProvider.setAttribute(request, Constants.BUYER_SESSION, b);

            request.setAttribute("isLogin", true);
        } else {
            Buyer buyer = (Buyer) sessionProvider.getAttribute(request, Constants.BUYER_SESSION);
            if (null != buyer) {
                request.setAttribute("isLogin", true);
            } else {
                request.setAttribute("isLogin", false);
            }
            String requestURI = request.getRequestURI();

            if (requestURI.contains(INTERCETOR_URL)) {
                if (null == buyer) {
                    response.sendRedirect("/shopping/login.shtml?" + RETURNURL + "=" + requestURI);
                    return false;
                }
            }
        }
        return true;
    }


    //方法后
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub

    }

    //页面渲染后
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // TODO Auto-generated method stub

    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }


}
