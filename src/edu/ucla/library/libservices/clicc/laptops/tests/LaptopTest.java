package edu.ucla.library.libservices.clicc.laptops.tests;

import edu.ucla.library.libservices.clicc.laptops.beans.AvailableItems;

import edu.ucla.library.libservices.clicc.laptops.generators.AvailableItemsGenerator;

import edu.ucla.library.libservices.clicc.laptops.webservices.AvailableLaptopService;

import org.junit.Test;
import org.junit.Assert;
import org.meanbean.test.BeanTester;

public class LaptopTest
{
  public LaptopTest()
  {
    super();
  }
  
  @Test
  public void accessMethodsWord()
  {
    BeanTester tester = new BeanTester();
    tester.testBean(AvailableItems.class);
  }
  
  @Test
  public void generatorReturnsItem()
  {
    AvailableItemsGenerator generator;
    generator = new AvailableItemsGenerator();
    generator.setLocation("Powell");
    Assert.assertNotNull("AvailableItemsGenerator.getLocItems should return populated object", generator.getLocItems());
    
  }
  
  @Test
  public void generatorReturnsItems()
  {
    AvailableItemsGenerator generator;
    generator = new AvailableItemsGenerator();
    Assert.assertNotNull("AvailableItemsGenerator.getItems should return populated object", generator.getItems());
  }
  
  @Test
  public void serviceReturnsItems()
  {
    AvailableLaptopService service;
    service = new AvailableLaptopService();
    Assert.assertNotNull("AvailableLaptopService.getLaptops should return populated object", service.getLaptops());
  }
}
