package com.hs.sbminiloopscrud.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;

@Component
public class DbConnectionTestRunner implements CommandLineRunner {
    private final DataSource dataSource;

    public DbConnectionTestRunner(DataSource dataSource){
        this.dataSource=dataSource;
    }

    @Override
    public void run(String... args) throws Exception{
        try (Connection connection = dataSource.getConnection()){
            System.out.println("MySQL connected ok, database = "+connection.getCatalog());
        }
    }

}
