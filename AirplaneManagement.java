import Airplane.Booking;
import customer.Customer;
import Destination.Destination;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AirplaneManagement {

    private static List<Booking> bookings = new ArrayList<>(); // List to store the bookings made by customers

    private static char[][] seats = new char[4][4]; // 2D array representing the seats on the airplane
    
    // The seats array is a 4x4 matrix where each element represents a seat
    // For example, seats[0][0] represents the seat in the first row and first column
    // Each seat can have two possible values: 'X' (booked) or a blank space (available)
    
    // Example representation of the seats array:
    // [ ][ ][ ][ ]
    // [ ][ ][ ][ ]
    // [ ][ ][ ][ ]
    // [ ][ ][ ][ ]
    // Initially, all seats are available and represented by blank spaces
    
    
    // this is for the txt file to be read edit this path same for line 100

    private static final String FILE_PATH = "C:/Users/HP/Desktop/Airplane/bookings.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        initializeSeats();
        readBookingsFromFile(FILE_PATH); // Read existing bookings from file

        // Rest of the code...
        // (Your existing code here)

        // Write updated bookings to file
        writeBookingsToFile(FILE_PATH);


        while (running) {
            System.out.println();
            System.out.println("Welcome to Dawn Airlines!");
            System.out.println("--------------------------");
            System.out.println();
            System.out.println("Would you like to:");
            System.out.println("1. Book a flight");

            System.out.println("2. Check your bookings");

            System.out.println("3. Click to Exit");

            System.out.print("Enter your choice (1 or 3): ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            if (choice == 1) {
                System.out.println();
                System.out.println("Please provide your details:");

                System.out.print("First Name: ");

                String firstName = scanner.nextLine();
                System.out.print("Last Name: ");

                String lastName = scanner.nextLine();

                String customerId = generateCustomerId(); // Generate unique customer ID
                Customer customer = new Customer(customerId, firstName, lastName); // Create a new Customer object with the generated customer ID, first name, and last name

                System.out.println();
                System.out.println("Let's find out the perfect flight for you " + customer.getFirstName() + customer.getLastName() + "!");

                // CODE ADDED FOR DESTINATION METHODS
                Destination destination = new Destination(scanner);
                System.out.println();
                String Country = destination.selectCountry();
                System.out.println();
                String City = destination.selectCity(Country);
                System.out.println();
                String Date = destination.selectDate();
                System.out.println();
                String flightTime = destination.selectTime(Date);
                System.out.println();
                String bookingTime = destination.bookingTime();
                System.out.println();
                Destination destination1 = new Destination(Country, City, Date, flightTime, bookingTime);
                //----------------------------------------------------------------------------------

                System.out.println("Select your class:");

                System.out.println("1. Business Class ($500)");

                System.out.println("2. Economy Class ($200)");

                System.out.println("3. Premium Economy Class ($300)");

                System.out.print("Enter your choice (1-3): ");

                int classChoice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                String classType;

                double price;

                // Determine the class type and price based on user's choice
                switch (classChoice) {

                    case 1:
                        // Business Class
                        classType = "Business Class";
                        price = 500.0;
                        break;

                    case 2:
                        // Economy Class
                        classType = "Economy Class";
                        price = 200.0;
                        break;

                    case 3:
                        // Premium Economy Class
                        classType = "Premium Economy Class";
                        price = 300.0;
                        break;

                    default:
                        // Invalid choice, defaulting to Economy Class
                        System.out.println("Invalid choice. Defaulting to Economy Class.");
                        classType = "Economy Class";
                        price = 200.0;
                }
                System.out.println();
                System.out.println("Here are the available seats:");
                displayAvailableSeats(); // Display the available seats to the user

                String selectedSeat = selectSeat(scanner); // Let the user select a seat

                if (selectedSeat != null) { // If a valid seat is selected
                    String bookingId = generateBookingId(); // Generate a unique booking ID
                    Booking booking = new Booking(bookingId, customer, selectedSeat, classType, price); // Create a new booking object
                    booking.setDestination(destination1);
                    bookings.add(booking); // Add the booking to the list of bookings

                    System.out.println();
                    System.out.println("Your booking is confirmed. Here are the details:");
                    System.out.println("--------------------------------------------------");
                    System.out.println();

                    System.out.println("Booking ID: " + bookingId);

                    System.out.println("Customer ID: " + customer.getCustomerId());

                    System.out.println("Name: " + customer.getFirstName() + " " + customer.getLastName());

                    System.out.println("Seat Number: " + selectedSeat);

                    System.out.println("Class: " + classType);

                    System.out.println("Price: $" + price);

                    // CODE ADDED FOR DESTINATION DETAILS PRINTING
                    System.out.println();
                    System.out.println("Destination Details: " + destination1.getCity() + ", " + destination1.getCountry());
                    System.out.println("Departure Date: " + destination1.getDate());
                    System.out.println("Flight Time: " + destination1.getFlightTime());
                    System.out.println("Ticked Booked on: " + destination1.getBookingTime());
                    System.out.println();
                    System.out.println("Thankyou for booking with us " + customer.getFirstName() + " " + customer.getLastName() + "!");
                    System.out.println("------------------------------------------------------");
                    //---------------------------------------------------------------------

                    // this is for the txt file to be read edit this path
                    writeBookingsToFile("C:/Users/HP/Desktop/Airplane/bookings.txt"); // Write updated bookings to file
                } else {

                    System.out.println("Invalid seat selection or seat already booked.");
                    System.out.println("Please try again.");

                }
            } else if (choice == 2) {
                System.out.print("Enter your customer ID: ");
                String customerId = scanner.nextLine();

                boolean found = false;

                // Iterate over the bookings to find the bookings associated with the given customer ID
                for (Booking booking : bookings) {
                    if (booking.getCustomer().getCustomerId().equals(customerId)) {
                        // Print the details of each booking found
                        System.out.println();
                        System.out.println("Your booking details");
                        System.out.println("------------------------");
                        System.out.println();
                        System.out.println("Booking ID: " + booking.getBookingId());
                        System.out.println("Attendant Name: " + booking.getCustomer().getFirstName() + " " + booking.getCustomer().getLastName());
                        System.out.println("Seat Number: " + booking.getSeatNumber());
                        System.out.println("Class: " + booking.getClassType());
                        System.out.println("Price: $" + booking.getPrice());
                        System.out.println("--------------------");
                        System.out.println("Destination: " + booking.getDestination().getCity() + ", " + booking.getDestination().getCountry());
                        System.out.println("Departure Date: " + booking.getDestination().getDate());
                        System.out.println("Flight time: " + booking.getDestination().getFlightTime());
                        System.out.println("Ticket booked on: " + booking.getDestination().getBookingTime());
                        System.out.println("----------------------");
                        found = true;
                    }
                }

                // If no bookings were found for the given customer ID, display a message
                if (!found) {
                    System.out.println("No bookings found for customer ID: " + customerId);
                }
            } else if (choice == 3) {
                System.out.println("Exiting the program...");
                System.exit(0);
            } else {
                System.out.println("Invalid choice. Please try again.");
            }

        }
        scanner.close();
    }
    // Initialize the seat matrix
    private static void initializeSeats() {
        // Iterate over each row
        for (int i = 0; i < 4; i++) {
            // Iterate over each column
            for (int j = 0; j < 4; j++) {
                // Assign a seat identifier to each seat based on the row index
                seats[i][j] = (char) ('A' + i);
            }
        }
    }

    // Display the available seats
    private static void displayAvailableSeats() {
        System.out.println("Available Seats:");

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print("| " + seats[i][j] + " ");
            }
            System.out.println("|");
        }
    }

