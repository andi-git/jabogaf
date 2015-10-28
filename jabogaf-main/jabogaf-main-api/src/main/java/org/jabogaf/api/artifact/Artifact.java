package org.jabogaf.api.artifact;

import org.jabogaf.api.gamecontext.GameContextBean;
import org.jabogaf.api.subject.GameSubject;

/**
 * An {@link Artifact} cast an object in the board game which can be used by a {@link GameSubject}.
 */
public interface Artifact extends GameContextBean {

    HandCount getHandCount();
}
