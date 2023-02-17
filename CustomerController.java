import java.util.ArrayList;
import java.util.List;

public class CustomerController {
    private List<Customer> customers = new ArrayList<>();

    public List<Customer> getCustomers() {
        return customers;
    }

    public CustomerController() {
    }

    public void listCustomers() {
        System.out.println("List of customers");
        for (Customer customer : customers) {
            System.out.println("Name: " + customer.getName() +
                    "\tRentals: " + customer.getRentals().size());
            for (Rental rental : customer.getRentals()) {
                System.out.print("\tTitle: " + rental.getVideo().getTitle() + " ");
                System.out.print("\tPrice Code: " + rental.getVideo().getPriceCode());
            }
        }
        System.out.println("End of list");
    }

    public void getCustomerReport() {
        System.out.println("Enter customer name: ");
        String customerName = VRUI.scanner.next();

        Customer foundCustomer = null;
        for (Customer customer : customers) {
            if (customer.getName().equals(customerName)) {
                foundCustomer = customer;
                break;
            }
        }

        if (foundCustomer == null) {
            System.out.println("No customer found");
        } else {
            String result = foundCustomer.getReport();
            System.out.println(result);
        }
    }

    public void register(String name) {
        Customer customer = new Customer(name);
        customers.add(customer);
    }

    void clearRentals(String customerName) {
        Customer foundCustomer = null;
        for (Customer customer : getCustomers()) {
            if (customer.getName().equals(customerName)) {
                foundCustomer = customer;
                break;
            }
        }

        if (foundCustomer == null) {
            System.out.println("No customer found");
        } else {
            System.out.println("Name: " + foundCustomer.getName() +
                    "\tRentals: " + foundCustomer.getRentals().size());
            for (Rental rental : foundCustomer.getRentals()) {
                System.out.print("\tTitle: " + rental.getVideo().getTitle() + " ");
                System.out.print("\tPrice Code: " + rental.getVideo().getPriceCode());
            }

            List<Rental> rentals = new ArrayList<Rental>();
            foundCustomer.setRentals(rentals);
        }
    }
}