using CattailBlogBackEndProgram.Data.SqlSugar;

namespace CattailBlogBackEndProgram.Service;

/// <summary>
/// 用户服务接口
/// </summary>
public interface IUserService
{
    /// <summary>
    /// 用户登录
    /// </summary>
    /// <param name="loginDto"></param>
    /// <returns></returns>
    Task<User> CheckLogin(UserLoginDto loginDto);

    /// <summary>
    /// 添加用户
    /// </summary>
    /// <param name="registerDto"></param>
    /// <returns></returns>
    Task<User> AddUser(UserRegisterDto registerDto);
    
    
    // User GetUser(Guid userId);
}