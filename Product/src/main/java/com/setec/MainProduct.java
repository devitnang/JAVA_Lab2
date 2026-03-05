package com.setec;

import java.util.ArrayList;
import java.util.List;

public class MainProduct {
	public static void main( String[] args ) {
		List<Product> products = new ArrayList<>();
		do {
			int choose = MyMethods.showMenu();
			
			switch(choose) {
			case 1-> MyMethods.addProduct(products);
			case 2-> MyMethods.editProduct(products);
			case 3-> MyMethods.deleteProduct(products);
			case 4-> MyMethods.showAllProducts(products);
			case 5-> System.exit(0);
			}
		} while (true);
	}
}
