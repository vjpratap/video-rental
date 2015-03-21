package com.twu.refactor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Rental {

    private Movie movie;

    private int daysRented;

    public Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    public int getDaysRented() {
        return daysRented;
    }

    public Movie getMovie() {
        return movie;
    }

    public int addFrequentRenterPoints(Customer customer) {
        int frequentRenterPoints = 1;
        if (isNewRelease() && daysRented > 1)
            frequentRenterPoints++;
        return frequentRenterPoints;
    }

    private Map<Integer, List<Double>> containPriceCodeList(){
        List<Double> newReleaseMovie = addInList(0,0,3);
        List<Double> childrenMovie = addInList(1.5,3,1.5);
        List<Double> regularMovie = addInList(2,2,1.5);
        Map<Integer, List<Double>> map = new HashMap<Integer, List<Double>>();
        map.put(Movie.CHILDRENS, childrenMovie);
        map.put(Movie.NEW_RELEASE, newReleaseMovie);
        map.put(Movie.REGULAR, regularMovie);
        return map;
    }

    private List<Double> addInList(double startingPrice, double daysLimitation, double perDayCharge) {
        List<Double> list = new ArrayList<Double>();
        list.add(startingPrice);
        list.add(daysLimitation);
        list.add(perDayCharge);
        return list;
    }

    private boolean isNewRelease() {
        return (movie.getPriceCode() == Movie.NEW_RELEASE);
    }

    public double setAmountForMovie(double thisAmount) {
        if(containPriceCodeList().containsKey(movie.getPriceCode()))
            return setPriceForMovie(containPriceCodeList().get(movie.getPriceCode()));
        return thisAmount;
    }

    private double setPriceForMovie(List<Double> list) {
        double thisAmount = list.get(0);
        if(daysRented > list.get(1))
            thisAmount += (daysRented - list.get(1)) * list.get(2);
        return thisAmount;
    }
}