package in.nit.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.nit.model.Student;
import in.nit.service.IStudentService;

@RestController
@RequestMapping("/student")
public class StudentRestController {
	@Autowired
	private IStudentService service;

	@PostMapping
	public ResponseEntity<String> saveStudent(@RequestBody Student student){

		ResponseEntity<String> resp=null;
		try {
			Integer id=service.saveStudent(student);

			resp=new ResponseEntity<String>(id+"saved succesfull", HttpStatus.OK);


		} catch (Exception e) {
			resp=new ResponseEntity<String>("Note saved", HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return resp;
	}


	@GetMapping
	public ResponseEntity<?> getAllStudent(){

		ResponseEntity<?> resp=null;

		try {

			List<Student> list=service.getAllStudent();

			if(list!=null && !list.isEmpty()) {
				resp=new ResponseEntity<List<Student>>(list,HttpStatus.OK);
			}else

				resp=new ResponseEntity<String>("No record found", HttpStatus.OK);

		} catch (Exception e) {

			resp=new ResponseEntity<String>("Unable to fetch data", HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();

		}

		return resp;
	}

	@GetMapping("/one/{id}")
	public ResponseEntity<?> getOneStudent(@PathVariable Integer id){

		ResponseEntity<?> resp=null;

		try {
			Optional<Student> opt=service.getOneStudent(id);
			if(opt.isPresent()) {
				resp=new ResponseEntity<Student>(opt.get(),HttpStatus.OK);
			}else
				resp=new ResponseEntity<String>("no data find",HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			resp=new ResponseEntity<String>("unable to fetch data",HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();

		}

		return resp;
	}
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<String> deleteStudent(@PathVariable Integer id){
		ResponseEntity<String> resp=null;

		try {
			boolean exist=service.isExist(id);

			if(exist) {
				service.deleteStudent(id);
				resp=new ResponseEntity<String>("deleted successfull",HttpStatus.OK);
			}else
				resp=new ResponseEntity<String>("no record found",HttpStatus.BAD_REQUEST);

		} catch (Exception e) {

			resp=new ResponseEntity<String>("unable to delete",HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
			// TODO: handle exception
		}
		return resp;
	}
	@PutMapping("/update")
	public ResponseEntity<String> updateStudent(@RequestBody Student student){
		ResponseEntity<String> resp=null;
		try {

			boolean exist=service.isExist(student.getStdId());
			if(exist) {
				service.saveStudent(student);
				resp=new ResponseEntity<String>("updated", HttpStatus.OK);
			}else
				resp=new ResponseEntity<String>("record not exist", HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			resp=new ResponseEntity<String>("Unable to updare", HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return resp;
	}
}
