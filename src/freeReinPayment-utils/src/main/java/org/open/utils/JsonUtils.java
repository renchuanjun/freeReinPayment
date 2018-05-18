package org.open.utils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.DefaultValueProcessor;
import net.sf.json.util.CycleDetectionStrategy;
import net.sf.json.util.JSONUtils;

import java.math.BigDecimal;
import java.util.*;

public class JsonUtils {

	/**
	 * 实体序列化成Json对象
	 * 
	 * @param object
	 * @return
	 */
	public static String SerializeJson(Object object) {
		JsonConfig jsonConfig =  configJson(null,"yyyy-MM-dd HH:mm:ss");
		JSONObject jsonObject = JSONObject.fromObject(object,jsonConfig);
		return jsonObject.toString();
	}

	/**
	 * Json对象反序列化实体
	 * 
	 * @param jsonString
	 * @return
	 */
	public static Object DeserializeJson(String jsonString,Class class1) {
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		JSONUtils.getMorpherRegistry().registerMorpher(
                new DateMorpherEx(new String[] { "yyyy-MM-dd HH:mm:ss",
                        "yyyy-MM-dd", "yyyy-MM-dd't'HH:mm:ss" }, (Date) null));
		Object bean = JSONObject.toBean(jsonObject,class1);
		return bean;
	}

	
	
	/**
	 * 集合实体序列化成Json对象
	 * 
	 * @param object
	 * @return
	 */
	public static String SerializeJsonByList(Object object) {
		JsonConfig jsonConfig =  configJson(null,"yyyy-MM-dd HH:mm:ss");
		JSONArray jsonArray = JSONArray.fromObject(object, jsonConfig);
		return jsonArray.toString();
	}

	/**
	 * Json反序列化集合对象
	 * 
	 * @param jsonString
	 * @return
	 */
	public static List DeserializeJsonByList(String jsonString,Class class1) {
		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		JSONUtils.getMorpherRegistry().registerMorpher(
                new DateMorpherEx(new String[] { "yyyy-MM-dd HH:mm:ss",
                        "yyyy-MM-dd", "yyyy-MM-dd't'HH:mm:ss" }, (Date) null));
		List list = JSONArray.toList(jsonArray, class1);
		return list;
	}
	
	/**
	 * 序列化map对象
	 * @param map
	 * @return
	 */
	public static <K, V> String SerializeJsonByMap(Map<K, V> map) {
		JsonConfig jsonConfig =  configJson(null,"yyyy-MM-dd HH:mm:ss");
		String jsonString = JSONObject.fromObject(map,jsonConfig).toString();    
		return jsonString;
	}
	
	/**
	 * 反序列化map对象
	 * @param jsonStr
	 * @return
	 */
	public static Map<String, Object> DeserializeJsonByMap(String jsonStr){  
        Map<String, Object> map = new HashMap<String, Object>();  
        //最外层解析  
        JSONObject json = JSONObject.fromObject(jsonStr);  
        for(Object k : json.keySet()){  
            Object v = json.get(k);   
            //如果内层还是数组的话，继续解析  
            if(v instanceof JSONArray){  
                List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();  
                Iterator<JSONObject> it = ((JSONArray)v).iterator();  
                while(it.hasNext()){  
                    JSONObject json2 = it.next();  
                    list.add(DeserializeJsonByMap(json2.toString()));  
                }  
                map.put(k.toString(), list);  
            } else {  
                map.put(k.toString(), v);  
            }  
        }  
        return map;  
    } 
	
	/**
	 * Json的Date数据类型转换
	 * @param excludes
	 * @param datePattern
	 * @return
	 */
	public static JsonConfig configJson(String[] excludes, String datePattern) {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(excludes);
		jsonConfig.setIgnoreDefaultExcludes(false);
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		jsonConfig.registerJsonValueProcessor(Date.class,
				new DateJsonValueProcessor(datePattern));

		//针对Integer为null返回默认值0的问题
		jsonConfig.registerDefaultValueProcessor(Integer.class,  
		        new DefaultValueProcessor() {  
		            public Object getDefaultValue(Class type) {  
		                return "";  
		            }  
		        });
		//针对BigDecimal为null返回默认值0.00的问题
		jsonConfig.registerDefaultValueProcessor(BigDecimal.class,  
		        new DefaultValueProcessor() {  
		            public Object getDefaultValue(Class type) {  
		                return "";  
		            }  
		        });
				
		//针对Boolean为null返回默认值false的问题
		jsonConfig.registerDefaultValueProcessor(Boolean.class,  
		        new DefaultValueProcessor() {  
		            public Object getDefaultValue(Class type) {  
		                return "";  
		            }  
		        });
		//针对Double为null返回默认值0.00的问题
		jsonConfig.registerDefaultValueProcessor(Double.class,  
		        new DefaultValueProcessor() {  
		            public Object getDefaultValue(Class type) {  
		                return "";  
		            }  
		        });
		//针对Float为null返回默认值0.00的问题
		jsonConfig.registerDefaultValueProcessor(Float.class,  
		        new DefaultValueProcessor() {  
		            public Object getDefaultValue(Class type) {  
		                return "";  
		            }  
		        });
		return jsonConfig;
	}

	/**
	 * 针对查询成员公司信息对应的json串,同时带自定义值
	 *
	 * @param className
	 * @param json
	 * @return
	 */
	public static String SetMemberTableDataJson(String  className, String json) {

//		String str = "{\"className\":" + className + ",\"rows\":" + json  + "}";
		String str = "{\"className\":\"" + className +"\",\"data\":" + json  + "}";
		return str;
	}
}
