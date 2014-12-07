package at.ahammer.boardgame.util;

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
                addAsWebInfResource("META-INF/beans.xml", "beans.xml").
//                addAsResource("META-INF/services/javax.enterprise.inject.spi.Extension", "META-INF/services/javax.enterprise.inject.spi.Extension").//
//                addAsResource("META-INF/services/at.ahammer.boardgame.api.cdi.GameContextManager", "META-INF/services/at.ahammer.boardgame.api.cdi.GameContextManager").//
                addPackages(true, "at.ahammer.boardgame.util").
                addPackages(true, "at.ahammer.boardgame.core");
        System.out.println(webArchive.toString(true));
        return webArchive;
    }

}