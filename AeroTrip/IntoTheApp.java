import java.io.*;
import java.util.Scanner;

public class IntoTheApp {
    String username;
    Scanner input = new Scanner(System.in);
    byte choice;
    char cmd;
    long phn;
    int refID;

    IntoTheApp(String username) {
        this.username = username;
    }

    public void Enquiry() throws IOException {
        String from = null, to = null;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("From: ");
            from = br.readLine();
            System.out.print("To: ");
            to = br.readLine();
        } catch (IOException e) {
            System.out.println("Error occurred");
        }
        FlightsNamesWithSorting FNS = new FlightsNamesWithSorting(from, to);
        FNS.DisplayFlightNames();
        System.out.print("1) Price: Low to High  \t\t\t2) Price: High to Low\n3) Duration: Low to High\t\t4) Duration: High to Low\nSearch based on: ");
        do {
            choice = Byte.parseByte(br.readLine());
            flights(choice, FNS);
            System.out.print("Want to Sort Again [Y/N]? ");
            cmd = br.readLine().charAt(0);
        } while (cmd == 'y' || cmd == 'Y');

        System.out.print("Want to proceed to Booking [Y/N]? ");
        cmd = br.readLine().charAt(0);
        if (cmd == 'y' || cmd == 'Y') {
            new Airliner(username, from, to);
        }
    }

    void cancelTicket(int refID) throws IOException {
        File f1 = new File("R:\\MiniProjects\\JavaProject\\CustomersDataBase.txt");
        FileInputStream fin = new FileInputStream(f1);
        File temp = new File("R:\\MiniProjects\\JavaProject\\TempCustomersDataBase.txt");
        FileOutputStream fout = new FileOutputStream(temp);
        BufferedReader buffin = new BufferedReader(new InputStreamReader(fin));
        BufferedWriter buffout = new BufferedWriter(new OutputStreamWriter(fout));
        String str;
        String[] credentials;
        boolean refIDFound = false;
        while ((str = buffin.readLine()) != null) {
            credentials = str.split(",");
            if (!String.valueOf(refID).equals(credentials[8])) {
                buffout.write(str);
                buffout.write("\n");
            } else {
                refIDFound = true;
            }
        }
        if (!refIDFound) {
            System.out.println("Reference ID is incorrect");
        } else {
            System.out.println("Cancelled Your Ticket Successfully.");
            buffout.flush();
        }
        fin.close();
        fout.close();
        FileInputStream fin1 = new FileInputStream(temp);
        BufferedReader buffin1 = new BufferedReader(new InputStreamReader(fin1));
        FileOutputStream fout1 = new FileOutputStream(f1);
        BufferedWriter buffout1 = new BufferedWriter(new OutputStreamWriter(fout1));
        while ((str = buffin1.readLine()) != null) {
            buffout1.write(str);
            buffout1.write("\n");
        }
        buffout1.flush();
        fin1.close();
        fout1.close();
    }

    void printTicket(int refID) throws IOException {
        FileInputStream fin = new FileInputStream("R:\\MiniProjects\\JavaProject\\CustomersDataBase.txt");
        BufferedReader buffin = new BufferedReader(new InputStreamReader(fin));
        String str;
        String[] credentials;
        boolean refIDFound = true;
        while ((str = buffin.readLine()) != null) {
            credentials = str.split(",");
            if (String.valueOf(refID).equals(credentials[8])) {
                refIDFound = true;
                System.out.println("Name: " + credentials[0] + "\tPhone No.: " + credentials[1] + "\nNumber Of( Adults: " + credentials[2] + " Children: " + credentials[3] +
                        " Infants: " + credentials[4] + ")\n" + "From: " + credentials[5] + " To: " + credentials[6] + "\nBooked Time & Date: " + credentials[7]);
                break;
            } else {
                refIDFound = false;
            }
        }
        if (!refIDFound) {
            System.out.println("Enter Correct Reference ID");
        }
        fin.close();
    }

    void Menu() throws InterruptedException, IOException {
        do {
            System.out.print("""
                    1) Book A Ticket\t\t\t\t2) Inquiry
                    3) Cancellation of ticket\t\t4) Print ticket
                    5) Exit
                    Select An Option:\s""");
            choice = input.nextByte();
            switch (choice) {
                case 1 -> {
                    Airliner AMS = new Airliner(username);
                    AMS.t.start();
                    AMS.t.join();
                    FlightsNamesWithSorting FNS = new FlightsNamesWithSorting(AMS.from, AMS.to);
                    FNS.DisplayFlightNames();
                    // Sorting Options
                    System.out.print("""
                            1) Price: Low to High  \t\t\t2) Price: High to Low
                            3) Duration: Low to High\t\t4) Duration: High to Low
                            5) Don't Want to Check""");
                    do {
                        System.out.print("Select an Option: ");
                        choice = input.nextByte();
                        flights(choice, FNS);
                        System.out.println("Want to Sort Again? ");
                    } while (input.next().equalsIgnoreCase("y"));
                }
                case 2 -> Enquiry();
                case 3 -> {
                    System.out.print("Enter Your Registered Mobile Number: ");
                    phn = input.nextLong();
                    System.out.print("Enter the 9 digit code sent to your registered mobile number +91" + String.valueOf(phn).charAt(0) + "XXXXXXX" + String.valueOf(phn).charAt(8) + String.valueOf(phn).charAt(9) + ": ");
                    refID = input.nextInt();
                    cancelTicket(refID);
                }
                case 4 -> {
                    System.out.print("Enter Your Registered Mobile Number: ");
                    phn = input.nextLong();
                    System.out.print("Enter the 9 digit code sent to your registered mobile number: " + String.valueOf(phn).charAt(0) + "XXXXXXX" + String.valueOf(phn).charAt(8) + String.valueOf(phn).charAt(9) + ": ");
                    refID = input.nextInt();
                    printTicket(refID);
                }
                case 5 -> {
                    System.out.println("Thank You For Using our Services....");
                    System.exit(0);
                }
                default -> System.out.println("Select a Valid Option");
            }
            System.out.println("Wanna Continue: ");
            cmd = input.next().charAt(0);
        } while (cmd == 'Y' || cmd == 'y');
    }

    void flights(byte choice, FlightsNamesWithSorting FNS) throws IOException {
        switch (choice) {
            case 1 -> FNS.sortByPrice('a');
            case 2 -> FNS.sortByPrice('d');
            case 3 -> FNS.sortByDuration('a');
            case 4 -> FNS.sortByDuration('d');
            case 5 -> System.out.println();
            default -> System.out.println("Select a Valid Option");
        }
    }
}
