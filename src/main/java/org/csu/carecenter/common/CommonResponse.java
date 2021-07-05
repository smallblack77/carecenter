package org.csu.carecenter.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.io.Serializable;

//CommonResponse表示通用响应
//使用泛型T，因为不能确定返回的对象是什么
//需要序列化
@Getter
//JSON序列化时空数据不被包含

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonResponse<T> implements Serializable {

    //和前端设计的Restful一致
    private int status;
    private String msg;
    private T data;

    private  CommonResponse(int status)
    {
        this.status = status;
    }

    private CommonResponse(int status,String msg)
    {
        this.status = status;
        this.msg = msg;
    }

    private CommonResponse(int status,String msg,T data)
    {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    private CommonResponse(int status,T data)
    {
        this.status = status;
        this.data = data;
    }

    //给客户端一个响应，只返回一个码既可以
    public static <T> CommonResponse <T> createForSuccess(){
        //不直接使用0，使用枚举，提高可维护性
        //return new CommonResponse<T>(0);
        return new CommonResponse<T>(ResponseCode.SUCCESS.getCode());
    }

    //如果想要既成功又返回数据
    public static <T> CommonResponse <T> createForSuccess(T data){
        return new CommonResponse<T>(ResponseCode.SUCCESS.getCode(),data);
    }

    //成功但是不给数据给一个字符串msg
    public static <T> CommonResponse <T> createForSuccessMessage(String msg){
        return new CommonResponse<T>(ResponseCode.SUCCESS.getCode(),msg);
    }

    //成功后既给信息又给数据
    public static <T> CommonResponse <T> createForSuccess(String msg,T data){
        return new CommonResponse<T>(ResponseCode.SUCCESS.getCode(),msg,data);
    }

    //失败
    //失败不需要给数据，必须给提示信息
    public static <T> CommonResponse <T> createForError() {
        return new CommonResponse<T>(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getDescription());
    }

    //给一个字符串过去用于显示
    public static <T> CommonResponse <T> createForError(String msg) {
        return new CommonResponse<T>(ResponseCode.ERROR.getCode(),msg);
    }

    //错误的码和错误的信息都给出指定
    public static <T> CommonResponse <T> createForError(int code,String msg) {
        return new CommonResponse<T>(code,msg);
    }

    //判断响应是成功还是失败
    @JsonIgnore
    //在JSON被序列化时忽略掉
    //JSON在序列化时会把所有非static方法序列化
    public boolean isSuccess()
    {
        return this.status == ResponseCode.SUCCESS.getCode();
    }
}
