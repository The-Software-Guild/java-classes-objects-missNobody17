package com.dvdproject.dto;
import java.time.LocalDate;
import java.util.Objects;

public class Dvd {

    private String title;
    private LocalDate releaseDate;
    private String mpaaRating;
    private String directorName;
    private String userRating;
    private String studio;
    
    @Override 
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Dvd other = (Dvd) obj;
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.mpaaRating, other.mpaaRating)) {
            return false;
        }
        if (!Objects.equals(this.directorName, other.directorName)) {
            return false;
        }
        if (!Objects.equals(this.userRating, other.userRating)) {
            return false;
        }
        if (!Objects.equals(this.studio, other.studio)) {
            return false;
        }
        if (!Objects.equals(this.releaseDate, other.releaseDate)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Dvd{" + "title=" + title + ", releaseDate=" + releaseDate + ", mpaaRating=" + mpaaRating + ", directorName=" + directorName + ", userRating=" + userRating + ", studio=" + studio + '}';
    }
    
    //Constructors
    public Dvd(String title) {
        this.title = title;
    }
    
    // getters and setters
    public String getTitle() {
        return title;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
    

    public String getMpaaRating() {
        return mpaaRating;
    }

    public void setMpaaRating(String mpaaRating) {
        this.mpaaRating = mpaaRating;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public String getUserRating() {
        return userRating;
    }

    public void setUserRating(String userRating) {
        this.userRating = userRating;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }   
 
}
