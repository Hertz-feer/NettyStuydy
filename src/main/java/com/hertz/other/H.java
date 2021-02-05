package com.hertz.other;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicReference;

public class H {

    private Logger logger = LoggerFactory.getLogger(H.class);

    private static AtomicReference<H> instance;

    private H(){
    }

    static {
        instance = new AtomicReference<>();
    }

    public static H getInstance(){
         for (;;){
             H h = instance.get();
             if (h != null){
                 return h;
             }
             h = new H();
             if (instance.compareAndSet(null,h)){
                 return instance.get();
             }
         }
    }
}
