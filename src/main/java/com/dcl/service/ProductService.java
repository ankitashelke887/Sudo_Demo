package com.dcl.service;

import java.util.List;

import com.dcl.dto.ProductDto;
import com.dcl.request.Productrequest;
import com.dcl.request.UpdateRequest;

public interface ProductService {
	
	ProductDto addProduct(Productrequest requset);
	
	ProductDto getById(Integer pId);
	
	List<ProductDto> getallProduct();
	
	ProductDto updateproductbyId(Integer pId, UpdateRequest requset);
	
	void deleteproductbyId(Integer pId);

}
