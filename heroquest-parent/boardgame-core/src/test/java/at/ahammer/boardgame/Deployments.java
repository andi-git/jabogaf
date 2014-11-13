package at.ahammer.boardgame;

import org.eu.ingwar.tools.arquillian.extension.suite.annotations.ArquillianSuiteDeployment;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;

@ArquillianSuiteDeployment
public class Deployments {

    @Deployment
    public static WebArchive deploy() {
        WebArchive webArchive = ShrinkWrap.create(WebArchive.class, "test.war").//
                addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml").
                addAsResource("META-INF/services/javax.enterprise.inject.spi.Extension", "META-INF/services/javax.enterprise.inject.spi.Extension").//
                addPackages(true, "at.ahammer");
        System.out.println(webArchive.toString(true));
        return webArchive;
    }

}
