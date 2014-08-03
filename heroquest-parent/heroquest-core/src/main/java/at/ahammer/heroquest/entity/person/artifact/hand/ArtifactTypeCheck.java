package at.ahammer.heroquest.entity.person.artifact.hand;

import at.ahammer.heroquest.entity.artifact.Artifact;

/**
 * Created by andreas on 26.07.14.
 */
@FunctionalInterface
public interface ArtifactTypeCheck<T> {

    T is(Artifact artifact);
}
