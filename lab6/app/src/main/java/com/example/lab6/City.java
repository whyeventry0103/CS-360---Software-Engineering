package com.example.lab6;

import java.util.Objects;

/**
 * This is a class that defines a City with a name and province
 */
public class City implements Comparable<City> {
    private String city;
    private String province;

    /**
     * Constructor for creating a new City
     * @param city The name of the city
     * @param province The province where the city is located
     */
    City(String city, String province) {
        this.city = city;
        this.province = province;
    }

    /**
     * Gets the name of the city
     * @return The city name
     */
    String getCityName() {
        return this.city;
    }

    /**
     * Gets the province where the city is located
     * @return The province name
     */
    String getProvinceName() {
        return this.province;
    }

    /**
     * Compares this city with another city for equality
     * Two cities are considered equal if they have the same city name AND province
     * @param o The object to compare with
     * @return true if the cities are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City)) return false;
        City other = (City) o;
        return city.equals(other.city) && province.equals(other.province);
    }

    /**
     * Generates a hash code for the city based on its name and province
     * @return The hash code value
     */
    @Override
    public int hashCode() {
        return Objects.hash(city, province);
    }

    /**
     * Compares this city with another city for ordering
     * Cities are ordered alphabetically by their names
     * @param o The city to compare with
     * @return A negative integer, zero, or a positive integer as this city
     *         name is less than, equal to, or greater than the specified city name
     */
    @Override
    public int compareTo(City o) {
        return this.getCityName().compareTo(o.getCityName());
    }
}