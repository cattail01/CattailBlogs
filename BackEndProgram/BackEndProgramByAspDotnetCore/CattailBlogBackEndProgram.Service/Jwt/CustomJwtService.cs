using System.IdentityModel.Tokens.Jwt;
using System.Security.Claims;
using System.Text;
using CattailBlogBackEndProgram.Data.SqlSugar;
using Microsoft.Extensions.Options;
using Microsoft.IdentityModel.Tokens;

namespace CattailBlogBackEndProgram.Service;

/// <summary>
/// jwt 服务
/// </summary>
public class CustomJwtService : ICustomJwtService
{
    private JwtOptions JwtOptions { get; set; } // jwt
    private JwtSecurityTokenHandler JwtSecurityTokenHandler => new JwtSecurityTokenHandler();  // jwt处理工具

    /// <summary>
    /// 构造函数，用于依赖注入
    /// </summary>
    public CustomJwtService(IOptionsMonitor<JwtOptions> jwtOptions)
    {
        JwtOptions = jwtOptions.CurrentValue; // 使用即使修改接口接受jwtOptions的信息
    }

    // 创建jwt token
    private SecurityToken CreateToken(Claim[] claims)
    {
        SymmetricSecurityKey key = new SymmetricSecurityKey(Encoding.UTF8.GetBytes(JwtOptions.SecurityKey)); // 设置加密key
        SigningCredentials creds = new SigningCredentials(key, SecurityAlgorithms.HmacSha256); // 设置加密类型
        // 生成jwt token
        var token = new JwtSecurityToken(
            issuer: JwtOptions.Issuer, // 配置发行者
            audience: JwtOptions.Audience, // 配置接受者
            claims: claims, // 载荷
            expires: DateTime.Now.AddMinutes(JwtOptions.Expires), // 过期时间
            signingCredentials: creds // 加密方式
        );
        return token;  // 返回token
    }

    /// <summary>
    /// 获取用户token
    /// </summary>
    public string GetToken(User user)
    {
        // 创建载荷，可以写一些不敏感的信息
        var claims = new[]
        {
            new Claim(ClaimTypes.Name, user.UserName),
            new Claim(ClaimTypes.Email, user.Email),
        };
        var token = CreateToken(claims);  // 生成token
        return JwtSecurityTokenHandler.WriteToken(token); // 返回token字符串
    }
}
