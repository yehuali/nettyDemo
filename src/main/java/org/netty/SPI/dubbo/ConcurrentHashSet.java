package org.netty.SPI.dubbo;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ConcurrentHashSet<E> extends AbstractSet<E> implements Set<E>, java.io.Serializable {
    private static final long serialVersionUID = -8672117787651310382L;

    private final ConcurrentMap<E, Object> map;

    public ConcurrentHashSet() {
        map = new ConcurrentHashMap<E, Object>();
    }

    @Override
    public Iterator<E> iterator() {
        return map.keySet().iterator();
    }

    @Override
    public int size() {
       return map.size();
    }
}
