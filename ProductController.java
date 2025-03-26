package com.example.demo.controller;

import java.security.KeyStore.Entry.Attribute;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.ProductEntity;
import com.example.demo.model.ProductModel;
import com.example.demo.service.ProductService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class ProductController {
@Autowired
ProductService productService;

  @GetMapping("/productform")
  public String getform(Model model) {
	  ProductModel  productModel=new ProductModel();
	  productModel.setPrice(1002);
	  model.addAttribute("productModel",productModel);
	  return "add-product";
  
  }
  
  @PostMapping("/saveProduct")
  public String saveproductdata(@Valid @ModelAttribute  ProductModel productModel,BindingResult bindingResult)
  {
	  if(bindingResult.hasErrors())
	  {
		  return "add-product";
	  }
	  productService.saveProductData(productModel);
	  return "success";
  }
  
  @GetMapping("/getAllProducts")
  public String getAllproducts(Model model) {
	  List<ProductEntity> products= productService.getAllProductsData();
	  model.addAttribute("products",products);
      return "product-list";
  }
  @GetMapping("/getProduct/{id}")
  public String getproductById(@PathVariable long id, Model model)
  {
	  Optional<ProductEntity> optionalproduct=productService.getProductById(id);
	  if(optionalproduct.isPresent()) {
		  ProductEntity products=optionalproduct.get();
		  model.addAttribute("products", products);
	  }
	  else {
		  model.addAttribute("error message","productwith id"+id+"notfound");
	  }
      return "product-list";
  }
  
  
  
  
  @GetMapping("/deleteProduct/{id}")
  public String deleteProduct(@PathVariable("id") Long id) { 
      productService.deleteProduct(id);
      return "redirect:/getAllProducts"; // Redirect to the product list after deletion
      }


//  @GetMapping("/editProduct/{id}")
//  public String editProduct(@PathVariable long id, Model model) {
//      Optional<ProductEntity> product = productService.getProductById(id);
//      if (product != null) {
//          model.addAttribute("product", product);
//          return "edit";  
//      } else {
//          return "redirect:/getAllProducts";
// }
//   
//  }
//
//  @PostMapping("/updateproduct/{id}")
//   String updateProduct(@PathVariable("id") Long id, @ModelAttribute ProductModel productModel)
//  {
//      productService.updateProduct(id, productModel); 
//      return "redirect:/getAllProducts"; 
//  
//  }



//@GetMapping("editproduct/{id}")
//public String editproduct(@PathVariable("id") long id ,Model model)
//{
//  ProductModel productmodel=productService.editproduct(id);
//  model.addAttribute("productModel",productmodel);
//  model.addAttribute("id",id);
//  return "editproduct";
//}
  @GetMapping("/editProduct/{id}")
  public String editProductForm(@PathVariable("id") long id, Model model) {
      ProductModel products = productService.editproduct(id);
      model.addAttribute("products", products);
      model.addAttribute("id", id);
      return "editproduct"; // This will render editproduct.html
  }
  
}


