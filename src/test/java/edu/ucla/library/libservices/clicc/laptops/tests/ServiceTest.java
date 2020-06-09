package edu.ucla.library.libservices.clicc.laptops.tests;

import org.junit.Assert;
import static org.junit.Assert.*;

import org.junit.Test;

import edu.ucla.library.libservices.clicc.laptops.webservices.AvailableLaptopService;

public class ServiceTest
{

    @Test
    public final void testGettLaptops()
    {
	AvailableLaptopService service;
	service = new AvailableLaptopService();
	Assert.assertNotNull( "AvailableLaptopService.getLaptops should return populated object",
	        service.getTestLaptops() );
    }

}
