package org.bevj.util;

import java.util.HashMap;

public class TypeIdMap<V> extends HashMap<TypeId, V>{

    public void put(V v) {
        put(TypeId.of(v.getClass()), v);
    }

}
