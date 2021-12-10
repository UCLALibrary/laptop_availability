package edu.ucla.library.libservices.clicc.laptops.util;

import java.io.IOException;

import org.junit.jupiter.api.Test;

/**
 * Tests the JettyEnvGenerator.
 */
class JettyEnvGeneratorTest {

    @Test
    final void testJettyEnvGenerator() throws IOException {
        JettyEnvGenerator.main(new String[] { "target/jetty-env.xml" });
    }

}
