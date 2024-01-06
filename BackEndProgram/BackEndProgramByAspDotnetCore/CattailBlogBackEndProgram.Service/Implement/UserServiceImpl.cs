using CattailBlogBackEndProgram.Data.SqlSugar;

namespace CattailBlogBackEndProgram.Service;

public class UserServiceImpl: IUserService
{
    /// <summary>
    /// 用户登录
    /// </summary>
    /// <param name="loginDto"></param>
    /// <returns></returns>
    public async Task<User> CheckLogin(UserLoginDto loginDto)
    {
        return await DbContext.Db.Queryable<User>()
            .FirstAsync(m => m.UserName == loginDto.Email && m.Password == loginDto.Password);
    }
}
