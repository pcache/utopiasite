package com.nuoyu.utopia.utopiasso.common.utils;

import com.nuoyu.utopia.utopiasso.web.dto.ExampleDto;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by liuxin3 on 2015/1/26.
 * json工具类 负责json转对象  或者对象转json
 */
public class JsonUtil {

    private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);
    //ObjectMapper实例 线程安全
    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 将java对象转换成json字符串 异常返回""
     *
     * @param obj 准备转换的对象
     * @return json字符串
     */
    public static String beanToJson(Object obj) {
        String json = "";
        try {
            json = objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            log.error("JsonUtil.beanToJson:::对象转json失败 参数:" + obj.toString(), e);
        }
        return json;
    }

    /**
     * 将json字符串转换成java对象 异常返回空 注意List<Object>时只能转成List<LinkedHashMap>
     *
     * @param json 准备转换的json字符串
     * @param cls  准备转换的类
     * @return
     * @throws Exception
     */
    public static <T> T jsonToBean(String json, Class<T> cls) {
        T result = null;
        try {
            result = objectMapper.readValue(json, cls);
        } catch (Exception e) {
            log.error("JsonUtil.jsonToBean:::json转对象失败 参数:" + json, e);
        }
        return result;
    }

    /**
     * 解析json 返回json节点 JsonNode
     *
     * @param json
     * @return
     */
    public static JsonNode transformJson(String json) {
        JsonNode node = null;
        try {
            node = objectMapper.readTree(json);
        } catch (IOException e) {
            log.error("JsonUtil.transformJson:::解析json失败 参数:" + json, e);
        }
        return node;
    }

    /**
     * 将json节点转化成List  若不是数组则返回空
     *
     * @param node
     * @param cls
     * @return
     */
    public static <T> List<T> transformJsonToList(JsonNode node, Class<T> cls) {
        List<T> result = null;
        if (null != node && node.isArray()) {
            result = new ArrayList<T>();
            Iterator<JsonNode> ito = node.iterator();
            while (ito.hasNext()) {
                result.add(jsonToBean(ito.next().toString(), cls));
            }
        }
        return result;
    }

    /**
     * 将Json转成List 若不是数组则返回空
     *
     * @param json 数组类型
     * @param cls
     * @return
     */
    public static <T> List<T> transformJsonToList(String json, Class<T> cls) {
        return transformJsonToList(transformJson(json), cls);
    }

}
