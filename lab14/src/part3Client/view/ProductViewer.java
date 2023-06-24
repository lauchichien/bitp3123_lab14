package part3Client.view;

import java.util.List;

import part3Model.Product;

public class ProductViewer {
	
	
	public void displayProducts(List<Product> products) {
		
		// Some information about the record
		System.out.println("\tNumber of record: " + products.size());
		System.out.println("\tProduct Information: " + "\n");
		
		// Print all products on console
		for (Product product:products) {
			
			System.out.println("\tProduct Id: " + product.getProductId());
			System.out.println("\tName: " + product.getName());
			System.out.println("\tPrice: RM" + product.getPrice() + "\n");
		}
		
	}

}
