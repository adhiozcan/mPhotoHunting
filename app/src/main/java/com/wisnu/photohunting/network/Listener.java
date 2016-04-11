package com.wisnu.photohunting.network;

import java.lang.reflect.Type;

/**
 * Possible callback when response is arrive
 */
public interface Listener<T>
{
    void onSuccess (Type response);

    void onError (String Message);
}
