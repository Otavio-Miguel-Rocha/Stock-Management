package net.weg.stockmanagement.service;

import lombok.AllArgsConstructor;
import net.weg.stockmanagement.model.DTO.ProductDTO;
import net.weg.stockmanagement.model.entity.Product;
import net.weg.stockmanagement.model.exceptions.ProductAlreadyExistException;
import net.weg.stockmanagement.model.exceptions.ProductCodeBarDoesntExistException;
import net.weg.stockmanagement.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepository productRepository;

    public Product save(ProductDTO productDTO) throws ProductAlreadyExistException{
        try {
            genericValidation(productDTO);
            Product product = new Product();
            BeanUtils.copyProperties(productDTO, product);
            return productRepository.save(product);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Product edit(ProductDTO productDTO){
        try {
            genericValidation(productDTO);
            Product product = findByCodeBar(productDTO.getCodeBar());
            BeanUtils.copyProperties(productDTO, product);
            return productRepository.save(product);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Product findByCodeBar(Long codeBar) throws ProductCodeBarDoesntExistException {
        Product product = productRepository.findByCodeBar(codeBar);
        if(product != null){
            return product;
        }
        throw new ProductCodeBarDoesntExistException(codeBar);
    }


    public Product findById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return product.get();
        }
        throw new RuntimeException("The product with id " + id + " doesn't exist!");
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    private void genericValidation(ProductDTO productDTO){
        try {
            productRepository.findByCodeBar(productDTO.getCodeBar());
            throw new ProductAlreadyExistException();
        } catch (Exception ignored) {}

        if (productDTO.getPrice() <= 0) {
            throw new RuntimeException("The price cannot be lower or equals to zero");
        }
        if (productDTO.getStock() < 0) {
            throw new RuntimeException("The stock cannot be negative");
        }
        if(productDTO.getDescription().equals("")){
            throw new RuntimeException("The description cannot be empty");
        }
        if(productDTO.getEndDate() == null){
            throw new RuntimeException("The end date cannot be empty");
        }
        if(productDTO.getName().equals("")){
            throw new RuntimeException("The product name cannot be empty");
        }
        if(productDTO.getWeight() <= 0){
            throw new RuntimeException("The product weight cannot be zero or lower than zero");
        }
        if(productDTO.getWidth() <= 0){
            throw new RuntimeException("The product width cannot be empty");
        }
        if(productDTO.getManufacturer().equals("")){
            throw new RuntimeException("The manufacturer cannot be empty");
        }
        if(productDTO.getCategory().equals("")){
            throw new RuntimeException("The category cannot be empty");
        }
    }

}
