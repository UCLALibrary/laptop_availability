
package edu.ucla.library.libservices.clicc.laptops.testing;

import edu.ucla.library.libservices.clicc.laptops.beans.AvailableItems;
//import edu.ucla.library.libservices.clicc.laptops.beans.AvailableLaptop;
import edu.ucla.library.libservices.clicc.laptops.beans.MemberList;
import edu.ucla.library.libservices.clicc.laptops.beans.AlmaItem;
import edu.ucla.library.libservices.clicc.laptops.beans.AlmaMember;
//import edu.ucla.library.libservices.clicc.laptops.generators.AvailableItemsGenerator;
//import edu.ucla.library.libservices.clicc.laptops.generators.AvailableLaptopGenerator;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import org.codehaus.jettison.json.JSONException;
import edu.ucla.library.libservices.clicc.laptops.clients.ItemClient;
import edu.ucla.library.libservices.clicc.laptops.clients.MemberClient;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Tester {

    public Tester() {
        super();
    }

    public static void main(String[] args) throws JSONException {
        List<AlmaItem> items = new ArrayList<>();
        final List<AlmaItem> availableItems;
        List<AvailableItems> locsWithCounts = new ArrayList<>();
        MemberClient client = new MemberClient();
        // Set<String> types = new TreeSet<>();
        Set<String> locations = new TreeSet<>();

        client.setBaseURL("https://api-na.hosted.exlibrisgroup.com/almaws/v1/conf/sets/");
        client.setKey("l8xxf5a239d1e9174c7c908fe163965d04fb");
        client.setSetID("2580289030006533");
        client.setLimit("100");
        client.setOffset("0");

        MemberList records = client.getRecords();

        System.out.println("total records: " + records.getRecordCount());
        System.out.println(new Date());
        addItems(records, items);
        if (records.getRecordCount() > 100) {
            int dividend = records.getRecordCount() / 100;
            for (int loop = 1; loop <= dividend; loop++) {
                int offset = (loop * 100);
                client.setOffset(String.valueOf(offset));
                records = client.getRecords();
                addItems(records, items);
            }
        }
        System.out.println("retrieved " + items.size() + " items.");
        System.out.println(new Date());
        availableItems =
                items.stream().filter(f -> f.getTheItem().getStatus().equals("1")).collect(Collectors.toList());
        System.out.println("retrieved " + items.size() + " available items.");
        availableItems.stream().forEach(i -> locations.add(i.getTheItem().getLocation()));
        // availableItems.stream().forEach(e -> types.add(e.getTheItem().getType()));
        locations.stream().forEach(e -> locsWithCounts.add(makeNewAvailableItem(e)));
        locsWithCounts.stream().forEach(e -> setCounts(e, availableItems));
        for (AvailableItems availables : locsWithCounts) {
            System.out.println(availables.getLocation() + "\tchrome: " + availables.getChromeBooks() + "\tmacs: " +
                    availables.getMacs() + "\tPCs: " + availables.getWindows() + "\tpads: " + availables.getIPads());
        }

        /*
         * AvailableItemsGenerator generator; List<AvailableItems> laptops; //String laptops; generator = new
         * AvailableItemsGenerator(); generator.setDbName( "" ); //laptops = generator.getWidgetLaptops(); laptops =
         * generator.getItems(); for ( AvailableItems theLaptop : laptops ) System.out.println( theLaptop.getLocation()
         * + "\t" + theLaptop.getChromeBooks() );
         */
    }

    private static void addItems(MemberList records, List<AlmaItem> items) {
        for (AlmaMember theRecord : records.getRecords()) {
            ItemClient client = new ItemClient();
            client.setKey("l8xxf5a239d1e9174c7c908fe163965d04fb");
            client.setLink(theRecord.getLink());
            AlmaItem item = client.getItem();
            items.add(item);
        }
    }

    private static AvailableItems makeNewAvailableItem(String aLocation) {
        AvailableItems bean = new AvailableItems();
        bean.setLocation(aLocation);
        return bean;
    }

    private static void setCounts(AvailableItems aAvailableItem, List<AlmaItem> aItemsList) {
        List<AlmaItem> locItems =
                aItemsList.stream().filter(f -> f.getTheItem().getLocation().equals(aAvailableItem.getLocation()))
                        .collect(Collectors.toList());
        for (AlmaItem theLocItem : locItems) {
            if (theLocItem.getTheItem().getType().equals("ccipad")) {
                aAvailableItem.setIPads(aAvailableItem.getIPads() + 1);
            } else if (theLocItem.getTheItem().getType().equals("cclaptop")) {
                if (theLocItem.getTheItem().getBarcode().startsWith("CBK")) {
                    aAvailableItem.setChromeBooks(aAvailableItem.getChromeBooks() + 1);
                } else if ((theLocItem.getTheItem().getBarcode().startsWith("MP"))) {
                    aAvailableItem.setMacs(aAvailableItem.getMacs() + 1);
                } else if ((theLocItem.getTheItem().getBarcode().startsWith("HP"))) {
                    aAvailableItem.setWindows(aAvailableItem.getWindows() + 1);
                } else {
                    System.out.println(
                            theLocItem.getTheItem().getBarcode() + " has type " + theLocItem.getTheItem().getType());
                }
            }
        }
        aAvailableItem.setLocation(aAvailableItem.getLocation().concat(" Lending"));
    }
}
