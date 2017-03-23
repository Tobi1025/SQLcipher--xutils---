# SQLcipher--xutils---
加密本地数据库
## 1.将SQLcipher 集成到xutils库中
①.将sqlcipher.jar复制到工程文件夹libs中；<br>
②.在工程main下，新建两个文件夹jniLibs和assets，将amreabi文件夹整个复制到jniLibs中，将icudt46l.zip复制到assets中；<br>
③.修改xutils 库下的DBUtils 类<br>
```Java
public static DbUtils create(Context context,String password) {
        SQLiteDatabase.loadLibs(context);//SQLiteDataba是net.sqlcipher.database包下的
        DaoConfig config = new DaoConfig(context);
        config.setPassword(password);//通过传入的password设置数据库访问密码
        return getInstance(config);
    }
```
## 2.项目中的用法和之前一样，只不过现在需要设置一个数据库加密的密码PASSWORD。
```Java
//SQLcipher 数据库访问密码
private static final String PASSWWORD = "123456";
public static void saveNote(String noteId, Context context) {
//两个参数的creat(Context context,String password)，默认创建xUtils.db
//三个参数的create(Context context, String password,String dbName) ,则可以指定数据库名称      
    DbUtils db = DbUtils.create(context, PASSWWORD);
        Note note = new Note();
        note.setNoteId(noteId);
        try {
            db.save(note);
        } catch (Exception e) {
            e.printStackTrace();
        }
}
```
## 3.查询数据库
```Java
public static boolean isNoteReaded(String notesId, Context context) {
          DbUtils db = null;        
          Note note = null;
//若查询密码与之前设置的不匹配，则net.sqlcipher.database.SQLiteException: file is encrypted or is not a database。
          try {
            db = DbUtils.create(context, PASSWWORD);
          } catch (Exception e) {
            e.printStackTrace();
          }

          try {
            note = db.findFirst(Selector.from(Note.class).where(WhereBuilder.b("noteId", "=",notesId)));
              } catch (Exception e) {
            e.printStackTrace();
          }
          if (note == null) {
            return false;
          }
          return true;
      }
```
## 4.SQLCipher优点
是一个在SQLite基础之上进行扩展的开源数据库，并且支持很多不同的平台，它主要是在SQLite的基础之上增加了数据加密功能，如果我们在项目中使用它来存储数据的话，就可以大大提高程序的安全性。

