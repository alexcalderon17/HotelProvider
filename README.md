## Proyecto
La solución consta de dos interfaces de usuario distintas: una dirigida a los clientes que desean alquilar habitaciones y otra incorporada en dispositivos de puertas para validar los códigos QR generados durante la reserva.

La interacción entre el backend y los frontends se realiza a través de Java RMI, facilitando la comunicación y gestión de datos entre los distintos componentes del sistema. Este proyecto se integra con los servicios de HotelProvider, un proveedor no real que ofrece una API REST para la consulta y reserva de alojamientos a nivel mundial. 

## Ejecución 

Para facilitar la gestión y ejecución del proyecto, hemos implementado un comando centralizado, ant build-all, que permite iniciar simultáneamente los tres componentes clave del sistema: el servidor principal (java-rmi-server), el servidor de puertas (java-rmi-puertas), y el cliente de usuarios (java-rmi-client). Este comando compila primero los códigos comunes necesarios para todos los componentes y luego lanza los servidores y el cliente en paralelo, proporcionando una manera rápida y eficaz de verificar la interacción completa del sistema. 


## Uso 

Cliente de Usuarios:
Para utilizar la interfaz de alquiler de habitaciones, los usuarios deben acceder a Java RMI Client Usuarios Ant. Esta aplicación les permite buscar habitaciones disponibles, realizar reservas y recibir correos electrónicos de confirmación con el código QR correspondiente a su reserva.

Cliente de Puertas:
El cliente instalado en las puertas, ubicado en Java RMI Client Puertas with Ant - copia, se encarga de la validación de los códigos QR de las reservas. Al escanear un código QR, el sistema verifica su validez y permite o deniega el acceso según corresponda.

Servidor RMI:
El servidor, que se encuentra en Java RMI Server with Ant, gestiona todas las solicitudes de los clientes y se comunica con el backend para procesar las reservas, consultas de disponibilidad y otras operaciones necesarias para el funcionamiento del sistema.


## Contribución 
Aceptamos cualquier tipo de sugerencia o contribución, os podeís poner en contacto con cualquiera de nosotros (contacto en autores).

## Autores
-Markos Agote, email: markosagote@opendeusto.es, repositorio GIT: pardddo1
-Alex Calderón, email: alexcalderon@opendeusto.es, repositorio GIT: alexcalderon17
-Pablo de Erauso, email: pablodeerauso@opendeusto.es, repositorio GIT: Derau21
-Bastiaan Handels, email: bastiaan.handels@opendeusto.es, repositorio GIT: Bastiaan777

