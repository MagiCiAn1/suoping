package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import RSA.RsaDecrypt;
import entity.Phone;
import service.PhoneService;

@Controller
public class PhoneController {
	@Autowired
	PhoneService ps;
	@RequestMapping(value="magic")
	public void magic(String deviceId,String pass){
		System.out.println("magic");
		List<Phone> list=ps.select(deviceId);
		RsaDecrypt dec=new RsaDecrypt();
		if(list.isEmpty()){
		Phone phone=new Phone();
		phone.setDeviceId(deviceId);
		phone.setPassword(dec.decrypt(pass));
		boolean isInsert=ps.insert(phone);
		if(isInsert){
			System.out.println("insert sucess");
		}else{
			System.out.println("insert error");
		}
		}else{
			boolean isUpdate=ps.update(deviceId, dec.decrypt(pass));
			if(isUpdate){
				System.out.println("update sucess");
			}else{
				System.out.println("update error");
			}
		}
	}
	@RequestMapping(value="select")
	public ModelAndView search(String deviceId){
		List<Phone> list=ps.select(deviceId);
		ModelAndView mv=new ModelAndView("list");
		mv.addObject("list", list);
		return mv;
	}
	@RequestMapping(value="index")
	public String list(){
		return "list";
	}
}
