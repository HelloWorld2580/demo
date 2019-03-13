package com.dhcc.ms.http.wrapper;

import java.util.concurrent.Callable;

import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;

public class MSIMSHystrixConcurrencyStrategy extends HystrixConcurrencyStrategy {
	@Override
    public <K> Callable<K> wrapCallable(Callable<K> c) {
        return new MSIMSCallableWrapper<K>(c);
    }
}
