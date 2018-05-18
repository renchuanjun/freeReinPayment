package org.open.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtils<K, V> {

	@Autowired
	private RedisTemplate redisTemplate;
	/***
	 * 添加值
	 * @param key
	 * @param value
	 * @param expiredTime 是毫秒，20分钟相当于1200000
	 */
	public <K, V> void put(K key, V value, long expiredTime) {
		ValueOperations<K, V> opsForValue = (ValueOperations<K, V>) redisTemplate.opsForValue();
		opsForValue.set(key, value, expiredTime, TimeUnit.MILLISECONDS);
	}
	
	/***
	 * 添加值(默认有效期20分钟)
	 * @param key
	 * @param value
	 */
	public <K, V> void put(K key, V value) {
		ValueOperations<K, V> opsForValue = (ValueOperations<K, V>) redisTemplate.opsForValue();
		opsForValue.set(key, value,1200000, TimeUnit.MILLISECONDS);
	}
	
	/***
	 * 获取值
	 * @param k
	 * @return
	 */
	public <K, V> V get(K k){
		ValueOperations<K, V> opsForValue = (ValueOperations<K, V>) redisTemplate.opsForValue();
		return opsForValue.get(k);
	}
	
	/***
	 * 删除值
	 * @param k
	 */
	public <K, V> void remove(K k){
		if (redisTemplate.hasKey(k)) {
			redisTemplate.delete(k);
		}
	}
	
	/***
	 * 检测键是否存在
	 * @param k
	 * @return
	 */
	public <K, V> boolean hasKey(K k){
		return redisTemplate.hasKey(k);
	}
	
	/***
	 * 添加多个值
	 * @param map
	 */
	public <K, V> void putByMulti(Map<K, V> map) {
		ValueOperations<K, V> opsForValue = (ValueOperations<K, V>) redisTemplate.opsForValue();
		opsForValue.multiSet(map);
	}
	
	/***
	 * 获取多个值
	 * @param keys
	 * @return
	 */
	public <K, V> List<V> getByMulti(Collection<K> keys){
		ValueOperations<K, V> opsForValue = (ValueOperations<K, V>) redisTemplate.opsForValue();
		return opsForValue.multiGet(keys);
	}
	
	/***
	 * 删除多个值
	 * @param keys
	 */
	public <K, V> void removeByMulti(Collection<K> keys){
		for (K k : keys) {
			if (redisTemplate.hasKey(k)) {
				redisTemplate.delete(k);
			}
		}
	}
	
	/***
	 * 续期
	 * @param key
	 * @param timeout
	 */
	public <K, V> void expire(K key,long timeout){
		if (redisTemplate.hasKey(key)) {
			redisTemplate.expire(key, timeout, TimeUnit.MILLISECONDS);
		}
	}
}
