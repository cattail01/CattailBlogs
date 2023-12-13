using System.Net;
using Microsoft.AspNetCore.Mvc;

namespace CattailBlogBackEndProgram.Utility.GlobalExceptionHandler;

public class GlobalExceptionHandlerMiddleware
{
    private readonly RequestDelegate _next;
    
    public GlobalExceptionHandlerMiddleware(RequestDelegate  next)
    {
        _next = next;
    }

    private Task HandleExceptionAsync(HttpContext context, Exception exception)
    {
        context.Response.ContentType = "application/json";
        context.Response.StatusCode = (int)HttpStatusCode.InternalServerError;

        JsonResult result;
    }

    public async Task Invoke(HttpContext context)
    {
        try
        {

        }
        catch (Exception e)
        {
            
        }
    }
}
