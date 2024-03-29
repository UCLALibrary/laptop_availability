package edu.ucla.library.libservices.clicc.laptops.webservices;

import edu.ucla.library.libservices.clicc.laptops.generators.AvailableItemsGenerator;
import edu.ucla.library.libservices.clicc.laptops.generators.AvailableLaptopGenerator;

import javax.servlet.ServletConfig;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import javax.sql.DataSource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "/available")
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
  @ApiOperation(value = "returns counts of available laptops/tablets", httpMethod = "GET",
                response = AvailableItemsGenerator.class, responseContainer = "Response")
  public Response getLaptops()
  {
    //AvailableLaptopGenerator docMaker;
    AvailableItemsGenerator docMaker;

    docMaker = new AvailableItemsGenerator();

    docMaker.setDbName( config.getServletContext().getInitParameter( "datasource.vger" ) );
    docMaker.prepItems();

    CacheControl cc = new CacheControl();
    cc.setMaxAge(2100);
    cc.setPrivate(true);

    ResponseBuilder builder = Response.ok().entity(docMaker);
    builder.cacheControl(cc);
    return builder.build();

    //return docMaker;
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

  public AvailableItemsGenerator getTestLaptops(DataSource ds)
  {
	  AvailableItemsGenerator docMaker;

      docMaker = new AvailableItemsGenerator();

      docMaker.setDbName("vger");
      docMaker.setDs( ds );
      docMaker.prepTestItems();

      return docMaker;
  }
}
