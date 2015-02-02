package com.nuoyu.utopia.utopiasso.common.context;

import java.lang.Object;
import java.util.HashMap;

/**
 * Created by liuxin3 on 2015/1/8.
 *      http://blog.csdn.net/lufeng20/article/details/24314381
 *      utopia参数工具类 存储request级变量
 */
public class UtopiaTheadLocal {

    //创建线程局部变量utopiaLocal，用来保存绑定在线程中的参数
    public static final ThreadLocal utopiaLocal = new ThreadLocal();

    /**
     * 存储对象到当前线程中，随线程的销毁而销毁
     * @param key 参数
     * @param value 存放在local中的对象
     */
    public static void setParamToLocal(String key,Object value){
        HashMap<String,Object> params = (HashMap<String, Object>) utopiaLocal.get();
        // 如果当前线程中未设置过参数，则创建参数容器
        if(null == params) {
            params = new HashMap<String, Object>();
            // 将参数容器绑定到当前线程中
            utopiaLocal.set(params);
        }
        params.put(key, value);
    }

    /**
     * 取出之前在当前线程中存储的对象，隔离其他线程
     * @param key 参数
     * @return 线程中的存储对象
     */
    public static Object getParamFromLocal(String key){
        HashMap<String,Object> params = (HashMap<String, Object>) utopiaLocal.get();
        // 如果已经设置过params 则从params取出之前设置的对象，隔离其他线程
        if (null != params){
            // 从容器中取出
            return params.get(key);
        }
        return null;
    }


}
