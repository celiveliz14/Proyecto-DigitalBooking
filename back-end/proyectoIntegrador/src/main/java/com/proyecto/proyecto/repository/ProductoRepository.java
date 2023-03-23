package com.proyecto.proyecto.repository;
import com.proyecto.proyecto.exception.ResourceNotFoundException;
import com.proyecto.proyecto.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto,Long> {
    Optional<List<Producto>> findAllByCiudadId(Long id) throws ResourceNotFoundException;
    Optional<List<Producto>> findAllByCategoriaId(Long id) throws ResourceNotFoundException;

    @Query(value = "select * from productos where id not in (select producto_id from reservas where (fecha_inicio < ?1 and fecha_final > ?2) or " +
            "(fecha_inicio between ?1 and ?2) or (fecha_final between ?1 and ?2))", nativeQuery = true)
    List<Producto> findAllByRangeDate(Date check_in_date, Date check_out_date);

    @Query(value = "select P.* from productos P where P.ciudad_id = ?1 and P.id not in (select distinct R.producto_id from reservas R where (R.fecha_final >= ?2 and R.fecha_inicio <= ?3));", nativeQuery = true)
    List<Producto> findAllByRangeDateAndCity(Long city_id, Date check_in_date, Date check_out_date);
}


