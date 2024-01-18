using CattailBlogBackEndProgram.Data.SqlSugar;

namespace CattailBlogBackEndProgram.Service;

/// <summary>
/// token 服务
/// </summary>
public interface ICustomJwtService
{
    /// <summary>
    /// 获取用户的token
    /// </summary>
    string GetToken(User user);
}