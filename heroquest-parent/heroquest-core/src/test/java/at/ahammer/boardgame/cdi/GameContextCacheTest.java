package at.ahammer.boardgame.cdi;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;
import java.util.UUID;

/**
 * Created by ahammer on 06.08.2014.
 */
@RunWith(Arquillian.class)
public class GameContextCacheTest {

    @Inject
    private BeanManager beanManager;

    @Test
    public void testGameContextCache() throws Throwable {
        final String[] objectReference = new String[3];
        UUID gameContextId1 = GameContext.run(beanManager, (gameContextId) -> {
            BeanWithGameScoped beanWithGameScoped = BeanProvider.getBean(beanManager, BeanWithGameScoped.class);
            objectReference[0] = beanWithGameScoped.toString();
            return gameContextId;
        });
        UUID gameContextId2 = GameContext.run(beanManager, (gameContextId) -> {
            BeanWithGameScoped beanWithGameScoped = BeanProvider.getBean(beanManager, BeanWithGameScoped.class);
            objectReference[1] = beanWithGameScoped.toString();
            return gameContextId;
        });
        UUID gameContextId3 = GameContext.run(gameContextId1, beanManager, (gameContextId) -> {
            BeanWithGameScoped beanWithGameScoped = BeanProvider.getBean(beanManager, BeanWithGameScoped.class);
            objectReference[2] = beanWithGameScoped.toString();
            return gameContextId;
        });
        Assert.assertEquals(gameContextId1, gameContextId3);
        Assert.assertNotEquals(gameContextId1, gameContextId2);
        Assert.assertEquals(objectReference[0], objectReference[2]);
        Assert.assertNotEquals(objectReference[0], objectReference[1]);
    }
}
