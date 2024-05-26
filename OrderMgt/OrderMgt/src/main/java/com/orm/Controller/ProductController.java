package com.orm.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orm.Dto.ProductDTO;
import com.orm.Service.ProductService;

@RestController
@RequestMapping("product")
public class ProductController {
	
	@Autowired
	private ProductService ps;
	
	//private final static Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@PostMapping("saveproduct")
	public ResponseEntity<ProductDTO> saveProduct(@Validated @RequestBody ProductDTO p){
		ResponseEntity<ProductDTO> rs = null;
		//logger.info("I am at ProductController saveProduct method started "+p);
		//logger.info("calling ProductService saveProduct method from ProductController.");
		ps.saveProduct(p);
		//logger.info("Product details are saved. Sending response back to browser.");
		rs = new ResponseEntity<ProductDTO>(p, HttpStatus.CREATED);
		//logger.info("I am at ProductController saveProduct method completed. "+p);
		return rs;
	}
	
	@PutMapping("updateproduct")
	public ResponseEntity<ProductDTO> updateProduct(@Validated @RequestBody ProductDTO p){
		ResponseEntity<ProductDTO> rs = null;
		//logger.info("I am at ProductController updateProduct method started. "+p);
		//logger.info("Calling ProductService update method from ProductController.");
		ps.updateProduct(p);
		//logger.info("Product details are updated. Sending response back to browser.");
		rs = new ResponseEntity<ProductDTO>(p, HttpStatus.ACCEPTED);
		//logger.info("I am at ProductController updateProduct method completed. "+p);
		return rs;
	}
	
	@DeleteMapping("deletebypid/{pid}")
	public ResponseEntity<Object> deleteProduct(@PathVariable Integer pid){
		ResponseEntity<Object> rs = null;
		//logger.info("I am at ProductController deleteProduct method started. "+pid);
		ps.deleteProduct(pid);
		//logger.info(pid+" : deleted succesfully.");
		rs = new ResponseEntity<Object>(pid+" : deleted succesfully.", HttpStatus.GONE);
		//logger.info("I am at ProductController deleteProduct method completed. "+pid);
		return rs;
	}
	
	@Cacheable("ProductPojo")
	@GetMapping("getbypid/{pid}")
	public ResponseEntity<ProductDTO> getByProductID(@PathVariable Integer pid){
		ResponseEntity<ProductDTO> rs = null;
		//logger.info("I am at ProductController getByProductID method started. "+pid);
		//logger.info("Calling ProductService getByProductID method from ProductController.");
		ProductDTO d =  ps.getByProductId(pid);
		//logger.info("I am at ProductController getByProductID method, sending response back to browser. "+pid);
		rs = new ResponseEntity<ProductDTO>(d, HttpStatus.OK);
		//logger.info("Response sent to the browser successfully.");
		//logger.info("I am at ProductController getByProductID method completed. "+pid);
		return rs;
	}
	
	@GetMapping("getbypname/{pname}")
	public ResponseEntity<List<ProductDTO>> getByProductName(@PathVariable String pname){
		ResponseEntity<List<ProductDTO>> rs = null;
		//logger.info("I am at ProductController getByProductName method started. "+pname);
		//logger.info("Calling ProductService getByProductName method from ProductController.");
		List<ProductDTO> d =  ps.getByProductName(pname);
		//logger.info("Sending response to browser. "+pname);
		rs = new ResponseEntity<List<ProductDTO>>(d, HttpStatus.OK);
		//logger.info("Response sent to the browser successfully.");
		//logger.info("I am at ProductController getByProductName method completed. "+pname);
		return rs;
	}
	
	//org.modelmapper.MappingException: ModelMapper mapping errors:
		//Caused by: org.hibernate.LazyInitializationException: failed to lazily initialize a collection of role: com.orm.Entity.ProductPojo.vpo: could not initialize proxy - no Sessions
		
		//while using schedule it will expect for eager loading because order associated with orderprocesspojo (one to many)
		//by default one to many has lazy loading, change fetch type to eager loading.
	//@Scheduled(cron = "0 * * * * *")
	//@Scheduled(fixedRate = 60000)
	@GetMapping("getallproducts")
	public ResponseEntity<List<ProductDTO>> getAll(){
		ResponseEntity<List<ProductDTO>> rs = null;
		//logger.info("I am at ProductController getAll method started.");
		//logger.info("Calling ProductService getAll method from ProductController.");
		List<ProductDTO> d =  ps.getAll();
		//logger.info("Data Received. Sending response to browser.");
		rs = new ResponseEntity<List<ProductDTO>>(d, HttpStatus.OK);
		//logger.info("Response sent to the browser successfully.");
		//logger.info("I am at ProductController getAll method completed.");
		return rs;
	}
}
