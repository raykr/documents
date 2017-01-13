package com.swroom.utils;

/**
 * SQL语句处理工具类
 * Created by jingz on 2017/1/13.
 */
public class SqlUtil {

    private String sqlTemplate; // SQL带占位符的模板
    private String[] args;  // 参数列表，顺序需一致

    public SqlUtil() {
    }

    public SqlUtil(String sqlTemplate, String[] args) {
        this.sqlTemplate = sqlTemplate;
        this.args = args;
    }

    public void setSqlTemplate(String sqlTemplate) {
        this.sqlTemplate = sqlTemplate;
    }

    public void setArgs(String[] args) {
        this.args = args;
    }

    /**
     * 获取sql语句
     * @return sqlstr
     * @throws Exception
     */
    public String getSql() throws Exception {
        return getSql(sqlTemplate, args);
    }

    /**
     * 实现Java占位符处理sql语句，避免在sql语句中连接变量
     * @param sql 带有占位符(?)的sql语句
     * @param args 占位符对应的变量，可变参数
     * @return 联接后的sql语句
     * @throws Exception
     */
    public static String getSql(String sql, String... args) throws Exception {
        // sql前后加空格的目的是，即使占位符?在字符串两端，也能保证分割成(?的数量+1)个
        StringBuffer sb = new StringBuffer(" ");
        sql = sb.append(sql).append(" ").toString();
        String[] sqlarr = sql.split("\\?");
        // check
        if (sqlarr.length - args.length != 1) {
            throw new Exception("占位符与参数个数不一致！");
        }

        sb = new StringBuffer(sqlarr[0]);

        for (int i = 0; i < args.length; i++) {
            sb.append(args[i]).append(sqlarr[i+1]);
        }

        return sb.toString().trim();
    }

    public static void main(String[] args) throws Exception {
        String sql = SqlUtil.getSql("select * from coreusercard where userno = '?' and znkh = '?'", "12138", "3333333333333");
        System.out.println(sql);

        String sqltemp = "select * from corecard where u = '?' and dd = '?'";
        String[] strs = {"123", "456"};
        SqlUtil sqlUtil = new SqlUtil(sqltemp, strs);
        System.out.println(sqlUtil.getSql());
    }

}
