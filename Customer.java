import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Customer {
    public static final int MILLISECONDES_OF_A_DAY = (1000 * 60 * 60 * 24);
    private String name;

    private List<Rental> rentals = new ArrayList<Rental>();

    public Customer(String name) {
        this.setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Rental> getRentals() {
        return rentals;
    }

    public void setRentals(List<Rental> rentals) {
        this.rentals = rentals;
    }

    public void addRental(Rental rental) {
        rentals.add(rental);

    }

    public String getReport() {
        String result = "Customer Report for " + getName() + "\n";

        List<Rental> rentals = getRentals();

        double totalCharge = 0;
        int totalPoint = 0;

        for (Rental each : rentals) {
            double eachCharge = 0;
            int eachPoint = 0;
            int daysRented = 0;

            daysRented = getDaysRented(each);
            eachCharge = calcCharge(each, eachCharge, daysRented);
            eachPoint = calcEachPoint(each, eachPoint, daysRented);

            result += addSummery(each, eachCharge, eachPoint, daysRented);

            totalCharge += eachCharge;
            totalPoint += eachPoint;
        }

        result = addResult(result, totalCharge, totalPoint);
        return result;
    }

    private String addResult(String result, double totalCharge, int totalPoint) {
        result += "Total charge: " + totalCharge + "\tTotal Point:" + totalPoint + "\n";

        if (totalPoint >= 10) {
            result += "Congrat! You earned one free coupon";
        } else if (totalPoint >= 30) {
            result += "Congrat! You earned two free coupon";
        }
        return result;
    }

    private String addSummery(Rental each, double eachCharge, int eachPoint, int daysRented) {
        String summery = "\t" + each.getVideo().getTitle() + "\tDays rented: " + daysRented + "\tCharge: " + eachCharge
                + "\tPoint: " + eachPoint + "\n";
        return summery;
    }
    private double calcCharge(Rental each, double eachCharge, int daysRented) {
        switch (each.getVideo().getPriceCode()) {
            case Video.REGULAR:
                eachCharge += 2;
                if (daysRented > 2)
                    eachCharge += (daysRented - 2) * 1.5;
                break;
            case Video.NEW_RELEASE:
                eachCharge = daysRented * 3;
                break;
        }
        return eachCharge;
    }

    private int calcEachPoint(Rental each, int eachPoint, int daysRented) {
        eachPoint++;

        if ((each.getVideo().getPriceCode() == Video.NEW_RELEASE))
            eachPoint++;

        if (daysRented > each.getDaysRentedLimit())
            eachPoint -= Math.min(eachPoint, each.getVideo().getLateReturnPointPenalty());
        return eachPoint;
    }

    private int getDaysRented(Rental each) {
        long diff;
        int daysRented;
        if (isReturned(each)) { // returned Video
            diff = each.getReturnDate().getTime() - each.getRentDate().getTime();
        } else { // not yet returned
            diff = new Date().getTime() - each.getRentDate().getTime();
        }
        daysRented = (int) (diff / MILLISECONDES_OF_A_DAY) + 1;
        return daysRented;
    }

    private boolean isReturned(Rental each) {
        return each.getStatus() == 1;
    }
}
