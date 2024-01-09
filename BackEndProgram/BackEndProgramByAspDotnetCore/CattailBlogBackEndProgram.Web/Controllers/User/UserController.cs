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
    private ILogger<UserController> Logger { get; set; } // 日志
    private IUserService UserService { get; set; } // 用户服务

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
        var user = await UserService.CheckLoginAsync(userLoginDto);
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
        var user = await UserService.AddUserAsync(userRegisterDto);
        var resultMessage = user.UserName + "注册成功";
        return ResultHelper.Success(resultMessage, user);
    }

    /// <summary>
    /// 获取验证码图片接口
    /// </summary>
    /// <returns></returns>
    [HttpGet]
    public async Task<IActionResult> GetVerificationCodeTexture(string t)
    {
        var verificationCode = VerificationCodeHelperSingleton.Instance.CreateVerificationCodeString(); // 生成验证码字符串
        var textureBytes =
            await VerificationCodeHelperSingleton.Instance
                .CreateVerificationCodeTextureAsync(verificationCode); // 生成验证码图片
        MemoryHelperSingleton.Instance.SetMemory(t, verificationCode, 1); // 数据存放到缓存
        Logger.LogInformation(t + "验证码" + verificationCode); // 打印信息
        // return ResultHelper.Success(File(textureBytes, @"image/jpeg"));  // 返回图片数据
        return File(textureBytes, @"image/jpeg");
    }

    /// <summary>
    /// 验证验证码是否正确
    /// </summary>
    /// <param name="t"></param>
    /// <param name="Answer"></param>
    /// <returns></returns>
    /// <exception cref="Exception"></exception>
    [HttpPost]
    public async Task<ResultSingleton> VerifyVerificationCode(string t, string Answer)
    {
        string? answer = MemoryHelperSingleton.Instance.GetMemory<string>(t);
        if (answer.ToLower() != Answer.ToLower())
        {
            throw new Exception("输入验证码错误");
        }
        return ResultHelper.Success("验证码验证正确");
    }

    // todo 后期将验证码功能改成图片验证码形式（滑动条或接入其他验证码接口）
}