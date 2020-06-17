package edu.ucla.library.libservices.clicc.laptops.tests;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import edu.ucla.library.libservices.clicc.laptops.webservices.AvailableLaptopService;

public class ServiceTest
{

  @Test
  public final void testGettLaptops()
  {
    AvailableLaptopService service;
    service = new AvailableLaptopService();
    assertNotNull( service.getTestLaptops(),
        "AvailableLaptopService.getLaptops should return populated object" );
  }

}
