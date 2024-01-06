using CattailBlogBackEndProgram.Data.SqlSugar;
using CattailBlogBackEndProgram.Service;
using CattailBlogBackEndProgram.Utility;
using Microsoft.AspNetCore.Mvc;

namespace CattailBlogBackEndProgram.Web.Controllers;

[ApiController]
[Route("[controller]/[action]")]
public class UserController : ControllerBase
{
    private ILogger<UserController> Logger { get; set; }
    private IUserService UserService { get; set; }

    public UserController(ILogger<UserController> logger, IUserService userService)
    {
        Logger = logger;
        UserService = userService;
    }
    
    [HttpPost]
    public async Task<ResultSingleton> Login(UserLoginDto userLoginDto)
    {
        var user = await UserService.CheckLogin(userLoginDto);
        Logger.LogInformation(user.UserName + "登录成功");
        return ResultHelper.Success(user);
    }
}
