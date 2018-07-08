package org.benevity.profile;

public aspect ProfilingAspect {

    pointcut publicOperation()
            : execution(public * *.*(..));

    Object around(): publicOperation() {
        long start = System.nanoTime();
        Object ret = proceed();
        long end = System.nanoTime();
        System.out.println(String.format("%s took %s nanoseconds", thisJoinPointStaticPart.getSignature(), (end-start)));
        return ret;
    }
}