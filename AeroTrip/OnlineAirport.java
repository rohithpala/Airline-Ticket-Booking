import java.io.*;
import java.util.*;

class Airliner implements Runnable {
    Scanner input = new Scanner(System.in);
    long phone;
    int refID;
    byte numOfAdults, numOfChildren, numOfInfants;
    String name, from, to, departureDate;
    Random randInt = new Random();
    Thread t = new Thread(this);
    Date date = new Date();

    public Airliner(String name) {
        this.name = name;
        from = null;
        to = null;
    }

    public Airliner(String name, String from, String to) {
        this.name = name;
        this.from = from;
        this.to = to;
    }

    public void run() {
        // Taking the Passenger Details
        System.out.print("Name: ");
        name = input.nextLine();
        System.out.print("Phone Number: ");
        phone = input.nextLong();
        System.out.print("Number of Adults: ");
        numOfAdults = input.nextByte();
        System.out.print("Number of Children: ");
        numOfChildren = input.nextByte();
        System.out.print("Number of Infants: ");
        numOfInfants = input.nextByte();
        System.out.print("Enter the From Address: ");
        if (from == null) {
            from = input.next().toLowerCase();
            System.out.print("Enter the To Address: ");
            to = input.next().toLowerCase();
        }
        departureDate = input.nextLine();
        // Generating a Reference ID
        refID = 10000000 + randInt.nextInt(9999999);
        storeInformation(name, phone, numOfAdults, numOfChildren, numOfInfants, from, to, refID);
    }

    void storeInformation(String name, long phone, byte numOfAdults, byte numOfChildren, byte numOfInfants, String from, String to, int refID) {
        try {
            // Storing the Data in File in csv format
            File f = new File("R:\\MiniProjects\\JavaProject\\CustomersDataBase.txt");
            BufferedWriter bufOut = new BufferedWriter(new FileWriter(f, true));
            date = new Date();
            bufOut.write(name + "," + phone + "," + numOfAdults + "," + numOfChildren + "," + numOfInfants + "," + from + "," + to + "," + date + "," + refID + "\n");
            bufOut.flush();
        } catch (IOException e) {
            System.out.println("IOException Caught: Error occurred in opening a file.");
        }
    }
}

class FlightsNamesWithSorting {
    //Flight Names are taken from the FlightNames.txt file
    String str, from, to;
    String[] credentials;  // Used while splitting

    public FlightsNamesWithSorting(String from, String to) {
        this.from = from;   // Storing from and to addresses for using them in other functions in this class
        this.to = to;
    }

    public void DisplayFlightNames() throws IOException {
        //Retrieves Flight Names Based on from , to address
        FileInputStream fin = new FileInputStream("R:\\MiniProjects\\JavaProject\\FlightNames.txt");
        BufferedReader buffin = new BufferedReader(new InputStreamReader(fin));
        int found = 0;
        System.out.println("Hold on! We are fetching flights for you........");
        System.out.println();
        while ((str = buffin.readLine()) != null) {
            credentials = str.split(",");
            if (from.equalsIgnoreCase(credentials[0]) && to.equalsIgnoreCase(credentials[1])) {
                found = 1;
                // Displaying user the flight details
                System.out.println(credentials[0] + "->" + credentials[1] + "\tCOST: " + credentials[2] + "\tDURATION: " + credentials[3]);
            }
        }
        if (found == 0)
            System.out.println("Sorry! Flights not Available.");
        System.out.println();
        fin.close();
    }

    //Sorting Based on the passenger choice
    public void sortByPrice(char choice) throws IOException {
        FileInputStream fin = new FileInputStream("R:\\MiniProjects\\JavaProject\\FlightNames.txt");
        BufferedReader buffin = new BufferedReader(new InputStreamReader(fin));
        String str;
        ArrayList<String> strs = new ArrayList<>();
        ArrayList<Integer> prices = new ArrayList<>();
        while ((str = buffin.readLine()) != null) {
            credentials = str.split(",");
            if (from.equalsIgnoreCase(credentials[0]) && to.equalsIgnoreCase(credentials[1])) {
                strs.add(str);
                prices.add(Integer.parseInt(credentials[2]));
            }
        }
        if (choice == 'a') {
            // Sorting Based on Cost: low to high
            Collections.sort(prices); // Sorting using the sort() function in ascending order
            System.out.println("Flights Based On Cost: Low to high");
            for (Integer price : prices) {
                for (String s : strs) {
                    credentials = s.split(",");
                    if (from.equalsIgnoreCase(credentials[0]) && to.equalsIgnoreCase(credentials[1]) && price.toString().equals(credentials[2])) {
                        System.out.println(credentials[0] + "->" + credentials[1] + "\tCOST: " + credentials[2] + "\tDURATION: " + credentials[3]);
                        break;
                    }
                }
            }
        } else {
            // Sorting Based on Cost: high to low
            prices.sort(Collections.reverseOrder()); // Sorting using the sort() function in descending order
            System.out.println("Flights Based On Cost: High to Low");
            for (Integer price : prices) {
                for (String s : strs) {
                    credentials = s.split(",");
                    if (from.equalsIgnoreCase(credentials[0]) && to.equalsIgnoreCase(credentials[1]) && price.toString().equals(credentials[2])) {
                        System.out.println(credentials[0] + "->" + credentials[1] + "\tCOST: " + credentials[2] + "\tDURATION: " + credentials[3]);
                        break;
                    }
                }
            }
        }
        fin.close();
    }

