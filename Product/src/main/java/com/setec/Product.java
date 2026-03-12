package com.setec;

import java.text.DecimalFormat;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Product {
	private int id;
	private String name;
	private double price;
	private int qty;

	public Product() {
		id = 0;
		name = "NoName";
		price = 0;
		qty = 0;
	}

	public Product(int id, String name, double price, int qty) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.qty = qty;
	}

	public void input(List<Product> products) {		
		while (true) {
			int enteredId = MyMethods.inputInt("Enter ID = ", 1, 999);
			if (MyMethods.isIdExist(products, enteredId)) {
	            System.out.println("ID " + enteredId + " already exists! Please enter a different ID.");
	        } else {
	            id = enteredId;
	            break;
	        }
		}

		name = MyMethods.inputString("Enter Name = ").trim();
		price = MyMethods.inputDouble("Enter Price = $", 0.01);
		qty = MyMethods.inputInt("Enter Qty = ", 0, 10000);
	}

	public double amount() {
		return price * qty;
	}

	public String getName() {
		return name.toUpperCase();
	}

	@Override
	public String toString() {
		var dfId = new DecimalFormat("000");
		var dfPrice = new DecimalFormat("$#,##0.00");

		return String.format("%-5s %-20s %-12s %-8d %-12s",
				dfId.format(id),
				getName(),
				dfPrice.format(price),
				qty,
				dfPrice.format(amount()));
	}
}