package cn.itcast.common.web.session;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

/**
 * 本地Session
 *
 * @author lx
 */
@Component
public class HttpSessionProvider implements SessionProvider {
    //往Session设置值
    public void setAttribute(HttpServletRequest request, String name,
                             Serializable value) {
        // TODO Auto-generated method stub
        HttpSession session = request.getSession();//true    Cookie JSESSIONID
        session.setAttribute(name, value);
    }

    //从Session中取值
    public Serializable getAttribute(HttpServletRequest request, String name) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            return (Serializable) session.getAttribute(name);
        }
        return null;
    }

    //退出登陆
    public void logout(HttpServletRequest request) {
        // TODO Auto-generated method stub
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        //Cookie JSESSIONID

    }

    //获取SessionID
    public String getSessionId(HttpServletRequest request) {
        // TODO Auto-generated method stub
        //request.getRequestedSessionId();  //Http://localhost:8080/html/sfsf.shtml?JESSIONID=ewrqwrq234123412
        return request.getSession().getId();
    }

}
