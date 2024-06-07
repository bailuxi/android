package more;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "UserInfo.db";
    private static final String TABLE_NAME = "user";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "NAME";
    private static final String COL_3 = "AGE";
    private static final String COL_4 = "GENDER";
    private static final String COL_5 = "STATUS";
    private static final String COL_6 = "NUMBER";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1); // 传入数据库名称和版本号
    }

    // 创建数据库表
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, AGE TEXT, GENDER TEXT, STATUS TEXT, NUMBER TEXT)");
    }

    // 升级数据库表
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 删除旧版本的表
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        // 创建新版本的表
        onCreate(db);
    }

    // 插入数据
    public boolean insertData(String name, String age, String gender, String status, String number) {
        SQLiteDatabase db = this.getWritableDatabase(); // 获取可写数据库对象
        ContentValues contentValues = new ContentValues(); // 创建内容

        contentValues.put(COL_2, name);
        contentValues.put(COL_3, age);
        contentValues.put(COL_4, gender);
        contentValues.put(COL_5, status);
        contentValues.put(COL_6, number);

        long result = db.insert(TABLE_NAME, null, contentValues);
        // 返回插入结果
        return result != -1;//成功返回行号，失败返回 -1
    }

    // 查询表
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        // 查询并返回所有数据的游标对象
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    // 根据ID删除
    public void deleteDataById(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_NAME, COL_1 + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    // 删除表中所有数据
    public void deleteAllData() {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_NAME, null, null);
        db.close();
    }

    // 重置ID
    public void resetIdCounter() {
        SQLiteDatabase db = this.getWritableDatabase();
        // 触发upgrade，重置ID
        onUpgrade(db, 1, 2); // 当前1，升级2

    }
}
