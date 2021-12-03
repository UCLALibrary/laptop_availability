package edu.ucla.library.libservices.clicc.laptops.generators;

import edu.ucla.library.libservices.clicc.laptops.beans.AvailableLaptop;

import edu.ucla.library.libservices.clicc.laptops.db.mappers.AvailableLaptopMapper;
import edu.ucla.library.libservices.clicc.laptops.db.source.DataSourceFactory;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;

@XmlRootElement( name = "availableLaptops" )
public class AvailableLaptopGenerator
{
  //private DriverManagerDataSource ds;
  private DataSource ds;
  private String dbName;
  @XmlElement( name = "laptops" )
  private List<AvailableLaptop> laptops;
  private static final String LAPTOP_QUERY =
    "SELECT * FROM vger_support.laptop_counts_alt ORDER BY location_name";
  private static final String MAX_LAPTOPS =
    "SELECT * FROM vger_support.laptop_counts_alt ORDER BY availables DESC";

  public AvailableLaptopGenerator()
  {
    super();
  }

  public void setDbName( String dbName )
  {
    this.dbName = dbName;
  }

  private String getDbName()
  {
    return dbName;
  }

  public List<AvailableLaptop> getLaptops()
  {
    makeConnection();

    laptops =
        new JdbcTemplate( ds ).query( LAPTOP_QUERY, new AvailableLaptopMapper() );
    return laptops;
  }

  public void prepMaxLaptops()
  {
    AvailableLaptop maxCount;

    makeConnection();

    laptops = new ArrayList<AvailableLaptop>(1);
    maxCount =
        ( AvailableLaptop ) new JdbcTemplate( ds ).query( 
          MAX_LAPTOPS, new AvailableLaptopMapper() ).get( 0 );
    laptops.add( maxCount );
  }

  public void prepLaptops()
  {
    makeConnection();

    laptops =
        new JdbcTemplate( ds ).query( LAPTOP_QUERY, new AvailableLaptopMapper() );
  }

  private void makeConnection()
  {
    ds = DataSourceFactory.createDataSource( getDbName() );
    //ds = DataSourceFactory.createCliccSource();
    //ds = DataSourceFactory.createVgerSource();
  }

  public String getWidgetLaptops()
  {
    makeConnection();
    StringBuffer output;

    output = new StringBuffer( "{ [" );

    laptops =
        new JdbcTemplate( ds ).query( LAPTOP_QUERY, new AvailableLaptopMapper() );

    for ( int index = 0; index < laptops.size(); index++ )
    {
      output.append( "\n{\"publicName:\"" ).append( laptops.get( index ).getPublicName() ).append( "\"," );
      output.append( "\"availableCount\":\"" ).append( laptops.get( index ).getAvailableCount() ).append( "\"}" );
      if ( index < ( laptops.size() - 1 ) )
        output.append( "," );
    }

    output.append( "] }" );

    return output.toString();
  }

}
/*
  private String addLaptop( AvailableLaptop thePC )
    throws JSONException
  {
    JSONObject jobj;

    jobj = new JSONObject( thePC, new String[]{"",""} );
    return jobj.toString( 1 );
  }
 */