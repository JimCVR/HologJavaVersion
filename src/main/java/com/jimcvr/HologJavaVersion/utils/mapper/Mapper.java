package com.jimcvr.HologJavaVersion.utils.mapper;

public interface Mapper<F, T> {

    T transform(F entity);
}
