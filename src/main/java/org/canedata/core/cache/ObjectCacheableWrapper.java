/**
 * Copyright 2010 CaneData.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.canedata.core.cache;

import org.canedata.cache.Cacheable;

import java.io.Serializable;

/**
 * Object wrapped to Cacheable.
 *
 * @author Yat-ton
 * @version 1.00.000 14-3-29
 */
public class ObjectCacheableWrapper<T> extends Cacheable.Adapter {
    protected T target = null;
    protected String key = null;

    public static <T> ObjectCacheableWrapper<T> of(String key, T t) {
        return new ObjectCacheableWrapper(key, t);
    }

    public ObjectCacheableWrapper(){
    }

    public ObjectCacheableWrapper(String key, T t){
        this.key = key;
        this.target = t;
    }

    @Override
    public Object getKey() {
        return key;
    }

    public void setKey(Object key) {
        this.key = key.toString();
    }

    public void setTarget(T target){
        this.target = target;
    }

    public T getTarget(){
        return target;
    }

    public ObjectCacheableWrapper<T> target(T target){
        this.target = target;

        return this;
    }

    public ObjectCacheableWrapper<T> key(String k){
        key = k;

        return this;
    }

    public boolean isNull() {
        return null == target;
    }

    @Override
    public boolean isWrappedFor(Class<?> iface) {
        return iface.isInstance(target);
    }

    @Override
    public <T> T unwrap(Class<T> iface) {
        return iface.cast(target);
    }
}
