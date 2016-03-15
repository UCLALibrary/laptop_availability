package edu.ucla.library.libservices.clicc.laptops.db.source;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.naming.Context;

public class DataSourceFactory
{
  public static DataSource createDataSource( String name )
  {
    Context envContext;
    InitialContext context;
    DataSource connection;

    try
    {
      context = new InitialContext();
      envContext = (Context)context.lookup("java:/comp/env");
      connection = ( DataSource ) envContext.lookup( name );
      //context = new InitialContext();
      //connection = ( DataSource ) context.lookup( name );
    }
    catch ( NamingException e )
    {
      e.printStackTrace();
      connection = null;
    }

    return connection;
  }

  public static DriverManagerDataSource createCliccSource()
  {
    DriverManagerDataSource ds;

    ds = new DriverManagerDataSource();
    ds.setDriverClassName( "com.microsoft.sqlserver.jdbc.SQLServerDriver" );
    ds.setUrl( "jdbc:sqlserver://longisland.clicc.ucla.edu:1433" );
    ds.setUsername( "MAGI_LIT_Web_Services" );
    ds.setPassword( "AR5s7UZDGcogTH06J7U2" );

    return ds;
  }

  public static DriverManagerDataSource createVgerSource()
  {
    DriverManagerDataSource ds;

    ds = new DriverManagerDataSource();
    ds.setDriverClassName( "oracle.jdbc.OracleDriver" );
    ds.setUrl( "jdbc:oracle:thin:@ils-db-prod.library.ucla.edu:1521:VGER" );
    ds.setUsername( "vger_support" );
    ds.setPassword( "vger_support_pwd" );

    return ds;
  }
}
