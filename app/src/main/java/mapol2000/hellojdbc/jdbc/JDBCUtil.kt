package mapol2000.hellojdbc.jdbc

import java.sql.*

object JDBCUtil {

    const val DRV: String = "org.mariadb.jdbc.Driver"
    private val URL: String = "jdbc:mariadb://mariadb.cgvd3dx5cw1w.ap-northeast-2.rds.amazonaws.com:3306/playground"
    private val USR: String = "playground"
    private val PWD: String = "playground2020"

    fun makeConn(): Connection? {
        var conn: Connection? = null
        try {
            Class.forName(DRV)
            conn = DriverManager.getConnection(URL, USR, PWD)
        } catch (e: ClassNotFoundException) {
            println("JDBC 드라이버가 없어요!")
        } catch (e: SQLException) {
            println("JDBC 연결 실패!")
        }
        return conn
    }

    fun destoryConn(conn: Connection?, pstmt: PreparedStatement?) {
        if (pstmt != null) try {
            pstmt.close()
        } catch (ex: SQLException) {
        }
        if (conn != null) try {
            conn.close()
        } catch (ex: SQLException) {
        }
    }

    fun destoryConn(conn: Connection?, pstmt: PreparedStatement?, rs: ResultSet?) {
        if (rs != null) try {
            rs.close()
        } catch (ex: SQLException) {
        }
        destoryConn(conn, pstmt)
    }

}