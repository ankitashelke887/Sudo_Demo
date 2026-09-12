package com.dcl.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dcl.dto.ProductDto;
import com.dcl.request.Productrequest;
import com.dcl.request.UpdateRequest;
import com.dcl.respons.ApiResponse;
import com.dcl.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService pservice;
	
	@PostMapping("/adding")
	public ResponseEntity<?> productRequest(@RequestBody Productrequest request){
		ProductDto pdto=pservice.addProduct(request);
		return ResponseEntity.ok(new ApiResponse<>("Product Added Successfully!",pdto,HttpStatus.OK));
	}
    @GetMapping("/get/{pId}")
	public ResponseEntity<?> getById(@PathVariable Integer pId){
		ProductDto pdto=pservice.getById(pId);
		return ResponseEntity.ok(pdto);
	}
    
    @GetMapping("/getproduct")
    public ResponseEntity<?> getAll(){
    	List<ProductDto> pdto=pservice.getallProduct();
    	return ResponseEntity.ok(pdto);
    }
    
    @PutMapping("/update/{pId}")
    public ResponseEntity<?> updateById(@PathVariable Integer pId, 
    		@RequestBody UpdateRequest request){
    	ProductDto pdto=pservice.updateproductbyId(pId, request);
    	return ResponseEntity.ok(pdto);
    }
    
    @DeleteMapping("/delete/{pId}")
    public ResponseEntity<?> deletebyId(@PathVariable Integer pId){
    	pservice.deleteproductbyId(pId);
    	return ResponseEntity.ok("Product deleted successfully!");
    	
    }

}
