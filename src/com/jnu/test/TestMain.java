package com.jnu.test;

import com.jnu.requestcache.Callback;
import com.jnu.requestcache.Observer;
import com.jnu.requestcache.Request;
import com.jnu.requestcache.RequestCache;
import com.jnu.utils.Log;
import com.wkp.Handler;
import com.wkp.Looper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestMain {
    private static final String TAG = "TestMain";

    RequestCache<Long, TestData> cache;

    private Handler mainHandler = new Handler();

    /**
     * 每隔5秒钟发送一次请求
     */
    private Runnable requestRunnable = new Runnable() {
        @Override
        public void run() {
            testRequest();
            mainHandler.postDelayed(this, 5000);
        }
    };

    public TestMain() {
        cache = new RequestCache<>(50);
        cache.setRequestMethod(new Request<Long, TestData>() {
            @Override
            public void request(List<Long> longs, Callback<List<TestData>> callback) {
                mainHandler.postDelayed(new Runnable() {
                    /**
                     * 模拟3秒钟后有数据回包
                     */
                    @Override
                    public void run() {
                        ArrayList<TestData> response = new ArrayList<>(longs.size());
                        for (long id : longs) {
                            response.add(new TestData(id, String.valueOf(id)));
                        }
                        callback.call(response);
                    }
                }, 3000);
            }
        });
    }

    public void start() {
        mainHandler.post(requestRunnable);
    }

    private void testRequest() {
        Random random = new Random();
        List<Long> ids = new ArrayList<>();
        for (int i = 0; i < 50; ++i) {
            long id = Math.abs(random.nextLong() % 1000L);
            cache.getHolderByKey(id).observe(new Observer<TestData>() {
                @Override
                public void onChange(TestData value) {
                    Log.log(TAG, "testRequest onChange %s", value);
                }
            });
            ids.add(id);
        }
        Log.log(TAG,"testRequest %s",ids);
    }


    public static void main(String[] args) {
        Looper.prepareMainLooper();
        new TestMain().start();
        Looper.loop();
    }
}
