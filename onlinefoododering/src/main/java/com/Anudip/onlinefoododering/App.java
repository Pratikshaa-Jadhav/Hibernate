package com.Anudip.onlinefoododering;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        // Create SessionFactory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml") // load configuration from hibernate.cfg.xml
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Restaurant.class)
                .addAnnotatedClass(FoodItem.class)
                .addAnnotatedClass(FoodOrder.class)
                .addAnnotatedClass(OrderDetail.class)
                .buildSessionFactory();

        // Create Session
        try (Session session = factory.openSession();
             Scanner scanner = new Scanner(System.in)) {

            // Start transaction
            Transaction transaction = session.beginTransaction();

            try {
                // Getting user input for Customer
                System.out.println("Enter Customer Details:");
                System.out.print("Name: ");
                String customerName = scanner.nextLine();
                System.out.print("Email: ");
                String customerEmail = scanner.nextLine();
                System.out.print("Address: ");
                String customerAddress = scanner.nextLine();
                System.out.print("Phone Number: ");
                String customerPhone = scanner.nextLine();

                Customer customer = new Customer();
                customer.setName(customerName);
                customer.setEmail(customerEmail);
                customer.setAddress(customerAddress);
                customer.setPhoneNumber(customerPhone);

                // Save the customer
                session.save(customer);

                // Initialize restaurants and menu items
                List<Restaurant> restaurants = initializeRestaurants(session);
                List<FoodItem> foodItems = initializeMenuItems(session, restaurants);

                List<OrderDetail> orderDetails = new ArrayList<>();
                double totalAmount = 0;

                // Allow user to select restaurant and order multiple times
                String moreOrders = "yes";

                while (moreOrders.equalsIgnoreCase("yes")) {
                    // Display available restaurants
                    System.out.println("\nAvailable Restaurants:");
                    for (int i = 0; i < restaurants.size(); i++) {
                        Restaurant rest = restaurants.get(i);
                        System.out.printf("%d. %s - %s\n", i + 1, rest.getName(), rest.getLocation());
                    }

                    System.out.println("Select a restaurant by number: ");
                    int restaurantSelection = getIntInput(scanner);

                    if (restaurantSelection < 1 || restaurantSelection > restaurants.size()) {
                        System.out.println("Invalid restaurant selection!");
                        continue;
                    }

                    Restaurant selectedRestaurant = restaurants.get(restaurantSelection - 1);

                    boolean orderAgain = true;

                    // Loop for ordering more items from the same restaurant
                    while (orderAgain) {
                        // Display food items for the selected restaurant
                        displayFoodItems(selectedRestaurant, foodItems);

                        // Allow user to select food items
                        System.out.println("Select food items by number (separated by commas, e.g., 1,2): ");
                        String[] selections = scanner.nextLine().split(",");

                        for (String selection : selections) {
                            int index = Integer.parseInt(selection.trim()) - 1;
                            // Filter items based on restaurant
                            List<FoodItem> filteredItems = filterFoodItemsByRestaurant(selectedRestaurant, foodItems);
                            if (index >= 0 && index < filteredItems.size()) {
                                FoodItem selectedItem = filteredItems.get(index);
                                System.out.print("Quantity for " + selectedItem.getName() + ": ");
                                int quantity = getIntInput(scanner);

                                OrderDetail orderDetail = new OrderDetail();
                                orderDetail.setFoodItem(selectedItem);
                                orderDetail.setQuantity(quantity);
                                orderDetails.add(orderDetail);
                                totalAmount += selectedItem.getPrice() * quantity;
                            } else {
                                System.out.println("Invalid selection: " + selection);
                            }
                        }

                        // Ask if the customer wants to order more from the same restaurant
                        System.out.print("Do you want to order more from the same restaurant? (yes/no): ");
                        String orderAgainResponse = scanner.nextLine();
                        orderAgain = orderAgainResponse.equalsIgnoreCase("yes");
                    }

                    // Ask if the customer wants to order from another restaurant
                    System.out.print("Do you want to order from another restaurant? (yes/no): ");
                    moreOrders = scanner.nextLine();
                }

                // Save the order
                FoodOrder foodOrder = new FoodOrder();
                foodOrder.setOrderDate(new java.sql.Date(System.currentTimeMillis())); // Correct method for setting the date
                foodOrder.setTotalAmount(totalAmount);
                foodOrder.setCustomer(customer); // Associate order with customer
                session.save(foodOrder);

                // Save each order detail
                for (OrderDetail detail : orderDetails) {
                    detail.setOrder(foodOrder);  // Link the order to order details
                    session.save(detail);
                }

                // Commit transaction
                transaction.commit();
                System.out.printf("\nData saved successfully! Thank you for visiting again..!! :) Total Amount: ₹%.2f\n", totalAmount);

            } catch (Exception e) {
                // Rollback transaction in case of an error
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();  // Log exception
            }

        } catch (HibernateException e) {
            e.printStackTrace();  // Handle SessionFactory issues
        } finally {
            factory.close();  // Ensure the factory is closed
        }
    }

    private static int getIntInput(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
        }
        int result = scanner.nextInt();
        scanner.nextLine();  // Consume the leftover newline
        return result;
    }

    // Initialize restaurants
    private static List<Restaurant> initializeRestaurants(Session session) {
        List<Restaurant> restaurants = new ArrayList<>();
        // Populate the restaurant list
        Restaurant restaurant1 = new Restaurant();
        restaurant1.setName("Spice of India");
        restaurant1.setLocation("12 Taj Road");
        session.save(restaurant1);
        restaurants.add(restaurant1);

        Restaurant restaurant2 = new Restaurant();
        restaurant2.setName("Curry House");
        restaurant2.setLocation("45 Delhi Street");
        session.save(restaurant2);
        restaurants.add(restaurant2);

        Restaurant restaurant3 = new Restaurant();
        restaurant3.setName("Biryani Palace");
        restaurant3.setLocation("78 Mumbai Avenue");
        session.save(restaurant3);
        restaurants.add(restaurant3);

        Restaurant restaurant4 = new Restaurant();
        restaurant4.setName("Tandoori Nights");
        restaurant4.setLocation("101 Chennai Circle");
        session.save(restaurant4);
        restaurants.add(restaurant4);

        return restaurants;
    }

    // Initialize menu items
    private static List<FoodItem> initializeMenuItems(Session session, List<Restaurant> restaurants) {
        List<FoodItem> foodItems = new ArrayList<>();

        // Menu for Spice of India
        FoodItem butterChicken = new FoodItem();
        butterChicken.setName("Butter Chicken");
        butterChicken.setPrice(350); // Price in INR
        butterChicken.setCategory("Main Course");
        butterChicken.setRestaurant(restaurants.get(0));
        session.save(butterChicken);
        foodItems.add(butterChicken);

        FoodItem naan = new FoodItem();
        naan.setName("Garlic Naan");
        naan.setPrice(50); // Price in INR
        naan.setCategory("Bread");
        naan.setRestaurant(restaurants.get(0));
        session.save(naan);
        foodItems.add(naan);

        // Menu for Curry House
        FoodItem chickenCurry = new FoodItem();
        chickenCurry.setName("Chicken Curry");
        chickenCurry.setPrice(300); // Price in INR
        chickenCurry.setCategory("Main Course");
        chickenCurry.setRestaurant(restaurants.get(1));
        session.save(chickenCurry);
        foodItems.add(chickenCurry);

        FoodItem roti = new FoodItem();
        roti.setName("Tandoori Roti");
        roti.setPrice(30); // Price in INR
        roti.setCategory("Bread");
        roti.setRestaurant(restaurants.get(1));
        session.save(roti);
        foodItems.add(roti);

        // Menu for Biryani Palace
        FoodItem chickenBiryani = new FoodItem();
        chickenBiryani.setName("Chicken Biryani");
        chickenBiryani.setPrice(250); // Price in INR
        chickenBiryani.setCategory("Main Course");
        chickenBiryani.setRestaurant(restaurants.get(2));
        session.save(chickenBiryani);
        foodItems.add(chickenBiryani);

        FoodItem muttonBiryani = new FoodItem();
        muttonBiryani.setName("Mutton Biryani");
        muttonBiryani.setPrice(350); // Price in INR
        muttonBiryani.setCategory("Main Course");
        muttonBiryani.setRestaurant(restaurants.get(2));
        session.save(muttonBiryani);
        foodItems.add(muttonBiryani);

        // Menu for Tandoori Nights
        FoodItem tandooriChicken = new FoodItem();
        tandooriChicken.setName("Tandoori Chicken");
        tandooriChicken.setPrice(400); // Price in INR
        tandooriChicken.setCategory("Main Course");
        tandooriChicken.setRestaurant(restaurants.get(3));
        session.save(tandooriChicken);
        foodItems.add(tandooriChicken);

        FoodItem paneerTikka = new FoodItem();
        paneerTikka.setName("Paneer Tikka");
        paneerTikka.setPrice(300); // Price in INR
        paneerTikka.setCategory("Appetizer");
        paneerTikka.setRestaurant(restaurants.get(3));
        session.save(paneerTikka);
        foodItems.add(paneerTikka);

        return foodItems;
    }

    // Display food items for the selected restaurant
    private static void displayFoodItems(Restaurant restaurant, List<FoodItem> foodItems) {
        System.out.println("\nMenu for " + restaurant.getName() + ":");
        int itemIndex = 1;

        for (FoodItem item : foodItems) {
            if (item.getRestaurant().equals(restaurant)) {
                System.out.printf("%d. %s - ₹%.2f (%s)\n", itemIndex++, item.getName(), item.getPrice(), item.getCategory());
            }
        }
    }

    // Filter food items based on selected restaurant
    private static List<FoodItem> filterFoodItemsByRestaurant(Restaurant restaurant, List<FoodItem> foodItems) {
        List<FoodItem> filteredItems = new ArrayList<>();
        for (FoodItem item : foodItems) {
            if (item.getRestaurant().equals(restaurant)) {
                filteredItems.add(item);
            }
        }
        return filteredItems;
    }
}
