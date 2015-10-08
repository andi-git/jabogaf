package org.jabogaf.util.log;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class LogWrapperTest {

    @Inject
    @SLF4J
    private LogWrapper logWrapper;

    @Test
    public void testLog() throws Exception {
        logWrapper.log().info("test");
    }

    @Test
    public void testDebug() throws Exception {
        logWrapper.debug("test {}", () -> "debug");
        logWrapper.debug("test {}", "debug");
    }

    @Test
    public void testInfo() throws Exception {
        logWrapper.info("test {}", () -> "info");
        logWrapper.info("test {}", "info");
    }

    @Test
    public void testWarn() throws Exception {
        logWrapper.warn("test {}", () -> "warn");
        logWrapper.warn("test {}", "warn");
    }

    @Test
    public void testError() throws Exception {
        logWrapper.error("test {}", () -> "error");
        logWrapper.error("test {}", "error");
    }
}