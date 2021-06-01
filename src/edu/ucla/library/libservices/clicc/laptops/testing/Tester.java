package edu.ucla.library.libservices.clicc.laptops.testing;

import edu.ucla.library.libservices.clicc.laptops.beans.*;
import edu.ucla.library.libservices.clicc.laptops.clients.ItemClient;
import edu.ucla.library.libservices.clicc.laptops.clients.MemberClient;
//import edu.ucla.library.libservices.clicc.laptops.generators.AvailableItemsGenerator;
//import edu.ucla.library.libservices.clicc.laptops.generators.AvailableLaptopGenerator;

import java.util.ArrayList;
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
    List<AlmaItem> items = new ArrayList<>();
    MemberClient client = new MemberClient();
    client.setBaseURL("https://api-na.hosted.exlibrisgroup.com/almaws/v1/conf/sets/");
    client.setKey("l8xxf5a239d1e9174c7c908fe163965d04fb");
    client.setSetID("2510255370006533");
    client.setLimit("100");
    client.setOffset("0");
    MemberList records = client.getRecords();
    System.out.println("total records: " + records.getRecordCount());
    addItems(records, items);
    if ( records.getRecordCount() > 100 )
    {
      int dividend = records.getRecordCount() / 100;
      for ( int loop = 1; loop <= dividend; loop++ )
      {
        int offset = (loop * 100);
        client.setOffset(String.valueOf(offset));
        records = client.getRecords();
        addItems(records, items);
      }
    }
    System.out.println("retrieved " + items.size() + " items.");
    /*if ( records.getRecords() != null && records.getRecords().size() > 0 )
    {
      System.out.println("retrieved records = " + records.getRecords().size());
      AlmaMember theRecord = records.getRecords().get(0);

      ItemClient client2 = new ItemClient();
      client2.setKey("l8xxf5a239d1e9174c7c908fe163965d04fb");
      client2.setLink(theRecord.getLink());
      AlmaItem item = client2.getItem();
      System.out.println(item.getItem().getBarcode() + "\t" + item.getItem().getLocation() + "\t" + item.getItem().getType());
    }
    for ( AlmaMember theRecord : records.getRecords() )
    {
      System.out.println("Desc: " + theRecord.getDescription() + "\tLink: " + theRecord.getLink());
    }
    AvailableItemsGenerator generator;
    List<AvailableItems> laptops;
    //String laptops;

    generator = new AvailableItemsGenerator();
    generator.setDbName( "" );
    //laptops = generator.getWidgetLaptops();
    laptops = generator.getItems();

    for ( AvailableItems theLaptop : laptops )
      System.out.println( theLaptop.getLocation() + "\t" + theLaptop.getChromeBooks() );*/
  }

  private static void addItems(MemberList records, List<AlmaItem> items) {
    for (AlmaMember theRecord : records.getRecords() ) {
      ItemClient client = new ItemClient();
      client.setKey("l8xxf5a239d1e9174c7c908fe163965d04fb");
      client.setLink(theRecord.getLink());
      AlmaItem item = client.getItem();
      items.add(item);
    }
  }
}
/*
make an empty list<almaitem>
retrieve the memberlist with limit = 100, offset=0, and get record count
loop through members to get items and add to item list
if record count <= 100 then we're done
else
  int dividend = count / 100;
  for loop index i = 1 to dividend inclusive do
    loop_offset = (i * 100) + 1;
    retrieve the memberlist with limit = 100, offset=loop_offset, and get record count
    loop through members to get items and add to item list
  done
fi
 */
/*
next need to group by location and count by type
    Set<String> locations = new TreeSet<>();
    Set<String> types = new TreeSet<>();
    Set<LocationCount> locsWithCounts = new TreeSet<>();
    items.stream().forEach(e -> locations.add(e.getLocation()));
    items.stream().forEach(e -> types.add(e.getItemType()));

    locations.stream().forEach(e -> locsWithCounts.add(makeNewLocCount(e)));
    locsWithCounts.stream().forEach(e -> setCounts(e, items, types));
  private static LocationCount makeNewLocCount(String loc)
  {
    LocationCount bean = new LocationCount();
    bean.setLocation(loc);
    return bean;
  }

  private static void setCounts(LocationCount e, List<LaptopLocation> items, Set<String> types)
  {
    List<LaptopLocation> locItems = items.stream()
                                         .filter(f -> f.getLocation().equals(e.getLocation()))
                                         .collect(Collectors.toList());
    for (String thetype : types)
    {
      int typeCount = (int) locItems.stream()
                                    .filter(g -> g.getItemType().equals(thetype))
                                    .count();
      switch( thetype )
      {
        case "chromebool" :e.setChromeBooks(typeCount);
          break;
        case "mac" :e.setMacs(typeCount);
          break;
        case "pc" :e.setWindows(typeCount);
          break;
        case "ipad" :e.setIPads(typeCount);
          break;
      }
    }
  }
 */