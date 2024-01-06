using CattailBlogBackEndProgram.Data.SqlSugar;
using CattailBlogBackEndProgram.Service;
using CattailBlogBackEndProgram.Utility;
using Microsoft.AspNetCore.Mvc;

namespace CattailBlogBackEndProgram.Web.Controllers;

/// <summary>
/// 用户接口
/// </summary>
[ApiController]
[Route("[controller]/[action]")]
public class UserController : ControllerBase
{
    private ILogger<UserController> Logger { get; set; }  // 日志
    private IUserService UserService { get; set; }  // 用户服务

    public UserController(ILogger<UserController> logger, IUserService userService)
    {
        Logger = logger;
        UserService = userService;
    }
    
    /// <summary>
    /// 登录接口
    /// </summary>
    /// <param name="userLoginDto"></param>
    /// <returns></returns>
    [HttpPost]
    public async Task<ResultSingleton> Login(UserLoginDto userLoginDto)
    {
        var user = await UserService.CheckLogin(userLoginDto);
        var resultMessage = user.UserName + "登录成功";
        Logger.LogInformation(resultMessage);
        return ResultHelper.Success(resultMessage, user);
    }

    /// <summary>
    /// 注册接口
    /// </summary>
    /// <param name="userRegisterDto"></param>
    /// <returns></returns>
    [HttpPost]
    public async Task<ResultSingleton> Register(UserRegisterDto userRegisterDto)
    {
        var user = await UserService.AddUser(userRegisterDto);
        var resultMessage = user.UserName + "注册成功";
        return ResultHelper.Success(resultMessage, user);
    }
}
