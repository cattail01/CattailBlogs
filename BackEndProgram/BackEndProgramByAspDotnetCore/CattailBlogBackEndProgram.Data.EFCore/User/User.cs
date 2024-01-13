namespace CattailBlogBackEndProgram.Data.EFCore;

/// <summary>
/// 用户实体类
/// </summary>
public class User
{
    /// <summary>
    /// 用户id，类型为guid
    /// </summary>
    public Guid? Id { get; set; }

    /// <summary>
    /// 创建时间
    /// </summary>
    public DateTime? GmtCreateTime { get; set; }

    /// <summary>
    /// 更新时间
    /// </summary>
    public DateTime? GmtUpdateTime { get; set; }
    
    /// <summary>
    /// 邮件地址
    /// </summary>
    public string Email { get; set; }

    /// <summary>
    /// 用户名
    /// </summary>
    public string? UserName { get; set; }

    /// <summary>
    /// 密码
    /// </summary>
    public string? Password { get; set; }
    
    /// <summary>
    /// 头像图片地址
    /// </summary>
    public string? Avatar { get; set; }
    
    /// <summary>
    /// 性别
    /// </summary>
    public int? Gender { get; set; }
    
    /// <summary>
    /// 账户状态
    /// </summary>
    public int? Status { get; set; }
}
