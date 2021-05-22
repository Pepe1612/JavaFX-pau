package containers;

import java.util.HashMap;
import java.util.Map;

public abstract class MapContainer<V> {

    protected final Map<Integer, V> map;

    public MapContainer() {
        map = new HashMap<>();
    }

    public boolean add(V newElement, int id) {
        if(!map.containsValue(newElement)) {
            map.put(id, newElement);
            return true;
        }
        else return false;
    }

    public boolean contains(V element) {
        return map.containsValue(element);
    }

    public boolean contains(int key) {
        return map.containsKey(key);
    }

    public V getById(int wantedId) {
        return map.get(wantedId);
    }

    public V removeById(int wantedId) {
        return map.remove(wantedId);
    }

    public int size() {
        return map.size();
    }

    public Map<Integer, V> getMap() {
        return map;
    }
}
