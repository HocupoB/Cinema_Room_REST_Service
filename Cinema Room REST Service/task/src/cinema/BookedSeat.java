package cinema;


import java.util.UUID;

public class BookedSeat {
    private UUID token;
    private Seat ticket;

    public BookedSeat(){}
    public BookedSeat (Token token, Seat bookedSeat) {
        this.token = token.getToken();
        this.ticket =bookedSeat;
    }

    public Seat getTicket() {
        return ticket;
    }

    public void setTicket(Seat ticket) {
        this.ticket = ticket;
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }
}
