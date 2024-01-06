namespace CattailBlogBackEndProgram.Data.SqlSugar;

public class UserRegisterDto
{
    public string UserName { get; set; }
    public string Password { get; set; }
    public string? Avatar { get; set; }
    public int Gender { get; set; }
    public string Email { get; set; }
}