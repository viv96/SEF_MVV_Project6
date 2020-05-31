package model;

public final class UserSession {
    private static UserSession instance;
    private User user;

    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }

        return instance;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean isManager() {
        if (this.user instanceof Manager) {
            return true;
        }

        return false;
    }

    public String getID() {
        return this.user.getId();
    }

    public void clearUser() {
        this.user = null;
    }
}
