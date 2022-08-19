package cinema;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.*;

public class Cinema {

    private int total_rows;
    private int total_columns;
    private List<Seat> available_seats;


    @JsonIgnore
    private List<BookedSeat> booked_seats;

    Cinema() {}
    Cinema(int total_rows, int total_columns) {
        this.total_rows = total_rows;
        this.total_columns = total_columns;
        this.available_seats = new ArrayList<>();
        this.booked_seats = new ArrayList<>();
        for(int i = 1; i <= total_rows; i++) {
            for( int j = 1; j <= total_columns; j++) {
                if(i<= 4)
                    available_seats.add(new Seat(i,j));
                else
                    available_seats.add(new Seat(i,j));

            }
        }
    }


    public List<BookedSeat> getBooked_seats() {
        return booked_seats;
    }

    public void setAvailable_seats(List<Seat> available_seats) {
        this.available_seats = available_seats;
    }

    public int getTotal_rows() {
        return total_rows;
    }

    public int getTotal_columns() {
        return total_columns;
    }


    public void setTotal_columns(int total_columns) {
        this.total_columns = total_columns;
    }

    public void setTotal_rows(int total_rows) {
        this.total_rows = total_rows;
    }

    public BookedSeat bookedSeat() {
        return  booked_seats.get(booked_seats.size()-1);
    }
    public Seat returnedSeat() {
        return available_seats.get(available_seats.size()-1);
    }

    public boolean checkAvailable(Seat seat) {
        for(Seat booked: available_seats) {
            if (booked.getRow() == seat.getRow() && booked.getColumn() == seat.getColumn()) {
                BookedSeat bookedSeat = new BookedSeat(new Token(), booked);
                booked_seats.add(bookedSeat);
                available_seats.remove(booked);
                return true;
            }
        }
        return false;
    }

    public boolean returnTicket(UUID token) {
        for(BookedSeat b : booked_seats) {
            if(b.getToken().equals(token)) {
                booked_seats.remove(b);
                available_seats.add(b.getTicket());
                return true;
            }
        }
        return false;
    }
    public List<Seat> getAvailable_seats() {
        return available_seats;
    }

    @JsonIgnore
    public int getCurrentIncome() {
        int income = 0;
        for(BookedSeat b : booked_seats) {
                income += b.getTicket().getPrice();
        }
        return income;
    }
    public Map<String, Integer> showStats() {
        Map<String, Integer> statistic = new HashMap<>();
        statistic.put("current_income" , getCurrentIncome());
        statistic.put("number_of_available_seats", available_seats.size());
        statistic.put("number_of_purchased_tickets", booked_seats.size());
        return statistic;
    }
}
