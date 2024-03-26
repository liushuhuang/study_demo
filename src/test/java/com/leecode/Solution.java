package com.leecode;

import java.util.concurrent.*;

/**
 * 2512. 奖励最顶尖的 K 名学生
 */
class Solution {
    final static ExecutorService executor = new ThreadPoolExecutor(6, 6, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletionService completionService = new ExecutorCompletionService(executor);
        completionService.submit(() -> getMouZhuFlightPrice());
        completionService.submit(() -> getMouXieFlightPrice());
        completionService.submit(() -> getMouTuanFlightPrice());
        for (int i = 0; i < 3; i++) {
            String result = (String) completionService.take().get();
            System.out.println(result);
            saveDb(result);
        }
    }
}
