using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using Microsoft.EntityFrameworkCore;

namespace CattailBlogBackEndProgram.Data;

/// <summary>
/// BlogArticle 实体类
/// </summary>
[PrimaryKey(nameof(Id))] // 指定主键
[Table("cattailblogs_blogarticles")] // 指定表名称
public class BlogArticle
{
    public Guid? Id { get; set; }
    
    [Column(TypeName = "datetime")] 
    public DateTime GmtCreateTime { get; set; }
    
    [Column(TypeName = "datetime")] 
    public DateTime GmtUpdateTime { get; set; }
    
    [Column(TypeName = "varchar(255)")]
    public string Title { get; set; }

    [Column(TypeName = "varchar(255)")] 
    public string? Description { get; set; }

    [Column(TypeName = "longtext")] 
    public string? Content { get; set; }

    public int Status { get; set; }

    public int Tag { get; set; }
}