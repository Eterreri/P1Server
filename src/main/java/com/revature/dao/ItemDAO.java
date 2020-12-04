package com.revature.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.revature.beans.Equipment;

public class ItemDAO {
	DatabaseManager db = new DatabaseManager();
	/**
	 * Gets all items from the database
	 * @return
	 */
	public ArrayList<Equipment> getAllItems(){
		ArrayList<Equipment> items = new ArrayList<Equipment>();
		String sqlQuery = "Select * from Equipment;";
		try (Connection connection = db.getConnection()) {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sqlQuery);
			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				float price = rs.getFloat(3);
				boolean isFood = rs.getBoolean(4);
				Equipment e = new Equipment(price, name, isFood, id, 1);
				items.add(e);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return items;
	}
}
