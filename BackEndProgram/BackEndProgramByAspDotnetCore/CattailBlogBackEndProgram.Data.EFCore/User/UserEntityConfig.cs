using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;

namespace CattailBlogBackEndProgram.Data.EFCore;

public class UserEntityConfig : IEntityTypeConfiguration<User>
{
    public void Configure(EntityTypeBuilder<User> builder)
    {
        builder.ToTable("cattailblogs_users");  // 表名映射
        builder.HasKey(d => d.UserId);  // 设置表主键
        
    }
}
