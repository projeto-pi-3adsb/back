#!/bin/sh

echo "Criando variáveis de ambiente"

export AZ_RESOURCE_GROUP=hemomanager
export AZ_DATABASE_NAME=hemomanager-sample
export AZ_LOCATION=eastus
export AZ_SQL_SERVER_USERNAME=hemomanager-master
export AZ_SQL_SERVER_PASSWORD=HemoAdmin-1234
export AZ_LOCAL_IP_ADDRESS=$45.184.194.246

export SPRING_DATASOURCE_URL="jdbc:sqlserver://$AZ_DATABASE_NAME.database.windows.net:1433;database=$AZ_DATABASE_NAME;user=$AZ_SQL_SERVER_USERNAME@$AZ_RESOURCE_GROUP;password=$AZ_SQL_SERVER_PASSWORD;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;"
export SPRING_DATASOURCE_USERNAME=spring@$AZ_DATABASE_NAME
export SPRING_DATASOURCE_PASSWORD=$AZ_SQL_SERVER_PASSWORD