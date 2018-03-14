package service;

import java.util.List;

import entity.Phone;

public interface PhoneService {
	public boolean insert(Phone phone);
	public List<Phone> select(String deviceID);
	public boolean update(String deviceId,String pass);
}