// Let the customer select a seat
private static String selectSeat(Scanner scanner) {
    System.out.print("Enter the seat number (e.g., B1): ");
    String seatNumber = scanner.nextLine();

    // Convert the seat number to row and column indices
    int row = seatNumber.charAt(0) - 'A';
    int column = Integer.parseInt(seatNumber.substring(1)) - 1;

    // Validate the seat number
    if (row >= 0 && row < 4 && column >= 0 && column < 4) {
        // Check if the seat is already booked
        if (seats[row][column] == 'X') {
            System.out.println("Seat already booked. Please select another seat.");
            return null;
        } else {
            // Mark the seat as booked and return the seat number
            seats[row][column] = 'X';
            return seatNumber;
        }
    } else {
        // Invalid seat number
        System.out.println("Invalid seat number. Please try again.");
        return null;
    }
}

// Generate a unique customer ID
private static String generateCustomerId() {
    return "CUST" + System.currentTimeMillis();
}

// Generate a unique booking ID
private static String generateBookingId() {
    return "BOOK" + System.currentTimeMillis();
}

// Read existing bookings from file
private static void readBookingsFromFile(String filePath) {
    try {
        File file = new File(filePath);
        if (file.exists()) {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");

                if (parts.length == 12) {
                    // Extract booking information from the line
                    String bookingId = parts[0];
                    String customerId = parts[1];
                    String firstName = parts[2];
                    String lastName = parts[3];
                    String seatNumber = parts[4];
                    String classType = parts[5];
                    double price = Double.parseDouble(parts[6]);

                    // Extract destination details
                    String country = parts[7];
                    String city = parts[8];
                    String date = parts[9];
                    String flightTime = parts[10];
                    String bookingTime = parts[11];

                    // Create customer and booking objects
                    Customer customer = new Customer(customerId, firstName, lastName);
                    Booking booking = new Booking(bookingId, customer, seatNumber, classType, price);

                    // Create destination object
                    Destination destination = new Destination(country, city, date, flightTime, bookingTime);
                    booking.setDestination(destination);

                    // Add the booking to the list
                    bookings.add(booking);
                } else {
                    System.out.println("Invalid format in bookings file: " + line);
                }

            }

            scanner.close();
        }
    } catch (IOException e) {
        System.out.println("An error occurred while reading the bookings file.");
        e.printStackTrace();
    }
}

    // Write updated bookings to file
    private static void writeBookingsToFile(String filePath) {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (Booking booking : bookings) {
                // Extract booking information
                String bookingId = booking.getBookingId();
                String customerId = booking.getCustomer().getCustomerId();
                String firstName = booking.getCustomer().getFirstName();
                String lastName = booking.getCustomer().getLastName();
                String seatNumber = booking.getSeatNumber();
                String classType = booking.getClassType();
                double price = booking.getPrice();

                // Extract destination details
                Destination destination = booking.getDestination();
                String country = destination.getCountry();
                String city = destination.getCity();
                String date = destination.getDate();
                String flightTime = destination.getFlightTime();
                String bookingTime = destination.getBookingTime();

                // Create a line in the file with booking and destination information
                String line = bookingId + "," + customerId + "," + firstName + "," + lastName + "," +
                        seatNumber + "," + classType + "," + price + "," +
                        country + "," + city + "," + date + "," + flightTime + "," + bookingTime + "\n";
                writer.write(line);
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the bookings file.");
            e.printStackTrace();
        }

    }
// CODE TO CLEAR SCREEN
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
