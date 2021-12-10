
package edu.ucla.library.libservices.clicc.laptops.util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

/**
 * A quick and dirty way to generate the jetty-env.xml for our container to use. This pulls configuration variables from
 * the ENV and puts them into the config file that Jetty expects.
 * <p>
 * This is the fast, not the correct, way to solve this problem.
 * </p>
 */
public class JettyEnvGenerator {

    /**
     * System-dependent end-of-line.
     */
    private static final String EOL = System.lineSeparator();

    /**
     * The main class for this generator.
     *
     * @param aArgsArray An array of arguments
     */
    public static void main(final String[] aArgsArray) throws IOException {
        final String dbURL = System.getenv("DB_URL");
        final String dbUsername = System.getenv("DB_USERNAME");
        final String dbPassword = System.getenv("DB_PASSWORD");
        final String dbDataSource = System.getenv("DB_DATASOURCE");
        final String dbPoolFactory = System.getenv("DB_POOL_FACTORY");;
        final StringBuilder xml = new StringBuilder();

        // Just confirm all these things are set -- fail early!
        Objects.requireNonNull(dbURL);
        Objects.requireNonNull(dbUsername);
        Objects.requireNonNull(dbPassword);
        Objects.requireNonNull(dbDataSource);
        Objects.requireNonNull(dbPoolFactory);
        Objects.requireNonNull(aArgsArray[0]);

        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>").append(EOL);
        xml.append("<!DOCTYPE Configure PUBLIC \"-//Jetty//Configure//EN\"").append(EOL);
        xml.append(" \"https://www.eclipse.org/jetty/configure_10_0.dtd\">").append(EOL);
        xml.append(EOL);
        xml.append("<Configure id=\"wac\" class=\"org.eclipse.jetty.webapp.WebAppContext\">").append(EOL);
        xml.append("<New class=\"org.eclipse.jetty.plus.jndi.Resource\">").append(EOL);
        xml.append("<Arg><Ref refid=\"wac\"/></Arg>").append(EOL);
        xml.append("<Arg>jdbc/rssDS</Arg>").append(EOL);
        xml.append("<Arg>").append(EOL);
        xml.append("<Call class=\"").append(dbPoolFactory).append("\" name=\"getPoolDataSource\">").append(EOL);
        xml.append("<Set name=\"URL\">").append(dbURL).append("</Set>").append(EOL);
        xml.append("<Set name=\"user\">").append(dbUsername).append("</Set>").append(EOL);
        xml.append("<Set name=\"password\">").append(dbPassword).append("</Set>").append(EOL);
        xml.append("<Set name=\"minPoolSize\">10</Set>").append(EOL); // should be configurable, too
        xml.append("<Set name=\"maxPoolSize\">100</Set>").append(EOL); // should be configurable, too
        xml.append("<Set name=\"initialPoolSize\">2</Set>").append(EOL); // should be configurable, too
        xml.append("<Set name=\"connectionFactoryClassName\">").append(dbDataSource).append("</Set>").append(EOL);
        xml.append("</Call>").append(EOL);
        xml.append("</Arg>").append(EOL);
        xml.append("</New>").append(EOL);
        xml.append("</Configure>").append(EOL);

        try (FileWriter configWriter = new FileWriter(aArgsArray[0])) {
            configWriter.write(xml.toString());
        }
    }

}
