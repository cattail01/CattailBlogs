namespace CattailBlogBackEndProgram.Utility;

/// <summary>
/// 统一返回值帮助类
/// </summary>
public static class ResultHelper
{
    /// <summary>
    /// ResultCode成员与其description attribute映射
    /// </summary>
    public static readonly Dictionary<ResultCode, string> ResultCodeMap = EnumHelper.GetDescriptions<ResultCode>();

    /// <summary>
    /// 空 object
    /// </summary>
    private static readonly object EmptyObject = new object();

    #region success result
    
    // /// <summary>
    // /// 请求成功
    // /// </summary>
    // /// <returns>统一返回值</returns>
    // public static ResultSingleton Success() => ResultSingleton.Instance.Build(true, EmptyObject);

    // /// <summary>
    // /// 请求成功
    // /// </summary>
    // /// <param name="data">数据</param>
    // /// <returns>统一返回值</returns>
    // public static ResultSingleton Success(object? data) => ResultSingleton.Instance.Build(true, data);

    /// <summary>
    /// 请求成功
    /// </summary>
    /// <param name="data">数据</param>
    /// <returns>统一返回值</returns>
    public static ResultSingleton Success(object? data = null) => ResultSingleton.Instance.Build(true, data);
    
    // /// <summary>
    // /// 请求成功
    // /// </summary>
    // /// <param name="message">消息</param>
    // /// <returns>统一返回值</returns>
    // public static ResultSingleton Success(string message) => ResultSingleton.Instance.Build(true, message);

    // /// <summary>
    // /// 请求成功
    // /// </summary>
    // /// <param name="message">消息</param>
    // /// <param name="data">统一返回值</param>
    // public static ResultSingleton Success(string message, object? data) =>
    //     ResultSingleton.Instance.Build(true, message, data);
    
    /// <summary>
    /// 请求成功
    /// </summary>
    /// <param name="message">消息</param>
    /// <param name="data">统一返回值</param>
    /// <returns>统一返回值</returns>
    public static ResultSingleton Success(string message, object? data = null) =>
        ResultSingleton.Instance.Build(true, message, data);

    #endregion success result
    
    #region error result
    
    /// <summary>
    /// 请求失败
    /// </summary>
    /// <returns>统一返回值</returns>
    public static ResultSingleton Error() => ResultSingleton.Instance.Build(false, EmptyObject);

    /// <summary>
    /// 失败
    /// </summary>
    /// <param name="message">消息</param>
    /// <returns>统一返回值</returns>
    public static ResultSingleton Error(string message) => ResultSingleton.Instance.Build(false, message);
    
    /// <summary>
    /// 请求失败
    /// </summary>
    /// <param name="resultCode">返回状态</param>
    /// <returns>统一返回值</returns>
    public static ResultSingleton Error(ResultCode resultCode) =>
        ResultSingleton.Instance.Build(resultCode, EmptyObject);

    /// <summary>
    /// 失败
    /// </summary>
    /// <param name="resultCode">返回状态</param>
    /// <param name="message">消息</param>
    /// <returns>统一返回值</returns>
    public static ResultSingleton Error(ResultCode resultCode, string message) =>
        ResultSingleton.Instance.Build(resultCode, message);
    
    #endregion error result
}
