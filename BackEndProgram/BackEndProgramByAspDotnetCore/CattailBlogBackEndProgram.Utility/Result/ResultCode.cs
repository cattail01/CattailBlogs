using System.ComponentModel;

namespace CattailBlogBackEndProgram.Utility;

/// <summary>
/// 后端处理结果，返回的状态码
/// </summary>
public enum ResultCode
{
    [Description("未知错误")]
    UnknownError = 0,
    
    [Description("请求成功")]
    Ok = 20000,
    
    [Description("无法理解的请求")]
    BadRequest = 40000,
    
    [Description("未进行身份认证")]
    Unauthorized = 40100,
    
    [Description("请求被拒绝")]
    Forbidden = 40300,
    
    [Description("参数无效")]
    InvalidParameter = 100001,
    
    [Description("参数类型不正确")]
    InvalidParameterType = 100002,
}

/*
 * Document
 *
 * 为了和http状态码区分开来，这里选择在原有的状态码基础上加上两个0
 * * 示例：Ok = 20000
 *
 * 参数错误码为 10000*
 */