package org.jabogaf.api.artifact.shield;

import org.jabogaf.api.artifact.Artifact;

/**
 * A shield used to protect against attacks. A {@link GameSubject} can hold it in a hand.
 * <p>
 * Every {@link Shield} relates to a {@link ShieldType}.
 */
public interface Shield extends Artifact {

    ShieldType getShieldType();
}
