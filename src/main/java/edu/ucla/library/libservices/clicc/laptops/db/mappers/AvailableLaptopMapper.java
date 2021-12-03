package edu.ucla.library.libservices.clicc.laptops.db.mappers;

import edu.ucla.library.libservices.clicc.laptops.beans.AvailableLaptop;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class AvailableLaptopMapper
  implements RowMapper
{
  public AvailableLaptopMapper()
  {
    super();
  }

  public Object mapRow( ResultSet rs, int rowNum )
    throws SQLException
  {
    AvailableLaptop bean;
    
    bean = new AvailableLaptop();
    bean.setAvailableCount( rs.getInt( "availables" ) );
    bean.setPublicName( rs.getString( "location_name" ).trim() );
    
    return bean;
  }
}
