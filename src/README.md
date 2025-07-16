# Proyecto API REST PRESUPUESTO
## Clase Abstracta
    Clase Abstracta OperacionInterfaz
        Clases Derivadas :  Ingreso - Gasto - Capital
        Atributos:
        - Id
        - fechaOperacion
        - fechaUpdate
        - concepto
        - monto
        - denominacion
        - tipoOperacionPersonalizada
        Cada atributo tiene sus metodos GET ; SET ;

## EndPoint de las Clases
### Clase Gasto
    /gasto
    CRUD
    - /gasto/create -> Para crear nuevos gastos
    - /gsato/get/{id} -> Obtener un registro por id
    - /gasto/delete/{id} -> Eliminar un registro por id
    SERVICIOS
    - /gasto/total -> Total gasto segun rango
    - /gasto/promedio -> Promedio gasto segun rango
    - /gasto/mas -> Obtener gasto maximo segun rango
    - /gasto/minimo -> Obtener gasto minimo segun rango
### Clase Ingreso
    /gasto
    CRUD
    - /ingreso/create -> Para crear nuevos ingreso
    - /ingreso/get/{id} -> Obtener un registro por id
    - /ingreso/delete/{id} -> Eliminar un registro por id
    SERVICIOS
    - /ingreso/total -> Total ingreso segun rango
    - /ingreso/promedio -> Promedio ingreso segun rango
    - /ingreso/mas -> Obtener ingreso maximo segun rango
    - /ingreso/minimo -> Obtener ingreso minimo segun rango

## Contenedor DOCKER
    Contenedor Docker MYSQL 8.0
    - nombre del contenedor: presupuesto-db
    - Variables de entorno de la base de datos:
        # MYSQL_ROOT_PASSWORD: rootpassword
        # MYSQL_DATABASE: mydatabase
        # MYSQL_USER: myuser
        # MYSQL_PASSWORD: secret
    - Puerto 54205:3306
    Volumen de Persistencia de datos
    - mysql_data:/var/lib/mysql
    Conexion
    networks:
      - presupuesto-net