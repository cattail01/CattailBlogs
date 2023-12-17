using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace CattailBlogBackEndProgram.Data.Test;

[TestClass]
public class EFCoreTest
{
    private Random _random = new Random();
    
    private BlogArticle CreateTestBlogArticle()
    {
        var blogArticle = new BlogArticle();
        blogArticle.Id = Guid.NewGuid();
        blogArticle.GmtCreateTime = DateTime.Now;
        blogArticle.GmtUpdateTime = DateTime.Now;
        blogArticle.Title = "TestTitle" + _random.NextInt64(100);
        blogArticle.Description = "TestDescription" + _random.NextInt64(100);
        blogArticle.Content = "TestContent" + _random.NextInt64(100);
        return blogArticle;
    }
    
    [TestMethod]
    public async Task TestAddData()
    {
        using (BlogDbContext blogDbContext  = new BlogDbContext())
        {
            var blogArticle = CreateTestBlogArticle();
            blogDbContext.BlogArticles.Add(blogArticle);
            await blogDbContext.SaveChangesAsync();
        }
    }
    
    [TestMethod]
    public async Task TestAddDataRandom()
    {
        using (BlogDbContext blogDbContext  = new BlogDbContext())
        {
            for(int i = 0; i < _random.NextInt64(10) + 1; i ++)
            {
                var blogArticle = CreateTestBlogArticle();
                blogDbContext.BlogArticles.Add(blogArticle);
                await blogDbContext.SaveChangesAsync();
            }
        }
    }

    [TestMethod]
    public void TestFindData()
    {
        using (BlogDbContext blogDbContext = new BlogDbContext())
        {
            var target = blogDbContext.BlogArticles.Where(each => each.Title == "TestTitle28");
            foreach (var item in target)
            {
                Console.WriteLine(item.Id + " " + item.Title);
            }
        }
    }

    [TestMethod]
    public async Task TestUpdateData()
    {
        using (var context = new BlogDbContext())
        {
            var blogArticles = context.BlogArticles.Where(each => each.Title == "TestTitle28");
            foreach (var article in blogArticles)
            {
                Console.WriteLine(article.Id + " " + article.Title + " " + article.GmtUpdateTime);
                article.GmtUpdateTime = DateTime.Now;
            }
            await context.SaveChangesAsync();
            
            Console.WriteLine();
            
            blogArticles = context.BlogArticles.Where(each => each.Title == "TestTitle28");
            foreach (var blogArticle in blogArticles)
            {
                Console.WriteLine(blogArticle.Id + " " + blogArticle.Title + " " + blogArticle.GmtUpdateTime);
            }
        }
    }

    [TestMethod]
    public void TestDeleteData()
    {
        using (var context = new BlogDbContext())
        {
            var blogArticles = context.BlogArticles.Where(each => each.Title == "TestTitle28");
            foreach (var blogArticle in blogArticles)
            {
                Console.WriteLine(blogArticle.Id + " " + blogArticle.Title + " " + blogArticle.GmtUpdateTime);
                context.BlogArticles.Remove(blogArticle);
            }
            context.SaveChanges();
        }
    }
    
    [TestMethod]
    public void TestEFCore()
    {
        
    }
}