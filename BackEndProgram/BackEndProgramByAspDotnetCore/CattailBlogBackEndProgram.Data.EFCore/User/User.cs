using System.ComponentModel.DataAnnotations.Schema;
using Microsoft.EntityFrameworkCore;

namespace CattailBlogBackEndProgram.Data;

/// <summary>
/// Users实体类
/// </summary>
[PrimaryKey(nameof(UserId))]
[Table("cattailblogs_users")]
public class User
{
    [Column(TypeName = "varchar(64)")] 
    public Guid? UserId { get; set; }

    [Column(TypeName = "datetime")] 
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
    public string? Email { get; set; }

    public int Status { get; set; }
}
