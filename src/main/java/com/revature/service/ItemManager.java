package com.revature.service;

import java.util.ArrayList;

import com.revature.beans.Equipment;
import com.revature.dao.ItemDAO;

public class ItemManager {
	ItemDAO itmDb = new ItemDAO();
	/**
	 * Gets all the items in the database
	 * @return
	 */
	public ArrayList<Equipment> getAllItems(){
		return itmDb.getAllItems();
	}
}
