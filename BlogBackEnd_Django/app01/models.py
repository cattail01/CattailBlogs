"""
网站表结构
"""

from django.db import models
from django.contrib.auth.models import AbstractUser
from django.db.models.signals import pre_delete
from django.dispatch import receiver
from django.utils.html import format_html


class Site(models.Model):
    """
    网站信息表
    """
    nid = models.AutoField(primary_key=True)
    title = models.CharField(max_length=32, verbose_name="网站标题")
    abstract = models.CharField(max_length=128, verbose_name="网站简介")
    keywords = models.CharField(max_length=128, verbose_name="网站关键字")
    record = models.CharField(max_length=32, verbose_name="网站备案号")
    create_data = models.DateTimeField(verbose_name="建站日期")
    version = models.CharField(max_length=16, verbose_name="网站版本号")

    # icon = models.FileField()

    def __str__(self):
        return self.title

    class Meta:
        verbose_name_plural = "网站信息"


class MyInfo(models.Model):
    """
    作者信息表
    """
    nid = models.AutoField(primary_key=True)
    name = models.CharField(max_length=32, verbose_name="名字")
    job = models.CharField(max_length=128, verbose_name="工作")
    email = models.EmailField(max_length=128, verbose_name="邮箱")
    site_url = models.CharField(max_length=128, verbose_name="网站链接")
    addr = models.CharField(max_length=64, verbose_name="地址", )
    bilibili_url = models.URLField(verbose_name="哔哩哔哩链接", null=True, blank=True)
    github_url = models.URLField(verbose_name="GitHub链接", null=True, blank=True, )

    class Meta:
        verbose_name_plural = "个人信息"


class Avatars(models.Model):
    """
    用户头像表
    """
    nid = models.AutoField(primary_key=True)
    url = models.FileField(verbose_name="用户头像地址", upload_to="avatars/")

    def __str__(self):
        return str(self.url)

    class Meta:
        verbose_name_plural = "用户头像"


@receiver(pre_delete, sender=Avatars)
def download_delete(instance, **_kwargs):
    instance.url.delete(False)


class UserInfo(AbstractUser):
    """
    用户表
    """
    nid = models.AutoField(primary_key=True)
    sign_choice = (
        (0, "用户名注册"),
        (1, "手机号注册"),
        (2, "邮箱注册"),
        (3, "QQ注册")
    )
    nick_name = models.CharField(max_length=32, verbose_name="昵称")
    sign_status = models.IntegerField(default=0, choices=sign_choice, verbose_name="注册方式")
    tel = models.CharField(verbose_name="手机号", max_length=12, null=True, blank=True)
    integral = models.IntegerField(default=20, verbose_name="用户积分")
    token = models.CharField(verbose_name="TOKEN", max_length=64, null=True, blank=True)
    avatar = models.ForeignKey(
        to="Avatars",
        to_field="nid",
        on_delete=models.SET_NULL,
        verbose_name="用户头像",
        null=True,
        related_name="UserAvatars"
    )
    collects = models.ManyToManyField(
        to="Articles",
        verbose_name="收藏的文章"
    )

    class Meta:
        verbose_name_plural = "用户"


class Cover(models.Model):
    """
    文章封面
    """
    nid = models.AutoField(primary_key=True)
    url = models.FileField(verbose_name="文章封面地址", upload_to="article_img/")

    def __str__(self):
        return str(self.url)

    class Meta:
        verbose_name_plural = "文章封面"


@receiver(pre_delete, sender=Cover)
def cover_delete(instance, **kwargs):
    instance.url.delete(False)


class ArticleTags(models.Model):
    """
    文章标签
    """
    nid = models.AutoField(primary_key=True)
    title = models.CharField(verbose_name="文章标签", max_length=128)

    def __str__(self):
        return self.title

    class Meta:
        verbose_name_plural = "文章标签"


