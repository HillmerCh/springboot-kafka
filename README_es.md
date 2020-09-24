# Payment-Shipment service SpringBoot - Kafka

[Go to English version](/README.md)


Este repositorio contiene APIs Rest creadas con Java SpringBoot, a la cual se la han adicionado algunos elementos como Hypermedia Controls, documentación con OpenAPI,
y seguridad OAuth 2.0. Las APIs comparten mensajes por medio de Kafka
 
Actualmente, el proyecto tiene 2 APIs: **payments** y **shipments**.
Cuando un nuevo pago es enviado (_POST_) un mensaje es enviado a un flujo de kafka desde el API `/payments`. Dichos mensajes son consumidos en el API `/shipments`, creando ordenes de envío. 
                                                                                                


## Características del API

- Cada API tiene 3 paquetes principales: **client**, **model** and **web**.
- Los Controllers no utilizan tienen acceso a las **entities**, los datos son compartidos por medio de **DTO** (_Data transfer object_)
- Cada API está dividido en 4 capas: **Controller**, **Service**, **Dao** and **Repository**
- La comunicación entre las capas se hace a través de interfaces
- Anotaciones de Bean Validation son utilizadas para hacer validaciones, entre estas se tienen: **@NotBlank**, **@NotNull**, **@Min** 


## Probar-Ejecutar este repositorio

* Para pruebas ejecute `./mvnw clean test`

* Para ejecutar ejecute `./mvnw spring-boot:run`

* API Info   
    
    * http://localhost:8080/api-docs
    
    * http://localhost:8080/api-docs-ui.html


* Rest endpoints 

    * **GET:** http://localhost:8080/payments =  Obtener todos los pagos en el sistema
    * **POST:** http://localhost:8080/payments = Adicionar un pago
    
    * **GET:** http://localhost:8080//shipments/{idShipmentOrder} = Consultar la orden de envío identificado con idShipmentOrder
 


## Contenidos

## El código

### Configuraciones Kafka


 
* Para usar Kafka es necesario
    
    * Create un stream (flujo) en un servidor Kafka, este demo usa un stream con el nombre **MY-STREAM**
    
    * Actualizar las propiedades de conexión con la aplicación en OKTA en el archivo `app/src/main/resources/application.properties`

   
        spring.kafka.consumer.bootstrap-servers=<KAFKA SERVER>
        spring.kafka.consumer.auto-offset-reset=earliest
        spring.kafka.consumer.key-deserializer=org.apache.kafka.common.serialization.StringDeserializer
        spring.kafka.consumer.value-deserializer=org.apache.kafka.common.serialization.StringDeserializer
        spring.kafka.producer.bootstrap-servers=<KAFKA SERVER>
        spring.kafka.producer.key-serializer=org.apache.kafka.common.serialization.StringSerializer
        spring.kafka.producer.value-serializer=org.apache.kafka.common.serialization.StringSerializer
        spring.kafka.properties.security.protocol=SASL_SSL
        spring.kafka.properties.sasl.mechanism=PLAIN
        spring.kafka.properties.sasl.jaas.config=<KAFKA SECURITY CONFIG>


### El productor de mensajes de Pagos (org.hillmerch.payments.web.engine.PaymentProducer)

Este clase is usada para enviar mensajes al stream de Kafka
 

### El productor de mensajes de Pagos  (org.hillmerch.shipments.web.engine.PaymentsConsumer)

Este clase is usada para consumir mensajes al stream de Kafka 


### Dependencies (pom.xml file)
  

* Dependencias SpringBoot Kafka


        org.springframework.kafka:spring-kafka
        

* Dependencias SpringBoot Kafka dependencies para pruebas

        
        org.springframework.kafka:spring-kafka-test:test
   
   

## Como usar los endpoints


* Creación de un nuevo pago con curl


     curl -X POST "http://localhost:8080/payments" -H  "accept: */*" -H  "Content-Type: application/json" -d "{\"id\":0,\"orderId\":\"ABC123\",\"date\":\"2020-09-24T20:55:29.124Z\",\"value\":100}"


La consola de los endpoints mostrarán unos logs como los siguientes:
     
    2020-09-24 18:12:42.045  INFO 58140 --- [nio-8080-exec-5] o.a.kafka.common.utils.AppInfoParser     : Kafka version: 2.5.1
    2020-09-24 18:12:42.046  INFO 58140 --- [nio-8080-exec-5] o.a.kafka.common.utils.AppInfoParser     : Kafka commitId: 0efa8fb0f4c73d92
    2020-09-24 18:12:42.046  INFO 58140 --- [nio-8080-exec-5] o.a.kafka.common.utils.AppInfoParser     : Kafka startTimeMs: 1600989162045
    2020-09-24 18:12:42.821  INFO 58140 --- [ad | producer-1] org.apache.kafka.clients.Metadata        : [Producer clientId=producer-1] Cluster ID: OSS
    2020-09-24 18:12:44.150  INFO 58140 --- [ PAYMENTS-0-C-1] o.h.s.web.engine.PaymentsConsumer        : Payment consumed from Shipments message -> {"id":5,"orderId":"string","dateOfPayment":[2020,9,24,23,12,33,554000000],"value":100}
    2020-09-24 18:12:44.151  INFO 58140 --- [ PAYMENTS-0-C-1] o.h.s.web.engine.PaymentsConsumer        : Creating shipment order -> ShipmentOrderDTO{id=null, orderId=string, processingDate=2020-09-24T18:12:44.151091, estimatedShippingDate=2020-09-24}
    2020-09-24 18:12:44.154  INFO 58140 --- [ PAYMENTS-0-C-1] o.h.s.web.engine.PaymentsConsumer        : Shipment order created -> ShipmentOrderDTO{id=6, orderId=string, processingDate=2020-09-24T18:12:44.151514, estimatedShippingDate=2020-09-24}
     
* Obtener la información del envío generado con curl 


    curl -X GET "http://localhost:8080/shipments/2" -H  "accept: */*"

La informacion de la orden de envío creada es similar a la siguiente:

    {
      "id": 2,
      "orderId": "123",
      "processingDate": "2020-09-24T17:56:44.746044",
      "estimatedShippingDate": "2020-09-24"
    }