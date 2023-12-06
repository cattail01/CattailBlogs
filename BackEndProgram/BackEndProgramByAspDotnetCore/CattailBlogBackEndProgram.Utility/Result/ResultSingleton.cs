namespace CattailBlogBackEndProgram.Utility;

/// <summary>
/// 统一结果封装
/// </summary>
public class ResultSingleton : SingletonBase<ResultSingleton>
{
    /// <summary>
    /// 返回状态码
    /// </summary>
    public int Code { get; set; }

    /// <summary>
    /// 返回状态
    /// </summary>
    public string? Status { get; set; }

    /// <summary>
    /// 返回消息
    /// </summary>
    public string? Message { get; set; }

    /// <summary>
    /// 返回数据
    /// </summary>
    public object? Data { get; set; }

    /// <summary>
    /// 最基本的构建返回值对象方法
    /// </summary>
    /// <param name="code">返回码</param>
    /// <param name="status">返回状态</param>
    /// <param name="message">返回消息描述</param>
    /// <param name="data">返回数据</param>
    /// <returns>统一返回封装</returns>
    /// <remarks>
    /// 常用于封装成更高级的重载函数使用
    /// 为了提供更灵活的开发方式，这里选择暴露出来
    /// </remarks>
    public ResultSingleton Build(int code, string? status, string? message, object? data)
    {
        Code = code;
        Status = status;
        Message = message;
        Data = data;
        return this;
    }

    /// <summary>
    /// 携带数据的可指定状态码的构建方法
    /// </summary>
    /// <param name="resultCode">状态码enum</param>
    /// <param name="data">数据</param>
    /// <returns>统一返回封装</returns>
    public ResultSingleton Build(ResultCode resultCode, object? data) => Build((int)resultCode, resultCode.ToString(),
        ResultCodeEnumDescriptionSingleton.Instance.ResultCodeDescriptionDictionary[resultCode], data);

    /// <summary>
    /// 指定消息的状态码构建方法
    /// </summary>
    /// <param name="resultCode"></param>
    /// <param name="message">手动指定消息</param>
    /// <returns>统一返回封装</returns>
    /// <remarks>注意：为了避免和object？冲突，设计message为string 非null类型</remarks>
    public ResultSingleton Build(ResultCode resultCode, string message) =>
        Build((int)resultCode, resultCode.ToString(), message, null);

    /// <summary>
    /// 指定消息的状态码构建方法
    /// </summary>
    /// <param name="resultCode">返回状态</param>
    /// <param name="message">消息</param>
    /// <param name="data">数据</param>
    /// <returns>统一返回封装</returns>
    public ResultSingleton Build(ResultCode resultCode, string message, object? data) =>
        Build((int)resultCode, resultCode.ToString(), message, data);


    /// <summary>
    /// 通过bool success 返回状态码
    /// </summary>
    /// <param name="success">表示连接是否成功</param>
    /// <returns>状态码：ok或未知错误</returns>
    private ResultCode DefaultResultCode(bool success) => success ? ResultCode.Ok : ResultCode.UnknownError;

    /// <summary>
    /// 构建方法-简略版
    /// </summary>
    /// <param name="success">是否成功</param>
    /// <param name="data">数据</param>
    /// <returns>统一返回封装</returns>
    public ResultSingleton Build(bool success, object? data) => Build(DefaultResultCode(success), data);

    /// <summary>
    /// 构建方法-简略版
    /// </summary>
    /// <param name="success">是否成功</param>
    /// <param name="message">消息</param>
    /// <returns>统一返回封装</returns>
    public ResultSingleton Build(bool success, string message) => Build(DefaultResultCode(success), message);
    
    /// <summary>
    /// 构建方法-简略版
    /// </summary>
    /// <param name="success">是否成功</param>
    /// <param name="message">消息</param>
    /// <param name="data">数据</param>
    /// <returns>统一返回封装</returns>
    public ResultSingleton Build(bool success, string message, object? data) =>
        Build(DefaultResultCode(success), message, data);
}
