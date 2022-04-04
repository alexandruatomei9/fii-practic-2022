package ro.info.iasi.fiipractic.service;

public class UserIdGeneratorImpl implements UserIdGenerator {

    private final boolean isSignatureEnabled;

    public UserIdGeneratorImpl(boolean isSignatureEnabled) {
        this.isSignatureEnabled = isSignatureEnabled;
    }

    @Override
    public String generateUserId(String firstName, String lastName) {
        String initialFirstNameAndLastName = firstName.substring(0, 1).concat(lastName);
        return isSignatureEnabled ? "uuid:".concat(initialFirstNameAndLastName) : initialFirstNameAndLastName;
    }
}
