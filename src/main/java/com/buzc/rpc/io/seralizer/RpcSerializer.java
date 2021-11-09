package com.buzc.rpc.io.seralizer;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtobufIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.objenesis.Objenesis;
import org.springframework.objenesis.ObjenesisStd;

import java.util.concurrent.TimeUnit;

/**
 * 序列化和反序列化工具（使用 Protostuff 协议）
 */
public class RpcSerializer {
    /** 使用 Objenesis 来实例化对象，它比 Java 反射更加强大*/
    private static Objenesis objenesis = new ObjenesisStd(true);
    /**Schema 缓存*/
    private static Cache<Class<?>, Schema<?>> cacheSchema = Caffeine.newBuilder()
            //最大数量
            .maximumSize(1000)
            // 设置缓存过期策略：最后一次访问或者写入开始时计时
            .expireAfterAccess(1, TimeUnit.DAYS)
            .build();
    /**
     * 获取 Schema
     */
    private static <T> Schema<T> getSchema(Class<T> cls){
        Schema<T> schema = (Schema<T>) cacheSchema.getIfPresent(cls);
        if(schema == null){
            // 新创建的 schema 放入缓存
            schema = RuntimeSchema.createFrom(cls);
            cacheSchema.put(cls, schema);
        }
        return schema;
    }

    /**
     * 序列化 obj --> byte[]
     * @param obj 要序列化的对象
     */
    public static <T> byte[] serialize(T obj){
        Class<T> cls = (Class<T>) obj.getClass();
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);

        try {
            Schema<T> schema = getSchema(cls);
            return ProtobufIOUtil.toByteArray(obj, schema, buffer);
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        } finally {
            buffer.clear();
        }
    }

    /**
     * 反序列化 byte[] --> obj
     */
    public static <T> T deserialize(byte[] data, Class<T> cls){
        try {
            T message = objenesis.newInstance(cls);
            Schema<T> schema = getSchema(cls);
            ProtobufIOUtil.mergeFrom(data, message, schema);
            return message;
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }
}
