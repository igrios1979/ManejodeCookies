package org.ignaciorios.apiservlet.webapp.headers.service;

import org.ignaciorios.apiservlet.webapp.headers.models.Producto;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ProductoServiceImpl implements ProductoService{
    @Override
    public List<Producto> listar() {
        return Arrays.asList(new Producto(1L,"Laptop","computacion",5000),
                             new Producto(2L,"Mouse","computacion",650),
                             new Producto(3L,"Sabanas","Linea blanca",985)

        );


    }

    @Override
    public Optional<Producto> buscarProducto(String nombre) {
      if(nombre == null || nombre.isEmpty()) {
          return Optional.empty();
      }
      return listar().stream().filter(p->p.getNombre().contains(nombre)).findFirst();
      }




}
