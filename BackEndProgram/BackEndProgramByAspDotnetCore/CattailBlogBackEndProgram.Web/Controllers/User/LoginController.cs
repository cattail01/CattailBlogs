using CattailBlogBackEndProgram.Data;
using CattailBlogBackEndProgram.Utility;
using Microsoft.AspNetCore.Mvc;

namespace CattailBlogBackEndProgram.Web.Controllers;

[ApiController]
[Route("[controller]/[action]")]
public class LoginController : ControllerBase
{
    [HttpPost]
    public async Task<ResultSingleton> UserLogin(UserLoginReq userLoginReq)
    {
        return ResultHelper.Success("登录成功");
    }
}
