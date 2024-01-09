using System.ComponentModel;
using System.Reflection;

namespace CattailBlogBackEndProgram.Utility;

/// <summary>
/// enum 帮助类
/// </summary>
public class EnumHelper
{
    /// <summary>
    /// 获取enum上的description attribute
    /// </summary>
    /// <typeparam name="T">enum</typeparam>
    /// <returns>类型与description映射</returns>
    public static Dictionary<T, String> GetDescriptions<T>() where T : Enum
    {
        var result = new Dictionary<T, String>();

        foreach (var value in Enum.GetValues(typeof(T)).Cast<T>())
        {
            FieldInfo? fieldInfo = typeof(T).GetField(value.ToString());
            DescriptionAttribute[]? attributes =
                fieldInfo?.GetCustomAttributes(typeof(DescriptionAttribute), false) as DescriptionAttribute[];
            result[value] = attributes?.Length > 0? attributes[0].Description : value.ToString();
        }
        return result; 
    }
}
