package org.jabogaf.core.subject;

import org.jabogaf.api.artifact.Artifact;
import org.jabogaf.api.behavior.look.CanLookReport;
import org.jabogaf.api.behavior.look.LookBehavior;
import org.jabogaf.api.behavior.look.LookBehaviorType;
import org.jabogaf.api.behavior.move.*;
import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.resource.*;
import org.jabogaf.api.state.GameState;
import org.jabogaf.api.subject.GameSubject;
import org.jabogaf.api.subject.SetterOfPosition;
import org.jabogaf.api.subject.artifact.ArtifactHolder;
import org.jabogaf.api.subject.artifact.hand.ArtifactHandlingException;
import org.jabogaf.api.subject.artifact.hand.ArtifactHandlingStrategy;
import org.jabogaf.api.subject.hand.Hand;
import org.jabogaf.core.behavior.look.LookBehaviorNull;
import org.jabogaf.core.behavior.move.MoveBehaviorNull;
import org.jabogaf.core.gamecontext.GameContextBeanBasic;
import org.jabogaf.core.gamecontext.GameContextBeanWithStateBasic;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Typed;
import javax.inject.Inject;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

/**
 * A subject (i.e. hero, monster,...) in the game.
 */
@SuppressWarnings("CdiManagedBeanInconsistencyInspection")
@Typed
public class GameSubjectBasic extends GameContextBeanWithStateBasic<GameSubject> implements GameSubject {

    @Inject
    private State state;

    @Inject
    private ArtifactHolder artifactHolder;

    @Inject
    private Resources resources;

    private MoveBehavior moveBehavior;

    private LookBehavior lookBehavior;

    @SuppressWarnings("CdiInjectionPointsInspection")
    @Inject
    @MoveBehaviorType(MoveBehaviorNull.class)
    private MoveBehavior moveBehaviorNull;

    @SuppressWarnings("CdiInjectionPointsInspection")
    @Inject
    @LookBehaviorType(LookBehaviorNull.class)
    private LookBehavior lookBehaviorNull;

    public GameSubjectBasic(String id, Field position) {
        this(id, position, null, null);
    }

    public GameSubjectBasic(String id, Field position, MoveBehavior moveBehavior, LookBehavior lookBehavior) {
        super(id);
        if (position == null) {
            throw new IllegalStateException("'position' must not be null");
        }
        this.state.setPosition(position);
        this.moveBehavior = moveBehavior != null ? moveBehavior : moveBehaviorNull;
        this.lookBehavior = lookBehavior != null ? lookBehavior : lookBehaviorNull;
    }

    protected void changeMoveBehavior(MoveBehavior moveBehavior) {
        this.moveBehavior = moveBehavior;
    }

    protected void changeLookBehavior(LookBehavior lookBehavior) {
        this.lookBehavior = lookBehavior;
    }

    protected SetterOfPosition getSetterOfPosition() {
        return this.state::setPosition;
    }

    @Override
    public Artifact getMainHandArtifact() {
        return artifactHolder.getMainHandArtifact();
    }

    @Override
    public Artifact getOffHandArtifact() {
        return artifactHolder.getOffHandArtifact();
    }

    @Override
    public List<ArtifactHandlingStrategy> getArtifactHandlingStrategies() {
        return artifactHolder.getArtifactHandlingStrategies();
    }

    @Override
    public boolean canHandle(ArtifactHandlingStrategy artifactHandlingStrategy) {
        return artifactHolder.canHandle(artifactHandlingStrategy);
    }

    @Override
    public boolean canHandle(Artifact artifact) {
        return artifactHolder.canHandle(artifact);
    }

    @Override
    public boolean canHandle(Artifact artifact, Hand.Type handType) {
        return artifactHolder.canHandle(artifact, handType);
    }

    @Override
    public void addArtifact(Artifact artifact) throws ArtifactHandlingException {
        artifactHolder.addArtifact(artifact);
    }

    @Override
    public void addArtifact(Artifact artifact, Hand.Type handType) throws ArtifactHandlingException {
        artifactHolder.addArtifact(artifact, handType);
    }

    @Override
    public void addArtifactHandlingStrategy(ArtifactHandlingStrategy artifactHandlingStrategy) {
        artifactHolder.addArtifactHandlingStrategy(artifactHandlingStrategy);
    }

    @Override
    public void removeArtifactHandlingStrategy(ArtifactHandlingStrategy artifactHandlingStrategy) {
        artifactHolder.removeArtifactHandlingStrategy(artifactHandlingStrategy);
    }

    @Override
    public void clearArtifactHandlingStrategies() {
        artifactHolder.clearArtifactHandlingStrategies();
    }

