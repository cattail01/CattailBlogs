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
        // [process] 表名映射
        // builder.ToTable("cattailblogs_blogarticles");

        #region 修改表结构

        // [process] 给Tag添加默认值
        builder.Property(e => e.Tag).HasDefaultValue(0);
        
        // [process] 给status添加默认值
        builder.Property(e => e.Status).HasDefaultValue(0).IsRequired(true);
        
        #endregion 修改表结构
        
    }
}
