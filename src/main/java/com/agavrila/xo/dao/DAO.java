package com.agavrila.xo.dao;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.apache.commons.dbutils.QueryRunner;

public abstract class DAO {
    protected String url = "localhost";
    protected String username = "root";
    protected String password = "root";
    protected QueryRunner queryRunner;

    protected DAO() {
        var dataSource = new MysqlDataSource();
        dataSource.setURL(getConnectionUrl());
        queryRunner = new QueryRunner(dataSource);
    }

    private String getConnectionUrl() {
        return "jdbc:mysql://" + url + "/nodsandcrosses?user=" + username + "&password=" + password;
    }

}
