package edu.ucla.library.libservices.clicc.laptops.testing;

import edu.ucla.library.libservices.clicc.laptops.beans.AvailableItems;
import edu.ucla.library.libservices.clicc.laptops.beans.AvailableLaptop;
import edu.ucla.library.libservices.clicc.laptops.generators.AvailableItemsGenerator;
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
    AvailableItemsGenerator generator;
    List<AvailableItems> laptops;
    //String laptops;

    generator = new AvailableItemsGenerator();
    generator.setDbName( "" );
    //laptops = generator.getWidgetLaptops();
    laptops = generator.getItems();

    for ( AvailableItems theLaptop : laptops )
      System.out.println( theLaptop.getLocation() + "\t" + theLaptop.getChromeBooks() );
  }
}
