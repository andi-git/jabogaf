package at.ahammer.boardgame.cdi;

import at.ahammer.boardgame.test.util.ActivateAlternatives;
import at.ahammer.boardgame.test.util.ArquillianGameContext;
import at.ahammer.boardgame.test.util.ArquillianGameContextTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.UUID;

/**
 * Created by andreas on 09.08.14.
 */
@RunWith(ArquillianGameContext.class)
@ActivateAlternatives({GameContextDeleteTimerAlternative.class, GameContextCacheDeleteStrategyAlternative.class})
public class GameContextCacheDeleteTest extends ArquillianGameContextTest {

    @Inject
    private BeanManager beanManager;

    @Test
    public void testDeleteTimer() throws Throwable {
        Field gameContextCacheField = GameContext.class.getDeclaredField("gameContextCache");
        gameContextCacheField.setAccessible(true);
        ((Map<UUID, GameContextInstance>) gameContextCacheField.get(GameContext.class)).clear();

        // create a gameContext
        UUID gameContext1 = GameContext.run(null, beanManager, (contextId) -> {
            System.out.println("1");
            return contextId;
        });
        // cache should be the created one
        Assert.assertEquals(1, ((Map<UUID, GameContextInstance>) gameContextCacheField.get(GameContext.class)).size());

        // wait
        Thread.sleep(2000);

        // create another gameContext
        UUID gameContext2 = GameContext.run(null, beanManager, (contextId) -> {
            System.out.println("2");
            return contextId;
        });
        // cache should be once again one, because the first gameContext is was old and removed
        Assert.assertEquals(1, ((Map<UUID, GameContextInstance>) gameContextCacheField.get(GameContext.class)).size());

        // wait
        Thread.sleep(2000);

        // reuse the same context, that has not to be removed
        GameContext.run(gameContext2, beanManager, (contextId) -> {
            System.out.println("3");
            return contextId;
        });
        // cache should be once again one, because the context was reused and so not removed
        Assert.assertEquals(1, ((Map<UUID, GameContextInstance>) gameContextCacheField.get(GameContext.class)).size());
    }
}
