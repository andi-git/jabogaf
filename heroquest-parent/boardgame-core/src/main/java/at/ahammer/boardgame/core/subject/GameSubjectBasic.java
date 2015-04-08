package at.ahammer.boardgame.core.subject;

import at.ahammer.boardgame.api.artifact.Artifact;
import at.ahammer.boardgame.api.behavior.look.LookBehavior;
import at.ahammer.boardgame.api.behavior.look.LookBehaviorType;
import at.ahammer.boardgame.api.behavior.move.*;
import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.cdi.GameContextBean;
import at.ahammer.boardgame.api.cdi.GameContextManager;
import at.ahammer.boardgame.api.resource.*;
import at.ahammer.boardgame.api.subject.GameSubject;
import at.ahammer.boardgame.api.subject.SetterOfPosition;
import at.ahammer.boardgame.api.subject.artifact.ArtifactHolder;
import at.ahammer.boardgame.api.subject.artifact.hand.ArtifactHandlingException;
import at.ahammer.boardgame.api.subject.artifact.hand.ArtifactHandlingStrategy;
import at.ahammer.boardgame.api.subject.hand.Hand;
import at.ahammer.boardgame.core.behavior.look.LookBehaviorNull;
import at.ahammer.boardgame.core.behavior.move.MoveBehaviorNull;
import at.ahammer.boardgame.core.cdi.GameContextBeanBasic;
import at.ahammer.boardgame.core.resource.MovePoint;
import at.ahammer.boardgame.core.resource.ResourcesBasic;
import at.ahammer.boardgame.core.subject.artifact.ArtifactHolderBasic;

import javax.enterprise.inject.Typed;
import javax.inject.Inject;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A subject (i.e. hero, monster,...) in the game.
 */
@SuppressWarnings("CdiManagedBeanInconsistencyInspection")
@Typed
public class GameSubjectBasic extends GameContextBeanBasic implements GameSubject {

    @Inject
    private ArtifactHolder artifactHolder;

    @Inject
    private Resources resources;

    private MoveBehavior moveBehavior;

    private LookBehavior lookBehavior;

    private Field position;

    @Inject
    @MoveBehaviorType(MoveBehaviorNull.class)
    private MoveBehavior moveBehaviorNull;

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
        this.position = position;
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
        return (position) -> {
            this.position = position;
        };
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
    public boolean canLook(Field target) {
        return lookBehavior.canLook(getPosition(), target);
    }

    @Override
    public Set<Field> getLookableFields() {
        return lookBehavior.getLookableFields(getPosition());
    }

    @Override
    public LookBehavior getLookBehavior() {
        return lookBehavior;
    }

    @Override
    public Field getPosition() {
        return position;
    }

    protected void setPosition(Field position) {
        this.position = position;
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
        return new GameSubjectBasic(getId() + System.currentTimeMillis(), field, getMoveBehavior(), getLookBehavior());
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
}