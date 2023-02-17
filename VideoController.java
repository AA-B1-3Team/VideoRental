import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VideoController {

    List<Video> videos = new ArrayList<Video>();

    public VideoController() {

    }

    public void returnVideo(Customer foundCustomer, String videoTitle) {
        if (foundCustomer == null) return;

        List<Rental> customerRentals = foundCustomer.getRentals();
        for (Rental rental : customerRentals) {
            if (rental.getVideo().getTitle().equals(videoTitle) && rental.getVideo().isRented()) {
                rental.returnVideo();
                rental.getVideo().setRented(false);
                break;
            }
        }
    }

    public Video findVideo(String videoTitle){
        Video foundVideo = null;
        for (Video video : videos) {
            if (video.getTitle().equals(videoTitle) && video.isRented() == false) {
                foundVideo = video;
                break;
            }
        }
        return foundVideo;
    }

    public void listVideos() {
        System.out.println("List of videos");

        for (Video video : videos) {
            System.out.println("Price code: " + video.getPriceCode() + "\tTitle: " + video.getTitle());
        }
        System.out.println("End of list");
    }

    public void rentVideo(Customer foundCustomer, String videoTitle) {
        if (foundCustomer == null) return;

        Video foundVideo = findVideo(videoTitle);
        if (foundVideo == null) return;

        foundVideo.setRented(true);

        Rental rental = new Rental(foundVideo);
        foundCustomer.addRental(rental);
    }

    public void register(String title, int videoType,int priceCode) {
        Date registeredDate = new Date();
        Video video = new Video(title, videoType, priceCode, registeredDate);
        videos.add(video);
    }
}