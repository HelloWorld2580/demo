package com.dhcc.ms.http.wrapper;

import java.util.concurrent.Callable;

import com.dhcc.ms.utils.MetaDataHelper;
import com.dhcc.ms.utils.dto.MetaDataDto;

public class MSIMSCallableWrapper<K> implements Callable<K> {

    private final Callable<K> actual;
    private final MetaDataDto parentMetaObject;
    //private final SomeContext parentThreadState;

    public MSIMSCallableWrapper(Callable<K> actual) {
        this.actual = actual;
        // copy whatever state such as MDC
        this.parentMetaObject= MetaDataHelper.getMetaData();
    }

    public K call() throws Exception {
    	MetaDataDto childMetaObject = MetaDataHelper.getMetaData();
        try {
            // set the state of this thread to that of its parent
            // this is where the MDC state and other ThreadLocal values can be set
        	MetaDataHelper.setMetaData(parentMetaObject);
            // execute actual Callable with the state of the parent
            return actual.call();
        } finally {
            // restore this thread back to its original state
        	MetaDataHelper.setMetaData(childMetaObject);
        }
    }

}
