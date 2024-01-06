using SqlSugar;

namespace CattailBlogBackEndProgram.Data.SqlSugar;

public class DbContext
{
    public static SqlSugarClient Db = new SqlSugarClient(new ConnectionConfig()
    {
        ConnectionString = "server=127.0.0.1;port=3306;user=root;password=123456;database=cattailblogbackenddb",
        DbType = DbType.MySql,
        IsAutoCloseConnection = true
    });
}