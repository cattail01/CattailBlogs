import sys

def readConfFile():
    '''
    读取config信息
    :return:
    '''

    pass

def optionsAfterReadConf():
    '''

    :return:
    '''

    pass

def cmdOnLinux():
    '''
    linux系统执行命令
    :return:
    '''

    pass


def cmdOnWindows():
    '''
    windows系统执行命令
    :return:
    '''

    pass

#
def start():
    '''
    执行主函数
    :return:
    '''

    # win 系统
    if sys.platform.startswith('win'):
        print("windows system")
        pass

    #Linux系统
    elif sys.platform.startswith('linux'):
        print("linux system")
        pass

    # 无法识别
    else:
        raise Exception("无法识别的操作系统");

    pass

if __name__ == "__main__":
    pass

