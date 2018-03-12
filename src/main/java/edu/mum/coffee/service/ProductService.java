package edu.mum.coffee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.coffee.domain.Product;
import edu.mum.coffee.domain.ProductType;
import edu.mum.coffee.repository.ProductRepository;

@Service
@Transactional
public class ProductService   {
	
	@Autowired
	private ProductRepository productRepository;

	@Secured("hasAuthority('ROLE_ADMIN')")
	public Product save(Product product) {
		return productRepository.save(product);
	}

	@Secured("hasAuthority('ROLE_ADMIN')")
	public void delete(Product product) {
		productRepository.delete(product);
	}

	@Secured("isAnonymous()")
	public Product getProduct(int productId) {
		return  productRepository.findOne(productId);
	}

	@Secured("isAnonymous()")
	public List<Product> getAllProduct() {
		return  productRepository.findAll() ;
	}

	@Secured("isAnonymous()")
	public List<Product> findByTextSearch(String criteria) {
		if (!criteria.contains("%")) {
			criteria = "%"+criteria+"%";
		}
		return productRepository.findByProductNameLikeOrDescriptionLikeAllIgnoreCase(criteria, criteria);
	}

	@Secured("isAnonymous()")
	public List<Product> findByPrice(double minPrice, double maxPrice) {
		return  productRepository.findByPriceBetween(minPrice, maxPrice);
	}

	@Secured("isAnonymous()")
	public List<Product> findByProductType(ProductType productType) {
		 return productRepository.findByProductType(productType);
	}
	
}
