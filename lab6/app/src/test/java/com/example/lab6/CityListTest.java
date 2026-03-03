package com.example.lab6;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CityListTest {

    private CityList mockCityList() {
        CityList cityList = new CityList();
        cityList.add(mockCity());
        return cityList;
    }

    private City mockCity() {
        return new City("Edmonton", "Alberta");
    }

    @Test
    void testAdd() {
        CityList cityList = mockCityList();
        assertEquals(1, cityList.getCities().size());

        City city = new City("Regina", "Saskatchewan");
        cityList.add(city);

        assertEquals(2, cityList.getCities().size());
        assertTrue(cityList.getCities().contains(city));
    }

    @Test
    void testAddException() {
        CityList cityList = mockCityList();
        City city = new City("Yellowknife", "Northwest Territories");
        cityList.add(city);

        assertThrows(IllegalArgumentException.class, () -> {
            cityList.add(city);
        });
    }

    @Test
    void testGetCities() {
        CityList cityList = mockCityList();

        // Check if the first city matches our mock city
        assertEquals(0, mockCity().compareTo(cityList.getCities().get(0)));

        // Add a new city
        City city = new City("Charlottetown", "Prince Edward Island");
        cityList.add(city);

        // Now the new city should be at position 0 (since it's sorted alphabetically by city name)
        // "Charlottetown" comes before "Edmonton", so it should be first
        assertEquals(0, city.compareTo(cityList.getCities().get(0)));
        assertEquals(0, mockCity().compareTo(cityList.getCities().get(1)));
    }

    @Test
    void testHasCity_returnsTrueWhenCityExists() {
        CityList cityList = mockCityList(); // Contains "Edmonton", "Alberta"
        City existingCity = mockCity();

        assertTrue(cityList.hasCity(existingCity));
    }

    @Test
    void testHasCity_returnsFalseWhenCityDoesNotExist() {
        CityList cityList = mockCityList();
        City nonExistentCity = new City("Calgary", "Alberta");

        assertFalse(cityList.hasCity(nonExistentCity));
    }

    @Test
    void testDelete_removesExistingCity() {
        CityList cityList = mockCityList();
        City cityToDelete = mockCity();

        cityList.delete(cityToDelete);

        assertEquals(0, cityList.countCities());
        assertFalse(cityList.hasCity(cityToDelete));
    }

    @Test
    void testDelete_throwsExceptionForNonExistentCity() {
        CityList cityList = mockCityList();
        City nonExistentCity = new City("Winnipeg", "Manitoba");

        assertThrows(IllegalArgumentException.class, () -> {
            cityList.delete(nonExistentCity);
        });
    }

    @Test
    void testCountCities_returnsCorrectNumber() {
        CityList cityList = new CityList(); // Start with empty list
        assertEquals(0, cityList.countCities());

        cityList.add(mockCity()); // Add Edmonton
        assertEquals(1, cityList.countCities());

        cityList.add(new City("Toronto", "Ontario"));
        assertEquals(2, cityList.countCities());
    }
}