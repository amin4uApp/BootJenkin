package com.tcs.web.ProductService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tcs.web.model.Product;
import com.tcs.web.repo.ProductRepository;
 
	 
	@Service
	@Transactional
	public class ProductService {
	 
	    @Autowired
	    private ProductRepository repo;
	     
	    public List<Product> listAll() {
	        return repo.findAll();
	    }
	     
	    public void save(Product product) {
	        repo.save(product);
	    }
	     
	    public Product get(long id) {
	        return repo.findById(id).get();
	    }
	     
	    public void delete(long id) {
	        repo.deleteById(id);
	    }
	}


