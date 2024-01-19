using System.Reflection;
using Microsoft.Extensions.DependencyInjection;

namespace CattailBlogBackEndProgram.Service;

/// <summary>
/// 包含Service注入服务的扩展代码
/// </summary>
public static class ApiServiceExtensions
{
    // 通过反射获取类类型
    private static List<Type> GetAllClassTypeByAttribute<TAttribute>() where TAttribute : Attribute
    {
        var serviceTypes = Assembly.GetExecutingAssembly() // 包含当前执行的代码的程序集
            .GetTypes() // 获取类型
            .Where(t => t.IsClass && !t.IsAbstract &&
                        t.GetCustomAttributes(typeof(TAttribute), false).Any()) // 找到所有的ApiServiceAttribute标记的类
            .ToList();
        return serviceTypes;
    }

    // 判断lifetime注入类
    private static void InjectServiceByLifeTime(IServiceCollection service, ServiceLifetime? lifetime,
        Type serviceClass)
    {
        switch (lifetime)
        {
            case ServiceLifetime.Singleton:
                service.AddSingleton(serviceClass);
                break;
            case ServiceLifetime.Scoped:
                service.AddScoped(serviceClass);
                break;
            case ServiceLifetime.Transient:
                service.AddTransient(serviceClass);
                break;
        }
    }

    // 使用接口的判断lifetime获取注入类
    private static void InjectServiceByLifeTime(IServiceCollection service, ServiceLifetime? lifetime,
        Type[] serviceInterfaces, Type serviceClass)
    {
        foreach (var serviceInterface in serviceInterfaces)
        {
            switch (lifetime)
            {
                case ServiceLifetime.Singleton:
                    service.AddSingleton(serviceInterface, serviceClass);
                    break;
                case ServiceLifetime.Scoped:
                    service.AddScoped(serviceInterface, serviceClass);
                    break;
                case ServiceLifetime.Transient:
                    service.AddTransient(serviceInterface, serviceClass);
                    break;
            }
        }
    }

    /// <summary>
    /// 注入服务主函数
    /// </summary>
    private static void InjectService(IServiceCollection service, List<Type> serviceTypes)
    {
        serviceTypes.ForEach(impl =>
        {
            var interfaces = impl.GetInterfaces(); // 获取该类的所有接口
            var apiServiceAttribute = impl.GetCustomAttribute<ApiServiceAttribute>(); // 获取该类上的api service特性
            var lifetime = apiServiceAttribute?.DiLifetime; // 获取该类上 api service上的定义生命周期
            var isInjectClass = apiServiceAttribute?.IsInjectClass; // 获取该类上 api service上的是否注入类约定
            // 通过接口进行依赖注入
            InjectServiceByLifeTime(service, lifetime, interfaces, impl);
            // 注入该类本身
            if (isInjectClass is not null || isInjectClass == true)
            {
                InjectServiceByLifeTime(service, lifetime, impl);
            }
        });
    }

    /// <summary>
    /// 自动注入服务
    /// </summary>
    public static void AddServices(this IServiceCollection service)
    {
        // 获取带有service attribute的所有类
        var serviceTypes = GetAllClassTypeByAttribute<ApiServiceAttribute>();

        // 获取上述类的接口，并实现依赖注入
        InjectService(service, serviceTypes);
    }
}