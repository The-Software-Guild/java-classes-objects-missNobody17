package com.dvdproject.dao;

import com.dvdproject.dto.Dvd;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;


public interface DvdProjectDao {
    Dvd addDvd(String title, Dvd dvd);
    Dvd removeDvd(String title);
    Dvd changeReleaseDate(String title, LocalDate releaseDate);
    Dvd changeMpaaRating(String title, String mpaaRating);   
    Dvd changeDirectorName(String title, String directorName);  
    Dvd changeUserRating(String title, String userRating);  
    Dvd changeStudioName(String title, String studioName);    
    List <Dvd> getAllDvds ();
    Dvd getDvd(String title);
    
    Map<String, Dvd> getDvdsLastYears(int years);
    Map<String, Dvd> getDvdsByMpaaRating(String mpaaRating);
    Map<String, Dvd> getDvdsByDirector(String directorName);
    Map<String, Dvd> getDvdsByStudio(String studioName);
}
