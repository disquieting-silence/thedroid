package dsq.thedroid.contacts;

public class NoPhoneNumberException extends Exception {
    public NoPhoneNumberException(String name) {
        super("No phone number found for: " + name);
    }
}
