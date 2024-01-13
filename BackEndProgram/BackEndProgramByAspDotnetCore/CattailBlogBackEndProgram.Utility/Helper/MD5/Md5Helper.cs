using System.Security.Cryptography;
using System.Text;

namespace CattailBlogBackEndProgram.Utility;

/// <summary>
/// md5帮助类
/// </summary>
public class Md5Helper: SingletonBase<Md5Helper>
{
    private MD5 _md5 = MD5.Create();  // 加密器
    
    /// <summary>
    /// 将字符串转换为加密md5字符串
    /// </summary>
    /// <param name="str"></param>
    /// <returns>加密md5字符串</returns>
    public string StringEncodingToMd516Str(string str)
    {
        var bytResult = _md5.ComputeHash(Encoding.Default.GetBytes(str));  // 获取加密文字字节数组
        string strResult = BitConverter.ToString(bytResult);  // 转换为字符串
        strResult = strResult.Replace("-", "");  // 去掉"-"
        return strResult;  // 返回加密字符串
    }
}