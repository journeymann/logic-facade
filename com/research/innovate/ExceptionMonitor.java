package com.research.processing.exception;

import com.research.processing.GenericObject;
import cucumber.api.Scenario;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect

public class ExceptionMonitor {
    private transient static final Log log = LogFactory.getLog(ExceptionMonitor.class);

    @AfterThrowing(pointcut = "execution(* com.research.processing..* (..))", throwing = "ex")
    public void logException(JoinPoint jp, Exception ex) {
        Scenario scenario;
        String scenarioName = null;
        if (jp.getTarget() instanceof GenericObject) {
            scenario = ((GenericObject) jp.getTarget()).getScenario();
            scenarioName = scenario.getName();
        }
        log.error("Logging exception in scenario" + scenarioName, ex);
    }
}
