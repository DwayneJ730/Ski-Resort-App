import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.RentalDao;
import models.Rental;
import org.junit.jupiter.api.*;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import services.RentalServices;
import util.dbconnection;


public class testGetRooms {
public static RentalDao rdao;
public static RentalServices rServ;
public static Connection mockConn;
public static ArrayList<Rental> rentals,rentalactuals,rentalexpected;

public  MockedStatic<dbconnection> mockedStatic;

    @BeforeAll
    static void initAll() throws SQLException {
        rdao = Mockito.mock(dao.RentalDao.class);
        mockConn = Mockito.mock(Connection.class);
        rentals = new ArrayList<>();
        Rental r1 = new Rental(1,"Mountain Queen",1,"Room");
        Rental r2 = new Rental(2,"Mountain King",1,"Room");
        rentals.add(r1);
        rentals.add(r2);
        when(rdao.getRooms()).thenReturn(rentals);

    }

    @BeforeEach
    void init() {
    }

    @Test
    public void test() throws SQLException{

        try (MockedStatic<dbconnection> mockedStatic = Mockito.mockStatic(dbconnection.class)) {
            mockedStatic.when(dbconnection::getConnection).thenReturn(mockConn);
            rentalactuals = rServ.getRooms();

            rentalexpected = new ArrayList<Rental>();
            Rental r1 = new Rental(1, "Mountain Queen", 1, "Room");
            Rental r2 = new Rental(2, "Mountain King", 1, "Room");
            rentalexpected.add(r1);
            rentalexpected.add(r2);

            assertEquals(rentalexpected, rentalactuals);
            }
        }
    @AfterEach
    void tearDown() {
    }

    @AfterAll
    static void tearDownAll() {
    }


}

/*
    rdao = mock(dao.RentalDao.class);
    mockConn = mock(Connection.class);
    rentals = new ArrayList<>();
    Rental r1 = new Rental(1,"Mountain Queen",1,"Room");
    Rental r2 = new Rental(2,"Mountain King",1,"Room");
    rentals.add(r1);
    rentals.add(r2);
    when(rdao.getRooms()).thenReturn(rentals);
 */