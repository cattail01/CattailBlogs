using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;

namespace CattailBlogBackEndProgram.Data;

public class BlogArticlesEntityConfig : IEntityTypeConfiguration<BlogArticles>
{
    public void Configure(EntityTypeBuilder<BlogArticles> builder)
    {
        builder.ToTable("cattailblogs_blogarticles");
    }
}