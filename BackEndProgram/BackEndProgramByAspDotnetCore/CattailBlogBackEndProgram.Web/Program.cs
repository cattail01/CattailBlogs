using CattailBlogBackEndProgram.Service;
using CattailBlogBackEndProgram.Web;

var builder = WebApplication.CreateBuilder(args);
builder.Logging.AddLog4Net(
    "Config/log4net.config"); // todo: log 4 net cant write message to file, use nlog instead later.

builder.Services.AddControllers();
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();
builder.Services.AddTransient<IUserService, UserServiceImpl>();

var app = builder.Build();
app.UseMiddleware<GlobalExceptionHandlerMiddleware>();
if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

app.UseHttpsRedirection();
app.UseAuthorization();
app.MapControllers();

app.Run();