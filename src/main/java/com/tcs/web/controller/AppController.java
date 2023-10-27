package com.tcs.web.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tcs.web.ContactService.ContactService;
import com.tcs.web.CourseService.CourseService;
import com.tcs.web.ProductService.ProductService;
import com.tcs.web.Question.QuestionService;
import com.tcs.web.StudentService.StudentService;
import com.tcs.web.TeacherService.TeacherService;
import com.tcs.web.UserService.UserService;
import com.tcs.web.adminService.AdminService;
import com.tcs.web.adminService.LoginService;
import com.tcs.web.export.AckReceipt;
import com.tcs.web.export.UserExcelExporter;
import com.tcs.web.export.UserPDFExporter;
import com.tcs.web.marksService.MarksService;
import com.tcs.web.model.Admin;
import com.tcs.web.model.Contact;
import com.tcs.web.model.Course;
import com.tcs.web.model.Login;
import com.tcs.web.model.Marks;
import com.tcs.web.model.Product;
import com.tcs.web.model.Questions;
import com.tcs.web.model.Signup;
import com.tcs.web.model.Student;
import com.tcs.web.model.Teacher;
import com.tcs.web.model.User;
import com.tcs.web.repo.studentRepo;
//import com.tcs.web.signupService.SignupService;
import com.tcs.web.signupService.SignupService;

	 
	@Controller
	public class AppController {
	 
	    @Autowired
	    private ProductService service;
	    
	    @Autowired
	    private CourseService courseService;
	    
	    @Autowired
	    private StudentService stdService;
	    
	    @Autowired
	    private TeacherService tservice;
	    
	    @Autowired
	    private AdminService admnSevice;
	    
	    @Autowired
	    private LoginService loginService;
	    
	    @Autowired
	    private ContactService feedback;
	    
	    @Autowired
	    private  SignupService signupservice;
	    @Autowired
	    private  MarksService  markservice;
	    
	    @Autowired
		private studentRepo stdrepo;
	    
	   @Autowired
	   private UserService UserService;
	    
	   @Autowired
	   private QuestionService QuestionService;
	    
	    @RequestMapping("/")
	    public String viewHomePage(Model model) {
	    	System.out.println("Controller hitted for home page.. ");
	        List<Product> listProducts = service.listAll();
	        model.addAttribute("listProducts", listProducts);
	        List<Course> listCourse = courseService.listAll();
	        model.addAttribute("listCourse", listCourse);          
	        return "home";
	    }
	    
	 
	    
	    @RequestMapping("/course")
	    public String showAvailableCourses(Model model) {
	        Course course = new Course();
	        model.addAttribute("course", course);
	        System.out.println("Course Controller hitted ...");
	        return "new_course";
	    }
	    


@RequestMapping(value = "/save", method = RequestMethod.POST)
public String saveProduct(@ModelAttribute("product") Product product) {
    service.save(product);
     return "redirect:/";
}

@RequestMapping(value = "/saveCourse", method = RequestMethod.POST)
public String saveCourse(@ModelAttribute("course") Course course) {
    //service.save(course);
	System.out.println("Course Save Controller hitted before saving ");
	courseService.saveCourse(course);
	System.out.println("Course Save Controller hitted ...");
    return "redirect:/";
}
	

@RequestMapping("/edit/{id}")
public String showEditStudentPage(@PathVariable(name = "id") int id ,Model model) {
   // ModelAndView mav = new ModelAndView("editstudent");
    Student std=stdService.showEditStudentPage(id);
    System.out.println("Controller before edit ..");
   // mav.addObject("student", std);
    model.addAttribute("student", std);
    return "editstudent"; 
}

@RequestMapping("/delete/{id}")
public String deleteProduct(@PathVariable(name = "id") int id) {
    service.delete(id);
    return "redirect:/";       
}

@RequestMapping("/student")
public String addStudent(Model model) {
	  Student student = new Student();
	    model.addAttribute("student", student);
	    System.out.println("Student Controller hitted ");
	    return "student";
}

@RequestMapping("/studentRegistration")
public String addRegistration(Model model) {
    Student student = new Student();
    model.addAttribute("student", student);
    System.out.println("Student Controller hitted ");
    return "student";
}

@RequestMapping(value = "/saveStudent", method = RequestMethod.POST)
public ModelAndView saveStudent(@ModelAttribute("student") Student student, @RequestParam("file") MultipartFile file) throws IOException
//public ModelAndView saveCourse(@ModelAttribute("student") Student student)
{   
	ModelAndView mav = new ModelAndView("studentRegistration");
	AckReceipt ackr =new AckReceipt(student);
	Student st=null;
	LocalDateTime dt = LocalDateTime.now();
	st=student;
	st.setDate_of_add(dt);
	/*
	 * if(file.getSize()>1048576) {
	 * //redirectAttributes.addFlashAttribute("message", );
	 * mav.addObject("message","File size exceeds the maximum allowed (1MB).");
	 * //return "redirect:/uploadResult";
	 * System.out.println("The file size exceeds  : "); return mav; } else {
	 */
	int id=stdService.studentRegistration(st);
	String str=String.valueOf(id);
    String  UPLOADED_FOLDER="E://temp//";	
	//Changed for Aws unix configuration
	//String  UPLOADED_FOLDER="/usr/uploaded_files/";	
	String ackid=UPLOADED_FOLDER+str+'_';
	byte[] bytes = file.getBytes();
   Path path = Paths.get(ackid + file.getOriginalFilename());
   Path pat= Files.write(path, bytes);   
   ackr.passFileName(path);
   System.out.println("The file name is : "+pat);
	mav.addObject("str1", str);
	
	return mav;
		  
}

@RequestMapping(value = "/saveEditStudent", method = RequestMethod.POST)

public ModelAndView saveEditStudent(@ModelAttribute("student") Student student)
{
	stdService.saveEditStudent(student);
    System.out.println("Edit Controller hitted" );
	ModelAndView mav = new ModelAndView("editMsg");
	List<Student> std =stdService.showRegisteredStudent();
	mav.addObject("std1", std);
	return mav;
		  		  
}

@RequestMapping(value = "/saveTeacher", method = RequestMethod.POST)
public String saveTeacher(@ModelAttribute("teacher") Teacher teacher)
{
	System.out.println(" Teacher's information saving ..");
	tservice.saveTeacher(teacher);
	return "successteacher";
}


@RequestMapping("/teacherRegistration")
public String teacherRegistration(Model model) {
    Teacher teacher = new Teacher();
    model.addAttribute("teacher", teacher);
    System.out.println("Teacher Controller hitted ");
    return "teacher";
}

@RequestMapping("/adminPassword")
public String adminUser(Model model) {
    Admin admin = new Admin();
    model.addAttribute("admin", admin);
    System.out.println("Admin is creating  ");
    return "admin";
}

@RequestMapping(value= "/adminPasswordConfirm", method = RequestMethod.POST)
public String adminUser(@ModelAttribute("admin") Admin admin)
{
   System.out.println("Admin service is creating  ");
	admnSevice.adminUser(admin);
	
	return "adminuser";
	
}


@RequestMapping("/signin")
public String loginUser(Model model) {
    Login login = new Login();
    model.addAttribute("login", login);
    System.out.println("login is creating  ");
    return "login";
}

@RequestMapping("/exam")
public String examPage(Model model) {
    User user = new User();
    model.addAttribute("user", user);
    System.out.println("User is creating  ");
    return "user";
}

@RequestMapping("/userDetails")
 public String examPageDisplay(@ModelAttribute("user") User user)
    {
	  System.out.println("User page/controller is creating  ");
	  UserService.saveUser(user);
	  System.out.println("User Saved in DB ");
	  return "question";
	
	}



@PostMapping("/questions")
public String submitForm(@ModelAttribute("Questions") Questions Questions) {
	QuestionService.saveQuestion(Questions);
	return "next";
}

@RequestMapping(value= "/logout")
public String logout()
{
   System.out.println("logout link clicked  ");
  	return "index";
	
}

@RequestMapping(value= "signinPasswd", method = RequestMethod.POST)
public String adminUser(@ModelAttribute("login") Login login)
{
	  System.out.println("login controller is creating  ");
	 String str= loginService.loginUser(login);
	  if(str=="loginsuccess")
		 return "loginsuccess";
	 else
	 {	 if(str=="admin1")
		 return "admin1";
	 } 
		 return "failed";
	
}

@RequestMapping("/feedback")
public String submitFeedBack(Model model) {
    Contact contact = new Contact();
    model.addAttribute("contact", contact);
           
    return "feedback";
}


@RequestMapping(value = "/savecontact", method = RequestMethod.POST)
public ModelAndView saveFeedback(@ModelAttribute("contact") Contact contact)
     { //System.out.println(" feedbcak's information saving ..");
		int id=feedback.savefeedBack(contact);
		System.out.println(" feedbcak's number returned .."+id);	
		//System.out.println("Contact page hitted the controller "+contact.getEmail());
		System.out.println("Count is"+id);
		String str=String.valueOf(id);
		ModelAndView mav = new ModelAndView("success");
		mav.addObject("str1", str);
		return mav;
		
	   }


@RequestMapping(value = "/register1")
public ModelAndView showRegisteredStudent(@ModelAttribute("student") Student student)
     { 
	
	  // System.out.println("Before service call ");
	   List<Student> std =stdService.showRegisteredStudent();
	   Long totalRecords= stdrepo.count();
	    int tot=std.size();
	  	System.out.println("Total Students in DB "+tot);
		System.out.println("Total Students in controller "+totalRecords);
	   ModelAndView mav = new ModelAndView("register");
	   mav.addObject("std1", std);
	   mav.addObject("totalRecords",totalRecords);	   
	   return mav;
		
	   }
@RequestMapping("/signup")
public String signUp(Model model) {
	Signup signup = new Signup();
	System.out.println("SignUp Controller Hitted ");
    model.addAttribute("signup", signup);
    //System.out.println("login is creating  ");
    return "signup";
}


 @RequestMapping(value = "/signupsave", method = RequestMethod.POST)
public String saveSignup(@ModelAttribute("signup") Signup signup)
{
	 System.out.println("Signup service is creating  ");
	 signupservice.signUpsave(signup);
	 return "signupsuccess";
}
 
@RequestMapping(value = "/export")
public void exportToExcel(HttpServletResponse response) throws IOException
   {
	System.out.println("Export to excel calling ");
	response.setContentType("application/octet-stream");
	String headerkey="Content-Disposition";
	String headervalue="attachment; filename=students.xlsx";
	response.setHeader(headerkey, headervalue);
	List<Student> liststd =stdService.showRegisteredStudent();
	UserExcelExporter exporter = new UserExcelExporter(liststd);
	exporter.export(response);
	}

@RequestMapping(value = "/pdf")
public void exportToPDF(HttpServletResponse response) throws IOException
   {
	System.out.println("Export to excel calling ");
	response.setContentType("application/pdf");
	DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
	String currentDateTime = dateFormatter.format(new Date());
	String headerkey="Content-Disposition";
	String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
	response.setHeader(headerkey, headerValue);
	List<Student> liststd =stdService.showRegisteredStudent();
	UserPDFExporter exporter = new UserPDFExporter(liststd);
	exporter.export(response);
	}


	
	@RequestMapping(value = "/ackReceipt")
	public void ackReceipt(HttpServletResponse response) throws IOException
	   {
		Student std = new Student();
		System.out.println("ack receipt  ");
		response.setContentType("application/pdf");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());
		String headerkey="Content-Disposition";
		String headerValue = "attachment; filename=ack_" + currentDateTime + ".pdf";
		response.setHeader(headerkey, headerValue);
		int id=stdService.ackNo();
		System.out.println("Student ack no from DB : "+id);
		String str=String.valueOf(id);	
		std =stdService.studentNameId(id);
		AckReceipt exporter = new AckReceipt(std);
		exporter.export(response);
	   }
	
	
	@RequestMapping(value = "/duplicateackReceipt")
	public void duplicateAckReceipt(HttpServletResponse response , String keyword) throws IOException
	   {
		Student std = new Student();
		System.out.println("Duplicate ack receipt  ");
		response.setContentType("application/pdf");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());
		String headerkey="Content-Disposition";
		String headerValue = "attachment; filename=ack_" + currentDateTime + ".pdf";
		response.setHeader(headerkey, headerValue);
		//int id=stdService.ackNo();
		String key= keyword;
		int id=Integer.parseInt(key);  
		System.out.println("Student ack no from DB : "+id);
		String str=String.valueOf(id);	
		std =stdService.studentNameId(id);
		AckReceipt exporter = new AckReceipt(std);
		exporter.export1(response);
	   }
	
	
	@RequestMapping(value = "/search")
	public ModelAndView search(Model model,String keyword)
	 {
		System.out.println("Search controller hitted ");
		List<Student> std = null;
		
			std= stdService.findByKeyword(keyword);
			if(std.isEmpty())
			{  ModelAndView mav = new ModelAndView("searchFailed");
				mav.addObject("std1", std);	
				return mav;
			}
			
			else
			{ 
			
			for(Student st:std)
			{
			System.out.println("Search controller inside keyword  "+st.getId());
			}
			ModelAndView mav = new ModelAndView("search");
			mav.addObject("std1", std);
		
		    return mav;
		    
			}
	 }
	
	@RequestMapping(value = "/result")
	public ModelAndView searchResult(Model model,String keyword)
	{
		System.out.println("Mark controller hitted ");
		Marks mark=null;
		mark=markservice.findResultByKeyword(keyword);
		if(mark==null)
		{
		ModelAndView mav = new ModelAndView("searchFailed");
	
		return mav;
		}
		
		else
		 if(mark.getCourse().equalsIgnoreCase("ADCA"))
		 {   System.out.println("Search controller inside keyword ADCA "+mark.getId());
		    ModelAndView mav = new ModelAndView("searchResults");
		    mav.addObject("std1", mark);
		    return mav;
		 }
		
		 else
		 {
			 System.out.println("Search controller inside DCA  "+mark.getId());
			    ModelAndView mav = new ModelAndView("searchResultsDCA");
			    mav.addObject("std1", mark);
			    return mav;
		 }
	}
	
	@RequestMapping("/results")
	public String findResult(Model model) {
	    Marks mrk = new Marks();
	    model.addAttribute("result", mrk);        
	    return "results";
	}
	

    @RequestMapping(value = "/getFeedback")
     public ModelAndView getAllFeedBack(@ModelAttribute("contact") Contact contact)
     {
      System.out.println("Get all feedback controller hitted ");
      List<Contact> cont=feedback.getAllFeedBack(contact);
      ModelAndView mav = new ModelAndView("allFeedBack");
      mav.addObject("std1",cont);
      return mav;
     }
  
	
}

