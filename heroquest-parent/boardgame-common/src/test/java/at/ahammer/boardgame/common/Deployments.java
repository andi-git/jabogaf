package at.ahammer.boardgame.common;

import at.ahammer.boardgame.core.controller.PlayerControllerBasic;
import org.eu.ingwar.tools.arquillian.extension.suite.annotations.ArquillianSuiteDeployment;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;

import java.io.File;

@ArquillianSuiteDeployment
public class Deployments {

    @Deployment
    public static WebArchive deploy() {
        WebArchive webArchive = ShrinkWrap.create(WebArchive.class, "test.war").//
                addAsWebInfResource("META-INF/beans.xml", "beans.xml").
//                addAsResource("META-INF/services/javax.enterprise.inject.spi.Extension", "META-INF/services/javax.enterprise.inject.spi.Extension").//
//                addAsResource("META-INF/services/at.ahammer.boardgame.cdi.GameContextManager", "META-INF/services/at.ahammer.boardgame.cdi.GameContextManager").//
                addPackages(true, "at.ahammer.boardgame.api").
                addPackages(true, "at.ahammer.boardgame.core").
                addPackages(true, "at.ahammer.boardgame.common");
//                addAsLibraries(new File(new File("").getAbsolutePath(), "target/arquillianLibs").listFiles());;
        System.out.println(webArchive.toString(true));
        return webArchive;
    }

}
