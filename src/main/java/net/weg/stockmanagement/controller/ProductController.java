package net.weg.stockmanagement.controller;

import net.weg.stockmanagement.model.entity.Product;
import net.weg.stockmanagement.model.exceptions.ProductAlreadyExistException;
import net.weg.stockmanagement.model.exceptions.ProductCodeBarDoesntExistException;
import net.weg.stockmanagement.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;


    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        try{
            return new ResponseEntity<>(productService.findById(id), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping
    public ResponseEntity<List<Product>> findAll(){
        try{
            return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping
    public ResponseEntity<?> save(@RequestBody Product product){
        try{
            return new ResponseEntity<>(productService.save(product), HttpStatus.OK);
        } catch (ProductAlreadyExistException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }



    @PutMapping
    public ResponseEntity<?> edit(@RequestBody Product product){
        try{
            return new ResponseEntity<>(productService.edit(product), HttpStatus.OK);
        } catch (ProductCodeBarDoesntExistException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{codeBar}")
    public ResponseEntity<?> delete(@PathVariable Long codeBar){
        try{
            productService.delete(codeBar);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
