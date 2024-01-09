using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace CattailBlogBackEndProgram.Utility.Test;

/// <summary>
/// 验证码测试
/// </summary>
[TestClass]
public class VerificationCodeTest
{
    /// <summary>
    /// 测试验证码输出图片
    /// </summary>
    [TestMethod]
    public async Task TestVerificationCodeImageOutput()
    {
        await VerificationCodeHelperSingleton.Instance.CreateVerificationCodeTextureAsync(VerificationCodeHelperSingleton
            .Instance.CreateVerificationCodeString());
    }
}