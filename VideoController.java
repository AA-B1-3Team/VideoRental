import java.util.ArrayList;
import java.util.List;

public class VideoController {
    private final VRUI VRUI;
    List<Video> videos = new ArrayList<Video>();

    public VideoController(VRUI VRUI) {
        this.VRUI = VRUI;
    }

    public void returnVideo() {
        System.out.println("Enter customer name: ");
        String customerName = VRUI.scanner.next();

        Customer foundCustomer = null;
        for (Customer customer : VRUI.getCustomers()) {
            if (customer.getName().equals(customerName)) {
                foundCustomer = customer;
                break;
            }
        }
        if (foundCustomer == null) return;

        System.out.println("Enter video title to return: ");
        String videoTitle = VRUI.scanner.next();

        List<Rental> customerRentals = foundCustomer.getRentals();
        for (Rental rental : customerRentals) {
            if (rental.getVideo().getTitle().equals(videoTitle) && rental.getVideo().isRented()) {
                rental.returnVideo();
                rental.getVideo().setRented(false);
                break;
            }
        }
    }

    public void listVideos() {
        System.out.println("List of videos");

        for (Video video : videos) {
            System.out.println("Price code: " + video.getPriceCode() + "\tTitle: " + video.getTitle());
        }
        System.out.println("End of list");
    }

    public void rentVideo() {
        System.out.println("Enter customer name: ");
        String customerName = VRUI.scanner.next();

        Customer foundCustomer = null;
        for (Customer customer : VRUI.getCustomers()) {
            if (customer.getName().equals(customerName)) {
                foundCustomer = customer;
                break;
            }
        }

        if (foundCustomer == null) return;

        System.out.println("Enter video title to rent: ");
        String videoTitle = VRUI.scanner.next();

        Video foundVideo = null;
        for (Video video : videos) {
            if (video.getTitle().equals(videoTitle) && video.isRented() == false) {
                foundVideo = video;
                break;
            }
        }

        if (foundVideo == null) return;

        Rental rental = new Rental(foundVideo);
        foundVideo.setRented(true);

        List<Rental> customerRentals = foundCustomer.getRentals();
        customerRentals.add(rental);
        foundCustomer.setRentals(customerRentals);
    }
}