using System.ComponentModel.DataAnnotations.Schema;
using Microsoft.EntityFrameworkCore;

namespace CattailBlogBackEndProgram.Data;

[PrimaryKey(propertyName:"Id")]
[Table("cattailblogs_blogarticles")]
public class BlogArticles
{
    [Column(TypeName = "varchar(64)")]
    public string? Id { get; set; }

    [Column(TypeName = "datetime")]
    public DateTime GmtCreateTime { get; set; }

    [Column(TypeName = "datetime")]
    public DateTime GmtUpdateTime { get; set; }

    [Column(TypeName = "varchar255")]
    public string? Title { get; set; }

    [Column(TypeName = "varchar255")]
    public string? Description { get; set; }

    [Column(TypeName = "longtext")]
    public string? Content { get; set; }

    public int Status { get; set; }
}
