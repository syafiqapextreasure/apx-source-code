package com.ppk.utilities;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service("commnServiceImpl")
public class CommonServiceImpl {

    /**
     * Removes success and error messages from the session
     * This is used in Thymeleaf templates to clear session messages after they've been displayed
     */
    public boolean removeSessionMessage() {
        try {
            HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
            
            // Remove common message attributes
            session.removeAttribute("succMsg");
            session.removeAttribute("errorMsg");
            session.removeAttribute("message");
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
} 