package com.orm.Service;

import java.util.List;

import com.orm.Dto.ProductDTO;

public interface ProductService {

	public abstract ProductDTO saveProduct(ProductDTO p);
	public abstract ProductDTO updateProduct(ProductDTO p);
	public abstract Object deleteProduct(Integer pid);
	public abstract ProductDTO getByProductId(Integer pid);
	public abstract List<ProductDTO> getByProductName(String pname);
	public abstract List<ProductDTO> getAll();
}
