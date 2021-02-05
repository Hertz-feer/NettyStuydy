package com.hertz.other;


import java.util.concurrent.ForkJoinPool;

/**
 * @author Hertz
 */
public class Test {


    public static void main(String[] args) {
//       Range r = Range.closed(1,10);
//        System.out.println(r.test(111));
//        //System.out.println(r.span(Range.closed(2, 14)));
//        System.out.println(r.gap(Range.closed(12,15)));
//        System.out.println(r);
//        final CompletableFuture<String> voidCompletableFuture = CompletableFuture.supplyAsync(() -> {
//            return "OK";
//        });
//        try {
//            System.out.println(voidCompletableFuture.get());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }

        final ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        forkJoinPool.execute(() -> {
            System.out.println(Thread.currentThread());
        });


    }
}
