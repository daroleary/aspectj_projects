package org.benevity.faulttolerance;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.remoting.RemoteAccessException;

@Aspect
public class FailureHandlingAspect {

    private static final int MAX_RETRIES = 3;

    /**
     * Declare around advice returns Object to accommodate potentially different return types in the selected join points.
     * Uses an anonymous pointcut to select calls to all methods with the @Idempotent annotation.
     *
     * You return the value returned by the invocation of proceed(), although the join point is returning an int,
     * AspectJ takes care of boxing and unboxing logic.
     *
     * @param joinPoint
     * @return the value from proceed()
     * @throws Throwable
     */
    @Around("call(@Idempotent * *(..))")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        int retry = 0;
        while (true) {
            try {
                return joinPoint.proceed();
            } catch (RemoteAccessException ex) {
                System.out.println("Encountered " + ex);
                if (++retry > MAX_RETRIES) {
                    throw ex;
                }
                System.out.println("\tRetrying...");
            }
        }
    }
}