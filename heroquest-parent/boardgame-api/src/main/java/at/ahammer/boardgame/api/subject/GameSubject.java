package at.ahammer.boardgame.api.subject;

import at.ahammer.boardgame.api.artifact.Artifact;
import at.ahammer.boardgame.api.behavior.look.LookBehavior;
import at.ahammer.boardgame.api.behavior.move.FieldsNotConnectedException;
import at.ahammer.boardgame.api.behavior.move.MoveBehavior;
import at.ahammer.boardgame.api.behavior.move.MoveNotPossibleException;
import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.cdi.GameContextBean;
import at.ahammer.boardgame.api.subject.artifact.hand.AddArtifactToHandStrategyContext;
import at.ahammer.boardgame.api.subject.artifact.hand.ArtifactHandManager;
import at.ahammer.boardgame.api.subject.artifact.hand.ArtifactHandlingException;
import at.ahammer.boardgame.api.subject.artifact.hand.ArtifactHandlingStrategy;
import at.ahammer.boardgame.api.subject.hand.Hand;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * A subject (i.e. hero, monster,...) in the game.
 */
@SuppressWarnings("CdiManagedBeanInconsistencyInspection")
public abstract class GameSubject extends GameContextBean {

    private final List<ArtifactHandlingStrategy> artifactHandlingStrategies = new ArrayList<>();
    private Hand mainHand = new Hand(Hand.Type.MAIN);
    private Hand offHand = new Hand(Hand.Type.OFF);
    private Field position;
    @Inject
    private ArtifactHandManager artifactHandManager;

    public GameSubject(String id, Field position) {
        super(id);
        this.position = position;
    }

    public Artifact getMainHandArtifact() {
        return mainHand.getArtifact();
    }

    public Artifact getOffHandArtifact() {
        return offHand.getArtifact();
    }

    public List<ArtifactHandlingStrategy> getArtifactHandlingStrategies() {
        return Collections.unmodifiableList(artifactHandlingStrategies);
    }

    protected void addArtifactHandlingStrategy(ArtifactHandlingStrategy artifactHandlingStrategy) {
        artifactHandlingStrategies.add(artifactHandlingStrategy);
    }

    protected void removeArtifactHandlingStrategy(ArtifactHandlingStrategy artifactHandlingStrategy) {
        artifactHandlingStrategies.remove(artifactHandlingStrategy);
    }

    protected void clearArtifactHandlingStrategies() {
        artifactHandlingStrategies.clear();
    }

    public boolean canHandle(ArtifactHandlingStrategy artifactHandlingStrategy) {
        return artifactHandlingStrategies.contains(artifactHandlingStrategy);
    }

    /**
     * Move the {@link GameSubject} from the current {@code position} to a {@link at.ahammer.boardgame.api.board.field.Field}
     * defined by the parameter {@code target}.
     * <p/>
     * The result is influenced by the available {@link at.ahammer.boardgame.api.behavior.move.MoveBehavior}.
     *
     * @param target the {@link at.ahammer.boardgame.api.board.field.Field} to move the {@link GameSubject} to
     * @return the new position as {@link Field}
     * @throws FieldsNotConnectedException if the {@link at.ahammer.boardgame.api.board.field.Field}s {@code position}
     *                                     and {@code target} are not connected
     * @throws MoveNotPossibleException    if the move from {@code position} to {@code target} is not possible, because
     *                                     it is blocked by a {@link at.ahammer.boardgame.api.object.GameObject} or
     *                                     something
     * @see at.ahammer.boardgame.api.behavior.move.MoveBehavior#move(at.ahammer.boardgame.api.subject.SetterOfPosition, at.ahammer.boardgame.api.board.field.Field)
     */
    public Field move(Field target) throws FieldsNotConnectedException, MoveNotPossibleException {
        return getMoveBehavior().move(createSetterOfPosition(), target);
    }

    /**
     * Check if the {@link GameSubject} can move from the current {@code position} to another {@link
     * at.ahammer.boardgame.api.board.field.Field} defined by the assigned {@code target}.
     * <p/>
     * The result is influenced by the available {@link at.ahammer.boardgame.api.behavior.move.MoveBehavior}.
     *
     * @param target the {@link at.ahammer.boardgame.api.board.field.Field} to move to
     * @return {@code true} if the move is possible
     * @see at.ahammer.boardgame.api.behavior.move.MoveBehavior#canMove(at.ahammer.boardgame.api.board.field.Field,
     * at.ahammer.boardgame.api.board.field.Field)
     */
    public boolean canMove(Field target) {
        return getMoveBehavior().canMove(getPosition(), target);
    }

    /**
     * Get a list of all {@link at.ahammer.boardgame.api.board.field.Field}s that can be moved to.
     *
     * @return a list of {@link at.ahammer.boardgame.api.board.field.Field}s that can be moved to
     * @see at.ahammer.boardgame.api.behavior.move.MoveBehavior#getMovableFields(at.ahammer.boardgame.api.board.field.Field)
     */
    public Set<Field> getMovableFields() {
        return getMoveBehavior().getMovableFields(position);
    }

