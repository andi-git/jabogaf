package org.jabogaf.core.gamecontext;

import org.jabogaf.api.gamecontext.GameContextRunner;
import org.jabogaf.core.gamecontext.bean.BeanWithGameScoped;
import org.jabogaf.test.gamecontext.ArquillianGameContextTest;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import java.util.UUID;

@RunWith(Arquillian.class)
public class GameContextCacheTest extends ArquillianGameContextTest {

    @Inject
    private GameContextRunner gameContextRunner;

    @Test
    public void testGameContextCache() throws Throwable {

        final String[] objectReference = new String[3];
        UUID gameContextId1 = gameContextRunner.runInGameContext(null, getBeanManager(), (gameContextId) -> {
            GameContextCache gameContextCache = CDI.current().select(GameContextCache.class).get();
            BeanWithGameScoped beanWithGameScoped = gameContextCache.getCurrentGameContextInstance().getFromDynamicContext(BeanWithGameScoped.class);
            objectReference[0] = beanWithGameScoped.toString();
            return gameContextId;
        });
        UUID gameContextId2 = gameContextRunner.runInGameContext(null, getBeanManager(), (gameContextId) -> {
            GameContextCache gameContextCache = CDI.current().select(GameContextCache.class).get();
            BeanWithGameScoped beanWithGameScoped = gameContextCache.getCurrentGameContextInstance().getFromDynamicContext(BeanWithGameScoped.class);
            objectReference[1] = beanWithGameScoped.toString();
            return gameContextId;
        });
        UUID gameContextId3 = gameContextRunner.runInGameContext(gameContextId1, getBeanManager(), (gameContextId) -> {
            GameContextCache gameContextCache = CDI.current().select(GameContextCache.class).get();
            BeanWithGameScoped beanWithGameScoped = gameContextCache.getCurrentGameContextInstance().getFromDynamicContext(BeanWithGameScoped.class);
            objectReference[2] = beanWithGameScoped.toString();
            return gameContextId;
        });
        Assert.assertEquals(gameContextId1, gameContextId3);
        Assert.assertNotEquals(gameContextId1, gameContextId2);
        Assert.assertEquals(objectReference[0], objectReference[2]);
        Assert.assertNotEquals(objectReference[0], objectReference[1]);
    }
}
