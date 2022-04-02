package ro.info.iasi.fiipractic.service;

public interface UserIdGenerator {

    String generateUserId(String firstName, String lastName);
}
