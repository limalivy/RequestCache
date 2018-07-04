package com.jnu.requestcache;

import java.util.List;

public class RequestCache<KEY, VALUE> {

    /**
     * 缓存池大小
     */
    private int cacheSize;

    private long waitListTime = getWaitListTime();

    private long waitResponseTime = getWaitResponseTime();

    private Request<KEY, VALUE> requestMethod;

    public RequestCache(int cacheSize) {
        this.cacheSize = cacheSize;
    }

    protected long getWaitListTime() {
        return 200;
    }

    protected long getWaitResponseTime() {
        return 5000;
    }

    public void setRequestMethod(Request<KEY,VALUE> requestMethod) {
        this.requestMethod = requestMethod;
    }

    /**
     * 根据key获取数据的holder，
     * 如果缓存池中有holder直接返回缓存词中的数据，
     * 否则需要发起网络请求
     *
     * @param key
     * @return
     */
    public DataHolder<VALUE> getHolderByKey(KEY key) {
        //todo
        return new DataHolder<>();
    }


    /**
     * 强制拉取数据，不管缓存池中是否存在数据，都必须发送请求，重新拉取数据
     *
     * @param key
     * @return
     */
    public DataHolder<VALUE> forceHolderByKey(KEY key) {
        //todo
        return null;
    }

    /**
     * 响应会自动回调
     * @param values
     */
    protected void onResponse(List<VALUE> values) {
        //todo
        //如果需要设置数据，直接使用holder.postValue();
        //如
        //DataHolder<Object> holder = xxx;
        //holder.postVale(values.get(0));
    }

    /**
     * 请求方法
     * @param keys
     */
    protected void request(List<KEY> keys) {
        if (requestMethod != null && keys != null && !keys.isEmpty()) {
            requestMethod.request(keys, new Callback<List<VALUE>>() {
                @Override
                public void call(List<VALUE> value) {
                    onResponse(value);
                }
            });
        }
    }

}
