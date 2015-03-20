package com.twu.refactor;

import java.util.ArrayList;

public class Customer {

	private String name;
	private ArrayList<Rental> rentalList = new ArrayList<Rental>();

	public Customer(String name) {
		this.name = name;
	}

	public void addRental(Rental arg) {
		rentalList.add(arg);
	}

	public String getName() {
		return name;
	}

	public String statement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		String result = "Rental Record for " + getName() + "\n";
        for(Rental rental : rentalList){
			double thisAmount = 0;

            thisAmount = rental.setAmountForMovie(thisAmount);

            frequentRenterPoints += rental.addFrequentRenterPoints(this);

			result += "\t" + rental.getMovie().getTitle() + "\t"
					+ String.valueOf(thisAmount) + "\n";
			totalAmount += thisAmount;

		}
		// add footer lines
        return getFooterLines(totalAmount, frequentRenterPoints, result);
	}

    private String getFooterLines(double totalAmount, int frequentRenterPoints, String result) {
        result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
        result += "You earned " + String.valueOf(frequentRenterPoints)
                + " frequent renter points";
        return result;
    }

}
