package com.example.demo.dtos;


import jakarta.validation.constraints.*;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ProductRequestDTO {

    @NotBlank(message = "Product name is required")
    @Size(min = 2,  max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    private Double price;

    @Size(max = 500, message = "Description can't exceed 500 characters")
    private String description;

    @NotNull(message = "Quantity is required")
    @Min(value = 0,message = "Quantity can't be negative ")
    private Integer quantity;

    @NotBlank(message = "Category is required")
    private String category;


    public @NotBlank(message = "Product name is required") @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Product name is required") @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters") String name) {
        this.name = name;
    }

    public @NotNull(message = "Price is required") @Positive(message = "Price must be positive") Double getPrice() {
        return price;
    }

    public void setPrice(@NotNull(message = "Price is required") @Positive(message = "Price must be positive") Double price) {
        this.price = price;
    }

    public @Size(max = 500, message = "Description can't exceed 500 characters") String getDescription() {
        return description;
    }

    public void setDescription(@Size(max = 500, message = "Description can't exceed 500 characters") String description) {
        this.description = description;
    }

    public @NotNull(message = "Quantity is required") @Min(value = 0, message = "Quantity can't be negative ") Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(@NotNull(message = "Quantity is required") @Min(value = 0, message = "Quantity can't be negative ") Integer quantity) {
        this.quantity = quantity;
    }

    public @NotBlank(message = "Category is required") String getCategory() {
        return category;
    }

    public void setCategory(@NotBlank(message = "Category is required") String category) {
        this.category = category;
    }


    @Override
    public String toString() {
        return "ProductRequestDTO{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                ", category='" + category + '\'' +
                '}';
    }
}
