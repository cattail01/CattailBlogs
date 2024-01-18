namespace CattailBlogBackEndProgram.Data.EFCore;

/// <summary>
/// 博客数据库名称
/// </summary>
public static class BlogDatabaseName
{
    /// <summary>
    /// 数据库名称
    /// </summary>
    public static readonly string DbName = "cattail_blog_database";  // todo 后期将该项目配置方式由内部配置变成外部配置
    
    /// <summary>
    /// 用户表名称
    /// </summary>
    public static readonly string UserTableName = $"{DbName}_{nameof(User)}s";

    /// <summary>
    /// 博客文章表名称
    /// </summary>
    public static readonly string ArticleTableName = $"{DbName}_{nameof(BlogArticle)}s";
    
}

