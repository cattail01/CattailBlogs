namespace CattailBlogBackEndProgram.Utility;

public class ResultCodeEnumDescriptionSingleton : SingletonBase<ResultCodeEnumDescriptionSingleton>
{
    private Dictionary<ResultCode, string> _resultCodeDescriptionDictionary = EnumHelper.GetDescriptions<ResultCode>();

    public Dictionary<ResultCode, string> ResultCodeDescriptionDictionary
    {
        get => _resultCodeDescriptionDictionary;
        private set => _resultCodeDescriptionDictionary = value;
    }
}