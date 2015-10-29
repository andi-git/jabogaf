package org.jabogaf.util;

import org.eu.ingwar.tools.arquillian.extension.suite.annotations.ArquillianSuiteDeployment;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;

import java.io.File;
import java.io.IOException;

@ArquillianSuiteDeployment
public class Deployments {

    @Deployment
    public static WebArchive deploy() throws IOException {
        return ShrinkWrap.create(WebArchive.class, "test.war").//
                addAsWebInfResource("META-INF/beans.xml", "beans.xml").
                addAsWebInfResource(new File("src/test/resources/META-INF/services/", "org.jabogaf.util.loader.MyInterfaceWithImplementation"), "services/org.jabogaf.util.loader.MyInterfaceWithImplementation").
                addPackages(true, "org.jabogaf.util").
                addPackages(true, "org.jabogaf.core");
    }

}
