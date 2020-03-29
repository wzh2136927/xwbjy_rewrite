package com.wxf.xwbjy_rewrite.result;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 统一结果类的使用参考了mybatis-plus中R对象的设计
 */
@Data
public class ActionResult {
    private Boolean success;
    private Integer code;
    private String message;
    private Map<String,Object> data = new HashMap<>();
    //单例模式  外界只可以调用统一返回类的方法，不可以直接创建，因此构造器私有；
    private ActionResult(){}
    // 通用返回成功
    public static ActionResult ok() {
        ActionResult r = new ActionResult();
        r.setSuccess(ResultCodeEnum.SUCCESS.getSuccess());
        r.setCode(ResultCodeEnum.SUCCESS.getCode());
        r.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        System.out.println(1121);
        return r;
    }

    // 通用返回失败，未知错误
    public static ActionResult error() {
        ActionResult r = new ActionResult();
        r.setSuccess(ResultCodeEnum.UNKNOWN_ERROR.getSuccess());
        r.setCode(ResultCodeEnum.UNKNOWN_ERROR.getCode());
        r.setMessage(ResultCodeEnum.UNKNOWN_ERROR.getMessage());
        return r;
    }

    // 设置结果，形参为结果枚举
    public static ActionResult setResult(ResultCodeEnum result) {
        ActionResult r = new ActionResult();
        r.setSuccess(result.getSuccess());
        r.setCode(result.getCode());
        r.setMessage(result.getMessage());
        return r;
    }


    /**------------使用链式编程，返回类本身-----------**/

     public ActionResult data(Map<String,Object>map){
         this.setData(map);
         return this;
     }
     public ActionResult data(String key,Object value){
         this.data.put(key,value);
         return this;
     }
     public ActionResult message(String message){
         this.setMessage(message);
         return this;
     }
     public ActionResult success(Boolean success){
         this.setSuccess(success);
         return this;
     }
     public ActionResult code(Integer code){
         this.setCode(code);
         return this;
     }

}
