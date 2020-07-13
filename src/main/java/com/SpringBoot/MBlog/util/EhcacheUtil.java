package com.SpringBoot.MBlog.util;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * Ehcache 缓存工具类
 * cacheName在ehcache.xml中配置
 */
public class EhcacheUtil {

	public static CacheManager manager = CacheManager.create();//默认读取classpath下的ehcache.xml

	/**
	 * 从缓存中获取数据
	 * @param cacheName (缓存名称)
	 * @param key (键)
	 * @return (键对应的数据)
	 */
	@SuppressWarnings("unchecked")
	public static <T> T get(String cacheName, Object key) {
		Cache cache = getCache(cacheName);
		Element element = cache.get(key);
		if (element != null) {
			return (T) element.getObjectValue();
		}
		return null;
	}
	/**
	 * 向缓存中加入数据，无超时时间
	 * @param cacheName (缓存名称)
	 * @param key (键)
	 * @param value (值)
	 */
	public static void put(String cacheName, Object key, Object value) {
		put(cacheName, key, value, 0, 0);
	}
	/**
	 * 向缓存中加入数据
	 * @param cacheName (缓存名称)
	 * @param key (键)
	 * @param value (值)
	 * @param timeToLiveSeconds (自创建时间开始，最多存活多少秒，默认为0，代表无穷大)
	 * @param timeToIdleSeconds (多久不访问则释放，默认为0，带表无穷大)
	 */
	public static void put(String cacheName, Object key, Object value,int timeToLiveSeconds,int timeToIdleSeconds) {
		Element element = new Element(key, value);
		element.setTimeToLive(timeToLiveSeconds);
		element.setTimeToIdle(timeToIdleSeconds);
		getCache(cacheName).put(element);
	}
	/**
	 * 从缓存中移除数据
	 * @param cacheName (缓存名称)
	 * @param key (键)
	 * @return (是否移除成功)
	 */
	public static boolean remove(String cacheName, Object key) {
		return getCache(cacheName).remove(key);
	}
	/**
	 * 获取缓存对象，此缓存应该是ehcache.xml中存在的，否则会失败
	 * @param cacheName (缓存名称)
	 * @return (缓存对象)
	 */
	public static Cache getCache(String cacheName) {
		Cache cache = manager.getCache(cacheName);
		if(cache==null) {
			throw new RuntimeException("The cache does not exists : "+cacheName);
		}
		return cache;
	}

	public static void main(String[] args) {
		String key = "key";
		String value = "hello";
		EhcacheUtil.put("mytest", key, value);
		String v = EhcacheUtil.get("mytest", key);
		System.out.println(v);
	}

}