using System.ComponentModel.DataAnnotations.Schema;

namespace CattailBlogBackEndProgram.Data.EFCore;

/// <summary>
/// User实体类
/// </summary>
public class User
{
    public Guid? UserId { get; set; }
    public DateTime GmtCreateTime { get; set; }

    [Column(TypeName = "datetime")] 
    public DateTime GmtUpdateTime { get; set; }

    [Column(TypeName = "varchar(255)")] 
    public string UserName { get; set; }
    
    [Column(TypeName = "varchar(255)")] 
    public string Password { get; set; }

    [Column(TypeName = "varchar(255)")] 
    public string? Avatar { get; set; }

    public int Gender { get; set; }

    [Column(TypeName = "varchar(64)")] 
    public string Email { get; set; }

    public int Status { get; set; }
}
