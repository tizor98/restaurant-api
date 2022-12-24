package com.local.project.persistence.crud;

import com.local.project.persistence.entity.Producto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> {

   // @Query(value = "SELECT * FROM productos WHERE id_categoria = ? ORDER BY nombre", nativeQuery = true)
   // Above example implements a native query, letting us naming the below method as we pleased. But it's better to use query methods
   List<Producto> findByIdCategoriaOrderByNombre(int idCategoria); // naming with query methods

   Optional<List<Producto>> findByCantidadStockLessThanAndEstado(int cantidadStock, boolean estado);

}