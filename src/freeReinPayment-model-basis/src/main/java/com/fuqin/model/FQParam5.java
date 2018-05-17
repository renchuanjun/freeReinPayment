package com.fuqin.model;

/**
 * Created by lenovo on 2017/4/11.
 */

public class FQParam5<T,K,V,M,N> implements java.io.Serializable{
    private T t;

    private K k;

    private V v;

    private M m;

    private N n;

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

    public M getM() {
        return m;
    }

    public void setM(M m) {
        this.m = m;
    }

    public N getN() {
        return n;
    }

    public void setN(N n) {
        this.n = n;
    }
}

