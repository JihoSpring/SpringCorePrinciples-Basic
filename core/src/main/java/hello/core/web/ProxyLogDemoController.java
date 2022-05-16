package hello.core.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import hello.core.common.MyProxyLogger;

@Controller
public class ProxyLogDemoController {

    private final ProxyLogDemoService service;
    private final MyProxyLogger myLogger;

    public ProxyLogDemoController(ProxyLogDemoService service, MyProxyLogger myLogger) {
        this.service = service;
        this.myLogger = myLogger;
    }

    @RequestMapping("proxy-log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {
        myLogger.setRequestUrl(request.getRequestURL().toString());
        myLogger.log("controller.logDemo()");

        service.logic(myLogger.getUuid());

        return "OK";
    }

}
