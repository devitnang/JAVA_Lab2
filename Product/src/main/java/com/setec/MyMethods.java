package com.setec;

import java.util.List;
import java.util.Scanner;

public class MyMethods {
	static Scanner cin = new Scanner(System.in);

	public static int showMenu() {
		System.out.println("\n--------- PRODUCT MANAGEMENT ---------");
		System.out.println("1. Add Product");
		System.out.println("2. Edit Product by ID");
		System.out.println("3. Delete Product by ID");
		System.out.println("4. Show All Products");
		System.out.println("5. Exit");
		printLine('-', 60);
		return inputInt("Choose (1-5): ", 1, 5);
	}

	public static int inputInt(String message, int min, int max) {
		int value;
		while (true) {
			try {
				System.out.print(message);
				String raw = cin.nextLine().trim();
				// reject "-0" explicitly
				if (raw.equals("-0")) {
					System.out.println("Invalid input! Please enter a whole number.");
					continue;
				}
				value = Integer.parseInt(raw);
				if (value < min || value > max) {
					System.out.println("Value must be between " + min + " and " + max + ".");
				} else {
					return value;
				}
			} catch (Exception e) {
				System.out.println("Invalid input! Please enter a whole number.");
			}
		}
	}

	public static double inputDouble(String message, double min) {
		double value;
		while (true) {
			try {
				System.out.print(message);
				value = Double.parseDouble(cin.nextLine().trim());
				if (value < min) {
					System.out.println("Value must be >= " + min);
				} else {
					return value;
				}
			} catch (Exception e) {
				System.out.println("Invalid input! Please enter a valid number.");
			}
		}
	}

	public static String inputString(String message) {
		String value;
		do {
			System.out.print(message);
			value = cin.nextLine().trim();
			if (value.isEmpty()) System.out.println("Input cannot be empty!");
		} while (value.isEmpty());
		return value;
	}
	
	public static Product findProductById(List<Product> products, int id) {
	    for (Product p : products) {
	        if (p.getId() == id) {
	            return p;
	        }
	    }
	    System.out.println("Product ID " + id + " not found!");
	    return null;
	}
	
	public static boolean isIdExist(List<Product> products, int id) {
	    for (Product p : products) {
	        if (p.getId() == id) {
	            return true;
	        }
	    }
	    return false;
	}

	public static void addProduct(List<Product> products) {
		int n = inputInt("How many products to add? ", 1, 100);

		for (int i = 0; i < n; i++) {
			System.out.println("\nProduct № " + (i + 1));
			Product p = new Product();
			p.input(products);
			products.add(p);
			System.out.println("Product added successfully!");
		}
	}

	public static void editProduct(List<Product> products) {
	    int id = inputInt("Enter ID to edit: ", 1, 999);
	    Product p = findProductById(products, id);
	    if (p == null) return;
	    
	    p.setName(inputString("New Name: ").trim());
	    p.setPrice(inputDouble("New Price: $", 0.01));
	    p.setQty(inputInt("New Qty: ", 0, 10000));
	    System.out.println("Product updated successfully!");
	}

	public static void deleteProduct(List<Product> products) {
		int id = inputInt("Enter ID to delete: ", 1, 999);
	    Product p = findProductById(products, id);
	    if (p == null) return;

	    products.remove(p);
	    System.out.println("Product deleted successfully!");
	}

	public static void printLine(char ch, int length) {
		System.out.println(String.valueOf(ch).repeat(length));
	}

	public static void showAllProducts(List<Product> products) {
		if (products.isEmpty()) {
			System.out.println("No products found!");
			return;
		}

		System.out.println();
		printLine('-', 60);
		System.out.printf("%-5s %-20s %-12s %-8s %-12s%n",
				"ID", "NAME", "PRICE ($)", "QTY", "AMOUNT ($)");
		printLine('-', 60);

		for (Product p : products) {
			System.out.println(p);
		}

		printLine('-', 60);
	}
}