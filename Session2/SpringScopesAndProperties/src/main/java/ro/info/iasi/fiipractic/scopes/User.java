package ro.info.iasi.fiipractic.scopes;

public class User {
    private String name;

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User [name=" + name + "]";
    }

}
