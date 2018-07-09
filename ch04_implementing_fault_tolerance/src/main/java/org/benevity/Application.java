package org.benevity;

import org.benevity.remoting.RemoteService;

/**
 */
public class Application {

    public static void main(String[] args) {
        RemoteService service = new RemoteService();
        int retVal = service.getReply();
        System.out.println("Reply is " + retVal);
    }
}
