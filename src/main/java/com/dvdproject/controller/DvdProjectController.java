package com.dvdproject.controller;

import com.dvdproject.dao.DvdProjectDao;
import com.dvdproject.dao.DvdProjectDaoFileImpl;
import com.dvdproject.dto.Dvd;
import com.dvdproject.ui.DvdProjectView;
import com.dvdproject.ui.UserIO;
import com.dvdproject.ui.UserIOConsoleImpl;
import java.util.Map;
import java.util.List;
import java.time.LocalDate;

public class DvdProjectController {
    private DvdProjectView view;
    private DvdProjectDao dao;

    public DvdProjectController(DvdProjectDao dao, DvdProjectView view) {
        //Controller = "brains of the operation". Knows what needs to be done, when it needs
        //to be done and what needs to be done, and what component can do it.
        //It acts as a general contractor, directoring work but never doing the work itself.
        //The controller can talk with the DAO and the view.
        this.dao = dao;
        this.view = view;
    }
    

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        try{
            while (keepGoing) {
                menuSelection = getMenuSelection();

                switch (menuSelection) {
                        case 1:
                            createDvd();
                            break;
                        case 2:
                            removeDvd();
                            break;
                        case 3:
                            editDvd();
                            break;
                        case 4:
                            listDvds();
                            break;
                        case 5:
                            getDvd();
                            break;
                        case 6:
                            findDvds();
                            break;
                        case 7:
                            keepGoing = false;
                            break;
                        default:
                            unknownCommand();
                }
            }
            //io.print("exit");
            exitMessage();
        } catch (Exception e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }
    private void createDvd() {
        view.displayCreateDvdBanner();
        Dvd newDvd = view.getNewDvdInfo();
        dao.addDvd(newDvd.getTitle(),newDvd);
        view.displayCreateSuccessBanner();
    }
    private void listDvds()  {
        view.displayDvdListBanner();
        List <Dvd> dvdList = dao.getAllDvds();
        view.displayDvdList(dvdList);
    }
    private void getDvd()  {
        view.displayDisplayDvdBanner();
        String dvdTitle = view.getDvdTitleChoice();
        Dvd dvd = dao.getDvd(dvdTitle);
        view.displayDvd(dvd);
    }
    private void removeDvd()   {
        view.displayRemoveDvdBanner();
        String title = view.getDvdTitleChoice();
        Dvd removedDvd = dao.removeDvd(title);
        view.displayRemoveResult(removedDvd);
    }
    private void exitMessage() {
        view.displayExitBanner();
    }
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }
    private void editDvd()   {
        view.displayEditDvdBanner();
        String title = view.getDvdTitleChoice();
        Dvd dvdToEdit = dao.getDvd(title);
        if (dvdToEdit==null) {
            view.displayNullDvd();
        } else {
            view.displayDvd(dvdToEdit);
            int editMenuSelection = 0;
            boolean keepGoing = true;
            while (keepGoing) {
                editMenuSelection = view.printEditMenuAndGetSelection();

                switch (editMenuSelection){
                    case 1:
                        editReleaseDate(title);
                        break;
                    case 2:
                        editMpaaRating(title);
                        break;
                    case 3:
                        editDirectorName(title);
                        break;
                    case 4:
                        editUserRating(title);
                        break;
                    case 5:
                        editStudioName(title);
                        break;
                    case 6:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }
                if (keepGoing == false) {
                    break;
            } 
        }
        }
    }
     

    private void editReleaseDate(String title)  {
        view.displayEditReleaseDateBanner();
        LocalDate newReleaseDate = view.getReleaseDate();
        Dvd editedDvd = dao.changeReleaseDate(title, newReleaseDate);
        view.displayEditResult();
    }
    private void editMpaaRating(String title)  {
        //view.displayEditMpaaRatingBanner();
        String newMpaaRating = view.getMpaaRating();
        Dvd editedDvd = dao.changeMpaaRating(title, newMpaaRating);
        view.displayEditResult();
    }
    private void editDirectorName(String title)  {
        //view.displayEditDirectorNameBanner();
        String newDirectorName = view.getDirectorName();
        Dvd editedDvd = dao.changeDirectorName(title, newDirectorName);
        view.displayEditResult();
    }
    private void editUserRating(String title)  {
        String newUserRating = view.getUserRating();
        Dvd editedDvd = dao.changeUserRating(title, newUserRating);
        view.displayEditResult();
    }
    private void editStudioName(String title)  {
        String newStudioName = view.getStudioName();
        Dvd editedDvd = dao.changeStudioName(title, newStudioName);
        view.displayEditResult();
    }
    
    private void findDvds()  {
        view.displayFindDvdsBanner();
            int findDvdsSelection = 0;
            boolean keepGoing = true;
            while (keepGoing) {
                findDvdsSelection = view.printFindMenuAndGetSelection();
                switch (findDvdsSelection){
                    case 1:
                        findMoviesLastNYears();
                        break;
                    case 2:
                        findMoviesByMpaaRating();
                        break;
                    case 3:
                        findMoviesByDirector();
                        break;
                    case 4:
                        findMoviesByStudio();
                        break;
                    case 5:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }
            } 
        }
    private void findMoviesLastNYears()  {
        int n = view.getNYears();
        Map<String, Dvd> filteredDvds = dao.getDvdsLastYears(n);
        view.displayDvds(filteredDvds);
    }
    
    private void findMoviesByMpaaRating()  {
        String mpaaRating = view.getMpaaRating();
        Map<String, Dvd> filteredDvds = dao.getDvdsByMpaaRating(mpaaRating);
        view.displayDvds(filteredDvds);
    }
    
    private void findMoviesByDirector()  {
        String director = view.getDirectorName();
        Map<String, Dvd> filteredDvds = dao.getDvdsByDirector(director);
        view.displayDvds(filteredDvds);
    }
    private void findMoviesByStudio()  {
        String studio = view.getStudioName();
        Map<String, Dvd> filteredDvds = dao.getDvdsByStudio(studio);
        view.displayDvds(filteredDvds);
    }
}
