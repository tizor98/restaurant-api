package com.local.project.persistence;

import com.local.project.domain.Purchase;
import com.local.project.domain.repository.PurchaseRepository;
import com.local.project.persistence.crud.CompraCrudRepository;
import com.local.project.persistence.entity.Compra;
import com.local.project.persistence.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompraRepository implements PurchaseRepository {

   @Autowired
   private CompraCrudRepository compraCrudRepository;
   @Autowired
   private PurchaseMapper mapper;


   @Override
   public List<Purchase> getAll() {
      List<Compra> compras = (List<Compra>) compraCrudRepository.findAll();
      return mapper.toPurchases(compras);
   }

   @Override
   public Optional<List<Purchase>> getByClient(String clientId) {
      return compraCrudRepository.findByIdCliente(clientId).map( compras -> mapper.toPurchases(compras));
   }

   @Override
   public Purchase save(Purchase purchase) {
      Compra compra = mapper.toCompra(purchase);
      compra.getProductos().forEach(prod -> prod.setCompra(compra));
      return mapper.toPurchase(compraCrudRepository.save(compra));
   }
}
