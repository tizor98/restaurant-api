package com.local.project.persistence.entity;

import javax.persistence.*;

@Entity
@Table(name = "compras_productos")
public class CompraProducto {

   @EmbeddedId
   private CompraProductoPK id;
   private Integer cantidad;
   private Double total;
   private Boolean estado;

   @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
   @MapsId("idCompra") // Do not touch. It is necessary to specify {id.} notation due to the present of a relationship with Compra.class
   @JoinColumn(name = "id_compra", referencedColumnName = "id_compra", insertable = false, updatable = false)
   private Compra compra;

   @ManyToOne(fetch = FetchType.EAGER)
   @MapsId("id.idProducto") // Do not touch. It is necessary to specify {id.} notation due to a lack of relation modeling with Producto.class
   @JoinColumn(name = "id_producto", referencedColumnName = "id_producto", insertable = false, updatable = false)
   private Producto producto;

   public CompraProductoPK getId() {
      return id;
   }

   public void setId(CompraProductoPK id) {
      this.id = id;
   }

   public Integer getCantidad() {
      return cantidad;
   }

   public void setCantidad(Integer cantidad) {
      this.cantidad = cantidad;
   }

   public Double getTotal() {
      return total;
   }

   public void setTotal(Double total) {
      this.total = total;
   }

   public Boolean getEstado() {
      return estado;
   }

   public void setEstado(Boolean estado) {
      this.estado = estado;
   }

   public Compra getCompra() {
      return compra;
   }

   public void setCompra(Compra compra) {
      this.compra = compra;
   }

   public Producto getProducto() {
      return producto;
   }

   public void setProducto(Producto producto) {
      this.producto = producto;
   }
}
