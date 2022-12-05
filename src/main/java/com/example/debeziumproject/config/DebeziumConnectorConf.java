package com.example.debeziumproject.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;

@Configuration
public class DebeziumConnectorConf {
    @Value("${source.datasource.host}")
    private String sourceDbHost;

    @Value("${source.datasource.database}")
    private String sourceDbName;

    @Value("${source.datasource.port}")
    private String sourceDbPort;

    @Value("${source.datasource.username}")
    private String sourceDbUsername;

    @Value("${source.datasource.password}")
    private String sourceDbPassword;
    /**
     * Member Database Connector Configuration
     */
    @Bean
    public io.debezium.config.Configuration memberConnector() throws IOException {
        File offsetStorageTempFile = File.createTempFile("offsets_", ".dat");
        File dbHistoryTempFile = File.createTempFile("dbhistory_", ".dat");
        return io.debezium.config.Configuration.create()
                .with("name", "source-mysql-connector")
                .with("connector.class", "io.debezium.connector.mysql.MySqlConnector")
                .with("offset.storage", "org.apache.kafka.connect.storage.FileOffsetBackingStore")
                .with("offset.storage.file.filename", "/tmp/offsets6.dat")
                .with("offset.flush.interval.ms", "60000")
                .with("database.hostname", sourceDbHost)
                .with("database.port", sourceDbPort)
                .with("database.user", sourceDbUsername)
                .with("database.password", sourceDbPassword)
                .with("database.dbname", sourceDbName)
                .with("database.include.list", sourceDbName)
                .with("database.allowPublicKeyRetrieval", true)
                .with("include.schema.changes", "false")
                .with("database.server.id", "10181")
                .with("database.server.name", "source-mysql-db-server")
                .with("database.history", "io.debezium.relational.history.FileDatabaseHistory")
                .with("database.history.file.filename", "/tmp/dbhistory.dat")
                .with("database.history.store.only.monitored.tables.ddl","true")
                .build();
    }
}
