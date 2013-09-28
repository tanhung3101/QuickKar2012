/**
 * Course: Software Development: Process & Tools
 * Lecturer: Quang Tran
 * Assignment: 2-2012B
 * Assignment Title: QuickKar2012
 *
 *      RMIT International University Vietnam
 * Bachelor of Information Technology - Application
 *
 */
package QuickKarModel.facade;

import java.util.Map;

/**
 * Customize Map to used various within program.
 *
 * @author vuongdothanhhuy
 * @param <K>
 * @param <V>
 */
public final class MapEntryElement<K, V> implements Map.Entry<K, V> {

    private final K key;
    private V value;

    public MapEntryElement(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public V setValue(V value) {
        V old = this.value;
        this.value = value;
        return old;
    }
}
