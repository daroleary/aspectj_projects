package org.benevity.faulttolerance;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Darren O'Leary
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Idempotent {
}
