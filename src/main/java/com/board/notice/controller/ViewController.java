package com.board.notice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.util.Enumeration;

/**
 * html 화면 이동
 *
 * @author Seok Jin, Oh
 * @since 2020 -06-10
 */
@Controller
public class ViewController {

    /**
     * 전체 적인 화면을 관할
     * view에서 viewName을 지정하여 이동.
     * view에서 받은 parameter는 model.attribute에 담아서 이동.
     *
     * @param request the request
     * @param model   the model
     * @return string
     * @throws Exception the exception
     */
    @GetMapping("/moveMenu")
    public String moveMenu(HttpServletRequest request, Model model) throws Exception {

        Enumeration<String> params = request.getParameterNames();
        String viwName = "/";
        while (params.hasMoreElements()){
            String name = params.nextElement();
            String value = request.getParameter(name);
            String decodeValue = URLDecoder.decode(value, "UTF-8");
            if("view".equals(name)) {
                viwName = URLDecoder.decode(decodeValue, "UTF-8");
            }
            model.addAttribute(name, decodeValue);

        }
        return viwName;
    }


    /**
     * home.html
     *
     * @param model the model
     * @return string
     */
    @GetMapping("/")
    public String home(Model model) {
        return "home";
    }

}
