package edu.ucla.library.libservices.clicc.laptops.tests;

import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;

import edu.ucla.library.libservices.clicc.laptops.beans.AvailableItems;
import edu.ucla.library.libservices.clicc.laptops.generators.AvailableItemsGenerator;

public class BeansAccessTest
{

  @Test
  public void itemsAccessMethodsWork()
  {
    new BeanTester().testBean(AvailableItems.class);
  }

  @Test
  public void generatorAccessMethodsWorK()
  {
    new BeanTester().testBean(AvailableItemsGenerator.class);
  }

}
