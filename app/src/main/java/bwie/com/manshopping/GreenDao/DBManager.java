package bwie.com.manshopping.GreenDao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by 黑白 on 2017/9/14.
 */

public class DBManager {
    private final static String dbName = "test_db";
    private static DBManager mInstance;
    private DaoMaster.DevOpenHelper openHelper;
    private Context context;

    public DBManager(Context context) {
        this.context = context;
        openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
    }

    /**
     * 获取单例引用
     *
     * @param context
     * @return
     */
    public static DBManager getInstance(Context context) {
        if (mInstance == null) {
            synchronized (DBManager.class) {
                if (mInstance == null) {
                    mInstance = new DBManager(context);
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
    public void insertUser(Shooping shopping) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        ShoopingDao shoopingDao = daoSession.getShoopingDao();
        shoopingDao.insert(shopping);
    }

    /**
     * 插入用户集合
     *
     * @param users
     */
    public void insertUserList(List<Shooping> users) {
        if (users == null || users.isEmpty()) {
            return;
        }
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        ShoopingDao userDao = daoSession.getShoopingDao();
        userDao.insertInTx(users);
    }

    /**
     * 删除一条记录
     *
     * @param user
     */
    public void deleteUser(Shooping user) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        ShoopingDao userDao = daoSession.getShoopingDao();
        userDao.delete(user);
    }

    /**
     * 更新一条记录
     *
     * @param user
     */
    public void updateUser(Shooping user) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        ShoopingDao shoopingDao = daoSession.getShoopingDao();
        shoopingDao.update(user);
    }

    /**
     * 查询用户列表
     */
    public List<Shooping> queryUserList() {
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        ShoopingDao userDao = daoSession.getShoopingDao();
        QueryBuilder<Shooping> qb = userDao.queryBuilder();
        List<Shooping> list = qb.list();
        return list;
    }

    /**
     * 查询用户列表
     */
    public List<Shooping> queryUserList(Long age) {
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        ShoopingDao userDao = daoSession.getShoopingDao();
        QueryBuilder<Shooping> qb = userDao.queryBuilder();
        qb.where(ShoopingDao.Properties.Id.gt(age)).orderAsc(ShoopingDao.Properties.Id);
        List<Shooping> list = qb.list();
        return list;
    }

}
