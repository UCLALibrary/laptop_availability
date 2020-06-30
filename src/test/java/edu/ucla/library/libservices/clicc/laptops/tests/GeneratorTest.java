package edu.ucla.library.libservices.clicc.laptops.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ucla.library.libservices.clicc.laptops.generators.AvailableItemsGenerator;

import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

public class GeneratorTest
{
  private AvailableItemsGenerator generator;


  @BeforeEach
  void init()
  {
	  EmbeddedDatabase ds = new EmbeddedDatabaseBuilder()
        .setType(EmbeddedDatabaseType.H2)
        .setName("testdb;DATABASE_TO_UPPER=false;MODE=Oracle")
        .addScript("create.sql")
        .addScript("populate.sql")
        .build();

	  generator = new AvailableItemsGenerator();
	  generator.setDs( ds );
  }

  @Test
  public final void testGetItems()
  {
	  assertNotNull( generator.getItems(), "AvailableItemsGenerator.getItems should return populated object" );
  }

  @Test
  public final void testGetLocItems()
  {
	  generator.setLocation( "Powell" );
	  assertNotNull( generator.getLocItems(), "AvailableItemsGenerator.getLocItems should return populated object" );
  }

}
