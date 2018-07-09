package org.benevity.remoting;

import org.benevity.faulttolerance.Idempotent;

import org.springframework.remoting.RemoteAccessException;

/**
 */
public class RemoteService {

    @Idempotent
    public int getReply() {
        double random = Math.random();
        if (random > 0.25) {
            throw new RemoteAccessException(String.format("Simulated failure occurred, random (%s) > 0.25", random));
        }
        System.out.println(String.format("Replying,  random (%s) > 0.25", random));
        return 5;
    }
}
