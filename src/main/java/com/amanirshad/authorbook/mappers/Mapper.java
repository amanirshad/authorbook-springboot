package com.amanirshad.authorbook.mappers;

public interface Mapper<A,B> {
    B mapTo(A a);
    A mapFrom(B b);
}
