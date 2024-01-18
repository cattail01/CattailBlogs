namespace CattailBlogBackEndProgram.Service;

/// <summary>
/// jwt 选项 （配置）
/// </summary>
public class JwtOptions
{
    /// <summary>
    /// 目标受众
    /// </summary>
    public string Audience = String.Empty;
    
    /// <summary>
    /// 安全代码
    /// </summary>
    public string SecurityKey = String.Empty;
    
    /// <summary>
    /// 发行者
    /// </summary>
    public string Issuer = String.Empty;

    /// <summary>
    /// 过期时间
    /// </summary>
    public int Expires = 5;  // 默认设置为5分钟
}