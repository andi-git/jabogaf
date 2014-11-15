package at.ahammer.boardgame.core.cdi;

import at.ahammer.boardgame.core.cdi.GameContext;
import at.ahammer.boardgame.core.cdi.GameContextCacheDeleteStrategy;
import at.ahammer.boardgame.core.cdi.GameContextDeleteTimer;
import at.ahammer.boardgame.core.cdi.GameContextInstance;
import at.ahammer.boardgame.core.test.AfterInGameContext;
import at.ahammer.boardgame.core.test.ArquillianGameContext;
import at.ahammer.boardgame.core.test.ArquillianGameContextTest;
import at.ahammer.boardgame.core.test.BeforeInGameContext;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.UUID;

@RunWith(ArquillianGameContext.class)
public class GameContextCacheDeleteTest extends ArquillianGameContextTest {

    @Inject
    private GameContextDeleteTimer gameContextDeleteTimer;

    @Inject
    private GameContextCacheDeleteStrategy gameContextCacheDeleteStrategy;

    @BeforeInGameContext
    public void before() {
        gameContextDeleteTimer.setCurrentDuration(1);
        gameContextCacheDeleteStrategy.setUnusedDuration(1);
    }

    @AfterInGameContext
    public void after() {
        gameContextDeleteTimer.resetDuration();
        gameContextCacheDeleteStrategy.resetUnusedDuration();
    }

    @Test
    public void testDeleteTimer() throws Throwable {
        Field gameContextCacheField = GameContext.class.getDeclaredField("gameContextCache");
        gameContextCacheField.setAccessible(true);
        ((Map<UUID, GameContextInstance>) gameContextCacheField.get(GameContext.class)).clear();

        // create a gameContext
        UUID gameContext1 = GameContext.run(null, getBeanManager(), (contextId) -> {
            System.out.println("1");
            return contextId;
        });
        // cache should be the created one
        Assert.assertEquals(1, ((Map<UUID, GameContextInstance>) gameContextCacheField.get(GameContext.class)).size());

        // wait
        Thread.sleep(2000);

        // create another gameContext
        UUID gameContext2 = GameContext.run(null, getBeanManager(), (contextId) -> {
            System.out.println("2");
            return contextId;
        });
        // cache should be once again one, because the first gameContext is was old and removed
        Assert.assertEquals(1, ((Map<UUID, GameContextInstance>) gameContextCacheField.get(GameContext.class)).size());

        // wait
        Thread.sleep(2000);

        // reuse the same context, that has not to be removed
        GameContext.run(gameContext2, getBeanManager(), (contextId) -> {
            System.out.println("3");
            return contextId;
        });
        // cache should be once again one, because the context was reused and so not removed
        Assert.assertEquals(1, ((Map<UUID, GameContextInstance>) gameContextCacheField.get(GameContext.class)).size());
    }
}
