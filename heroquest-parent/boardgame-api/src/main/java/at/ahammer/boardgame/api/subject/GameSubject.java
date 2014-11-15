package at.ahammer.boardgame.api.subject;

import at.ahammer.boardgame.api.artifact.Artifact;
import at.ahammer.boardgame.api.artifact.weapon.WeaponType;
import at.ahammer.boardgame.api.behavior.look.LookBehavior;
import at.ahammer.boardgame.api.behavior.look.LookNotPossibleException;
import at.ahammer.boardgame.api.behavior.move.FieldsNotConnectedException;
import at.ahammer.boardgame.api.behavior.move.MoveBehavior;
import at.ahammer.boardgame.api.behavior.move.MoveNotPossibleException;
import at.ahammer.boardgame.api.cdi.GameContextBean;
import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.subject.artifact.hand.ArtifactHandManager;

import javax.inject.Inject;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * A subject (i.e. hero, monster,...) in the game.
 */
@SuppressWarnings("ALL")
public abstract class GameSubject extends GameContextBean {

    private final Set<WeaponType> weaponTypes = new HashSet<>();
    private Artifact leftHand;
    private Artifact rightHand;
    private Field position;
    @Inject
    private ArtifactHandManager artifactHandManager;

    public GameSubject(String id, Field position) {
        super(id);
        this.position = position;
    }

    public Artifact getLeftHand() {
        return leftHand;
    }

    public Artifact getRightHand() {
        return rightHand;
    }

    public Set<WeaponType> getWeaponTypes() {
        return Collections.unmodifiableSet(weaponTypes);
    }

    protected void addWeaponType(WeaponType weaponType) {
        weaponTypes.add(weaponType);
    }

    protected void removeWeaponType(WeaponType weaponType) {
        weaponTypes.remove(weaponType);
    }

    protected void clearWeaponTypes(WeaponType weaponType) {
        weaponTypes.clear();
    }

    public boolean canHandle(WeaponType weaponType) {
        return weaponTypes.contains(weaponType);
    }

    /**
     * Move the {@link GameSubject} from the current {@code position} to a {@link
     * at.ahammer.boardgame.api.board.field.Field} defined by the parameter {@code target}.
     * <p/>
     * The result is influenced by the available {@link at.ahammer.boardgame.api.behavior.move.MoveBehavior}.
     *
     * @param target the {@link at.ahammer.boardgame.api.board.field.Field} to move the {@link
     *               GameSubject} to
     * @throws FieldsNotConnectedException if the {@link at.ahammer.boardgame.api.board.field.Field}s {@code position} and
     *                                     {@code target} are not connected
     * @throws MoveNotPossibleException    if the move from {@code position} to {@code target} is not possible, because
     *                                     it is blocked by a {@link at.ahammer.boardgame.api.object.GameObject} or
     *                                     something
     */
    public abstract void move(Field target) throws FieldsNotConnectedException, MoveNotPossibleException;

    /**
     * Check if the {@link GameSubject} can move from the current {@code position} to
     * another {@link at.ahammer.boardgame.api.board.field.Field} defined by the assigned {@code target}.
     * <p/>
     * The result is influenced by the available {@link at.ahammer.boardgame.api.behavior.move.MoveBehavior}.
     *
     * @param target the {@link at.ahammer.boardgame.api.board.field.Field} to move to
     * @return {@code true} if the move is possible
     */
    public abstract boolean canMove(Field target);

    /**
     * Look to from the current {@code position}of the {@link GameSubject} to target {@link
     * at.ahammer.boardgame.api.board.field.Field} defined by the {@code target}.
     * <p/>
     * The result is influenced by the available {@link at.ahammer.boardgame.api.behavior.look.LookBehavior}.
     *
     * @param target the {@link at.ahammer.boardgame.api.board.field.Field} to move the {@link
     *               GameSubject} to
     * @throws at.ahammer.boardgame.api.behavior.look.LookNotPossibleException if the move from {@code position} to {@code target} is not possible, because it
     *                                  is blocked by a {@link at.ahammer.boardgame.api.object.GameObject} or something
     */
    public abstract void look(Field target) throws LookNotPossibleException;

    /**
     * Check if the {@link GameSubject} can look from the current {@code position} to
     * another {@link at.ahammer.boardgame.api.board.field.Field} defined by the assigned {@code target}.
     * <p/>
     * The result is influenced by the available {@link at.ahammer.boardgame.api.behavior.look.LookBehavior}.
     *
     * @param target the {@link at.ahammer.boardgame.api.board.field.Field} to move to
     * @return {@code true} if the look is possible
     */
    public abstract boolean canLook(Field target);

    public Field getPosition() {
        return position;
    }

    /**
     * Check if the current {@link GameSubject} can handle (can use) the assigned {@link
     * at.ahammer.boardgame.api.artifact.Artifact}.
     *
     * @param artifact the {@link at.ahammer.boardgame.api.artifact.Artifact} to add
     * @return {@code true} it the {@link GameSubject} can handle (can use) the {@link
     * at.ahammer.boardgame.api.artifact.Artifact}
     * @see at.ahammer.boardgame.api.subject.artifact.hand.ArtifactHandManager#canHandle(Artifact, GameSubject)
     */
    public boolean canHandle(Artifact artifact) {
        return artifactHandManager.canHandle(artifact, this);
    }

    /**
     * Add an {@link at.ahammer.boardgame.api.artifact.Artifact} to the hand of the current {@link
     * GameSubject}
     * <p/>
     * Every {@link GameSubject} has a {@link java.util.Set} of {@link
     * ArtifactHandStrategy} where it is defined, which {@link at.ahammer.boardgame.api.artifact.Artifact} can be used.
     *
     * @param artifact the {@link at.ahammer.boardgame.api.artifact.Artifact} to add
     * @see at.ahammer.boardgame.api.subject.artifact.hand.ArtifactHandManager#addArtifact(Artifact, GameSubject)
     */
    public void addArtifact(Artifact artifact) {
        artifactHandManager.addArtifact(artifact, this);
    }

    public abstract MoveBehavior getMoveBehavior();

    public abstract LookBehavior getLookBehavior();

    public abstract void changeMoveBehavior(MoveBehavior moveBehavior);

    public abstract void changeLookBehavior(LookBehavior lookBehavior);
}