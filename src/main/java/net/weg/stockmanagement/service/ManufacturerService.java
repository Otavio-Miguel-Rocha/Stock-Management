package net.weg.stockmanagement.service;

import lombok.AllArgsConstructor;
import net.weg.stockmanagement.model.entity.Manufacturer;
import net.weg.stockmanagement.repository.ManufacturerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ManufacturerService {
    private ManufacturerRepository manufacturerRepository;

    public Manufacturer save(Manufacturer manufacturer) throws Exception {
        try {
            return manufacturerRepository.save(manufacturer);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    public Manufacturer findById(Long id) throws Exception {
        Optional<Manufacturer> product = manufacturerRepository.findById(id);
        if (product.isPresent()) {
            return product.get();
        }
        throw new Exception("The manufacturer with id " + id + " doesn't exist!");
    }

    public List<Manufacturer> findAll() {
        return manufacturerRepository.findAll();
    }

    public void delete(Long id) throws Exception {
        try {
            findById(id);
            manufacturerRepository.deleteById(id);
        } catch (Exception e) {
            throw new Exception(e);
        }

    }


}
