package edu.ucla.library.libservices.clicc.laptops.webservices;

import edu.ucla.library.libservices.clicc.laptops.generators.AvailableLaptopGenerator;

import javax.servlet.ServletConfig;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

@Path( "/available" )
public class AvailableLaptopService
{
  @Context
  ServletConfig config;

  public AvailableLaptopService()
  {
    super();
  }

  @GET
  @Produces( "application/json" )
  public AvailableLaptopGenerator getLaptops()
  {
    AvailableLaptopGenerator docMaker;

    docMaker = new AvailableLaptopGenerator();

    docMaker.setDbName( config.getServletContext().getInitParameter( "datasource.vger" ) );
    docMaker.prepLaptops();

    return docMaker;
  }


  @GET
  @Produces( "application/json" )
  @Path( "/most" )
  public AvailableLaptopGenerator getMaxLaptops()
  {
    AvailableLaptopGenerator docMaker;

    docMaker = new AvailableLaptopGenerator();

    docMaker.setDbName( config.getServletContext().getInitParameter( "datasource.vger" ) );
    docMaker.prepMaxLaptops();

    return docMaker;
  }

  @GET
  @Produces( "application/json" )
  @Path( "/ccle" )
  public String getLaptopsCCLE()
  {
    AvailableLaptopGenerator docMaker;

    docMaker = new AvailableLaptopGenerator();

    docMaker.setDbName( config.getServletContext().getInitParameter( "datasource.clicc" ) );

    return docMaker.getWidgetLaptops();
  }
}
