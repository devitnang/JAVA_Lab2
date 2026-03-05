package com.setec;

import java.text.DecimalFormat;
import java.util.Scanner;

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

	public void input() {
		var cin = new Scanner(System.in);
		System.out.print("Enter ID = ");
		id = cin.nextInt();
		System.out.print("Enter Name = ");
		cin.nextLine();
		name = cin.nextLine();
		System.out.print("Enter Price =$");
		price = cin.nextDouble();
		System.out.print("Enter Qty = ");
		qty = cin.nextInt();
		System.out.println("Amount = $" + amount());
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