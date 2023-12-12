using CattailBlogBackEndProgram.Utility;
using Microsoft.AspNetCore.Mvc;

namespace CattailBlogBackEndProgram.Web;

[ApiController]
[Route("/test/[controller]/[action]")]
public class GetAllBlogArticlesController : ControllerBase
{
    private ILogger<GetAllBlogArticlesController> _logger;

    public GetAllBlogArticlesController(ILogger<GetAllBlogArticlesController> logger)
    {
        _logger = logger;
    }

    [HttpGet(Name = "GetAllArticles")]
    public object GetAllArticles()
    {
        // return ResultHelper.Success(data: "hello unity result packaging");
        return BadRequest("hello unity result packaging");
    }
}
