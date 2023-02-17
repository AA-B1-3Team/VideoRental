import java.util.Date;
import java.util.Scanner;

public class VRUI {
    private static Scanner scanner = new Scanner(System.in);
    private final CustomerController customerController = new CustomerController();
    private final VideoController videoController = new VideoController();

    public static void main(String[] args) {
        VRUI ui = new VRUI();

        boolean quit = false;
        while (!quit) {
            int command = ui.showCommand();
            switch (command) {
                case 0:
                    quit = true;
                    break;
                case 1:
                    ui.customerController.listCustomers();
                    break;
                case 2:
                    ui.videoController.listVideos();
                    break;
                case 3:
                    ui.registerCustomer();
                    break;
                case 4:
                    ui.register("video");
                    break;
                case 5:
                    ui.videoController.rentVideo();
                    break;
                case 6:
                    ui.videoController.returnVideo();
                    break;
                case 7:
                    ui.customerController.getCustomerReport();
                    break;
                case 8:
                    ui.clearRentals();
                    break;
                case -1:
                    ui.init();
                    break;
                default:
                    break;
            }
        }
        System.out.println("Bye");
    }

    public void clearRentals() {
        System.out.println("Enter customer name: ");
        String customerName = VRUI.scanner.next();
        customerController.clearRentals(customerName);
    }

    private void init() {
        Customer james = new Customer("James");
        Customer brown = new Customer("Brown");
        customerController.getCustomers().add(james);
        customerController.getCustomers().add(brown);

        Video v1 = new Video("v1", Video.CD, Video.REGULAR, new Date());
        Video v2 = new Video("v2", Video.DVD, Video.NEW_RELEASE, new Date());
        videoController.videos.add(v1);
        videoController.videos.add(v2);

        Rental r1 = new Rental(v1);
        Rental r2 = new Rental(v2);

        james.addRental(r1);
        james.addRental(r2);
    }

    public void register(String object) {
        if (object.equals("customer")) {
            // TODO: register 의 분리가 완료되면 지워야 합니다. Conflict 방지를 위해 일단 남겨뒀습니다.
            System.out.println("Enter customer name: ");
            String name = scanner.next();
            Customer customer = new Customer(name);
            customerController.getCustomers().add(customer);
        } else {
            System.out.println("Enter video title to register: ");
            String title = scanner.next();

            System.out.println("Enter video type( 1 for VHD, 2 for CD, 3 for DVD ):");
            int videoType = scanner.nextInt();

            System.out.println("Enter price code( 1 for Regular, 2 for New Release ):");
            int priceCode = scanner.nextInt();

            Date registeredDate = new Date();
            Video video = new Video(title, videoType, priceCode, registeredDate);
            videoController.videos.add(video);
        }
    }

    public void registerCustomer() {
        System.out.println("Enter customer name: ");
        String name = VRUI.scanner.next();
        customerController.register(name);
    }

    public int showCommand() {
        System.out.println("\nSelect a command !");
        System.out.println("\t 0. Quit");
        System.out.println("\t 1. List customers");
        System.out.println("\t 2. List videos");
        System.out.println("\t 3. Register customer");
        System.out.println("\t 4. Register video");
        System.out.println("\t 5. Rent video");
        System.out.println("\t 6. Return video");
        System.out.println("\t 7. Show customer report");
        System.out.println("\t 8. Show customer and clear rentals");

        int command = scanner.nextInt();

        return command;

    }
}
