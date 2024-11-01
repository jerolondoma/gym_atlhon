# Gimnasio Athlon backend
* Crear la base de datos y unilar con el backend 
* Tendremos unos modulos que contendran las entidades, repositorio, controloradores en la API 
RES 
-Hacemos la conexion con la base de datos entre MYSQL y el backend dentro de la carpeta resources 
y en el archivo application.properties con las siguientes lineas de codigo

``` MYSQL 
spring.application.name=Gimnasio_Athlon
spring.datasource.url=jdbc:mysql://localhost:3306/Gimnasio_Athlon?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=password 
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
spring.jpa.hibernate.ddl-auto=update
```
*   **Modulo : Entidades**
------
    ## Entidad Login
``` JAVA 
 . Usaremos las siguientes anotaciones 
    @Entity
    @Table (name = "Login")
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
 . Con los siguientes atributos para la entidad login
    public class Login {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long usuarioID;
        
        private String nombreusuario;
        private String password;
        private String rol;
    }

```
------
    ## Entidad Cliente
``` JAVA 
 . Usaremos las siguientes anotaciones 
    @Entity
    @Table (name = "Cliente")
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
. Con los siguientes atributos para la entidad login    
    public class Cliente {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long clienteID;

        private String nombre;
        private String apellido;
        private String email;
        private String fecharegistro;
        private String fechavencimiento;
    }
```
------
    ## Entidad Planes
``` JAVA 
 . Usaremos las siguientes anotaciones 
    @Entity
    @Table (name = "Planes")
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
. Con los siguientes atributos para la entidad login    
    public class Planes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long planID;

    private String nombreplan;
    private String duracion;
    private String descripcion;
    private String precio;
    }
```
------
    ## Entidad Factura
``` JAVA 
 . Usaremos las siguientes anotaciones 
    @Entity
    @Table(name = "Factura")
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
. Con los siguientes atributos para la entidad login    
    public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long facturaID;
    
    private String fechaF;
    private String precioF;

    @ManyToOne
    private Cliente clienteID;
    
    @ManyToOne
    private Planes planID;
    }
```
*   **Modulo : Controlador**
------
    ## Controlador de login
```JAVA
. Usaremos las siguientes anotaciones 
    @RestController
    @RequestMapping("/api/logins")
    public class LoginController {

        @Autowired
        private LoginRepositorie loginRepositorie;
        
        /*Obtener todos los logins */
        @GetMapping
        public List <Login> obtener_todos_logins() {
            return loginRepositorie.findAll();
        }

        /*Obtener logins por medio de la ID */
        @GetMapping ("/{usuarioID}")
        public Login obtener_login_por_ID(@PathVariable("usuarioID") Long usuarioID ) {
            return loginRepositorie.findById(usuarioID).orElse(null);
        }

        /*Crear un login  */
        @PostMapping()
        public Login crear_login(@RequestBody Login  login) {
            return loginRepositorie.save(login);
        }

        /*Actualizar login */
        @PutMapping("/{usuarioID}")
        public Login actualizar_login(@PathVariable ("usuarioID") Long usuarioID, @RequestBody Login  login) {
            login.setUsuarioID(usuarioID);
            return loginRepositorie.save(login);  
        }
        
        /*Eliminar Login */
        @DeleteMapping("/{usuarioID}")
        public void eliminar_login(@PathVariable("usuarioID") Long login) {
            loginRepositorie.deleteById(login);
        }
    }
```
------
    ## Controlador de cliente
``` JAVA
. Usaremos las siguientes anotaciones 
    @RestController
    @RequestMapping("/api/clientes")
    public class ClienteController {
        
        @Autowired
        private ClienteRepositorie clienteRepositorie;

        /*Obtener todos los clientes */
        @GetMapping
        public List <Cliente> obtener_todos_clientes() {
            return clienteRepositorie.findAll();
        }
        
        /*Obtener por medio de una id */
        @GetMapping("/{clienteID}")
        public Cliente obtener_cliente_id(@PathVariable ("clienteID") Long clienteID) {
            return clienteRepositorie.findById(clienteID).orElse(null);
        }

        /*Crear un cliente */
        @PostMapping()
        public Cliente crear_cliente(@RequestBody Cliente cliente) {
            return clienteRepositorie.save(cliente);
        }
        
        /*Actualizar cliente*/
        @PutMapping("/{clienteID}")
        public Cliente actualizar_cliente(@PathVariable ("clienteID") Long clienteID, @RequestBody Cliente cliente) {
            cliente.setClienteID(clienteID);
            return clienteRepositorie.save(cliente);
        }

        /* Eliminar cliente */
        @DeleteMapping("/{clienteID}")
        public void eliminar_cliente(@PathVariable ("clienteID") Long clienteID) {
            clienteRepositorie.deleteById(clienteID);
        }
    }
 ```
