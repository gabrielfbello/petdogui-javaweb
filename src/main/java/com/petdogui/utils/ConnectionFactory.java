package com.petdogui.utils;

import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.SQLException;
import java.sql.Connection;

public class ConnectionFactory {

    public static final String RESOURCE_NAME = "java:/PetdoguiDS";

    public DataSource getDataSource() throws NamingException {

        Context c = new InitialContext();

        return (DataSource) c.lookup(RESOURCE_NAME);

    }

    public Connection getConnection() {

        try {

            return getDataSource().getConnection();

        } catch(SQLException | NamingException ex) {

            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);

            return null;

        }

    }

}
