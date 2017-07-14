package example.holders;


/**
 * Created by xlizy on 15/11/4.
 *
 * 用于动态数据源
 */
//@Slf4j
public class DBContextHolder {

    private static ThreadLocal<String> contextHolder = new ThreadLocal<>();

    public static final String MASTER = "masterDataSource";
    public static final String SLAVE = "slaveDataSource";

    public static String getDbType() {
        String db = contextHolder.get();
        if (db == null) {
            db = MASTER;// 默认是主库
        }
        return db;
    }

    public static void setDbType(String str) {
        //设置多次的话 主库优先
        String db = contextHolder.get();
        if (MASTER.equals(db)) {
            return;
        }
//        log.info("切换到{}数据源", str);
        contextHolder.set(str);
    }

    public static void clearDBType() {
        contextHolder.remove();
    }

}
