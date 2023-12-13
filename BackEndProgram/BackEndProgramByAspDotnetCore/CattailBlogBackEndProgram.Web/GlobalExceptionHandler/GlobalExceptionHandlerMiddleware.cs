using System.Net;
using CattailBlogBackEndProgram.Utility;

namespace CattailBlogBackEndProgram.Web;

public class GlobalExceptionHandlerMiddleware
{
    private readonly RequestDelegate _next;

    public GlobalExceptionHandlerMiddleware(RequestDelegate next)
    {
        _next = next;
    }

    private Task HandleExceptionAsync(HttpContext context, Exception exception)
    {
        context.Response.ContentType = "application/json";

        // todo 这里需要研究返回值代码，并给予合适的返回代码
        // context.Response.StatusCode = (int)HttpStatusCode.InternalServerError;

        // 自定义异常基类处理
        if (exception is BusinessException businessException)
        {
            context.Response.StatusCode = (int)HttpStatusCode.InternalServerError;
            return context.Response.WriteAsJsonAsync(businessException.ResultSingleton);
        }

        // other Exceptions
        context.Response.StatusCode = (int)HttpStatusCode.InternalServerError;
        return context.Response.WriteAsJsonAsync(ResultHelper.Error("其他类型异常"));
    }

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