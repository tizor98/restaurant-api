package com.local.project.persistence;

import com.local.project.domain.Product;
import com.local.project.domain.repository.ProductRepository;
import com.local.project.persistence.crud.ProductoCrudRepository;
import com.local.project.persistence.entity.Producto;
import com.local.project.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository // We tell Spring that this class has interaction with db
public class ProductoRepository implements ProductRepository {

   @Autowired
   private ProductoCrudRepository productoCrudRepository;
   @Autowired
   private ProductMapper mapper;

   @Override
   public List<Product> getAll() {
      List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();
      return mapper.toProducts(productos);
   }

   @Override
   public Optional<List<Product>> getByCategory(int categoryId) {
      List<Producto> productos = productoCrudRepository.findByIdCategoriaOrderByNombre(categoryId);
      return Optional.of(mapper.toProducts(productos));
   }

   @Override
   public Optional<List<Product>> getScareProducts(int quantity) {
      Optional<List<Producto>> productos = productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity, true);
      return productos.map( prods -> mapper.toProducts(prods));
   }

   @Override
   public Optional<Product> getProduct(int productId) {
      return productoCrudRepository.findById(productId).map( prod -> mapper.toProduct(prod));
   }

   @Override
   public Product save(Product product) {
      return mapper.toProduct(productoCrudRepository.save(mapper.toProducto(product)));
   }

   @Override
   public void delete(int productId) {
      productoCrudRepository.deleteById(productId);
   }

}
