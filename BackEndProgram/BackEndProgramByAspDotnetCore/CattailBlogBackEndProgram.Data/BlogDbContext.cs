using Microsoft.EntityFrameworkCore;

namespace CattailBlogBackEndProgram.Data;

public class BlogDbContext : DbContext
{
    // [notice] 连接几个表就写几个
    public DbSet<BlogArticles>? BlogArticles { get; set; }
    public DbSet<User>? Users { get; set; }
    public DbSet<ArticleAuthor>? ArticleAuthors { get; set; }

    protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
    {
        string connStr = "Server=localhost;Port=3306;Database=cattailblogbackenddb;UserId=root;Password=123456";
        optionsBuilder.UseMySql(connStr, ServerVersion.AutoDetect(connStr));
    }

    protected override void OnModelCreating(ModelBuilder modelBuilder)
    {
        base.OnModelCreating(modelBuilder);

        // 从当前程序集中自动加载所有实现IEntityTypeConfiguration接口的类
        modelBuilder.ApplyConfigurationsFromAssembly(GetType().Assembly);
    }
}
