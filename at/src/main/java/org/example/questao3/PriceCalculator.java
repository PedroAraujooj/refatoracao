package org.example.questao3;

public class PriceCalculator {

    public double calculateFinalPrice(double basePrice, int customerType, boolean holiday) {
        double customerDiscount = getCustomerDiscount(customerType);
        double holidayDiscount = getHolidayDiscount(holiday);
        double totalDiscount = customerDiscount + holidayDiscount;

        return applyDiscount(basePrice, totalDiscount);
    }

    private double getCustomerDiscount(int customerType) {
        if (customerType == 1) {
            return 0.10;
        }

        if (customerType == 2) {
            return 0.15;
        }

        return 0.0;
    }

    private double getHolidayDiscount(boolean holiday) {
        return holiday ? 0.05 : 0.0;
    }

    private double applyDiscount(double basePrice, double discount) {
        return basePrice * (1 - discount);
    }
}