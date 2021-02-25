package com.items.DAO;

public interface ItemsDAO {
	
	public String insertItem(String code, String name, String price, String desc);
	public String readItems();
	public boolean Deleteitems(String itemid);

}
