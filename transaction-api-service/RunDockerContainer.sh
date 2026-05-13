docker run -p 8080:8080 \
-e DB_URL=jdbc:postgresql://host.docker.internal:5432/transaction_system \
-e DB_USER=postgres \
-e DB_PASSWORD=Jose@1234 \
-e KF_SER=host.docker.internal:9092 \
transaction-api-service