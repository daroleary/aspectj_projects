package org.benevity;

import org.benevity.messaging.MessageCommunicator;

/**
 */
public class Application {

    public static void main(String[] args) {
        MessageCommunicator messageCommunicator = new MessageCommunicator();
        messageCommunicator.deliver("Wanna learn AspectJ?");
        messageCommunicator.deliver("Harry", "having fun?");
        System.out.println("Last accessed time for messageCommunicator "
                + messageCommunicator.getLastAccessedTime());
    }
}
