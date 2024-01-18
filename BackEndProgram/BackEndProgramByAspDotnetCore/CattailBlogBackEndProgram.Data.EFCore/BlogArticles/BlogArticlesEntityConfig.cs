using CattailBlogBackEndProgram.Data.EFCore;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;

namespace CattailBlogBackEndProgram.Data;

/// <summary>
/// BlogArticles实体类配置
/// </summary>
public class BlogArticlesEntityConfig : IEntityTypeConfiguration<BlogArticle>
{
    public void Configure(EntityTypeBuilder<BlogArticle> builder)
    {
        // 表名映射
        builder.ToTable(BlogDatabaseName.ArticleTableName);
        
        // 配置作者id约定外键
        builder.Property(article => article.AuthorId)
            .HasColumnName(nameof(BlogArticle.AuthorId))  // 配置名称
            .HasComment("连接用户id的约定外键，在应用逻辑层应注意添加处理逻辑，比如删除用户时需要先删除相关内容")  // 配置注释
            .IsRequired()
            ;
    }
}

// notice 由于外键可能带来性能和实际操作的麻烦，在项目中实行约定外键，即手动处理外键内容
