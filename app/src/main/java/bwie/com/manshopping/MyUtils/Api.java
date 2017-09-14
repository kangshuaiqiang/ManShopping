package bwie.com.manshopping.MyUtils;

/**
 * Created by peng on 2017/9/1.
 */

public interface Api {
    //    http://169.254.85.75/mobile/index.php?act=goods_class
//    http://169.254.85.75/mobile/index.php?act=goods_class&gc_id=1
    //http://169.254.85.75/mobile/index.php?act=goods_class&gc_id=1
    public static boolean isOnline = false;
    public static final String PRODUCT = "http://www.baidu.com";
    public static final String DEVELOP = "http://169.254.85.75";
    public static final String HOST = isOnline ? PRODUCT : DEVELOP;


    public static final String MAIN_PAGE = HOST + "/mobile/index.php?act=index";//首页
    public static final String LINK_MOBILE_CLASS = HOST + "/mobile/index.php?act=goods_class";
    public static final String TWO = HOST + "/mobile/index.php?act=goods_class&gc_id=";
    public static final String TWO_2 = HOST + "/mobile/index.php?act=goods_class&gc_id=";

    //    LINK_MOBILE_GOODS_SEARCH =http://169.254.85.75/mobile/index.php?act=goods&op=goods_list&page=100
//    public static final String SHOPPUNGLIST = HOST + "/mobile/index.php?act=goods&op=goods_list&page=100 ";
    //    http://169.254.85.75/mobile/index.php?act=goods&op=goods_list&page=100&gc_id=540
    public static final String SHOPPINGLIST = HOST + "/mobile/index.php?act=goods&op=goods_list&page=100&gc_id=";
    //    http://169.254.85.75/mobile/index.php?act=goods&op=goods_detail&goods_id=100009
    public static final String DETAIL = HOST + "/mobile/index.php?act=goods&op=goods_detail&goods_id=";
    //    http://169.254.85.75/mobile/index.php?act=goods&op=goods_detail&goods_id=100009
//http://169.254.85.75/mobile/index.php?act=login&op=register
    public static final String REGISTER = HOST + "/mobile/index.php?act=login&op=register";
    //    http://169.254.85.75/mobile/index.php?act=login
    public static final String LOGIN = HOST + "/mobile/index.php?act=login";
}
