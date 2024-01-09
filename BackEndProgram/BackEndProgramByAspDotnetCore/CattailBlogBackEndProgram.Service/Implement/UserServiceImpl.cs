using AutoMapper;
using CattailBlogBackEndProgram.Data.SqlSugar;

namespace CattailBlogBackEndProgram.Service;

public class UserServiceImpl: IUserService
{
    private IMapper Mapper { get; set; }

    public UserServiceImpl(IMapper mapper)
    {
        Mapper = mapper;
    }
    
    /// <summary>
    /// 用户登录
    /// </summary>
    /// <param name="loginDto"></param>
    /// <returns></returns>
    public async Task<User> CheckLoginAsync(UserLoginDto loginDto)
    {
        return await DbContext.Db.Queryable<User>()
            .FirstAsync(m => m.UserName == loginDto.Email && m.Password == loginDto.Password);
    }
    
    // UserRegisterDto转换为User
    private User TransUserRegisterDtoToUser(UserRegisterDto userRegisterDto)
    {
        var user = Mapper.Map<User>(userRegisterDto);
        var date = DateTime.Now;
        user.GmTCreateTime = date;
        user.GmtUpdateTime = date;
        user.UserId = Guid.NewGuid().ToString();
        user.Status = 0;
        return user;
    }
    
    /// <summary>
    /// 添加用户
    /// </summary>
    /// <param name="userRegisterDto"></param>
    /// <returns></returns>
    /// <exception cref="Exception"></exception>
    public async Task<User> AddUserAsync(UserRegisterDto userRegisterDto)
    {
        // 验证是否存在
        if (await DbContext.Db.Queryable<User>().AnyAsync(m => m.Email == userRegisterDto.Email))
        {
            throw new Exception("该邮箱/用户已存在");
        }
        // 插入数据
        var user = TransUserRegisterDtoToUser(userRegisterDto);
        DbContext.Db.Insertable(user).ExecuteCommand();
        // 验证是否成功
        if (!await DbContext.Db.Queryable<User>().AnyAsync(e => e.Email == userRegisterDto.Email))
        {
            throw new Exception("用户创建失败");
        }
        return user;
    }
}
