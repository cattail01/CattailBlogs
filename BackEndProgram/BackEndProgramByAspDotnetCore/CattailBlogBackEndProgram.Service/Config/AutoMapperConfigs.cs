using AutoMapper;
using CattailBlogBackEndProgram.Data.SqlSugar;

namespace CattailBlogBackEndProgram.Service;

public class AutoMapperConfigs: Profile
{
    public AutoMapperConfigs()
    {
        CreateMap<User, UserLoginDto>();
        CreateMap<UserRegisterDto, User>();
    }
}