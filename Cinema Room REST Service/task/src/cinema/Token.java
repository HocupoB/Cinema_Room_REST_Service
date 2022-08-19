package cinema;

import java.util.UUID;

public class Token {
    UUID token;

    public Token() {
        token = UUID.randomUUID();
    }

    public Token(UUID token) {
        this.token = token;
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }
}
