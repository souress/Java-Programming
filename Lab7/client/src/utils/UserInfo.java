package utils;

public class UserInfo {
    private final String username;
    private final String password;
    private boolean isAuthorized = false;

    public UserInfo(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setAuthorized() {
        isAuthorized = true;
    }

    public boolean isAuthorized() {
        return isAuthorized;
    }
}
