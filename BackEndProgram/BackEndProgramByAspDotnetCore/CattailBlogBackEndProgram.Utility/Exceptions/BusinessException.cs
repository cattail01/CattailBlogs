namespace CattailBlogBackEndProgram.Utility.Exceptions;

/// <summary>
/// 业务异常类
/// </summary>
/// <remarks>
/// <para>携带一个返回值ResultSingleton类型成员</para>
/// <para>todo 后期会根据异常类型增加增加继承自该异常类的类</para>
/// </remarks>
public class BusinessException : Exception
{
    public ResultSingleton ResultSingleton { get; private set; }

    public BusinessException(ResultSingleton resultSingleton)
    {
        ResultSingleton = resultSingleton;
    }
}
