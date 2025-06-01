package Destination;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Destination {
    private String Country;
    private String City;
    private String Date;
    private String flightTime;
    private String bookingTime;

    public Destination(String Country, String City, String Date, String flightTime, String bookingTime) {
        this.Country = Country;
        this.City = City;
        this.Date = Date;
        this.flightTime = flightTime;
        this.bookingTime = bookingTime;

    }

    public String getCountry() {
        return Country;
    }

    public String getCity() {
        return City;
    }

    public String getDate() {
        return Date;
    }

    public String getFlightTime() {
        return flightTime;
    }

    public String getBookingTime() {
        return bookingTime;
    }


    private Scanner scanner;
    private Map<String, String[]> cityOptions;
    private Map<String, String[]> dateOptions;
    private Map<String, String[]> timeOptions;

    public Destination(Scanner scanner) {
        this.scanner = scanner;
        this.cityOptions = createCityOptions();
        this.dateOptions = createDateOptions();
        this.timeOptions = createTimeOptions();
    }

    // Array maps for creating country, city, date and flight time options
    private Map<String, String[]> createCityOptions() {
        Map<String, String[]> options = new HashMap<>();
        options.put("France", new String[] {"Paris", "Marseille", "Lyon"});
        options.put("United Arab Emirates", new String[] {"Dubai", "Abu Dhabi", "Sharjah"});
        options.put("United States", new String[] {"New York City", "Los Angeles", "Chicago"});
        return options;
    }

    private Map<String, String[]> createDateOptions() {
        Map<String, String[]> options = new HashMap<>();
        options.put("All Dates", new String[] {"25/7/2023", "16/9/2023", "30/12/2023"});
        return options;
    }

    private Map<String, String[]> createTimeOptions() {
        Map<String, String[]> options = new HashMap<>();
        options.put("25/7/2023", new String[] {"12:00 PM", "6:00 PM", "9:30 PM"});
        options.put("16/9/2023", new String[] {"9:00 AM", "5:00 PM", "3:00 AM"});
        options.put("30/12/2023", new String[] {"6:00 AM", "2:30 PM", "8:00 PM"});
        return options;
    }

    // methods to country, city, date, flighttime selection
    public String selectCountry() {
        System.out.println("Select a country:");
        System.out.println("1. France");
        System.out.println("2. United Arab Emirates");
        System.out.println("3. United States");
        System.out.print("Enter the country number: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        String country;
        switch (choice) {
            case 1:
                country = "France";
                break;
            case 2:
                country = "United Arab Emirates";
                break;
            case 3:
                country = "United States";
                break;
            default:
                System.out.println("Invalid choice. Defaulting to France.");
                country = "France";
        }

        return country;
    }

    public String selectCity(String country) {
        String[] cities = cityOptions.get(country);
        if (cities == null) {
            System.out.println("Invalid country. Defaulting to Paris.");
            return "Paris";
        }

        System.out.println("Select a city for " + country + ":");
        for (int i = 0; i < cities.length; i++) {
            System.out.println((i + 1) + ". " + cities[i]);
        }
        System.out.print("Enter the city number: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (choice >= 1 && choice <= cities.length) {
            return cities[choice - 1];
        } else {
            System.out.println("Invalid choice. Defaulting to City Paris.");
            return "Paris";
        }
    }

    public String selectDate() {
        System.out.println("Select a date:");
        String[] dates = dateOptions.get("All Dates");
        for (int i = 0; i < dates.length; i++) {
            System.out.println((i + 1) + ". " + dates[i]);
        }
        System.out.print("Enter the date number: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (choice >= 1 && choice <= dates.length) {
            return dates[choice - 1];
        } else {
            System.out.println("Invalid choice. Defaulting to 25/7/2023.");
            return "25/7/2023";
        }
    }

    public String selectTime(String date) {
        String[] times = timeOptions.get(date);
        if (times == null) {
            System.out.println("Invalid date. Defaulting to Time A1.");
            return "12:00 PM";
        }

        System.out.println("Available flight times for " + date + ":");
        for (int i = 0; i < times.length; i++) {
            System.out.println((i + 1) + ". " + times[i]);
        }
        System.out.print("Enter the time number: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (choice >= 1 && choice <= times.length) {
            return times[choice - 1];
        }

        return date;
    }

    // method for generating current device time

    public String bookingTime() {
        Date currentTime;
        currentTime = new Date();
        return currentTime.toString();
    }
}