    public void sortByDuration(char choice) throws IOException {
        FileInputStream fin = new FileInputStream("R:\\MiniProjects\\JavaProject\\FlightNames.txt");
        BufferedReader buffin = new BufferedReader(new InputStreamReader(fin));
        String str;
        ArrayList<String> strs = new ArrayList<>();
        ArrayList<Integer> duration = new ArrayList<>();
        while ((str = buffin.readLine()) != null) {
            credentials = str.split(",");
            if (from.equalsIgnoreCase(credentials[0]) && to.equalsIgnoreCase(credentials[1])) {
                strs.add(str);
                duration.add(Integer.parseInt(credentials[3].replace("hrs", "")));
            }
        }

        if (choice == 'a') {
            // Sorting Based on Duration: low to high
            Collections.sort(duration); // Sorting using the sort() function in ascending order
            System.out.println("Flights Based On Duration: Low to High");
            for (Integer dur : duration) {
                for (String s : strs) {
                    credentials = s.split(",");
                    if (from.equalsIgnoreCase(credentials[0]) && to.equalsIgnoreCase(credentials[1]) && dur.toString().equals(credentials[3].replace("hrs", ""))) {
                        System.out.println(credentials[0] + "->" + credentials[1] + "\tCOST: " + credentials[2] + "\tDURATION: " + credentials[3]);
                        break;
                    }
                }
            }
        } else {
            // Sorting Based on Duration: high to low
            duration.sort(Collections.reverseOrder()); // Sorting using the sort() function in ascending order
            System.out.println("Flights Based On Duration: High to Low");
            for (Integer dur : duration) {
                for (String s : strs) {
                    credentials = s.split(",");
                    if (from.equalsIgnoreCase(credentials[0]) && to.equalsIgnoreCase(credentials[1]) && dur.toString().equals(credentials[3].replace("hrs", ""))) {
                        System.out.println(credentials[0] + "->" + credentials[1] + "\tCOST: " + credentials[2] + "\tDURATION: " + credentials[3]);
                        break;
                    }
                }
            }
        }
        fin.close();
    }
}

public class OnlineAirport {
    public static void main(String[] args) throws InterruptedException, IOException {

        String temp, username, password;
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int flag = 0;
        File db = new File("R:\\MiniProjects\\JavaProject\\LoginSignupDatabase.txt");
        char option = 'y';
        while (option == 'y' || option == 'Y') {
            System.out.println("SignUp or Login?");
            String str = input.readLine();
            if (str.equalsIgnoreCase("signup")) {
                System.out.print("Username: ");
                username = input.readLine();
                BufferedReader reader = new BufferedReader(new FileReader(db));
                while ((temp = reader.readLine()) != null) {
                    String[] details = temp.split(",");
                    if (username.equals(details[0])) {
                        System.out.println("User already exists. Program terminated");
                        flag = 1;
                        break;
                    }
                }
                if (flag == 0) {
                    System.out.print("Set your password:");
                    String pass = input.readLine();
                    System.out.println("Account created successfully");
                    BufferedWriter writer = new BufferedWriter(new FileWriter(db, true));
                    writer.write(username + "," + pass + "\n");
                    writer.flush();
                    writer.close();
                }
            } else {
                flag = 0;
                System.out.print("Username: ");
                username = input.readLine();
                System.out.print("Password: ");
                password = input.readLine();
                BufferedReader reader = new BufferedReader(new FileReader(db));
                while ((temp = reader.readLine()) != null) {
                    String[] details = temp.split(",");
                    if (username.equals(details[0])) {
                        if (!details[1].equals(password)) {
                            flag = 1;
                            System.out.println("Wrong Password");
                            break;
                        }
                        break;
                    }
                }
                if (temp == null) {
                    System.out.println("No user exists with given credentials.");
                } else if (flag != 1) {
                    System.out.println("Logged in Successfully.");
                    new IntoTheApp(username).Menu();
                }
            }
            System.out.print("Do you want to continue the process [Y/N]? ");
            option = input.readLine().charAt(0);
        }
    }
}