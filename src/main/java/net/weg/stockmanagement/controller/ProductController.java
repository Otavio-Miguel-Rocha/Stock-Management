package net.weg.stockmanagement.controller;

import net.weg.stockmanagement.model.DTO.ProductDTO;
import net.weg.stockmanagement.model.entity.Product;
import net.weg.stockmanagement.model.exceptions.ProductAlreadyExistException;
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
    public ResponseEntity<Product> findById(@PathVariable Long id){
        try{
            return new ResponseEntity<>(productService.findById(id), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
    public ResponseEntity<?> save(@RequestBody ProductDTO productDTO){
        try{
            return new ResponseEntity<>(productService.save(productDTO), HttpStatus.OK);
        } catch (ProductAlreadyExistException e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }



    @PutMapping
    public ResponseEntity<?> edit(@RequestBody ProductDTO productDTO){
        try{
            return new ResponseEntity<>(productService.edit(productDTO), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> delete(@PathVariable Long id){
        try{
            productService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
