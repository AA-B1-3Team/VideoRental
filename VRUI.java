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
                    ui.registerVideo();
                    break;
                case 5:
                    ui.rentVideo();
                    break;
                case 6:
                    ui.returnVideo();
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

    private void returnVideo() {
        System.out.println("Enter customer name: ");
        String customerName = VRUI.scanner.next();
        Customer foundCustomer = customerController.findCustomer(customerName);

        System.out.println("Enter video title to register: ");
        String title = scanner.next();

        videoController.returnVideo(foundCustomer, title);
    }

    private void rentVideo() {
        System.out.println("Enter customer name: ");
        String customerName = VRUI.scanner.next();
        Customer foundCustomer = customerController.findCustomer(customerName);

        System.out.println("Enter video title to register: ");
        String title = scanner.next();

        videoController.rentVideo(foundCustomer, title);
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

        Video v1 = new Video("v1", VideoType.CD, PriceCode.REGULAR, new Date());
        Video v2 = new Video("v2", VideoType.DVD, PriceCode.NEW_RELEASE, new Date());
        videoController.videos.add(v1);
        videoController.videos.add(v2);

        Rental r1 = new Rental(v1);
        Rental r2 = new Rental(v2);

        james.addRental(r1);
        james.addRental(r2);
    }

    public void registerCustomer() {
        System.out.println("Enter customer name: ");
        String name = VRUI.scanner.next();
        customerController.register(name);
    }

    public void registerVideo() {
        System.out.println("Enter video title to register: ");
        String title = scanner.next();

        System.out.println("Enter video type( 1 for VHD, 2 for CD, 3 for DVD ):");
        int videoType = scanner.nextInt();

        System.out.println("Enter price code( 1 for Regular, 2 for New Release ):");
        int priceCode = scanner.nextInt();

        videoController.register(title, videoType, priceCode);
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