------
    ## Controlador de planes
``` JAVA
. Usaremos las siguientes anotaciones 
    @RestController
    @RequestMapping("/api/planes")
    public class PlanesController {

        @Autowired
        private PlanesRepositorie planesrepositorie;

        /*Obtener todos los planes */
        @GetMapping
        public List <Planes> obtener_todos_planes() {
            return planesrepositorie.findAll();
        }

        /*Obtener planes por medio de la ID */
        @GetMapping ("/{planID}")
        public Planes obtener_planes_id(@PathVariable("planID") Long planID) {
            return planesrepositorie.findById(planID).orElse(null);
        }

        /*Crear un plan  */
        @PostMapping()
        public Planes crear_plan(@RequestBody Planes planes) {
            return planesrepositorie.save(planes);
        }
        
        /*Actualizar planes */
        @PutMapping("/{planID}")
        public Planes actualizar_plan(@PathVariable ("planID") Long planID, @RequestBody Planes planes) {
            planes.setPlanID(planID);
            return planesrepositorie.save(planes);  
        }

        /*Eliminar plan*/
        @DeleteMapping("/{planID}")
        public void eliminar_plan(@PathVariable ("planID") Long planID) { 
            planesrepositorie.deleteById(planID);
        }
    }
 ```
------
    ## Controlador de factura
``` JAVA
. Usaremos las siguientes anotaciones 
    @RestController
    @RequestMapping("/api/factura")
    public class FacturaController {

        @Autowired
        private FacturaRepositorie facturaRepositorie;

        /*Obtener todos las facturas */
        @GetMapping
        public List <Factura> obtener_todas_facturas() {
            return facturaRepositorie.findAll();
        }

        /*Obtener facturas por medio de la ID */
        @GetMapping ("/{facturaID}")
        public Factura obtener_factura_id(@PathVariable("facturaID") Long facturaID) {
            return facturaRepositorie.findById(facturaID).orElse(null);
        }

        /*Crear una factura  */
        @PostMapping()
        public Factura crear_factura(@RequestBody Factura factura) {
            return facturaRepositorie.save(factura);
        }

        /*Actualizar facturas */
        @PutMapping("/{facturaID}")
        public Factura actualizar_factura(@PathVariable ("facturaID") Long facturaID, @RequestBody Factura factura) {
            factura.setFacturaID(facturaID);
            return facturaRepositorie.save(factura);  
        }
        
        /*Eliminar plan*/
        @DeleteMapping("/{facturaID}")
        public void eliminar_factura(@PathVariable ("facturaID") Long facturaID) { 
            facturaRepositorie.deleteById(facturaID);
        }
    }
 ```

*   **Modulo : Repositorio**
------
    ## Repositorio Login
``` JAVA
. Tendremos un repositorio que sera base entre el controlador, el modelo y
la informacion que utilicemos 
    public interface LoginRepositorie  extends JpaRepository <Login, Long> {
    }

 ```
------
    ## Repositorio cliente
``` JAVA
    public interface ClienteRepositorie  extends JpaRepository <Cliente, Long> {
    }
 ```
------
    ## Repositorio planes
``` JAVA
    public interface PlanesRepositorie  extends JpaRepository <Planes, Long>{
    }
 ```
------
    ## Repositorio factura
``` JAVA
    public interface FacturaRepositorie  extends JpaRepository <Factura, Long >{ 
    }
 ```
*   **Modulo : Config**
------
    ## corsConfig
``` JAVA
. es el uso compartido de recursos entre origenes 
    @Configuration
    @EnableWebMvc
    public class corsConfig  implements WebMvcConfigurer{
        
        @Override
        public void addCorsMappings(CorsRegistry registry){
            registry.addMapping("/*")
            .allowedOrigins("*")//Escribir la url del proyecto front a conectar
            .allowedMethods("*")//podemos especificar los metodos a permitir desfde el front
            .allowCredentials(true);//para que permita las credenciales
        }
    }
```


