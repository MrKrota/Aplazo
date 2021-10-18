package com.caldelas.retoaplazo;

import com.caldelas.retoaplazo.entity.Pagos;
import org.springframework.data.repository.CrudRepository;

public interface PagosRepository extends CrudRepository<Pagos, Integer> {
    Pagos findPagosById(Integer id);
}
