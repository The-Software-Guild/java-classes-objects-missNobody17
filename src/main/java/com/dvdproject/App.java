package com.dvdproject;
import com.dvdproject.controller.DvdProjectController;
import com.dvdproject.dao.DvdProjectDao;
import com.dvdproject.dao.DvdProjectDaoFileImpl;
import com.dvdproject.ui.DvdProjectView;
import com.dvdproject.ui.UserIO;
import com.dvdproject.ui.UserIOConsoleImpl;


public class App {
    public static void main(String[] args) {
          UserIO myIo = new UserIOConsoleImpl();
          DvdProjectView myView = new DvdProjectView(myIo);
          DvdProjectDao myDao = new DvdProjectDaoFileImpl();
          DvdProjectController controller = new DvdProjectController(myDao, myView);

          controller.run();
    }
}
