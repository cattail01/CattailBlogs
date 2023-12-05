using Microsoft.AspNetCore.Mvc;

namespace CattailBlogBackEndProgram.Web;

[ApiController]
[Route("/test/[controller]")]
public class GetAllBlogArticlesController : ControllerBase
{
    private ILogger<GetAllBlogArticlesController> _logger;

    public GetAllBlogArticlesController(ILogger<GetAllBlogArticlesController> logger)
    {
        _logger = logger;
    }

    [HttpGet(Name = "GetAllBlogArticles1")]
    public object GetAllBlogArticles()
    {
        return "success";
    }
}
