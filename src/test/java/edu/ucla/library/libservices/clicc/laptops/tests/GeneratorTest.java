package edu.ucla.library.libservices.clicc.laptops.tests;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import edu.ucla.library.libservices.clicc.laptops.generators.AvailableItemsGenerator;

public class GeneratorTest
{

  @Test
  public final void testGetItems()
  {
	  AvailableItemsGenerator generator;
	  generator = new AvailableItemsGenerator();
	  assertNotNull( generator.getItems(), "AvailableItemsGenerator.getItems should return populated object" );
  }

  @Test
  public final void testGetLocItems()
  {
	  AvailableItemsGenerator generator;
	  generator = new AvailableItemsGenerator();
	  generator.setLocation( "Powell" );
	  assertNotNull( generator.getLocItems(), "AvailableItemsGenerator.getLocItems should return populated object" );
  }

}
