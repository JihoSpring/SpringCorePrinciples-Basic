package hello.core.web;

import org.springframework.stereotype.Service;

import hello.core.common.MyProxyLogger;

@Service
public class ProxyLogDemoService {

    private final MyProxyLogger myLogger;

    public ProxyLogDemoService(MyProxyLogger myLogger) {
        this.myLogger = myLogger;
    }

    public void logic(String uuid) {
        myLogger.log("service.logic() - is same uuid ? " + uuid.equals(myLogger.getUuid()));
    }

}
