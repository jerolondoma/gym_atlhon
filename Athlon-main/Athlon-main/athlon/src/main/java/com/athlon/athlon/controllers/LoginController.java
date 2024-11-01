package com.athlon.athlon.controllers;

import java.util.List;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.RestController;
import com.athlon.athlon.models.Login;
import com.athlon.athlon.repositories.LoginRepositorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
@RequestMapping("/api/logins")
public class LoginController {

    @Autowired
    private LoginRepositorie loginRepositorie;

    /* Obtener todos los logins */
    @GetMapping
    public List<Login> obtenerTodosLogins() {
        return loginRepositorie.findAll();
    }

    /* Obtener login por medio de la ID */
    @GetMapping("/{usuarioID}")
    public Login obtenerLoginPorID(@PathVariable("usuarioID") Long usuarioID) {
        return loginRepositorie.findById(usuarioID).orElse(null);
    }

    /* Crear un login */
    @PostMapping()
    public Login crearLogin(@RequestBody Login login) {
        return loginRepositorie.save(login);
    }

    /* Actualizar login */
    @PutMapping("/{usuarioID}")
    public Login actualizarLogin(@PathVariable("usuarioID") Long usuarioID, @RequestBody Login login) {
        login.setUsuarioID(usuarioID);
        return loginRepositorie.save(login);
    }

    /* Eliminar Login */
    @DeleteMapping("/{usuarioID}")
    public void eliminarLogin(@PathVariable("usuarioID") Long usuarioID) {
        loginRepositorie.deleteById(usuarioID);
    }

    /* Validar login para inicio de sesión */
    @PostMapping("/validate")
    @ResponseBody
    public Map<String, Object> validarLogin(@RequestBody Map<String, String> loginData) {
        String nombreUsuario = loginData.get("nombreusuario");
        String password = loginData.get("password");

        Login login = loginRepositorie.findByNombreusuario(nombreUsuario);
        Map<String, Object> response = new HashMap<>();

        if (login != null && login.getPassword().equals(password)) {
            response.put("success", true);
            response.put("message", "Login exitoso");
        } else {
            response.put("success", false);
            response.put("message", "Credenciales incorrectas");
        }

        return response;
    }
}
