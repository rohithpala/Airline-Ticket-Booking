package AeroTripGUI;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

public class FlushWholeData {
    public static void main(String[] args) throws IOException {
        // Please check "Configurations" before running the program
        String dirname = System.getProperty("user.dir"); // Path till AeroTripGUI

        // Directories
        File enq = new File(dirname + "\\Databases\\Enquiries");
        File th = new File(dirname + "\\Databases\\TransactionHistories");
        File pp = new File(dirname + "\\Databases\\ProfilePictures");

        // Files
        File db = new File(dirname + "\\Databases\\LoginSignUpDatabase.txt");
        File rid = new File(dirname + "\\Databases\\ReferenceID.txt");

        File[] directories = {enq, th, pp};

        // Removing the files in the directories
        for(File dir : directories) {
            for (File file : Objects.requireNonNull(dir.listFiles())) {
                file.delete();
            }
        }

        // Clearing databases
        new FileWriter(db, false).close();
        new FileWriter(rid, false).close();
    }
}
