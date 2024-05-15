FROM mysql/mysql-server:8.0.32
ENV MYSQL_DATABASE tropicalflix
COPY ./scripts/ /docker-entrypoint-initdb.d/