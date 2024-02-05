package lkenneth.expensetracker.domain.expenseTracker.models;

public class SignUpRequest {

    private String email;
    private String password;
    private String firebaseUUID;

    // Constructors, getters, setters, etc.

    // Default constructor
    public SignUpRequest() {
    }

    // Parameterized constructor
    public SignUpRequest(String email, String password, String firebaseUUID) {
        this.email = email;
        this.password = password;
        this.firebaseUUID = firebaseUUID;
    }

    // Getter and Setter methods

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirebaseUUID() {
        return firebaseUUID;
    }

    public void setFirebaseUUID(String firebaseUUID) {
        this.firebaseUUID = firebaseUUID;
    }
}

