using CattailBlogBackEndProgram.Utility;
using Microsoft.AspNetCore.Mvc;

namespace CattailBlogBackEndProgram.Web.Test;

#if DEBUG
[ApiController]
[Route("/test/[controller]/[action]")]
#endif

public class GetAllBlogArticlesController : ControllerBase
{
    private ILogger<GetAllBlogArticlesController> _logger;

    public GetAllBlogArticlesController(ILogger<GetAllBlogArticlesController> logger)
    {
        _logger = logger;
    }

    [HttpGet]
    public object GetAllArticles()
    {
        object result = new object();
        return Ok(ResultHelper.Success(result));
        // throw new BusinessException(ResultHelper.Error(ResultCode.BadRequest, "测试全局异常处理"));
    }
}
