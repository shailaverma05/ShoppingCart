import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Base class representing a generic item
class Item {
    protected String name;
    protected double price;

    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    // Overridable method for showing item details
    public String showDetails() {
        return name + ": ₹" + price;
    }
}

// Subclass representing electronics that extends Item
class Electronics extends Item {
    private int warrantyInMonths;

    public Electronics(String name, double price, int warrantyInMonths) {
        super(name, price);
        this.warrantyInMonths = warrantyInMonths;
    }

    public int getWarrantyInMonths() {
        return warrantyInMonths;
    }

    // Overriding method to provide more details about electronics items
    @Override
    public String showDetails() {
        return name + ": ₹" + price + " (Warranty: " + warrantyInMonths + " months)";
    }
}

// Subclass representing groceries that extends Item
class Grocery extends Item {
    private double weightInKg;

    public Grocery(String name, double price, double weightInKg) {
        super(name, price);
        this.weightInKg = weightInKg;
    }

    public double getWeightInKg() {
        return weightInKg;
    }

    // Overriding method to provide more details about grocery items
    @Override
    public String showDetails() {
        return name + ": ₹" + price + " (Weight: " + weightInKg + " kg)";
    }
}

// Cart class representing the shopping cart
class Cart {
    private List<Item> items;
    private double totalPrice;

    public Cart() {
        items = new ArrayList<>();
        totalPrice = 0;
    }

    // Method overloading for adding items
    public void addItem(Item item) {
        items.add(item);
        totalPrice += item.getPrice();
        System.out.println("Added " + item.getName() + " to the cart.");
        showTotalPrice();
    }

    // Method overloading for adding multiple items at once
    public void addItem(Item[] itemArray) {
        for (Item item : itemArray) {
            items.add(item);
            totalPrice += item.getPrice();
        }
        showTotalPrice();
    }

    public void removeItem(Item item) {
        if (items.remove(item)) {
            totalPrice -= item.getPrice();
            System.out.println(item.getName() + " removed from the cart.");
            showTotalPrice();
        } else {
            System.out.println("Item not found in the cart.");
        }
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void showTotalPrice() {
        System.out.println("Total Price: ₹" + totalPrice);
    }

    public void showCart() {
        System.out.println("Items in your cart:");
        for (Item item : items) {
            System.out.println(item.showDetails()); // Polymorphism: Calls overridden methods
        }
        showTotalPrice();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void clearCart() {
        items.clear();
        totalPrice = 0;
        System.out.println("Cart has been cleared.");
    }
}

// Main class to interact with the user
public class shoppingcart {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Cart cart = new Cart();

        // Sample items
        Grocery apple = new Grocery("Apple", 99.0, 1.0);
        Grocery banana = new Grocery("Banana", 79.0, 1.2);
        Electronics phone = new Electronics("Smartphone", 29999.0, 24);
        Electronics laptop = new Electronics("Laptop", 79999.0, 12);

        boolean shopping = true;

        while (shopping) {
            System.out.println("\nShopping Menu:");
            System.out.println("1. Add Apple to Cart - ₹99.0");
            System.out.println("2. Add Banana to Cart - ₹79.0");
            System.out.println("3. Add Smartphone to Cart - ₹29,999.0 (24 months warranty)");
            System.out.println("4. Add Laptop to Cart - ₹79,999.0 (12 months warranty)");
            System.out.println("5. View Cart");
            System.out.println("6. Remove Item");
            System.out.println("7. Checkout");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    cart.addItem(apple);
                    break;
                case 2:
                    cart.addItem(banana);
                    break;
                case 3:
                    cart.addItem(phone);
                    break;
                case 4:
                    cart.addItem(laptop);
                    break;
                case 5:
                    cart.showCart();
                    break;
                case 6:
                    System.out.print("Enter the name of the item to remove: ");
                    String itemName = scanner.next();
                    boolean found = false;
                    for (Item item : new Item[] { apple, banana, phone, laptop }) {
                        if (item.getName().equalsIgnoreCase(itemName)) {
                            cart.removeItem(item);
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Item not found in the cart.");
                    }
                    break;
                case 7:
                    if (!cart.isEmpty()) {
                        cart.showCart();
                        System.out.println("Proceeding to checkout...");
                        shopping = false;
                    } else {
                        System.out.println("Your cart is empty.");
                    }
                    break;
                case 8:
                    shopping = false;
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
        scanner.close();
        System.out.println("Thank you for shopping with us!");
    }
}
