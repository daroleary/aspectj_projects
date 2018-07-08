package org.benevity.security;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class SecurityAspect {
    private Authenticator authenticator = new Authenticator();

    @Pointcut("execution(* org.benevity.messaging.MessageCommunicator.deliver(..))")
    public void secureAccess() {
    }

    @Before("secureAccess()")
    public void secure() {
        System.out.println("Checking and authenticating user");
        authenticator.authenticate();
    }
}