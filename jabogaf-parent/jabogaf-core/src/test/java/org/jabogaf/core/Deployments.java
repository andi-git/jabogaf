package org.jabogaf.core;

import org.eu.ingwar.tools.arquillian.extension.suite.annotations.ArquillianSuiteDeployment;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;

import java.io.IOException;

@ArquillianSuiteDeployment
public class Deployments {

    @Deployment
    public static WebArchive deploy() throws IOException {
        WebArchive webArchive = ShrinkWrap.create(WebArchive.class, "test.war").//
                addAsWebInfResource("META-INF/beans.xml", "META-INF/beans.xml").
                addAsResource("META-INF/services/javax.enterprise.inject.spi.Extension", "META-INF/services/javax.enterprise.inject.spi.Extension").//
                addAsResource("META-INF/services/org.jabogaf.api.cdi.GameContextManager", "META-INF/services/org.jabogaf.api.cdi.GameContextManager").//
                addPackages(true, "org.jabogaf.api").
                addPackages(true, "org.jabogaf.core").
                addPackages(true, "org.jabogaf.util");
//        System.out.println(webArchive.toString(true));
        return webArchive;
    }

}
