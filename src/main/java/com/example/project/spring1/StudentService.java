package com.example.project.spring1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
	//static Logger log = Logger.GetLoggerFactory(Student.class);
	@Autowired 
	JdbcTemplate jt;
	@Autowired 
	NamedParameterJdbcTemplate np;
   public int insert(Student s)
   {
	   int id=s.getId();
	   int age= s.getAge();
	   String name= s.getName();
	   String email= s.getEmail();
	   int no= s.getPhoneNo();
	   
	  // System.out.println(no);
	   //System.out.println(id);
	   //System.out.println(age); 
	   String sql= "insert into mssitg140 values(?,?,?,?,?)";
	   return jt.update(sql,id,name,age,email,no);
	 }
   public int update1(Student s)
   {
	   String sql1="update mssitg140 set name=? where id=?"; 
	   return jt.update(sql1,s.getName(),s.getId());
   }
   
   public Map<String,String> insertn(Student s)
   {
	   Map<String,String> m=new HashMap();
	           String sql2= "insert into mssitg140 (id,name,age,email,phoneNo) values(:id,:name,:age,:email,:phoneNo)";
	           SqlParameterSource sps = new MapSqlParameterSource()
	           .addValue("id",s.getId())
	           .addValue("name",s.getName())
	           .addValue("age",s.getAge())
	           .addValue("email",s.getEmail())
	           .addValue("phoneNo",s.getPhoneNo());
			    int i=np.update(sql2,sps);
			    if(i>0)
			    {
			    	m.put("200", "Inserted Successfully");
			    }
			    else
			    {
			    	m.put("Failed", "Insertion Failed");
			    }
				return m;
   }
   
   public List<Map<String, Object>> select2() {
	   List<Map<String, Object>> l1= new ArrayList<Map<String, Object>>();
	   Map<String, Object> m1= null;
	    List<Map<String,Object>> lm =new ArrayList<Map<String,Object>>();
	     lm=jt.queryForList("select * from mssitg140 ");
	     for (Map<String,Object> i : lm)
	     {
	    	 m1= new HashMap<String, Object>();
	    	 m1.put("id",i.get("id"));
	    	 m1.put("name",i.get("name"));
	    	 m1.put("age", i.get("age"));
	    	 m1.put("email",i.get("email"));
	    	 m1.put("phoneNo",i.get("phoneNo"));
	    	 l1.add(m1);
	     }
	return l1;
	 }
   
   public List<Map<String, Object>> selectdynamo(Student s) {
	   
	   List<Map<String, Object>> l1= new ArrayList<Map<String, Object>>();
	   Map<String, Object> m1= null;
	    List<Map<String,Object>> lm =new ArrayList<Map<String,Object>>();
	    String sql="select * from mssitg140 where ";
	    if(s.getId()==0&&s.getName()==null&&s.getAge()==0&&s.getEmail()==null&&s.getPhoneNo()==0)
	    {
	    	sql="select * from mssitg140 where 1=1";
	    }
	    if(s.getId()!=0)
	     {
	    	  sql=sql+ " id="+s.getId();
	    	
	     }
	     if(s.getName()!=null)
	     {
	    	 sql=sql+" name="+"'"+s.getName()+"'";
	     }
	     if(s.getAge()!=0)
	     {
	    	 sql=sql+" age="+s.getAge();
	     }
	     if(s.getEmail()!=null)
	     {
	    	 sql=sql+"email="+"'"+s.getEmail()+"'";
	     }
	     if(s.getPhoneNo()!=0)
	     {
	    	 sql=sql+"phoneNo="+s.getPhoneNo();
	     }
	    
	    lm=jt.queryForList(sql);
	    System.out.println(sql);
	     for (Map<String,Object> i : lm)
	     {
	    	 m1= new HashMap<String, Object>(); 
	    	 m1.put("id",i.get("id"));
	    	 m1.put("name",i.get("name"));
	    	 m1.put("age", i.get("age"));
	    	 m1.put("email",i.get("email"));
	    	 m1.put("phoneNo",i.get("phoneNo"));
	    	 l1.add(m1);
	     }
	return l1;
	 }
   
 public List<Map<String, Object>> displaydate(Student s) {
	   
	   List<Map<String, Object>> l1= new ArrayList<Map<String, Object>>();
	   Map<String, Object> m1= null;
	    List<Map<String,Object>> lm =new ArrayList<Map<String,Object>>();
	   String sql=null;
	    if(s.getStartdate()!=null && s.getEnddate()!=null)
	    {
	    	sql= "select * from employeebio where startdate>='"+s.getStartdate()+"' and enddate<='"+s.getEnddate()+"'";
	    }
	    else if(s.getStartdate()!=null &&s.getEnddate()==null)
	    {
	    	sql= "select * from mss.employeebio where startdate>='"+s.getStartdate()+"'";
	    }
	    else if(s.getStartdate()==null &&s.getEnddate()!=null)
	    {
	    	sql= "select * from employeebio where enddate='"+s.getEnddate()+"'";
	    }
	    else
	    {
	    	sql= "select * from employee ";
	    }
	    System.out.println(sql);
	    lm=jt.queryForList(sql);
	    System.out.println(sql);
	     for (Map<String,Object> i : lm)
	     {
	    	 m1= new HashMap<String, Object>(); 
	    	 m1.put("id",i.get("id"));
	    	 m1.put("name",i.get("name"));
	    	 m1.put("email",i.get("email"));
	    	 m1.put("phoneNo",i.get("phoneNo"));
	    	 m1.put("department", i.get("department"));
	    	 m1.put("projectmanager", i.get("projectmanager"));
	    	 m1.put("project", i.get("project"));
	    	 m1.put("technnology", i.get("technology"));
	    	 m1.put("startdate", i.get("startdate"));
	    	 m1.put("enddate", i.get("enddate"));
	    	 l1.add(m1);
	     }
	return l1;
	 }   
   
   
   
   
   
}
