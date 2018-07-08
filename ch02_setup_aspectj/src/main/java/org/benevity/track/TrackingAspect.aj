package org.benevity.track;

import org.benevity.messaging.MessageCommunicator;

public aspect TrackingAspect {

    /**
     * Following statement makes the weaver assign the AccessTracked interface as the
     * parent of the MessageCommunicator class.
     */
    declare parents: MessageCommunicator implements AccessTracked;

    /**
     * Following attaches the lastAccessedTime and updateLastAccessedTime, getLastAccessedTime methods to the AccessTracked type.
     */
    private long AccessTracked.lastAccessedTime;

    public void AccessTracked.updateLastAccessedTime() {
        lastAccessedTime = System.currentTimeMillis();
    }

    public long AccessTracked.getLastAccessedTime() {
        return lastAccessedTime;
    }

    /**
     * execution(* AccessTracked+.*(..)) | Advises all methods of types AccessTracked via (+ ~ subtypes)
     * !execution(* AccessTracked.*(..)) | Ignore the methods in AccessTracked itself
     * this(accessTracked)               | The this() pointcut collects the tracked object so you can call the updateLastAccessedTime() method on it.
     */
    before(AccessTracked accessTracked)
            : execution(* AccessTracked+.*(..))
            && !execution(* AccessTracked.*(..))
            && this(accessTracked) {
        accessTracked.updateLastAccessedTime();
    }

    private static interface AccessTracked {
    }
}