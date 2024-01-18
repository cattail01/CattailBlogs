using CattailBlogBackEndProgram.Utility;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;

namespace CattailBlogBackEndProgram.Data.EFCore;

/// <summary>
/// 用户实体配置
/// </summary>
public class UserEntityConfig : IEntityTypeConfiguration<User>
{
    /// <summary>
    /// 表的相关配置
    /// </summary>
    /// <param name="builder"></param>
    public void Configure(EntityTypeBuilder<User> builder)
    {
        builder.ToTable($"{BlogDatabaseName.UserTableName}"); // 表名映射
        builder.HasKey(d => d.Id); // 设置表主键

        // 配置id
        builder.Property(user => user.Id)
            .HasColumnName(nameof(User.Id)) // 定义名称
            .HasColumnType("VARCHAR(64)") // 定义类型
            .HasDefaultValueSql("UUID()") // 定义默认值
            .IsRequired() // 定义not null
            ;

        // 配置创建时间
        builder.Property(user => user.GmtCreateTime)
            .HasColumnName(nameof(User.GmtCreateTime)) // 定义名称
            .HasColumnType("DATETIME") // 定义类型
            .HasDefaultValueSql("GETDATE()") // 定义默认值
            .IsRequired() // 定义not null
            ;

        // 配置更新时间
        builder.Property(user => user.GmtUpdateTime)
            .HasColumnName(nameof(User.GmtUpdateTime))  // 定义名称
            .HasColumnType("DATETIME")  // 定义类型
            .HasComputedColumnSql("GETDATE()") // 定义计算列
            .IsRequired()  // 定义not null
            ;

        // 配置用户名
        builder.Property(user => user.UserName)
            .HasColumnName(nameof(User.UserName))  // 定义名称
            .HasColumnType("VARCHAR(64)")  // 定义类型
            .HasDefaultValue("新用户")  // 定义默认值
            .IsRequired()  // 定义not null
            ;
        
        // 配置密码
        builder.Property(user => user.Password)
            .HasColumnName(nameof(User.Password)) // 定义名称
            .HasColumnType("VARCHAR(64)") // 定义类型
            .HasDefaultValue(Md5Helper.Instance.StringEncodingToMd516Str("password123456")) // 定义默认值
            .IsRequired() // 定义not null
            ;

        // 配置头像
        builder.Property(user => user.Avatar)
            .HasColumnName(nameof(User.Avatar)) // 定义名称
            .HasColumnType("VARCHAR(256)") // 定义类型
            .HasDefaultValue("https://tupian.qqw21.com/article/UploadPic/2020-8/20208522181570993.jpg") // 定义默认值
            .IsRequired(false) // 定义not null false
            ;
        
        // 配置邮箱地址
        builder.Property(user => user.Email)
            .HasColumnName(nameof(User.Email)) // 定义名称
            .HasColumnType("VARCHAR(64)") // 定义类型
            .HasDefaultValue("123456@qq.com") // 定义默认值
            .IsUnicode(true)  // 设置唯一索引
            .IsRequired(true)  // 设置not null
            ;
        
        // 配置性别
        builder.Property(user => user.Gender)
            .HasColumnName(nameof(User.Gender)) // 定义名称
            .IsRequired() // 定义not null 
            ;
        // todo 第一次再efcore中使用enum，等表建好，看看具体类型究竟如何，然后再考虑默认值的问题
        
        // 配置账户状态
        builder.Property(user => user.Status)
            .HasColumnName(nameof(User.Status)) // 定义名称
            .IsRequired() // 定义not null
            ;
        // todo 下面也是
    }
}
