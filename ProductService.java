package com.example.demo.service;

import java.time.LocalDateTime; 
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ProductEntity;
import com.example.demo.model.ProductModel;
import com.example.demo.repository.ProductRepository;



@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public void saveProductData(ProductModel productModel) {
        ProductEntity productEntity = new ProductEntity();
        double price = productModel.getPrice();
        int quantity = productModel.getQuantity();
        
        // Calculate tax (10% of price)
        double taxAmount = price * 10 / 100;
        
        // Calculate total price (price * quantity)
        double totalPrice = price * quantity;

        // Set values for the product entity
        productEntity.setName(productModel.getName());
        productEntity.setPrice(price);  
        productEntity.setQuantity(quantity);
        productEntity.setBrand(productModel.getBrand());
        productEntity.setMadein(productModel.getMadein());
        productEntity.setTaxAmount(taxAmount);
        productEntity.setTotalAmount(totalPrice); 
        productEntity.setCreatedAt(LocalDateTime.now());
        productEntity.setCreatedby(System.getProperty("user.name")); 

        
        productRepository.save(productEntity);
    }

    
    public List<ProductEntity> getAllProductsData() {
        return productRepository.findAll();
    }

    
    public Optional<ProductEntity> getProductById(long id) {
		Optional<ProductEntity> product=productRepository.findById(id);
		return product;
	}
    
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

//    public void updateProduct(Long id, ProductModel productModel) {
//        
//        ProductEntity product = productRepository.findById(id).orElse(null);
//        if(product!=null) {
//                

//        // Update the fields of the existing product
//        product.setName(productModel.getName());
//        product.setBrand(productModel.getBrand());
//        product.setPrice(productModel.getPrice());
//        product.setQuantity(productModel.getQuantity());
//        product.setMadein(productModel.getMadein());
//        //recalculate the price 
//        double totalAmount=product.getPrice()* product.getQuantity();
//        double tax=totalAmount *10/100;
//        product.setTotalAmount(totalAmount); 
//        product.setTaxAmount(tax);
//        product.setCreatedAt(LocalDateTime.now());
//        product.setCreatedby(System.getProperty("user.name")); 
//
//
//        // Save the updated product
//        productRepository.save(product);
//        }
//    }
//}
//
//
// 
 //      	  }


			public ProductModel editproduct(long id) {
				ProductEntity productEntity=productRepository.findById(id).get();
  	          ProductModel productModel=new ProductModel();
  	          productModel.setName(productEntity.getName());
  	          productModel.setPrice(productEntity.getPrice());
  	          productModel.setQuantity(productEntity.getQuantity());
  	          productModel.setMadein(productEntity.getMadein());
  	          productModel.setBrand(productEntity.getBrand());
				return productModel;
			}


			
        	
}