package at.ahammer.boardgame.cdi;

/**
 * Created by andreas on 02.08.14.
 */
public abstract class NewInstanceInGameContext {

    private final String id;

    public NewInstanceInGameContext(String id) {
        if (id == null || "".equals(id)) {
            throw new IllegalArgumentException("id must have a value!");
        }
        if (GameContext.current().getNewInstancesInGameContext().stream().filter(b -> b.getId().equals(id)).count() > 0) {
            throw new IllegalStateException("id '" + id + "' already in use");
        }
        this.id = id;
        GameContext.addNewInstanceInGameContext(this);
    }

    public String getId() {
        return id;
    }

    protected <T> T fromGameContext(Class<T> clazz) {
        // get from CDI-context
        T result = GameContext.current().getFromDynamicContext(clazz);
        if (result == null) {
            // get from all instances created within the current cdi-context
            return GameContext.current().getNewInstanceInGameContext(clazz);
        }
        return result;
    }

    protected NewInstanceInGameContext fromGameContext(String id) {
        return GameContext.current().getNewInstanceInGameContext(id);
    }

    protected <T> T fromGameContext(Class<T> clazz, String id) {
        return (T) GameContext.current().getNewInstanceInGameContext(clazz, id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NewInstanceInGameContext that = (NewInstanceInGameContext) o;

        if (!id.equals(that.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return id;
    }

}
