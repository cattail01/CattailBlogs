using Microsoft.EntityFrameworkCore;

namespace CattailBlogBackEndProgram.Data.EFCore;

/// <summary>
/// 数据库上下文类
/// </summary>
public class BlogDbContext : DbContext
{
    /// <summary>
    /// 博客文章
    /// </summary>
    public DbSet<BlogArticle>? BlogArticles { get; set; }
    
    /// <summary>
    /// 用户
    /// </summary>
    public DbSet<User>? Users { get; set; }
    
    protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
    {
        string connStr =
            $"Server=localhost;Port=3306;Database={BlogDatabaseName.DbName};UserId=root;Password=123456"; // mysql 连接字符串
        optionsBuilder.UseMySql(connStr, ServerVersion.AutoDetect(connStr));
    }

    protected override void OnModelCreating(ModelBuilder modelBuilder)
    {
        base.OnModelCreating(modelBuilder);

        // 从当前程序集中自动加载所有实现IEntityTypeConfiguration接口的类
        modelBuilder.ApplyConfigurationsFromAssembly(GetType().Assembly);
    }
}