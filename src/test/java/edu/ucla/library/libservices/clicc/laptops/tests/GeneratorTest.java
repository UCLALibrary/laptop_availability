package edu.ucla.library.libservices.clicc.laptops.tests;

import org.junit.Assert;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ucla.library.libservices.clicc.laptops.generators.AvailableItemsGenerator;

public class GeneratorTest
{

    @Test
    public final void testGetItems()
    {
	AvailableItemsGenerator generator;
	generator = new AvailableItemsGenerator();
	Assert.assertNotNull( "AvailableItemsGenerator.getItems should return populated object", generator.getItems() );
    }

    @Test
    public final void testGetLocItems()
    {
	AvailableItemsGenerator generator;
	generator = new AvailableItemsGenerator();
	generator.setLocation( "Powell" );
	Assert.assertNotNull( "AvailableItemsGenerator.getLocItems should return populated object",
	        generator.getLocItems() );
    }

}
