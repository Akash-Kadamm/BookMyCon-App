package com.bookmycon.service;

import com.bookmycon.dto.StockDTO;
import com.bookmycon.model.Product;
import com.bookmycon.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    Logger logger=Logger.getLogger(ProductService.class);

    /*
     * Retrieve All products.
     *
     * @param
     * @return List of Products
     *
     * */
    @Cacheable(value = "products")
    public List<Product> getAllProducts(){
        logger.info("Getting all products");
        return productRepository.findAll();
    }


    /*
     * Add new Product.
     *
     * @param Product object
     * @return Product object
     *
     * */
    @CacheEvict(value = "products",allEntries = true)
    public Product addProduct(Product product){
        logger.info("Adding product with details: name={}" + product.getProductName() + " price={}" + product.getProductPrice());
        return productRepository.save(product);
    }


    /*
     * Update product.
     *
     * @param Product object
     * @return Product object
     *
     * */
    @CacheEvict(value = "products",allEntries = true)
    public void updateProduct(Product product ){
        logger.info("Updating product with id: {}" + product.getProductId());
         int productId=product.getProductId();
        Product savedProduct=productRepository.findById(productId).get();
        savedProduct.setProductName(product.getProductName());
        productRepository.save(savedProduct);

        
    }

    /*
     * Delete product.
     *
     * @param int productId
     * @return String
     *
     * */
    @CacheEvict(value = "products",allEntries = true)
    public String deleteProduct(int productId){
        productRepository.deleteById(productId);
        logger.info("Deleting product with id: {}" + productId);
        return  "Product is deleted..";
    }


    /*
    * Update Stock of product
    *
    * @param StockDTO object
    * @return String message
    * */
    public String addStock(StockDTO stockDTO){
        Product savedProduct=productRepository.findById(stockDTO.getProductId()).get();
        savedProduct.setProductAvailableQTY(stockDTO.getStockValue());
        Product updatedStockProduct=productRepository.save(savedProduct);
        logger.info("Adding stock for product with id: {}" + stockDTO.getProductId());
        return "Stock is Updated For product : "+savedProduct;
    }
}
