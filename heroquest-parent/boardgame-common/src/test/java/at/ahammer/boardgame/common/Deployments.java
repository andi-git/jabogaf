package at.ahammer.boardgame.common;

import org.eu.ingwar.tools.arquillian.extension.suite.annotations.ArquillianSuiteDeployment;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;

@ArquillianSuiteDeployment
public class Deployments {

    @Deployment
    public static WebArchive deploy() {
        WebArchive webArchive = ShrinkWrap.create(WebArchive.class, "test.war").//
                addAsWebInfResource("META-INF/beans.xml", "META-INF/beans.xml").
                addPackages(true, "at.ahammer.boardgame.api").
                addPackages(true, "at.ahammer.boardgame.core").
                addPackages(true, "at.ahammer.boardgame.common").
                addPackages(true, "at.ahammer.boardgame.util");
//        System.out.println(webArchive.toString(true));
        return webArchive;
    }

}
