package org.benevity.tracing;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public aspect JoinPointTraceAspect {
    private int callDepth;

    /**
     * !within(JoinPointTraceAspect) : exclude all join points with the aspect, therefore selecting all
     * calls, execution, field access and so forth outside of JoinPointTraceAspect
     */
    @Pointcut("!within(JoinPointTraceAspect)")
    public void traced() {
    }

    /**
     * The before advice executes just before each advised join point. Call dept is the level in the execution stack of method calls.
     *
     * @param thisJoinPoint current join point
     */
    @Before("traced()")
    public void tracedBefore(JoinPoint thisJoinPoint) {
        print("Before", thisJoinPoint);
        callDepth++;
    }

    /**
     * The after advice executes just after each advised join point. Call dept is the level in the execution stack of method calls.
     *
     * @param thisJoinPoint current join point
     */
    @After("traced()")
    public void tracedAfter(JoinPoint thisJoinPoint) {
        callDepth--;
        print("After", thisJoinPoint);
    }

    private void print(String prefix, Object message) {
        for (int i = 0; i < callDepth; i++) {
            System.out.print(" ");
        }
        System.out.println(prefix + ": " + message);
    }
}