    @Override
    public void resetHands() {
        artifactHolder.resetHands();
    }

    @Override
    public CanLookReport canLook(Field target) {
        return lookBehavior.canLook(this, target);
    }

    @Override
    public Set<Field> getLookableFields() {
        return lookBehavior.getLookableFields(this);
    }

    @Override
    public LookBehavior getLookBehavior() {
        return lookBehavior;
    }

    @Override
    public Field getPosition() {
        return this.state.getPosition();
    }

    protected void setPosition(Field position) {
        this.state.setPosition(position);
    }

    @Override
    public MoveBehavior getMoveBehavior() {
        return moveBehavior;
    }

    @Override
    public Field move(Field target, ResourceHolder resourceHolder) throws FieldsNotConnectedException, MoveNotPossibleException, NotEnoughResourceException {
        return moveBehavior.move(this, getSetterOfPosition(), target, resourceHolder);
    }

    @Override
    public Field move(MovePath movePath, ResourceHolder resourceHolder) throws FieldsNotConnectedException, MoveNotPossibleException, NotEnoughResourceException {
        return moveBehavior.move(this, getSetterOfPosition(), movePath, resourceHolder);
    }

    @Override
    public CanMoveReport canMove(Field target, ResourceHolder resourceHolder) {
        return moveBehavior.canMove(this, target, resourceHolder);
    }

    @Override
    public boolean canMove(MovePath movePath, ResourceHolder resourceHolder) {
        return moveBehavior.canMove(this, movePath, resourceHolder);
    }

    @Override
    public List<MovePath> getMovableFields(ResourceHolder resourceHolder) {
        return moveBehavior.getMovableFields(this, resourceHolder);
    }

    @Override
    public MovePath getShortestPath(Field target, ResourceHolder resourceHolder) {
        return moveBehavior.getShortestPath(this, target, resourceHolder);
    }

    @Override
    public Moveable cloneMoveable() {
        return cloneMoveable(getPosition());
    }

    @Override
    public Moveable cloneMoveable(Field field) {
        return new GameSubjectBasic(getId() + randomId(), field, getMoveBehavior(), getLookBehavior());
    }

    @Override
    public boolean isMoveableTarget(Field field) {
        return moveBehavior.checkMoveUnableToEnd(this, field).isEmpty();
    }

    @Override
    public Resource get(Class<? extends Resource> type) {
        return resources.get(type);
    }

    @Override
    public void setResource(Resource resource) {
        resources.setResource(resource);
    }

    @Override
    public boolean canPay(Payment payment) {
        return resources.canPay(payment);
    }

    @Override
    public void pay(Payment payment) throws NotEnoughResourceException {
        resources.pay(payment);
    }

    @Override
    public void earn(Payment payment) {
        resources.earn(payment);
    }

    @Override
    public int amountOf(Class<? extends Resource> resource) {
        return resources.amountOf(resource);
    }

    @Override
    public Set<Resource> getResources() {
        return resources.getResources();
    }

    @Override
    public List<Resource> getSortedResources() {
        return resources.getSortedResources();
    }

    @Override
    public List<Resource> getSortedResources(Comparator<? super Resource> comparator) {
        return resources.getSortedResources(comparator);
    }

    @Override
    public ResourceHolder cloneResourceHolder() {
        return resources.cloneResourceHolder();
    }

    @Override
    public Field move(Field target) throws FieldsNotConnectedException, MoveNotPossibleException, NotEnoughResourceException {
        return moveBehavior.move(this, getSetterOfPosition(), target, this);
    }

    @Override
    public Field move(MovePath movePath) throws FieldsNotConnectedException, MoveNotPossibleException, NotEnoughResourceException {
        return moveBehavior.move(this, getSetterOfPosition(), movePath, this);
    }

    @Override
    public CanMoveReport canMove(Field target) {
        return moveBehavior.canMove(this, target, this);
    }

    @Override
    public boolean canMove(MovePath movePath) {
        return moveBehavior.canMove(this, movePath, this);
    }

    @Override
    public List<MovePath> getMovableFields() {
        return moveBehavior.getMovableFields(this, this);
    }

    @Override
    public MovePath getShortestPath(Field target) {
        return moveBehavior.getShortestPath(this, target, this);
    }

    @Override
    public GameState<GameSubject> getState() {
        return state;
    }

    @Dependent
    public static class State extends GameState<GameSubject> {

        private Field position;

        public Field getPosition() {
            return position;
        }

        public void setPosition(Field position) {
            this.position = position;
        }

        @Override
        public Class<GameSubject> classOfContainingBean() {
            return GameSubject.class;
        }
    }
}