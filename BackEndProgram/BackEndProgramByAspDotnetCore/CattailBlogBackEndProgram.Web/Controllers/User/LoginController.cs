using CattailBlogBackEndProgram.Data;
using CattailBlogBackEndProgram.Utility;
using Microsoft.AspNetCore.Mvc;

namespace CattailBlogBackEndProgram.Web.Controllers;

[ApiController]
[Route("[controller]/[action]")]
public class LoginController : ControllerBase
{

    [HttpPost]
    // public async Task<IActionResult> Login()
    public async Task<ResultSingleton> UserLogin(UserLoginReq userLoginReq)
    {
        if(userLoginReq.UserName != "admin" || userLoginReq.Password != "admin")
        {
            throw new Exception("用户名或密码错误");
        }
        
        return ResultHelper.Success("登录成功");
    }
}
