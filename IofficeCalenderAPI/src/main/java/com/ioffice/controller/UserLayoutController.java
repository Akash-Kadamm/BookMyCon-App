package com.ioffice.controller;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ioffice.dto.DataDto;
import com.ioffice.model.Areas;
import com.ioffice.model.UserLayout;
import com.ioffice.repository.AreasRepository;
import com.ioffice.repository.UserLayoutRepository;

//import com.IOffice.model.Areas;
//import com.IOffice.model.AreasDto;
//import com.IOffice.model.DataDto;
//import com.IOffice.model.User;
//import com.IOffice.model.UserLayout;
//import com.IOffice.repository.AreasRepository;
//import com.IOffice.repository.UserLayoutRepository;
//import com.IOffice.response.ResponseHandler;

@CrossOrigin("*")
@RequestMapping("/userLayout")
@RestController
public class UserLayoutController {

	@Autowired 
	UserLayoutRepository userLayoutRepository;
	
	@Autowired
	AreasRepository areasRepository;
	
	
	
	
//	@Autowired
//	ResponseHandler responseHandler;
//
//	
//	@GetMapping(value = "/userLayoutObj")
//    public ResponseEntity<Object> Get() {
//          return responseHandler.generateResponse("ak", responseHandler);
// }
//
//	@GetMapping(value = "/areasList")
//    public ResponseEntity<List<AreasDto>>  GetAreas() {
//		
//		List<AreasDto> areasList=new ArrayList<>();
//		
//		for (Areas areas : areasRepository.findByUserLayout(userLayoutRepository.findById("ak").get())) {
//			
//			AreasDto areasDto=new AreasDto(areas.getName(), areas.getShape(), areas.getCoords(),areas.getPreFillColor(), areas.getFillColor());
//			if(areasDto.getCoords()!=null) {
//				areasDto.setPreFillColor("transperant");
//				areasDto.setFillColor("black");
//				areasList.add(areasDto);
//			}
//		}
//		
//          return  new ResponseEntity<List<AreasDto>>(areasList,HttpStatus.OK);
// }
	
	
	
	
	
	
	@GetMapping("/")
	public ResponseEntity<List<UserLayout>>  getAllUserLayout()

	{
		
		return  new ResponseEntity<List<UserLayout>>(userLayoutRepository.findAll(),HttpStatus.OK);			
		
	}
	
	
	@GetMapping("/areas")
	public ResponseEntity<List<Areas>>  getAllAreas()

	{
		
		return  new ResponseEntity<List<Areas>>(areasRepository.findAll(),HttpStatus.OK);			
		
	}
	
	
	@PostMapping("/addareas")
	public ResponseEntity<String> addareas(@RequestBody DataDto dataDto)
	{
        // preFillColor: "transperant",

		Areas areas=new Areas(0, dataDto.getName(), dataDto.getShape(), dataDto.getCoords(),"transperant",dataDto.getFillColor(),userLayoutRepository.findById(dataDto.getNameId()).get());
		

//		System.out.println(areas);
	    areasRepository.save(areas);
		return new ResponseEntity<String>("record added successfully", HttpStatus.CREATED);
	}
	
	@PostMapping("/addUserLayout")
	public ResponseEntity<String> addUserLayout(@RequestBody UserLayout userLayout)
	{
		
		

		System.out.println(userLayout);
		userLayoutRepository.save(userLayout);
		return new ResponseEntity<String>("record added successfully", HttpStatus.CREATED);
	}
	
	@GetMapping("/{name}")
	public ResponseEntity<Optional<UserLayout>> getUserLayoutById(@PathVariable(value = "name") String name)
	{
		Optional<UserLayout> user=userLayoutRepository.findById(name);
		
	return new ResponseEntity<Optional<UserLayout>>(user,HttpStatus.OK);
	}

	@PutMapping("/{name}")
	public ResponseEntity<String> editUserLayout(@PathVariable(value = "name") String name, @RequestBody UserLayout userLayout)
	{
		userLayoutRepository.save( userLayout);
	return new ResponseEntity<String>("record updated",HttpStatus.OK);
	}
	
	@DeleteMapping("/{name}")
	public ResponseEntity<String> deleteUserById(@PathVariable String name)
	{
//		
//	Areas areas=	userLayoutRepository.getById(name).getAreas();
//	
//	aerAreasRepository.delete(areas);
//	
	userLayoutRepository.deleteById(name);
	
	
		return new ResponseEntity<String>("record deleted",HttpStatus.OK);
	}
}
