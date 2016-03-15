package edu.ucla.library.libservices.clicc.laptops.testing;

import edu.ucla.library.libservices.clicc.laptops.beans.AvailableLaptop;
import edu.ucla.library.libservices.clicc.laptops.generators.AvailableLaptopGenerator;

import java.util.List;

import org.codehaus.jettison.json.JSONException;

public class Tester
{
  public Tester()
  {
    super();
  }

  public static void main( String[] args )
    throws JSONException
  {
    AvailableLaptopGenerator generator;
    //List<AvailableLaptop> laptops;
    String laptops;
    
    generator = new AvailableLaptopGenerator();
    generator.setDbName( "" );
    laptops = generator.getWidgetLaptops();
    
    //for ( AvailableLaptop theLaptop : laptops )
      System.out.println( laptops );
  }
}
