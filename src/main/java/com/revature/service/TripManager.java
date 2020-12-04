package com.revature.service;

import java.util.ArrayList;

import com.revature.beans.Equipment;
import com.revature.beans.Trip;
import com.revature.dao.TripDAO;

public class TripManager {
	TripDAO trip = new TripDAO();
	/**
	 * Creates a trip
	 * @param userId
	 * @param name
	 * @return
	 */
	public Trip createTrip(int userId, String name) {
		return trip.createTrip(userId, name);
	}
	/**
	 * Should delete a trip theoretically
	 * @param tripId
	 * @return
	 */
	public boolean deleteTrip(int tripId)
	{
		return trip.deleteTrip(tripId);
	}
	/**
	 * This gets all the equipment for the trip
	 * @param tripId
	 * @param userId
	 * @return
	 */
	public ArrayList<Equipment> GetEquipmentTrip(int tripId, int userId)
	{
		ArrayList<Equipment> itemList = GetItemsTrip(tripId, userId);
		ArrayList<Equipment> foodList = GetItemsFood(tripId, userId);
		for(Equipment e : itemList)
		{
			foodList.add(e);
		}
		return foodList;
	}
	/**
	 * only gets the nonfood items
	 * @param tripId
	 * @param userId
	 * @return
	 */
	public ArrayList<Equipment> GetItemsTrip(int tripId, int userId){
		return trip.SelectEquipmentTrip(tripId);
	}
	/**
	 * only gets food items
	 * @param tripId
	 * @param userId
	 * @return
	 */
	public ArrayList<Equipment> GetItemsFood(int tripId, int userId)
	{
		return trip.SelectFoodTrip(tripId);
	}
	/**
	 * gets a specific instance of a trip and allows for the editing of that trip
	 * @param tripId
	 * @param userId
	 * @return
	 */
	public Trip getTrip(int tripId, int userId) {
		return trip.GetATrip(tripId, userId);
	}
	/**
	 * Adds items to the trip
	 * @param userId
	 * @param tripId
	 * @param equipId
	 * @param quantity
	 * @return
	 */
	public boolean addItemToTrip(int userId, int tripId, int equipId, int quantity)
	{
		return trip.AddItemToTrip(tripId, userId, quantity, equipId);
	}
	/**
	 * Allows the user to change the name of the trip
	 * @param userId
	 * @param tripId
	 * @param newName
	 * @return
	 */
	public Trip EditTripName(int userId, int tripId, String newName) {
		return trip.updateTripName(userId, tripId, newName);
	}
}
