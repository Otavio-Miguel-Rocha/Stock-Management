package net.weg.stockmanagement.service;

import lombok.AllArgsConstructor;
import net.weg.stockmanagement.model.entity.Product;
import net.weg.stockmanagement.model.exceptions.ProductAlreadyExistException;
import net.weg.stockmanagement.model.exceptions.ProductCodeBarDoesntExistException;
import net.weg.stockmanagement.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepository productRepository;

    public Product save(Product product) throws ProductAlreadyExistException{
        try {
            findByCodeBar(product.getCodeBar());
            throw new ProductAlreadyExistException();
        } catch (ProductCodeBarDoesntExistException e) {
            return productRepository.save(product);
        }
    }

    public Product edit(Product product) throws ProductCodeBarDoesntExistException {
        try {
            findByCodeBar(product.getCodeBar());
            return productRepository.save(product);
        } catch (ProductCodeBarDoesntExistException e) {
            throw new ProductCodeBarDoesntExistException(product.getCodeBar());
        }
    }


    public void findByCodeBar(Long codeBar) throws ProductCodeBarDoesntExistException {
        if(productRepository.findByCodeBar(codeBar) == null){
            throw new ProductCodeBarDoesntExistException(codeBar);
        }
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

    public void delete(Long codeBar) throws ProductCodeBarDoesntExistException {
        try{
            findByCodeBar(codeBar);
            productRepository.deleteByCodeBar(codeBar);
        } catch (ProductCodeBarDoesntExistException e){
            throw new ProductCodeBarDoesntExistException(codeBar);
        }
    }

}
