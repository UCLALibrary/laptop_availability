package edu.ucla.library.libservices.clicc.laptops.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.*;
import io.swagger.jaxrs.config.BeanConfig;

public class SwaggerBootstrap
  extends HttpServlet
{
  private static final String CONTENT_TYPE = "text/html; charset=windows-1252";

  public void init(ServletConfig config)
    throws ServletException
  {
    super.init(config);

    BeanConfig beanConfig;

    beanConfig = new BeanConfig();
    beanConfig.setVersion( "2.0.0" );
    beanConfig.setSchemes( new String[] { "https" } );
    beanConfig.setHost( "webservices.library.ucla.edu" );
    beanConfig.setBasePath( "/laptops" );
    beanConfig.setResourcePackage( "edu.ucla.library.libservices.clicc.laptops.webservices" ); 
    beanConfig.setScan( true );
  }

  public void service(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
  }
}
