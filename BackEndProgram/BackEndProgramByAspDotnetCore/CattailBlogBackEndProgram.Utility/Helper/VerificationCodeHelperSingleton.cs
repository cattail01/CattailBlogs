using System.Text;
using SixLabors.Fonts;
using SixLabors.ImageSharp;
using SixLabors.ImageSharp.Drawing.Processing;
using SixLabors.ImageSharp.Formats.Jpeg;
using SixLabors.ImageSharp.PixelFormats;
using SixLabors.ImageSharp.Processing;

namespace CattailBlogBackEndProgram.Utility;

/// <summary>
/// 验证码帮助类
/// </summary>
public class VerificationCodeHelperSingleton : SingletonBase<VerificationCodeHelperSingleton>
{
    private readonly string _chars = "abcdefghigklmnopqrstuvwxyz1234567890"; // 验证码展示数据
    private readonly StringBuilder _stringBuilder = new StringBuilder();
    private const int _frontSize = 40; // 字体大小
    private Font _font = SystemFonts.CreateFont("Arial", _frontSize, FontStyle.Bold | FontStyle.Italic);

    private Color[] _colors = new[] // colors
    {
        Color.Aqua, Color.Aquamarine, Color.Black, Color.Orange, Color.Pink, Color.Blue, Color.Green,
        Color.Yellow, Color.Peru, Color.Purple, Color.Plum, Color.Lime, Color.Magenta, Color.Maroon,
        Color.MediumBlue, Color.MidnightBlue, Color.Navy
    };

    private readonly Random _random = new Random();

    /// <summary>
    /// 创建验证码字符串
    /// </summary>
    /// <param name="length"></param>
    /// <returns></returns>
    public string CreateVerificationCodeString(int length = 4)
    {
        Random random = new Random(DateTime.Now.Millisecond); // 随机数生成器  // 使用时间
        for (int i = 0; i < length; i++)
        {
            char chr = _chars[random.Next(_chars.Length)];
            if (char.IsLetter(chr) && (_random.Next() & 1) == 0)
            {
                chr = char.ToUpper(chr);
            }

            _stringBuilder.Append(chr);
        }

        string resultStr = _stringBuilder.ToString();
        _stringBuilder.Clear();
        return resultStr;
    }

    /// <summary>
    /// 创建验证码图片
    /// </summary>
    /// <param name="verificationCode"></param>
    /// <returns></returns>
    public async Task<byte[]> CreateVerificationCodeTextureAsync(string verificationCode)
    {
        int width = 200;
        int height = 60;
        using Image<Rgba32> image = new Image<Rgba32>(width, height); // 创建画布
        image.Mutate(x => x.DrawLine(Pens.DashDot(Color.White, width),
            new PointF[] { new PointF() { X = 0, Y = 0 }, new PointF() { X = width, Y = height } })); // 绘制底色

        PointF startPointF = new PointF(5, _random.Next(5, height - _frontSize)); // 落笔位置
        int verificationCodeLength = verificationCode.Length; // 验证码字符串长度
        int colorNun = _colors.Length; // 随机颜色数量

        for (int i = 0; i < verificationCodeLength; ++i) // 绘制字符串
        {
            string chr = verificationCode[i].ToString();
            Color color = _colors[_random.Next(colorNun)];
            image.Mutate(x => x.DrawText(chr, _font, color, startPointF));
            startPointF.X += (int)((width - 10) / verificationCodeLength);
            startPointF.Y = _random.Next(5, height - _frontSize);
        }

        var pen = Pens.Dash(Color.FromRgba((byte)192, (byte)192, (byte)192, (byte)(byte.MaxValue / 2)), 2);

        for (var k = 0; k < 40; k++) // 绘制干扰线
        {
            PointF[] points = new PointF[2];
            points[0] = new PointF(_random.Next(width), _random.Next(height));
            points[1] = new PointF(_random.Next(width), _random.Next(height));
            image.Mutate(x => x.DrawLine(pen, points));
        }

        // await image.SaveAsJpegAsync(@"C:\Users\33475\Desktop\test\test.jpeg");  // 保存到本地预览  // todo 删除

        using MemoryStream stream = new MemoryStream();
        await image.SaveAsync(stream, JpegFormat.Instance);
        return stream.ToArray();
    }
    // todo 还有部分细节需要优化，甚至可能改变验证码的呈现方式，采用更加先进的验证方式代替传统验证码
}
