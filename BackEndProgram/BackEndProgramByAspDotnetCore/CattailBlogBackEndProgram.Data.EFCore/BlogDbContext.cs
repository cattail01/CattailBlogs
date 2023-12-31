﻿using Microsoft.EntityFrameworkCore;

namespace CattailBlogBackEndProgram.Data.EFCore;

public class BlogDbContext : DbContext
{
    public DbSet<BlogArticle>? BlogArticles { get; set; }
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
