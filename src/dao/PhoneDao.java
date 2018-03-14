package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import entity.Phone;

public interface PhoneDao {
	public int insert(Phone phone);
	public List<Phone> select(String deviceId);
	public int update(@Param("deviceId") String deviceId, @Param("pass") String password);
}
