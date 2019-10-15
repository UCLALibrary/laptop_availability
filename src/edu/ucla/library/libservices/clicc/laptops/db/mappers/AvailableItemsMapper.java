package edu.ucla.library.libservices.clicc.laptops.db.mappers;

import edu.ucla.library.libservices.clicc.laptops.beans.AvailableItems;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class AvailableItemsMapper
  implements RowMapper
{
  public AvailableItemsMapper()
  {
    super();
  }

  @Override
  public Object mapRow( ResultSet rs, int rowNum )
    throws SQLException
  {
    AvailableItems bean;
    
    bean = new AvailableItems();
    bean.setChromeBooks( rs.getInt( "chromebooks_in" ) );
    bean.setIPads( rs.getInt( "ipads_in" ) );
    bean.setLocation( rs.getString( "loc" ) );
    bean.setMacs( rs.getInt( "mac_laptops_in" ) );
    bean.setWindows( rs.getInt( "win_laptops_in" ) );
    
    return bean;
  }
}
