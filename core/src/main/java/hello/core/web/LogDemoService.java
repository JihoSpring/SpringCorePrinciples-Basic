package hello.core.web;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

import hello.core.common.MyLogger;

@Service
public class LogDemoService {

    private final ObjectProvider<MyLogger> myLoggerProvider;

    public LogDemoService(ObjectProvider<MyLogger> myLoggerProvider) {
        this.myLoggerProvider = myLoggerProvider;
    }

    public void logic(String uuid) {
        MyLogger myLogger = myLoggerProvider.getObject();
        myLogger.log("service.logic() - is same uuid ? " + uuid.equals(myLogger.getUuid()));
    }

}
