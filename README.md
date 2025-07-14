# GATEWAY
Es un gateway que controla el paso del  [microservicio clientes](https://github.com/fsialer/manantial-gateway)

## Tabla de recursos
| NOMBRE                           | RUTA                      | PETICION | PARAMETROS                                             | CUERPO                                                                                                                | 
|----------------------------------|---------------------------|----------|--------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------| 
| Listar clientes                  | /api/v1/customers         | GET      | NINGUNO                                                | NINGUNO                                                                                                               |
| Obtener metricas de los clientes | /api/v1/customers/metrics | GET      | NINGUNO                                                | NINGUNO                                                                                                               |
| Guardar clientes                 | /api/v1/customers    | POST     | NINGUNO                                                | {<br/>"names":"Jhon"<br/>"lastNames":"Doe"<br/>"email":"jhondoe@example.com"<br/>"userId":"cde8c071a420424abf2"<br/>} |

## TECH
1. WebFlux
2. Spring cloud gateway
4. Spring resources
5. Docker
6. Github action