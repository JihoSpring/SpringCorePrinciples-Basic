package hello.core.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import hello.core.common.MyLogger;

@Controller
public class LogDemoController {

    private final LogDemoService logDemoService;
    private final ObjectProvider<MyLogger> myLoggerProvider;

    public LogDemoController(LogDemoService logDemoService, ObjectProvider<MyLogger> myLogger) {
        this.logDemoService = logDemoService;
        this.myLoggerProvider = myLogger;
    }

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {
        MyLogger myLogger = myLoggerProvider.getObject();
        myLogger.setRequestUrl(request.getRequestURL().toString());
        myLogger.log("controller.logDemo()");

        logDemoService.logic(myLogger.getUuid());

        return "OK";
    }

}
