package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.PhoneDao;
import entity.Phone;
import service.PhoneService;
@Service
public class PhoneServiceImp implements PhoneService{
	@Autowired
	PhoneDao dao;
	@Override
	public boolean insert(Phone phone) {
		// TODO Auto-generated method stub
		int rows=dao.insert(phone);
		return rows>0;
	}

	@Override
	public List<Phone> select(String deviceID) {
		// TODO Auto-generated method stub
		return dao.select(deviceID);
		
	}

	@Override
	public boolean update(String deviceId, String pass) {
		// TODO Auto-generated method stub
		int rows=dao.update(deviceId,pass);
		return rows>0;
	}
	

}
