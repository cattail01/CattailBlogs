using Microsoft.Extensions.Caching.Memory;

namespace CattailBlogBackEndProgram.Utility;

/// <summary>
/// 缓存帮助类
/// </summary>
public class MemoryHelperSingleton: SingletonBase<MemoryHelperSingleton>
{
    /// <summary>
    /// 缓存
    /// </summary>
    public IMemoryCache? MemoryCache { get; private set; }

    public MemoryHelperSingleton()
    {
        if (MemoryCache is null)
        {
            MemoryCache = new MemoryCache(new MemoryCacheOptions());
        }
    }

    /// <summary>
    /// 向缓存中放置内容
    /// </summary>
    /// <param name="key"></param>
    /// <param name="value"></param>
    /// <param name="expireMins"></param>
    /// <typeparam name="T"></typeparam>
    public void SetMemory<T>(string key, T value, int expireMins)
    {
        MemoryCache?.Set(key, value, TimeSpan.FromMinutes(expireMins));
    }

    /// <summary>
    /// 获取缓存中的值
    /// </summary>
    /// <param name="key"></param>
    /// <typeparam name="TResult"></typeparam>
    /// <returns></returns>
    public TResult? GetMemory<TResult>(string key)
    {
        return MemoryCache.Get<TResult>(key);
    }
}
