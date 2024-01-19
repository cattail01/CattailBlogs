using CattailBlogBackEndProgram.Data.EFCore;
using CattailBlogBackEndProgram.Service;
using CattailBlogBackEndProgram.Web;
using NLog.Web;

var builder = WebApplication.CreateBuilder(args);
builder.Logging.AddNLog("./Config/nlog.config");  // 添加nlog日志，指定日志配置文件位置
builder.Services.AddControllers();
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();
builder.Services.AddAutoMapper(typeof(AutoMapperConfigs));  // 添加自动映射服务 auto mapper
builder.Services.AddDbContext<BlogDbContext>();  // 添加efcore 数据库上下文
builder.Services.AddServices();  // 自动添加服务

var app = builder.Build();
app.UseMiddleware<GlobalExceptionHandlerMiddleware>();  // 配置全局异常处理的中间件
if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}
app.UseHttpsRedirection();
app.UseAuthorization();
app.MapControllers();

app.Run();
