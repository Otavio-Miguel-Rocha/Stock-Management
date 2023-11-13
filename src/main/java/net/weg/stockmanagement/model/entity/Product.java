package net.weg.stockmanagement.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "The name of Product cannot be null")
    @NotEmpty(message = "The name of Product cannot be empty")
    private String name;

    @Column(nullable = false)

    @Positive(message = "The price of Product cannot be lower or equals than zero")
    private Double price;

    @Min(value = 0, message = "The stock of Product cannot be lower than zero")
    private Integer stock;

    @NotNull(message = "The end date of Product cannot be empty")
    private Date endDate;

    @NotNull(message = "The description of Product cannot be null")
    @NotEmpty(message = "The description of Product cannot be empty")
    private String description;

    @NotNull(message = "The code bar of Product cannot be null")
    @Column(unique = true, updatable = false)
    private Long codeBar;

    @NotNull(message = "The weight of Product cannot be null")
    @Min(value = 0, message = "The weight of Product cannot be lower than zero")
    private Double weight;

    @NotNull(message = "The width of Product cannot be null")
    @Min(value = 0, message = "The width of Product cannot be lower than zero")
    private Double width;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Manufacturer manufacturer;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Category category;
}
