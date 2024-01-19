using System.Net;
using CattailBlogBackEndProgram.Utility;

namespace CattailBlogBackEndProgram.Web;

public class GlobalExceptionHandlerMiddleware
{
    private readonly RequestDelegate _next; // 程序上下文
    private readonly ILogger<GlobalExceptionHandlerMiddleware> _logger;  // 日志

    /// <summary>
    /// 构造函数
    /// </summary>
    public GlobalExceptionHandlerMiddleware(RequestDelegate next, ILogger<GlobalExceptionHandlerMiddleware> logger)
    {
        _next = next;
        _logger = logger;
    }

    // 异常处理
    private Task HandleExceptionAsync(HttpContext context, Exception exception)
    {
        context.Response.ContentType = "application/json";  // 设置返回类型
        
        // 自定义异常基类处理
        if (exception is BusinessException businessException)
        {
            context.Response.StatusCode = (int)HttpStatusCode.BadRequest;  // 返回 400 bad request
            return context.Response.WriteAsJsonAsync(businessException.ResultSingleton);
        }

        // other Exceptions
        context.Response.StatusCode = (int)HttpStatusCode.BadRequest;  // 返回 400 bad request
        _logger.LogError(exception, exception.Message);  // 记录错误日志
        return context.Response.WriteAsJsonAsync(
            ResultHelper.Error(string.IsNullOrEmpty(exception.Message) ? "其他类型异常" : exception.Message));
    }
    
    // todo 暂时将所有error设置为400badrequest，后期再u欧化

    /// <summary>
    /// 供原程序调用
    /// </summary>
    public async Task Invoke(HttpContext context)
    {
        try
        {
            await _next(context);
        }
        catch (Exception e)
        {
            await HandleExceptionAsync(context, e);
        }
    }
}
