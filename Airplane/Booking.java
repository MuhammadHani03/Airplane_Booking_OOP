package Airplane;

import Destination.Destination;
import customer.Customer;

public class Booking extends Ticket {
    public Booking(String bookingId, Customer customer, String seatNumber, String classType, double price) {
        super(bookingId, customer, seatNumber, classType, price);
    }

    private Destination destination;

    public Destination getDestination() {
        return destination;}

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

}

