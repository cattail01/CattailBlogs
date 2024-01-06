using SqlSugar;

namespace CattailBlogBackEndProgram.Data.SqlSugar;

[SugarTable("cattailblogs_users")]
public class User
{
    [SugarColumn(IsPrimaryKey = true, IsIdentity = false)]
    public string UserId { get; set; }
    public DateTime GmTCreateTime { get; set; }
    public DateTime GmtUpdateTime { get; set; }
    public string UserName { get; set; }
    public string Password { get; set; }
    public string? Avatar { get; set; }
    public int Gender { get; set; }
    public string Email { get; set; }
    public int Status { get; set; }
}