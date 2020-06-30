package edu.ucla.library.libservices.clicc.laptops.tests;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import edu.ucla.library.libservices.clicc.laptops.webservices.AvailableLaptopService;

import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

public class ServiceTest
{

  @Test
  public final void testGettLaptops()
  {
    EmbeddedDatabase ds = new EmbeddedDatabaseBuilder()
        .setType(EmbeddedDatabaseType.H2)
        .setName("testdb;DATABASE_TO_UPPER=false;MODE=Oracle")
        .addScript("create.sql")
        .addScript("populate.sql")
        .build();

    AvailableLaptopService service;
    service = new AvailableLaptopService();
    assertNotNull( service.getTestLaptops(ds),
        "AvailableLaptopService.getLaptops should return populated object" );
  }

}
