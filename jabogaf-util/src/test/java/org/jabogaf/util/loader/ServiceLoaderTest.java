package org.jabogaf.util.loader;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class ServiceLoaderTest {

    @Test
    public void testLoad() throws Exception {
        ServiceLoader.load(MyInterfaceWithImplementation.class).foo();
    }

    @Test(expected = RuntimeException.class)
    public void testLoadNotAvailable() throws Exception {
        ServiceLoader.load(MyInterfaceWithoutImplementation.class).foo();
    }
}