package Airplane;

import customer.Customer;

public class Ticket {
    private String bookingId;
    private Customer customer;
    private String seatNumber;
    private String classType;
    private double price;

    public Ticket(String bookingId, Customer customer, String seatNumber, String classType, double price) {
        this.bookingId = bookingId;
        this.customer = customer;
        this.seatNumber = seatNumber;
        this.classType = classType;
        this.price = price;
    }

    public String getBookingId() {
        return bookingId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public String getClassType() {
        return classType;
    }

    public double getPrice() {
        return price;
    }
}

