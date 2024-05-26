package com.orm.serviceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orm.Dto.ProductDTO;
import com.orm.Entity.ProductPojo;
import com.orm.Repo.ProductRepo;
import com.orm.Service.ProductService;
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepo pr;
	
	@Autowired
	private ModelMapper mm;
	
	private final static Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	@Override
	public ProductDTO saveProduct(ProductDTO p) {
		logger.info("I am at ProductServiceImpl saveProduct method started. "+p);
		ProductPojo pp = mm.map(p, ProductPojo.class);
		logger.info(pp.toString());
		pr.save(pp);
		logger.info("I am at ProductServiceImpl saveProduct method completed. "+p);
		return p;
	}

	@Override
	public ProductDTO updateProduct(ProductDTO p) {
		logger.info("I am at ProductServiceImpl updateProduct method started. "+p);
		ProductPojo pp = mm.map(p, ProductPojo.class);
		pr.save(pp);
		logger.info("I am at ProductServiceImpl updateProduct method completed. "+p);
		return p;
	}

	@Override
	public Object deleteProduct(Integer pid) {
		logger.info("I am at ProductServiceImpl deleteProduct method started. "+pid);
		pr.deleteById(pid);
		logger.info("I am at ProductServiceImpl deleteProduct method completed. "+pid);
		return pid;
	}

	@Override
	public ProductDTO getByProductId(Integer pid) {
		logger.info("I am at ProductServiceImpl getbyproductid method started. ");
		Optional<ProductPojo> k = pr.findById(pid);
		ProductPojo d = k.get();
		ProductDTO pd = mm.map(d, ProductDTO.class);
		logger.info(pd.toString());
		logger.info("I am at ProductServiceImpl getbyproductid method completed. ");
		return pd;
	}

	@Override
	public List<ProductDTO> getByProductName(String pname) {
		logger.info("I am at ProductServiceImpl getByProductName method started. ");
		List<ProductPojo> k = pr.getByPname(pname);
		ProductDTO[] d = mm.map(k, ProductDTO[].class);
		List<ProductDTO> u = Arrays.asList(d);
		for(ProductDTO t : u) {
			logger.info(t.toString());
		}
		logger.info("I am at ProductServiceImpl getByProductName method completed. ");	
		return u;
	}

	@Override
	public List<ProductDTO> getAll() {
		logger.info("I am at ProductServiceImpl getAll method started. ");
		List<ProductPojo> k = pr.findAll();
		ProductDTO[] d = mm.map(k, ProductDTO[].class);
		List<ProductDTO> y = Arrays.asList(d);
		for(ProductDTO t : y) {
			logger.info(t.toString());
		}
		logger.info("I am at ProductServiceImpl getAll method completed. ");
		return y;
	}
}
