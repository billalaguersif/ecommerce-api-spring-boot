package fr.billal.ecommerceapi.service;

import fr.billal.ecommerceapi.repository.ProductRepository;
import fr.billal.ecommerceapi.model.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    // récupere tous les produits
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
    //récupérer un produit par son id
    public Optional<Product> getProductById(Long id){
        return productRepository.findById(id);
    }
    //creer ou enregistre un produit
    public Product createProduct(Product product){
        return productRepository.save(product);
    }
    // mettre a jour un produit existant
    public  Product updateProduct(Long id, Product updatedProduct){
        return productRepository.findById(id).map(existingProduct ->{
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setDescription(updatedProduct.getDescription());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setStockQuantity(updatedProduct.getStockQuantity());
            return productRepository.save(existingProduct);
        }).orElseThrow(()-> new RuntimeException("product not found with id "+id));
    }
    //supprimer un produit par son id
    public  void deleteProduct(Long id){
        productRepository.deleteById(id);
    }

}
