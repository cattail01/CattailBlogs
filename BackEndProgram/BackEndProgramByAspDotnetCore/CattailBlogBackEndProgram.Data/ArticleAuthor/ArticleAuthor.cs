using System.ComponentModel.DataAnnotations.Schema;
using Microsoft.EntityFrameworkCore;

namespace CattailBlogBackEndProgram.Data;

[Table("cattailblogs_articleauthor")]
[PrimaryKey(nameof(Id))]
public class ArticleAuthor
{
    public Guid? Id { get; set; }

    public string? UserId { get; set; }

    public string? ArticleId { get; set; }
}