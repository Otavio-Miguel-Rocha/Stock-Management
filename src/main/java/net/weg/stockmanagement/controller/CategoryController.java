package net.weg.stockmanagement.controller;

import net.weg.stockmanagement.model.entity.Category;
import net.weg.stockmanagement.model.entity.Product;
import net.weg.stockmanagement.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id){
        try{
            return new ResponseEntity<>(categoryService.findById(id), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping
    public ResponseEntity<List<Category>> findAll(){
        try{
            return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping
    public ResponseEntity<?> save(@RequestBody Category category){
        try{
            return new ResponseEntity<>(categoryService.save(category), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }



    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@RequestBody Category category, @PathVariable Long id){
        try{
            category.setId(categoryService.findById(id).getId());
            return new ResponseEntity<>(categoryService.save(category), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try{
            categoryService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
