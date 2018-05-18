package org.open.model;

/**
 * Created by lenovo on 2017/4/11.
 */

public class FQParam3<T,K,V> implements java.io.Serializable{
    private T t;

    private K k;

    private V v;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public K getK() {
        return k;
    }

    public void setK(K k) {
        this.k = k;
    }

    public V getV() {
        return v;
    }

    public void setV(V v) {
        this.v = v;
    }
}

