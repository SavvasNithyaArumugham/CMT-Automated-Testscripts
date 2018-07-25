package com.pearson.automation.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** DBUtils.java: This program is used to fetch the data from database
 * 
 */

public class DBUtils 
{
	
	/** This Method is used to get Column values form Database
	 * @param connection
	 * @param sql
	 * @return column values from database
	 * @throws SQLException
	 */
	public static String[] getDBValue(Connection connection, String sql)
			throws SQLException {

		Statement stmt = null;
		String resultArr[] = null;
		ResultSet rs = null;
		ResultSetMetaData metadata = null;
		ArrayList<String> result = new ArrayList<String>();
		ArrayList<String> columns = new ArrayList<String>();

		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
			metadata = rs.getMetaData();
			int columnCount = metadata.getColumnCount();

			while (rs.next()) {

				for (int i = 1; i < columnCount + 1; i++) {
					String columnName = metadata.getColumnName(i);
					columns.add(columnName);
					result.add(rs.getString(columnName));
				}
			}
			resultArr = new String[result.size()];
			resultArr = result.toArray(resultArr);

		} catch (Exception e) {
			throw e;
		} finally {

			try {
				rs.close();
			} catch (Exception e) {
				throw e;
			}

			try {
				stmt.close();
			} catch (Exception e) {
				throw e;
			}
		}
		return resultArr;
	}

	/** This Method is used to get Column values form Database using multiple parameters
	 * @param connection
	 * @param sql
	 * @return column values from database
	 * @throws SQLException
	 * @param muliParameters
	 * @return
	 * @throws SQLException
	 */
	public static String[] getDBMultiValueParam(Connection connection,
			String sql, String multiParameters[])
			throws SQLException {

		PreparedStatement pstmt = null;
		String resultArr[] = null;
		ResultSet rs = null;
		ResultSetMetaData metadata = null;
		ArrayList<String> result = new ArrayList<String>();
		ArrayList<String> columns = new ArrayList<String>();

		try {
			
			//Prepare statement
			pstmt = connection.prepareStatement(sql);

			if(multiParameters!=null)
			{
				for(int index=0;index<multiParameters.length;index++)
				{
					pstmt.setString(index+1, multiParameters[index]);
				}
			}
			
			//Execute a query
			rs = pstmt.executeQuery();
			metadata = rs.getMetaData();
			int columnCount = metadata.getColumnCount();

			//Extract data from result set
			while (rs.next()) {

				for (int i = 1; i < columnCount + 1; i++) {

					String columnName = metadata.getColumnName(i);
					columns.add(columnName);
					result.add(rs.getString(columnName));

				}
			}
			resultArr = new String[result.size()];
			resultArr = result.toArray(resultArr);

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				rs.close();
			} catch (Exception e) {
				throw e;
			}
			try {
				pstmt.close();
			} catch (Exception e) {
				throw e;
			}
		}
		return resultArr;
	}

	/** This method split the sql queries using given input and get the values from database
	 * @param connection
	 * @param sql
	 *            split the query using given input
	 * @return column values from Database
	 * @throws SQLException
	 */

	public static String[] splitTheQueryAndGetDBValue(Connection connection, String sql, String splitChar)
			throws SQLException {

		Statement stmt = null;
		String resultArr[] = null;
		ResultSet rs = null;
		ResultSetMetaData metadata = null;
		ArrayList<String> result = new ArrayList<String>();
		ArrayList<String> columns = new ArrayList<String>();
		try {

			String multipleQueries[] = sql.split(splitChar);
			List<String> queriesList = new ArrayList<>(Arrays.asList(multipleQueries));

			for (String query : queriesList) {

				stmt = connection.createStatement();
				rs = stmt.executeQuery(query);
				metadata = rs.getMetaData();
				int columnCount = metadata.getColumnCount();

				while (rs.next()) {

					for (int i = 1; i < columnCount + 1; i++) {
						String columnName = metadata.getColumnName(i);
						columns.add(columnName);
						result.add(rs.getString(columnName));
					}
				}
				resultArr = new String[result.size()];
				resultArr = result.toArray(resultArr);
			}

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				rs.close();
			} catch (Exception e) {
				throw e;
			}
			try {
				stmt.close();
			} catch (Exception e) {
				throw e;
			}
		}
		return resultArr;
	}

	/**This method establishes connection to database
	 * @param dbDriver
	 * @param dbUrl
	 * @param dbName
	 * @param dbUsername
	 * @param dbPassword
	 * @return connection from the database
	 * @throws Exception 
	 */

	public static Connection getDBConnection(String dbDriver, String dbUrl,
			String dbName, String dbUsername, String dbPassword) throws Exception {

		Connection connection = null;

		try {
			
			//Register JDBC driver
			Class.forName(dbDriver).newInstance();
			
			//Open a connection
			connection = DriverManager.getConnection(dbUrl + dbName,
					dbUsername, dbPassword);
			
		} catch (Exception e) {
			throw e;
		}
		return connection;
	}

}