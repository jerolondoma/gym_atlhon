package com.athlon.athlon.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.athlon.athlon.models.Factura;

public interface FacturaRepositorie  extends JpaRepository <Factura, Long >{ 
}