class Articles(models.Model):
    """
    文章表
    """
    nid = models.AutoField(primary_key=True)
    title = models.CharField(verbose_name="标题", max_length=128, null=False, blank=True)
    # autor = models.CharField(max_length=32, verbose_name="作者")
    autor = models.ForeignKey(
        to="UserInfo",
        to_field="nid",
        verbose_name="文章作者",
        on_delete=models.SET_NULL,
        null=True
    )
    source = models.CharField(max_length=64, verbose_name="来源", null=True, blank=True)
    source_link = models.URLField(verbose_name="来源链接", null=True, blank=True)
    abstract = models.CharField(verbose_name="文章简介", max_length=128, null=True, blank=True)
    content = models.TextField(verbose_name="文章内容", null=True, blank=True)
    create_data = models.DateTimeField(verbose_name="文章发布日期", auto_now_add=True, blank=True)
    update_data = models.DateTimeField(verbose_name="文章更改日期", auto_now=True, blank=True)
    status_choice = (
        (0, "未发布"),
        (1, "已发布")
    )
    status = models.IntegerField(verbose_name="文章的保存状态", choices=status_choice)
    is_recommend = models.BooleanField(verbose_name="是否上推荐", default=False)
    recover = models.ForeignKey(
        to="Cover",
        to_field="nid",
        on_delete=models.SET_NULL,
        verbose_name="文章封面",
        null=True,
        blank=True
    )
    look_count = models.IntegerField(verbose_name="文章阅读量", default=0)
    comment_count = models.IntegerField(verbose_name="文章评论量", default=0)
    digg_count = models.IntegerField(verbose_name="文章点赞量", default=0)
    collects_count = models.IntegerField(verbose_name="文章收藏量", default=0)
    category_choice = (
        (0, "前端"),
        (1, "后端")
    )
    category = models.IntegerField(verbose_name="文章分类", choices=category_choice, null=True, blank=True)
    tag = models.ManyToManyField(
        to="ArticleTags",
        verbose_name="文章标签",
        blank=True
    )

    def __str__(self):
        return self.title

    class Meta:
        verbose_name_plural = "文章"


class Comment(models.Model):
    """
    评论表
    """
    nid = models.AutoField(primary_key=True)
    digg_count = models.IntegerField(verbose_name="点赞", default=0)
    article = models.ForeignKey(verbose_name="评论文章", to="Articles", to_field="nid", on_delete=models.CASCADE)
    user = models.ForeignKey(verbose_name="评论者", to="UserInfo", to_field="nid", on_delete=models.CASCADE)
    content = models.TextField(verbose_name="评论内容", )
    comment_count = models.IntegerField(verbose_name="子评论数", default=0)
    drawing = models.TextField(verbose_name="配图", null=True, blank=True)
    create_time = models.DateTimeField(verbose_name="创建时间", auto_now_add=True)
    parent_comment = models.ForeignKey("self", null=True, blank=True, on_delete=models.CASCADE, verbose_name="父评论")

    def __str__(self) -> str:
        return self.content

    class Meta:
        verbose_name_plural = "评论"


class News(models.Model):
    """
    爬取新闻
    """
    nid = models.AutoField(primary_key=True)
    create_date = models.DateTimeField(auto_now_add=True, verbose_name="获取时间")

    class Meta:
        verbose_name_plural = "新闻爬取"


class Memoir(models.Model):
    """
    回忆录
    """
    nid = models.AutoField(primary_key=True)
    title = models.CharField(max_length=64, verbose_name="事件名称")
    content = models.TextField(verbose_name="事件内容")
    create_time = models.DateTimeField(verbose_name="创建时间", auto_now_add=True, null=True, blank=True)
    update_time = models.DateTimeField(verbose_name="更新时间", auto_now=True, null=True, blank=True)
    drawing = models.TextField(verbose_name="配图组，以;隔开", null=True, blank=True)

    class Meta:
        verbose_name_plural = "回忆录"


class Moods(models.Model):
    """
    心情
    """
    nid = models.AutoField(primary_key=True)
    # author = models.ForeignKey(verbose_name="发布人", to="UserInfo", to_field="nid", on_delete=models.SET_NULL)
    author = models.CharField(verbose_name="发布人", max_length=128)
    avatar = models.ForeignKey(
        to="Avatars",
        to_field="nid",
        on_delete=models.SET_NULL,
        null=True,
        verbose_name="心情的发布头像"
    )
    create_time = models.DateTimeField(verbose_name="发布时间", auto_now_add=True, )
    content = models.TextField(verbose_name="心情内容")
    drawing = models.TextField(verbose_name="配图组，以;隔开", null=True, blank=True)
    comment_count = models.IntegerField(verbose_name="评论数", default=0)
    digg_count = models.IntegerField(verbose_name="点赞数", default=0)

    def __str__(self):
        return self.author

    class Meta:
        verbose_name_plural = "心情"


class MoodComment(models.Model):
    """
    心情评论
    """
    nid = models.AutoField(primary_key=True)
    avatar = models.ForeignKey(
        to="Avatars",
        to_field="nid",
        on_delete=models.SET_NULL,
        null=True,
        verbose_name="心情的发布头像",
    )
    name = models.CharField(verbose_name="评论人", max_length=16, null=True)
    content = models.TextField(verbose_name="评论内容")
    digg_count = models.IntegerField(verbose_name="点赞数", default=0)
    mood = models.ForeignKey(
        to="Moods",
        to_field="nid",
        on_delete=models.SET_NULL,
        verbose_name="评论的心情",
        null=True,
    )
    create_time = models.DateTimeField(verbose_name="评论时间", auto_now=True)

    def __str__(self):
        return self.content

    class Meta:
        verbose_name_plural = "心情评论"


