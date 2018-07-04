package com.jnu.requestcache;

import java.util.List;

public interface Request<KEY, VALUE> {
    void request(List<KEY> keys, Callback<List<VALUE>> callback);
}
