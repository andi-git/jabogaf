package org.jabogaf.util.log;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;

import javax.inject.Inject;

import static org.junit.Assert.assertNotNull;

@RunWith(Arquillian.class)
public class LogTest {

    @Inject
    @SLF4J
    private Logger log;

    @Inject
    private LogProducer logProducer;

    @Test
    public void testLog() {
        assertNotNull(log);
        assertNotNull(logProducer.produce(LogTest.class));
    }
}
