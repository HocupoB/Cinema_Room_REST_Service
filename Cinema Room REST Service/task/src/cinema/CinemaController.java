package cinema;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Map;



@RestController
public class CinemaController {

    private final Cinema cinema;

    public CinemaController() {
        this.cinema = new Cinema(9 ,9);
    }

    @GetMapping("/seats")
    public Cinema getCinema() {
        return cinema;
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> purchase(@RequestBody Seat seat) {
        if(seat.getColumn() > cinema.getTotal_columns() ||
                seat.getRow() > cinema.getTotal_rows() ||
                seat.getRow() < 1 ||
                seat.getColumn() < 1 )
            return new ResponseEntity<>(Map.of("error", "The number of a row or a column is out of bounds!"), HttpStatus.BAD_REQUEST);

        else if(cinema.checkAvailable(seat)) {
            return new ResponseEntity<>(cinema.bookedSeat(), HttpStatus.OK);
        }

        return new ResponseEntity<>(Map.of("error", "The ticket has been already purchased!"), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/return")
    public ResponseEntity<?> returnTicket(@RequestBody Token token) {
        if(cinema.returnTicket(token.getToken())) {
            return new ResponseEntity<>(Map.of("returned_ticket", cinema.returnedSeat()), HttpStatus.OK);
        }
        return new ResponseEntity<>(Map.of("error", "Wrong token!"), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/stats")
    public ResponseEntity<?> showStats(@RequestParam(required = false) String password) {
        if(password != null && password.equals("super_secret"))
            return new ResponseEntity<>(cinema.showStats(), HttpStatus.OK);
        else
            return new ResponseEntity<>(Map.of("error", "The password is wrong!"), HttpStatus.UNAUTHORIZED);

    }





}
