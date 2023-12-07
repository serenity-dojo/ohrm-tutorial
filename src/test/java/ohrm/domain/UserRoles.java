package ohrm.domain;

public enum UserRoles {
    ADMIN("Admin","admin123");

    private final String username;
    private final String password;

    UserRoles(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String username() {
        return username;
    }

    public String password() {
        return password;
    }
}
