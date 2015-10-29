package org.jabogaf.common;

import org.eu.ingwar.tools.arquillian.extension.suite.annotations.ArquillianSuiteDeployment;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;

@ArquillianSuiteDeployment
public class Deployments {

    @Deployment
    public static WebArchive deploy() {
        return ShrinkWrap.create(WebArchive.class, "test.war").//
                addAsWebInfResource("META-INF/beans.xml", "META-INF/beans.xml").
                addPackages(true, "org.jabogaf.api").
                addPackages(true, "org.jabogaf.core").
                addPackages(true, "org.jabogaf.common").
                addPackages(true, "org.jabogaf.util");
    }

}
