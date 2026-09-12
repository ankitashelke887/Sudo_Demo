package com.dcl.service.imp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.hibernate.annotations.Collate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcl.dto.ProductDto;
import com.dcl.entity.Product;
import com.dcl.repo.ProductRepo;
import com.dcl.request.Productrequest;
import com.dcl.request.UpdateRequest;
import com.dcl.service.ProductService;

@Service
public class ProductserviceImp  implements ProductService{
	
	@Autowired
	private ProductRepo prepo;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public ProductDto addProduct(Productrequest requset) {
		Product p=new Product();
		mapper.map(requset, p);
		prepo.save(p);
		return mapper.map(p, ProductDto.class);
	}

	@Override
	public ProductDto getById(Integer pId) {
	    Product p=prepo.findById(pId).orElse(null);
	    if(p==null){
	    	throw new RuntimeException("Product not found");
	    }
	    ProductDto pdto=new ProductDto();
	    pdto.setPId(p.getPId());
	    pdto.setProductName(p.getProductName());
	    pdto.setBrand(p.getBrand());
	    pdto.setPrice(p.getPrice());
		return pdto;
	}

	@Override
	public List<ProductDto> getallProduct() {
		List<Product> product=prepo.findAll();
		Function<Product, ProductDto> function=(p)->{
			ProductDto dto=new ProductDto();
			dto.setPId(p.getPId());
			dto.setProductName(p.getProductName());
			dto.setBrand(p.getBrand());
			dto.setPrice(p.getPrice());
			return dto;
		};
		List<ProductDto> pl= product.stream().map(function).collect(Collectors.toList());
		return pl;
	}

	@Override
	public ProductDto updateproductbyId(Integer pId, UpdateRequest requset) {
		Product existingProduct=prepo.findById(pId).orElseThrow(()->new RuntimeException("Product Not Found!"));
		existingProduct.setProductName(requset.getProductName());
		existingProduct.setPrice(requset.getPrice());
		existingProduct.setBrand(requset.getBrand());
		Product afterupdate=prepo.save(existingProduct);
		
		ProductDto dto=new ProductDto();
		dto.setPId(afterupdate.getPId());
		dto.setProductName(afterupdate.getProductName());
		dto.setBrand(afterupdate.getBrand());
		dto.setPrice(afterupdate.getPrice());
		return dto;
	}

	@Override
	public void deleteproductbyId(Integer pId) {
		Product p=prepo.findById(pId).orElseThrow(()->new RuntimeException("Product Not Found!"));
		prepo.deleteById(pId);
	}
	
	

}
