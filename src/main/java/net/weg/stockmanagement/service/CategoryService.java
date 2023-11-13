package net.weg.stockmanagement.service;

import lombok.AllArgsConstructor;
import net.weg.stockmanagement.model.entity.Category;
import net.weg.stockmanagement.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService {
    private CategoryRepository categoryRepository;

    public Category save(Category category) throws Exception {
        try {
            return categoryRepository.save(category);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    public Category findById(Long id) {
        Optional<Category> product = categoryRepository.findById(id);
        if (product.isPresent()) {
            return product.get();
        }
        throw new RuntimeException("The category with id " + id + " doesn't exist!");
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public void delete(Long id) throws Exception {
        try {
            findById(id);
            categoryRepository.deleteById(id);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }


}
