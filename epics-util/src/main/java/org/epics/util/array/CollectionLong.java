/**
 * Copyright information and license terms for this software can be
 * found in the file LICENSE.TXT included with the distribution.
 */
package org.epics.util.array;

/**
 * A collection of {@code long}s.
 *
 */
public interface CollectionLong extends CollectionNumber {

    @Override
    IteratorLong iterator();

}
