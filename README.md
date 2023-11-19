# Shop System
En este proyecto vamos a controlar distintas entidades y procesos, es simular un sistema de administración, donde podamos tener **Empleados**, **Productos**, **Clientes**, **Proveedores**, entre otras entidades mas que se muestran en el diagrama mas adelante.

## Herramientas utilizadas
- OpenJDK v11
- Java EE v8
- Java ServerFaces v2.2.17
- Primefaces v11
- Enterprise Java Bean(EJB)
- EclipseLink JPA
- Docker Compose
- MySQL
- Payara v5
- Bootstrap v5

## Configuración para Payara 5
- Descarga payara : [Payara Platform Community Edition – Payara Services Ltd](https://www.payara.fish/downloads/payara-platform-community-edition/http:// "Payara Platform Community Edition – Payara Services Ltd").
- Descargar el driver de mysql desde la pagina oficial: [MySQL :: Download Connector/J](https://dev.mysql.com/downloads/connector/j/:// "MySQL :: Download Connector/J").
- Extraer el archivo y mover el **.jar** a la carpeta de nuestro dominio que se encuentra en la siguiente ruta: **payara5/glassfish/domains/domain1**.
- Lanzar el servidor y acceder al **localhost:4848** y crear un nuevo pool de conexión.
	- **ServerName: localhost**
	- **PortNumber: 3306**
	- **User: root**
	- **Password: admin123**
	- **DatabaseName: shop_system_db**
	- **UseSSL: false**
- Por ultimo configurar el JDBC Resources con el siguiente nombre: **jdbc/__shop_system**.
