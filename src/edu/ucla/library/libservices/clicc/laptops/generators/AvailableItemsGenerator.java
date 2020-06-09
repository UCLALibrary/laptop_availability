package edu.ucla.library.libservices.clicc.laptops.generators;

import edu.ucla.library.libservices.clicc.laptops.beans.AvailableItems;
import edu.ucla.library.libservices.clicc.laptops.db.mappers.AvailableItemsMapper;
import edu.ucla.library.libservices.clicc.laptops.db.source.DataSourceFactory;

import java.util.List;

import javax.sql.DataSource;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.jdbc.core.JdbcTemplate;

@XmlRootElement( name = "availableItems" )
public class AvailableItemsGenerator
{
  private static final String ITEMS_QUERY =
    "SELECT \"loc\", \"chromebooks_in\", \"mac_laptops_in\",\"win_laptops_in\", \"ipads_in\" FROM "
    + "vger_support.clicc_counts ORDER BY \"loc\"";
  private static final String LOC_QUERY =
    "SELECT \"loc\", \"chromebooks_in\", \"mac_laptops_in\",\"win_laptops_in\", \"ipads_in\" FROM "
    + "vger_support.clicc_counts WHERE \"loc\" = ? ORDER BY \"loc\"";

  private DataSource ds;
  private String dbName;
  private String location;
  private AvailableItems locItems;
  @XmlElement( name = "items" )
  private List<AvailableItems> items;

  public AvailableItemsGenerator()
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

  public void setLocation(String location)
  {
    this.location = location;
  }

  private String getLocation()
  {
    return location;
  }

  public void prepItems()
  {
    makeConnection();

    items =
        new JdbcTemplate( ds ).query( ITEMS_QUERY, new AvailableItemsMapper() );
  }

  public List<AvailableItems> getItems()
  {
	//makeConnection();
	makeTestConnection();

    items =
        new JdbcTemplate( ds ).query( ITEMS_QUERY, new AvailableItemsMapper() );
    return items;
  }

  public AvailableItems getLocItems()
  {
    makeTestConnection();
    locItems = ( AvailableItems ) new JdbcTemplate(ds).queryForObject(LOC_QUERY, new Object[]{getLocation()}, new AvailableItemsMapper());
    return locItems;
  }

  private void makeConnection()
  {
    ds = DataSourceFactory.createDataSource( getDbName() );
    //ds = DataSourceFactory.createVgerSource();
  }

  private void makeTestConnection()
  {
	  ds = DataSourceFactory.createTestSource();
  }
}
