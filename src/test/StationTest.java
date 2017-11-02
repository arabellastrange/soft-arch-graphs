package test;

import graph.Station;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Created by ioanluca on 01/11/17.
 */
class StationTest {
    private Station station;
    private Station sameStation;
    private Station differentStation;

    @BeforeEach
    void setUp() {
        station = new Station(1, "Kelvinhall");
        sameStation = new Station(1, "Buchanan Street");
        differentStation = new Station(2, "Cowcaddens");
    }

    @Test
    void equals_SameObjects_True() {
        assertEquals(station, sameStation);
    }

    @Test
    void equals_DifferentObjects_False() {
        assertNotEquals(station, differentStation);
    }

    @Test
    void equals_reflexive() {
        assertEquals(station, station);
    }

    @Test
    void equals_symmetric() {
        assertEquals(station.equals(sameStation), sameStation.equals(station));
    }

    @Test
    void equals_transitive() {
        Station anotherSameStation = new Station(1, "Hillhead");
        assertEquals(station, sameStation);
        assertEquals(sameStation, anotherSameStation);
        assertEquals(station, anotherSameStation);
    }

    @Test
    void equals_Null_False() {
        assertNotEquals(station, null);
    }

    @Test
    void equals_consistent() {
        for (int i = 0; i < 130; i++) {
            assertEquals(station, sameStation);
            assertNotEquals(station, differentStation);
        }
    }
}