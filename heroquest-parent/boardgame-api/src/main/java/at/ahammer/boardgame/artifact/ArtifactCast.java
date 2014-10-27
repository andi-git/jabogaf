package at.ahammer.boardgame.artifact;

/**
 * This function can be used to cast an {@link at.ahammer.boardgame.artifact.Artifact} cast a concrete sub-type.
 */
@FunctionalInterface
public interface ArtifactCast<T> {

    /**
     * Cast the assigned {@link at.ahammer.boardgame.artifact.Artifact} cast a concrete sub-type. If it's a sub-type, return the casted object. If it's not a sub-type, return null-representation of the sub-type.
     *
     * @param artifact - the {@link at.ahammer.boardgame.artifact.Artifact} to cast
     * @return the casted object if the {@link at.ahammer.boardgame.artifact.Artifact} cast the concrete subtype, otherwise the null-representation.
     */
    T cast(Artifact artifact);
}