class NavCategory(models.Model):
    """
    导航分类
    """
    nid = models.AutoField(primary_key=True)
    title = models.CharField(verbose_name="分类标题", max_length=128)
    icon = models.CharField(verbose_name="分类图标", max_length=64)

    def __str__(self):
        return self.title

    class Meta:
        verbose_name_plural = "导航分类"


class Navs(models.Model):
    """
    网站导航
    """
    nid = models.AutoField(primary_key=True)
    nav_category = models.ForeignKey(
        to="NavCategory",
        to_field="nid",
        on_delete=models.SET_NULL,
        verbose_name="网站导航的分类",
        null=True,
    )
    icon_href = models.URLField(verbose_name="图片链接", help_text="在线链接", null=True, blank=True)
    icon = models.FileField(verbose_name="网站图标", null=True, blank=True, upload_to="site_icon/",
                            help_text="文件优先级大于链接")
    title = models.CharField(max_length=128, verbose_name="网站标题")
    abstract = models.CharField(max_length=128, verbose_name="网站简介", null=True)
    create_time = models.DateTimeField(auto_now=True, verbose_name="创建时间")
    href = models.URLField(verbose_name="网站链接")
    status_chioce = (
        (0, "待审核"),
        (1, "已通过"),
        (2, "被驳回")
    )
    status = models.IntegerField(verbose_name="网站状态", choices=status_chioce, default=0)

    def color_state(self):
        assign_state_name: str = ""
        color_code: str = ""
        if self.status == 0:
            assign_state_name = "待审核"
            color_code = "#ec921e"
        elif self.status == 1:
            assign_state_name = "已通过"
            color_code = "green"
        elif self.status == 2:
            assign_state_name = "被驳回"
            color_code = "red"

        return format_html(
            "<span style='color:{};'>{}</span>",
            color_code,
            assign_state_name
        )

    color_state.short_description = "导航状态"

    def __str__(self):
        return self.title

    class Meta:
        verbose_name_plural = "导航分类"


class MenuImg(models.Model):
    """
    站点背景图
    """
    nid = models.AutoField(primary_key=True)
    url = models.FileField(verbose_name="图片地址", upload_to="site_bg/")

    def __str__(self):
        return str(self.url)

    class Meta:
        verbose_name_plural = "站点背景图"


class Menu(models.Model):
    """
    站点背景
    """
    nid = models.AutoField(primary_key=True)
    menu_title = models.CharField(verbose_name="菜单名称", max_length=32, null=True)
    menu_title_en = models.CharField(verbose_name="菜单英文名称", max_length=64, null=True)
    title = models.CharField(verbose_name="slogan", max_length=64, null=True)
    abstract = models.TextField(verbose_name="slogan介绍", help_text=";区分", null=True)
    abstract_time = models.IntegerField(verbose_name="slogan切换时间", help_text="单位秒，默认8秒", default=8)
    is_rotation = models.BooleanField(verbose_name="是否轮播slogan介绍", default=True)
    menu_url = models.ManyToManyField(verbose_name="菜单轮播图片", help_text="可以多选，多选轮播", to="MenuImg")
    is_menu_rotation = models.BooleanField(verbose_name="是否轮播banner图", help_text="多选会轮播", default=False)
    menu_rotation_time = models.IntegerField(verbose_name="背景图切换时间", help_text="单位秒，默认8秒", default=8)

    class Meta:
        verbose_name_plural = "站点背景"


class Advert(models.Model):
    """
    广告表
    """
    nid = models.AutoField(primary_key=True)
    title = models.CharField(verbose_name="产品名称", max_length=64, null=True)
    href = models.URLField(verbose_name="跳转链接")
    img = models.FileField(verbose_name="图片地址", null=True, blank=True, help_text="单图", upload_to="advert/")
    img_list = models.TextField(verbose_name="图片组", null=True, blank=True,
                                help_text="上传图片请用线上地址；使用;隔开更多图片")
    is_show = models.BooleanField(verbose_name="是否展示", default=False)
    author = models.CharField(verbose_name="广告主", max_length=64, null=True, blank=True)
    abstract = models.CharField(verbose_name="产品简介", max_length=128, null=True, blank=True)

    class Meta:
        verbose_name_plural = "广告"


class Feedback(models.Model):
    """
    反馈意见
    """
    nid = models.AutoField(primary_key=True)
    email = models.EmailField(verbose_name="邮箱")
    content = models.TextField(verbose_name="反馈信息")
    state_choice = (
        (0, "待处理"),
        (1, "已处理")
    )
    state = models.IntegerField(verbose_name="处理状态", choices=state_choice, default=0)
    processing_content = models.TextField(verbose_name="回复的内容", null=True, blank=True)

    def __str__(self):
        return self.content

    class Meta:
        verbose_name_plural = "用户反馈"
