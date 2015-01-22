package at.ahammer.boardgame.api.artifact.shield;

import at.ahammer.boardgame.api.artifact.Artifact;

/**
 * A shield used to protect against attacks. A {@link
 * at.ahammer.boardgame.api.subject.GameSubject} can hold it in a hand.
 * <p/>
 * Every {@link Shield} relates to a {@link ShieldType}.
 */
public interface Shield extends Artifact {

    ShieldType getShieldType();
}
