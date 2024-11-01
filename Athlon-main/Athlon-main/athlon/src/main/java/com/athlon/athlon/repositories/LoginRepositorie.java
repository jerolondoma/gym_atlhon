package com.athlon.athlon.repositories;

import com.athlon.athlon.models.Login;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepositorie extends JpaRepository<Login, Long> {
    Login findByNombreusuario(String nombreusuario);
}