    /**
     * Check if the {@link GameSubject} can look from the current {@code position} to another {@link
     * at.ahammer.boardgame.api.board.field.Field} defined by the assigned {@code target}.
     * <p/>
     * The result is influenced by the available {@link at.ahammer.boardgame.api.behavior.look.LookBehavior}.
     *
     * @param target the {@link at.ahammer.boardgame.api.board.field.Field} to move to
     * @return {@code true} if the look is possible
     * @see at.ahammer.boardgame.api.behavior.look.LookBehavior#canLook(at.ahammer.boardgame.api.board.field.Field,
     * at.ahammer.boardgame.api.board.field.Field)
     */
    public boolean canLook(Field target) {
        return getLookBehavior().canLook(getPosition(), target);
    }

    /**
     * Get a list of all {@link at.ahammer.boardgame.api.board.field.Field}s that can be looked to.
     *
     * @return a list of {@link at.ahammer.boardgame.api.board.field.Field}s that can be looked to
     * @see at.ahammer.boardgame.api.behavior.look.LookBehavior#getLookableFields(at.ahammer.boardgame.api.board.field.Field)
     */
    public Set<Field> getLookableFields() {
        return getLookBehavior().getLookableFields(getPosition());
    }

    public Field getPosition() {
        return position;
    }

    protected void setPosition(Field position) {
        this.position = position;
    }

    /**
     * @see #canHandle(at.ahammer.boardgame.api.artifact.Artifact, at.ahammer.boardgame.api.subject.hand.Hand.Type)
     */
    public boolean canHandle(Artifact artifact) {
        return canHandle(artifact, Hand.Type.MAIN);
    }

    /**
     * Check if the current {@link GameSubject} can handle (can use) the assigned {@link
     * at.ahammer.boardgame.api.artifact.Artifact}.
     *
     * @param artifact the {@link at.ahammer.boardgame.api.artifact.Artifact} to add
     * @param handType the {@link at.ahammer.boardgame.api.subject.hand.Hand.Type} to add the {@link
     *                 at.ahammer.boardgame.api.artifact.Artifact} to
     * @return {@code true} it the {@link GameSubject} can handle (can use) the {@link
     * at.ahammer.boardgame.api.artifact.Artifact}
     * @see at.ahammer.boardgame.api.subject.artifact.hand.ArtifactHandManager#canHandle(at.ahammer.boardgame.api.subject.artifact.hand.AddArtifactToHandStrategyContext)
     */
    public boolean canHandle(Artifact artifact, Hand.Type handType) {
        return artifactHandManager.canHandle(createAddArtifactToHandStrategyContext(artifact, handType));
    }

    /**
     * @see #addArtifact(at.ahammer.boardgame.api.artifact.Artifact, at.ahammer.boardgame.api.subject.hand.Hand.Type)
     */
    public void addArtifact(Artifact artifact) throws ArtifactHandlingException {
        addArtifact(artifact, Hand.Type.MAIN);
    }

    /**
     * Check if the current {@link GameSubject} can handle (can use) the assigned {@link
     * at.ahammer.boardgame.api.artifact.Artifact}.
     *
     * @param artifact the {@link at.ahammer.boardgame.api.artifact.Artifact} to add
     * @param handType the {@link at.ahammer.boardgame.api.subject.hand.Hand.Type} to add the {@link
     *                 at.ahammer.boardgame.api.artifact.Artifact} to
     * @return {@code true} it the {@link GameSubject} can handle (can use) the {@link
     * at.ahammer.boardgame.api.artifact.Artifact}
     * @throws at.ahammer.boardgame.api.subject.artifact.hand.ArtifactHandlingException if the handling is not possible
     *                                                                                  -> check via {@link #canHandle(at.ahammer.boardgame.api.artifact.Artifact,
     *                                                                                  at.ahammer.boardgame.api.subject.hand.Hand.Type)}
     *                                                                                  first!
     * @see at.ahammer.boardgame.api.subject.artifact.hand.ArtifactHandManager#addArtifact(at.ahammer.boardgame.api.subject.artifact.hand.AddArtifactToHandStrategyContext))
     */
    public void addArtifact(Artifact artifact, Hand.Type handType) throws ArtifactHandlingException {
        artifactHandManager.addArtifact(createAddArtifactToHandStrategyContext(artifact, handType));
    }

    private AddArtifactToHandStrategyContext createAddArtifactToHandStrategyContext(Artifact artifact, Hand.Type handType) {
        return new AddArtifactToHandStrategyContext().setArtifact(artifact).
                setHandType(handType).
                setGameSubject(this).
                setSetterOfArtifactsForHands(createSetterOfArtifactsForHands());
    }

    private SetterOfArtifactsForHands createSetterOfArtifactsForHands() {
        return (artifactForMainHand, artifactForOffHand) -> {
            mainHand.setArtifact(artifactForMainHand);
            offHand.setArtifact(artifactForOffHand);
        };
    }

    private SetterOfPosition createSetterOfPosition() {
        return (position) -> {
            this.position = position;
        };
    }

    public abstract MoveBehavior getMoveBehavior();

    public abstract LookBehavior getLookBehavior();

    protected abstract void changeMoveBehavior(MoveBehavior moveBehavior);

    protected abstract void changeLookBehavior(LookBehavior lookBehavior);

    protected Hand getMainHand() {
        return mainHand;
    }

    protected Hand getOffHand() {
        return offHand;
    }
}