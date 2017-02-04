package iii.org.tw.testthreadpool;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            testInvokeAll();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void testInvokeAll() throws Exception
    {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        List<Callable<String>> tasks = new ArrayList<Callable<String>>();
        tasks.add(new SleepSecondsCallable("t1", 2));
        tasks.add(new SleepSecondsCallable("t2", 2));
        tasks.add(new SleepSecondsCallable("t3", 2));
        tasks.add(new SleepSecondsCallable("t4", 2));
        tasks.add(new SleepSecondsCallable("t5", 2));
       // tasks.add(new RandomTenCharsTask());
        //tasks.add(new ExceptionCallable());

        // 调用该方法的线程会阻塞,直到tasks全部执行完成(正常完成/异常退出)
        List<Future<String>> results = executorService.invokeAll(tasks);

        // 任务列表中所有任务执行完毕,才能执行该语句
        System.out.println("wait for the result." + results.size());

        executorService.shutdown();
        /*

        for (Future<String> f : results)
        {
            // isCanceled=false,isDone=true
            System.out.println("isCanceled=" + f.isCancelled() + ",isDone="
                    + f.isDone());

            // ExceptionCallable任务会报ExecutionException
            System.out.println("task result=" + f.get());
        }
        */
    }

}
