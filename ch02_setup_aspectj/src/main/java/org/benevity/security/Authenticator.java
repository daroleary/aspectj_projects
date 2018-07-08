package org.benevity.security;

/**
 */
class Authenticator {

    private ThreadLocal<String> authenticatedUser = new ThreadLocal<>();

    void authenticate() {
        if (isAuthenticated()) {
            return;
        }
        String[] userNamePassword = getUserNamePassword();
        if (!userNamePassword[0].equals(userNamePassword[1])) {
            throw new AuthenticationException("User/password didn't match");
        }
        authenticatedUser.set(userNamePassword[0]);
    }

    private boolean isAuthenticated() {
        return authenticatedUser.get() != null;
    }

    private String[] getUserNamePassword() {
        return new String[]{"doleary","doleary"};
    }
}
