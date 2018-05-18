package org.open.model;

/**
 * Created by lenovo on 2017/4/11.
 */

public class FQParam2<T,K> implements java.io.Serializable{
    private T t;

    private K k;

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
}

