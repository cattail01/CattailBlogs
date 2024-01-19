using Microsoft.Extensions.DependencyInjection;

namespace CattailBlogBackEndProgram.Service;

/// <summary>
/// 自动注册service
/// </summary>
[AttributeUsage(AttributeTargets.Class)]
public class ApiServiceAttribute : Attribute
{
    /// <summary>
    /// 定义service的生命周期
    /// </summary>
    public ServiceLifetime DiLifetime { get; private set; } = ServiceLifetime.Scoped;

    /// <summary>
    /// 是否将类本身进行注入
    /// </summary>
    public bool IsInjectClass { get; set; } = false;

    /// <summary>
    /// 构造函数
    /// </summary>
    public ApiServiceAttribute(ServiceLifetime lifetime = ServiceLifetime.Scoped, bool isInjectClass = false)
    {
        DiLifetime = lifetime;
        IsInjectClass  = isInjectClass;
    }
}