# API-CLIMA
Weather Hourly Query and Query History Service

Este proyecto consiste en una aplicación web que permite consultar el clima por hora en una ciudad específica. Además, registra todas las consultas realizadas en una base de datos para su posterior visualización.

## Características

- **Consulta de clima por hora**: Los usuarios pueden consultar el clima actual por hora en una ciudad ingresando el codigo de ciudad de la misma (Ver api Accuweather).
- **Registro en la base de datos**: Cada vez que se realiza una consulta del clima, se guarda la consulta realizada en una base de datos para su posterior revisión.
- **Visualización del historial de consultas**: Se proporciona un servicio separado para ver todas las consultas realizadas.

## Tecnologías utilizadas

- **Java 8**: Se hace uso de la programacion funcional.
- **Spring Boot**: Se utiliza para el backend y la gestión de las consultas de clima.
- **Junit y Mockito**: Se utiliza para realizar pruebas unitarias.
- **Base de datos relacional (H2)**: Se usa para almacenar el historial de consultas.
- **API de clima (AccuWeather)**: Se integra para obtener datos meteorológicos.
- **Lombok**: Se utiliza para la generacion de metodos recurrentes
- **Swagger**: Se documenta la Api en el siguiente link: http://localhost:8080/swagger-ui/index.html

## Uso

Para usar la aplicación, sigue estos pasos:

1. **Ejecución del proyecto**:
   - Clonar este repositorio.
   - Configurar la variables de entorno .
     ![Intellij](https://github.com/Arias-unlam/api-clima/blob/main/docs/img.png)
     ![Intellij](https://github.com/Arias-unlam/api-clima/blob/main/docs/img_1.png)
   - Ejecuta el comando de maven: mvn clean install.
   - Ejecuta el proyecto, fijate de que esten bien puestas las variables de entorno
   - En la carpeta docs/postman-collection puedes importar la coleccion para realizar pruebas
2. **Consulta del clima por hora**:
   - Accede a Accuweather y prueba buscar codigos de ciudad
   - Ejecuta la coleccion de postman, por defecto viene un codigodeciudad de jujuy: "4337", si no consigues codigos distintos, puede probar en aumentar ese codigo en forma ascendente o descendete: ej "4338" o "4336"
   - Verás el clima actual y la hora por cada registro.

3. **Visualización del historial de consultas**:
   - Accede al servicio de historial de consultas desde la URL proporcionada en postman.
   - Obtendrás una lista de todas las consultas previas realizadas en la base de datos.
