package bwie.com.manshopping.MyUtils;

/**
 * Created by peng on 2017/9/1.
 */

public interface Api {
    //    http://169.254.85.75/mobile/index.php?act=goods_class
    public static boolean isOnline = false;
    public static final String PRODUCT = "http://www.baidu.com";
    public static final String DEVELOP = "http://169.254.85.75";
    public static final String HOST = isOnline ? PRODUCT : DEVELOP;


    public static final String MAIN_PAGE = HOST + "/mobile/index.php?act=index";//首页
    public static final String LINK_MOBILE_CLASS = HOST + "/mobile/index.php?act=goods_class";

}
