package com.dvdproject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.dvdproject.dao.DvdProjectDao;
import com.dvdproject.dao.DvdProjectDaoFileImpl;
import com.dvdproject.dto.Dvd;

public class DvdProjectDaoFileImplTest {
    DvdProjectDao testDao;
    DvdProjectDaoFileImplTest(){

    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws IOException {
        String testFile = "test.txt";
        new FileWriter(testFile);
        testDao = new DvdProjectDaoFileImpl(testFile);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testAddGetDvd() throws Exception {
        String title = "Movie title 1";
        Dvd dvd = new Dvd(title);
        dvd.setDirectorName("Director name");
        dvd.setMpaaRating("12A");
        dvd.setReleaseDate(LocalDate.parse("12-Mar-22")); 
        dvd.setStudio("studio name");
        dvd.setUserRating("10");

        testDao.addDvd(title, dvd);
        Dvd retrievedDvd = testDao.getDvd(title);
        assertEquals(dvd, retrievedDvd);
    }

    @Test
    public void testAddGetAllDvds() throws Exception {
        String title1 = "Movie title 1";
        Dvd dvd1 = new Dvd(title1);
        dvd1.setDirectorName("Director name");
        dvd1.setMpaaRating("12A");
        dvd1.setReleaseDate(LocalDate.parse("12-Mar-22"));
        dvd1.setStudio("studio name");
        dvd1.setUserRating("10");
        String title2 = "Movie title 2";
        Dvd dvd2 = new Dvd(title2);
        dvd2.setDirectorName("Director name2");
        dvd2.setMpaaRating("12A - 2");
        dvd2.setReleaseDate(LocalDate.parse("13-Mar-22"));
        dvd2.setStudio("studio name - 2");
        dvd2.setUserRating("10 - 2");
        
        testDao.addDvd(title1, dvd1);
        testDao.addDvd(title2, dvd2);
        
        List <Dvd> dvdList = testDao.getAllDvds();
        assertNotNull(dvdList);
        assertEquals(2, dvdList);

        assertTrue(dvdList.contains(dvd1));
        assertTrue(dvdList.contains(dvd2));
    }

    @Test
    public void testRemoveDvd() throws Exception {
        String title1 = "Movie title 1";
        Dvd dvd1 = new Dvd(title1);
        dvd1.setDirectorName("Director name");
        dvd1.setMpaaRating("12A");
        dvd1.setReleaseDate(LocalDate.parse("12-Mar-22"));
        dvd1.setStudio("studio name");
        dvd1.setUserRating("10");
        String title2 = "Movie title 2";
        Dvd dvd2 = new Dvd(title2);
        dvd2.setDirectorName("Director name2");
        dvd2.setMpaaRating("12A - 2");
        dvd2.setReleaseDate(LocalDate.parse("13-Mar-22"));
        dvd2.setStudio("studio name - 2");
        dvd2.setUserRating("10 - 2");
        
        testDao.addDvd(title1, dvd1);
        testDao.addDvd(title2, dvd2);

        Dvd removedDvd = testDao.removeDvd(title1);
        assertEquals(dvd1, removedDvd);

        List <Dvd> dvdList = testDao.getAllDvds();
        assertNotNull(dvdList);
        assertEquals(1, dvdList.size());

        assertFalse(dvdList.contains(dvd1));
        assertTrue(dvdList.contains(dvd2));

        Dvd removedDvd2 = testDao.removeDvd(title2);
        assertEquals(dvd2, removedDvd2);

        List <Dvd> dvdList2 = testDao.getAllDvds();
        assertTrue(dvdList2.isEmpty());

        Dvd retrievedDvd = testDao.getDvd(title1);
        assertNull(retrievedDvd);

        Dvd retrievedDvd2 = testDao.getDvd(title2);
        assertNull(retrievedDvd2);
    }
        
}
