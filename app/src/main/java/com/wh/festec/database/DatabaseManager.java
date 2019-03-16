package com.wh.festec.database;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

/**
 * Created by 41926 on 2019/3/13 20:58.
 */
public class DatabaseManager {

    private DaoSession mDaoSession = null;
    private UserProFileDao mDao = null;

    private DatabaseManager() {
    }

    public DatabaseManager init(Context context) {
        initDao(context);
        return this;
    }

    private static final class Holder {
        private static final DatabaseManager INSTANCE = new DatabaseManager();
    }

    public static DatabaseManager getInstance() {
        return Holder.INSTANCE;
    }

    private void initDao(Context context) {
        final ReleaseOpenHelper helper = new ReleaseOpenHelper(context, "fast_ec.db");
        final Database db = helper.getWritableDb();
        mDaoSession = new DaoMaster(db).newSession();
        mDao = mDaoSession.getUserProFileDao();
    }

    public final UserProFileDao getDao() {
        return mDao;
    }
}
