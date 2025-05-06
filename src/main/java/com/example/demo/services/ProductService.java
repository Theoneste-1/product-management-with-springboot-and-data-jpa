package com.example.demo.services;

import com.example.demo.dtos.ProductRequestDTO;
import com.example.demo.dtos.ProductResponseDTO;
import com.example.demo.entities.Product;
import com.example.demo.repository.ProductRepo;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import jakarta.transaction.Transactional;
import com.example.demo.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {


    @Autowired
    private ProductRepo productRepo;

    private final Logger log = LoggerFactory.getLogger(ProductService.class);

    private final ModelMapper modelMapper;

    public ProductService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;

    }

    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {

        Product product = modelMapper.map(productRequestDTO, Product.class);
        Product savedProduct = productRepo.save(product);
        ProductResponseDTO response = modelMapper.map(savedProduct, ProductResponseDTO.class);
        return response;
    }

    public ProductResponseDTO getProductById(Long id) {
       if(productRepo.existsById(id)) {
           return modelMapper.map(productRepo.findById(id).get(), ProductResponseDTO.class);
       }
        throw new ResourceNotFoundException("Product with id " + id + " not found");
    }

    public List<ProductResponseDTO> getAllProducts() {
        return productRepo.findAll()
                .stream()
                .map(product -> modelMapper.map(product, ProductResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO productRequestDTO) {
        Product existingProduct = productRepo.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Product not found with id "+ id));
        modelMapper.map(productRequestDTO, existingProduct);

        Product updatedProduct = productRepo.save(existingProduct);
        return modelMapper.map(updatedProduct, ProductResponseDTO.class);
    }

    public void deleteProduct(Long id) {
        if(!productRepo.existsById(id)) {
            throw new ResourceNotFoundException("Product not found with id: "+ id);
        }
        productRepo.deleteById(id);


    }


    public List<ProductResponseDTO> getProductsByCategory(String category) {
        return productRepo.findByCategory(category)
                .stream()
                .map((product)-> modelMapper.map(product, ProductResponseDTO.class))
                .collect(Collectors.toList());
    }
}
