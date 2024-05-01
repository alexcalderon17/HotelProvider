## Proyecto

Este proyecto desarrolla un sistema de gestión remota implementado a través de Java RMI (Remote Method Invocation) centrado en la operación y administración de "puertas" mediante códigos QR. La clase fundamental de este proyecto es la clase PuertasFacadeImpl, que actúa como una fachada que expone métodos remotos para ser consumidos por clientes RMI. Estos métodos permiten operaciones como la apertura y cierre de las puertas.

Actualmente, el java-rmi-puertas se encuentra en una etapa temprana de desarrollo ya que hasta ahora nos hemos centrado en el proyecto del servidor, java-rmi-server, y en el del cliente principal, java-rmi-client, por lo que muchas de las funcionalidades planificadas aún están en proceso de ser implementadas. Esto significa que, aunque la infraestructura básica para la comunicación RMI y la interacción con códigos QR está establecida, el sistema no cuenta todavía con todas las capacidades esperadas. 

## Ejecución y uso

Este proyecto requiere Java y Ant para compilar y ejecutar. Asegúrate de tenerlos instalados y configurados en tu entorno de desarrollo. 

A continuación, clona el repositorio en tu máquina local y navega al directorio del proyecto. Para compilar el proyecto, ejecuta el siguiente comando en la terminal: ant build. 

Una vez compilado el proyecto, puedes iniciar el cliente de puertas con el siguiente comando: ant serverPuertas

## Contribución 
Aceptamos cualquier tipo de sugerencia o contribución, os podeís poner en contacto con cualquiera de nosotros (contacto en autores).

## Autores
-Markos Agote, email: markosagote@opendeusto.es, repositorio GIT: pardddo1
-Alex Calderón, email: alexcalderon@opendeusto.es, repositorio GIT: alexcalderon17
-Pablo de Erauso, email: pablodeerauso@opendeusto.es, repositorio GIT: Derau21
-Bastiaan Handels, email: bastiaan.handels@opendeusto.es, repositorio GIT: Bastiaan777








