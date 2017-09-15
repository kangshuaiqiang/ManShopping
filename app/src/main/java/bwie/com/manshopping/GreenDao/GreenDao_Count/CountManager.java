package bwie.com.manshopping.GreenDao.GreenDao_Count;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import bwie.com.manshopping.GreenDao.DaoMaster;
import bwie.com.manshopping.GreenDao.DaoSession;
import bwie.com.manshopping.GreenDao.Shooping;
import bwie.com.manshopping.GreenDao.ShoopingDao;

/**
 * Created by 黑白 on 2017/9/14.
 */

public class CountManager {

    private final static String dbName = "count.db";
    private static CountManager mInstance;
    private DaoMaster.DevOpenHelper openHelper;
    private Context context;

    public CountManager(Context context) {
        this.context = context;
        openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
    }

    /**
     * 获取单例引用
     *
     * @param context
     * @return
     */
    public static CountManager getInstance(Context context) {
        if (mInstance == null) {
            synchronized (CountManager.class) {
                if (mInstance == null) {
                    mInstance = new CountManager(context);
                }
            }
        }
        return mInstance;
    }

    /**
     * 获取可读数据库
     */
    private SQLiteDatabase getReadableDatabase() {
        if (openHelper == null) {
            openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
        }
        SQLiteDatabase db = openHelper.getReadableDatabase();
        return db;
    }


    /**
     * 获取可写数据库
     */
    private SQLiteDatabase getWritableDatabase() {
        if (openHelper == null) {
            openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
        }
        SQLiteDatabase db = openHelper.getWritableDatabase();
        return db;
    }

    /**
     * 插入一条记录
     *
     * @param
     */
    public void insertUser(Count_Price coun) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        Count_PriceDao count_priceDao = daoSession.getCount_PriceDao();
        count_priceDao.insert(coun);
    }

    /**
     * 插入用户集合
     *
     * @param users
     */
    public void insertUserList(List<Count_Price> users) {
        if (users == null || users.isEmpty()) {
            return;
        }
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        Count_PriceDao count_priceDao = daoSession.getCount_PriceDao();
        count_priceDao.insertInTx(users);
    }

    /**
     * 删除一条记录
     *
     * @param
     */
    public void deleteUser(Count_Price coun) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        Count_PriceDao count_priceDao = daoSession.getCount_PriceDao();
        count_priceDao.delete(coun);
    }

    /**
     * 更新一条记录
     *
     * @param coun
     */
    public void updateUser(Count_Price coun) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        Count_PriceDao count_priceDao = daoSession.getCount_PriceDao();
        count_priceDao.update(coun);
    }

    /**
     * 查询用户列表
     */
    public List<Count_Price> queryUserList() {
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        Count_PriceDao count_priceDao = daoSession.getCount_PriceDao();
        QueryBuilder<Count_Price> qb = count_priceDao.queryBuilder();
        List<Count_Price> list = qb.list();
        return list;
    }

    /**
     * 查询用户列表
     */
    public List<Count_Price> queryUserList(Long age) {
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        Count_PriceDao count_priceDao = daoSession.getCount_PriceDao();
        QueryBuilder<Count_Price> qb = count_priceDao.queryBuilder();
        qb.where(ShoopingDao.Properties.Id.gt(age)).orderAsc(ShoopingDao.Properties.Id);
        List<Count_Price> list = qb.list();
        return list;
    }

}
