# Usar una imagen base con Payara Server 5
FROM payara/server-full:5.2021.1

# Copiar el archivo WAR de tu aplicación al directorio autodeploy de Payara
COPY ShopSystem.war $DEPLOY_DIR

# Exponer el puerto en el que se ejecutará tu aplicación
EXPOSE 8080