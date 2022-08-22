package com.dvdproject.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import com.dvdproject.dto.Dvd;


public class DvdProjectDaoFileImpl implements DvdProjectDao {
    private Map <String, Dvd> dvds = new HashMap<>();  
    
    private final String LIBRARY_FILE;
    private final  String DELIMITER = "::";
    
    public DvdProjectDaoFileImpl() {
        LIBRARY_FILE = "DvdLibrary.txt";
    }
    
    public DvdProjectDaoFileImpl(String libraryTextFile) {
        LIBRARY_FILE = libraryTextFile;
    }
    
    @Override
    public Dvd addDvd(String title, Dvd dvd) {
        //loadLibrary reads the LIBRARY_FILE into memory. 
        loadLibrary();
        //New DVD is added to the dvds HashMap.
        Dvd newDvd = dvds.put(title, dvd);
        //writeLibrary writes all the DVDs in the DVD library out to a LIBRARY_FILE.
        writeLibrary();
        return newDvd;
    }

    @Override
    public Dvd removeDvd(String title)  {
        loadLibrary();
        Dvd removedDvd = dvds.remove(title);
        writeLibrary();
        return removedDvd;
    }
    
    @Override
    public List<Dvd> getAllDvds() {
        loadLibrary();
        return new ArrayList(dvds.values());
    }

    @Override
    public Dvd getDvd(String title)  {
        loadLibrary();
        return dvds.get(title);
    }

    @Override
    public Dvd changeReleaseDate(String title, LocalDate releaseDate) {
        loadLibrary();
        Dvd dvdToEdit = dvds.get(title);
        dvdToEdit.setReleaseDate(releaseDate);
        writeLibrary();
        return dvdToEdit;
    }

    @Override
    public Dvd changeMpaaRating(String title, String mpaaRating)  {
        loadLibrary();
        Dvd dvdToEdit = dvds.get(title);
        dvdToEdit.setMpaaRating(mpaaRating);
        writeLibrary();
        return dvdToEdit;
    }

    @Override
    public Dvd changeDirectorName(String title, String directorName)  {
        loadLibrary();
        Dvd dvdToEdit = dvds.get(title);
        dvdToEdit.setDirectorName(directorName);
        writeLibrary();
        return dvdToEdit;
    }

    @Override
    public Dvd changeUserRating(String title, String userRating) {
        loadLibrary();
        Dvd dvdToEdit = dvds.get(title);
        dvdToEdit.setUserRating(userRating);
        writeLibrary();
        return dvdToEdit;
    }
    
    @Override
    public Dvd changeStudioName(String title, String studioName)  {
        loadLibrary();
        Dvd dvdToEdit = dvds.get(title);
        dvdToEdit.setStudio(studioName);
        writeLibrary();
        return dvdToEdit;
    }
    
    @Override
    public Map<String, Dvd> getDvdsLastYears(int years)  {
        LocalDate now = LocalDate.now();
        LocalDate sinceThisDate = now.minusYears(years);
        loadLibrary();
        Map<String, Dvd> dvdsLastYears = dvds.entrySet().stream()
                .filter((dvd) -> dvd.getValue().getReleaseDate().isAfter(sinceThisDate))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)); 
        return dvdsLastYears;
    }
    @Override
    public Map<String, Dvd> getDvdsByMpaaRating(String mpaaRating)  {
        loadLibrary();
        Map<String, Dvd> dvdsMpaRating = dvds
                .entrySet()
                .stream()
                .filter((dvd) -> dvd.getValue().getMpaaRating().equalsIgnoreCase(mpaaRating))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)); 
        return dvdsMpaRating;
    }
    @Override
    public Map<String, Dvd> getDvdsByDirector(String directorName)  {
        loadLibrary();
        Map<String, Dvd> dvdsByDirector = dvds
                .entrySet()
                .stream()
                .filter((dvd) -> dvd.getValue().getDirectorName().equalsIgnoreCase(directorName))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return dvdsByDirector;
    }
    @Override
    public Map<String, Dvd> getDvdsByStudio(String studioName)  {
        loadLibrary();
        Map<String, Dvd> dvdsByStudio = dvds
                .entrySet().stream().filter((dvd) -> dvd.getValue().getStudio().equalsIgnoreCase(studioName))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return dvdsByStudio;
    }
    
    private String marshallDvd(Dvd aDvd) {
        String dvdAsText = aDvd.getTitle() + DELIMITER;
        dvdAsText += aDvd.getReleaseDate() + DELIMITER;
        dvdAsText += aDvd.getMpaaRating() + DELIMITER;
        dvdAsText += aDvd.getDirectorName() + DELIMITER;
        dvdAsText += aDvd.getUserRating() + DELIMITER;
        dvdAsText += aDvd.getStudio();
        return dvdAsText;
    }
    
    // UnmarshallDvd translates a line of text into a DVD object. 
    private Dvd unmarshallDvd(String dvdAsText) {
        String [] dvdTokens = dvdAsText.split(DELIMITER);
        //Given the pattern above, the DVD title is in index 0 of the array.
        String title = dvdTokens[0];
        String releaseDate = dvdTokens[1];
        String mpaaRating = dvdTokens[2];
        String directorName = dvdTokens[3];
        String userRating = dvdTokens[4];
        String studio = dvdTokens[5];
        
        //A new DVD object is created using the title to satisfy the 
        //requirements of the DVD constructor
        Dvd dvdFromFile = new Dvd(title);
        //The remaining tokens are then set into the DVD object using the appropriate setters.
        dvdFromFile.setReleaseDate(LocalDate.parse(releaseDate));
        dvdFromFile.setMpaaRating(mpaaRating);
        dvdFromFile.setDirectorName(directorName);
        dvdFromFile.setUserRating(userRating);
        dvdFromFile.setStudio(studio);
        return dvdFromFile;
    }
    
    // loadLibrary reads the LIBRARY_FILE into memory. 
    private void loadLibrary()  {
        Scanner scanner = null;
        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(LIBRARY_FILE)));
            // currentLine holds the most recent line read from the file
            String currentLine;
            // curentDvd holds the most recent DVD unmarshalled
            Dvd currentDvd;
            // Go through LIBRARY_FILE line by line, decoding each line into a DVD
            // object by calling the unmarshallDvd method. Process while we have more
            // more lines in the file
            while (scanner.hasNextLine()) {
                // get the next line in the file
                currentLine = scanner.nextLine();
                // unmarshall the line into a DVD
                currentDvd = unmarshallDvd(currentLine);

                // The Dvd title is used as a map key to put the currentDvd ino the map
                dvds.put(currentDvd.getTitle(), currentDvd);
            }
            // Clean up
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("-_- Could not load roster data into memory.");
        }
    }
        
    
    // writeLibrary writes all the DVDs in the DVD library out to a LIBRARY_FILE.
    private void writeLibrary()  {
        PrintWriter out = null;

        try {
            out = new PrintWriter(new FileWriter(LIBRARY_FILE));
            String dvdAsText;
            List<Dvd> dvdList = this.getAllDvds();
            for (Dvd currentDvd : dvdList) {
                // turn a DVD into a string
                dvdAsText = marshallDvd(currentDvd);
                // write the DVD object to to the file;
                out.println(dvdAsText);
                // force PrintWriter to write line to the file
                out.flush();
            }
            // Clean up
            out.close();
        } catch (IOException e) {
            System.out.println("Could not save DVD data");
        }
    }

}