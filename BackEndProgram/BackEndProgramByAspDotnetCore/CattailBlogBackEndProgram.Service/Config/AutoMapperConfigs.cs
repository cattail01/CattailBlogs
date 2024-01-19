using AutoMapper;
using CattailBlogBackEndProgram.Data.SqlSugar;

namespace CattailBlogBackEndProgram.Service;

/// <summary>
/// auto mapper 配置类
/// </summary>
public class AutoMapperConfigs: Profile
{
    /// <summary>
    /// 构造函数，表示对谁创建映射
    /// </summary>
    public AutoMapperConfigs()
    {
        CreateMap<User, UserLoginDto>();
        CreateMap<UserRegisterDto, User>();
    }
}