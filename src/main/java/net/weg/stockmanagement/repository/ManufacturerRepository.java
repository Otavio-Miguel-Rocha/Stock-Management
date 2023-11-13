package net.weg.stockmanagement.repository;

import net.weg.stockmanagement.model.entity.Manufacturer;
import net.weg.stockmanagement.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {
}
