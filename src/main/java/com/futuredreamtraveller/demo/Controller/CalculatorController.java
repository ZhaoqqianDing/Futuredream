package com.futuredreamtraveller.demo.Controller;

import com.futuredreamtraveller.demo.Common.CommonUtils;
import com.futuredreamtraveller.demo.Service.CalculatorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

@RequestMapping("/CalculatorController")
@Slf4j
@RestController
public class CalculatorController {
    @Autowired
    private CalculatorService calculatorService;
    @RequestMapping("/test")
    public String test(HttpServletRequest request){
        String num = CommonUtils.trim(request.getParameter("num"));

        String type=(String)(request.getSession().getAttribute("session_Traffic_tools"));
        if(null==type){
            return "you should choose a tools first!";
        }
        log.info(type);
        if(type.equals("")){
            return "Please choose a traffic tools";
        }
        log.info("Your input is: "+num);
        if( !Pattern.compile("^[+-]?[0-9.]+$").matcher(num).find()){
            return "you should input a number!";
        }
        String ans="";
        try{
            ans=calculatorService.calculatorTheBenefit(Double.valueOf(num),type);
        }
        catch (Exception e){
            return "errors, please connect us or try again latter";
        }
        log.info(ans);
        return ans;
    }
    @RequestMapping("/chooseType")
    public String chooseType(HttpServletRequest request){
        String type = CommonUtils.trim(request.getParameter("type"));
        request.getSession().setAttribute("session_Traffic_tools",type);
        log.info("User Choose type: "+ type);
        return "You choose "+type;
    }
}
