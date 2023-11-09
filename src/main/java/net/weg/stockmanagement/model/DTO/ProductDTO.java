package net.weg.stockmanagement.model.DTO;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Data
@AllArgsConstructor
public class ProductDTO {
    private String name;
    private Double price;
    private Integer stock;
    private Date endDate;
    private String description;
    private Long codeBar;
    private Double weight;
    private Double width;
    private String manufacturer;
    private String category;
}
