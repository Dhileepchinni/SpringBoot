package com.example.project.spring1;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
@CrossOrigin(origins="*")
public class StudentController {
	
	@Autowired
	StudentService ss;
@PostMapping("/insert")
	
	public String student(@RequestBody Student s )
	{
	//log.info("");
		int i = ss.insert(s);
		//System.out.println(s.getAge());
		//System.out.println("....");
		if (i>0)
		{
			return "Data inserted Successfully";
		}
		else
		{
			return "Not inserted";
		}
		
	}
@PostMapping("/update")
public String studentupdate(@RequestBody Student s)
{
	int i = ss.update1(s);
	if (i>0)
	{
		return "Data inserted Successfully";
	}
	else
	{
		return "Not inserted";
	}
	}
   
@PostMapping("/insertn")
public Map<String,String> studentinsert(@RequestBody Student s)
{
	Map m= ss.insertn(s);
	return m;
	
}
@GetMapping("/select")
public List select1()
{
	
	return ss.select2();
}

@PostMapping("/select1")
public List<Map<String, Object>> select2(@RequestBody Student s)
{
	
	return ss.selectdynamo(s);
}

@PostMapping("/display")
public List<Map<String,Object>> displaydate(@RequestBody Student s)
{
	return ss.displaydate(s);
}
}
