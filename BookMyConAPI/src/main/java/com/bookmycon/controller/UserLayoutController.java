package com.bookmycon.controller;



import java.util.*;

import com.bookmycon.service.AreasService;
import com.bookmycon.service.UserLayoutService;
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

import com.bookmycon.dto.AreasDto;
import com.bookmycon.dto.DataDto;
import com.bookmycon.model.Areas;
import com.bookmycon.model.UserLayout;
import com.bookmycon.repository.AreasRepository;

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
	UserLayoutService userLayoutService;
	
	@Autowired
	AreasService areasService;


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
	@GetMapping(value = "/areasList")
    public ResponseEntity<List<AreasDto>>  GetAreas() {
		
		List<AreasDto> areasList=new ArrayList<>();
		
		for (Areas areas : areasService.findByUserLayout(userLayoutService.findAll().get(0))) {
			
			AreasDto areasDto=new AreasDto(areas.getName(), areas.getShape(), areas.getCoords(),areas.getPreFillColor(), areas.getFillColor());
			if(areasDto.getCoords()!=null) {
				areasDto.setPreFillColor("#00000000");
				areasDto.setFillColor("transperant");
				areasList.add(areasDto);
			}
		}
		
          return  new ResponseEntity<List<AreasDto>>(areasList,HttpStatus.OK);
}





	
	@GetMapping("/")
	public ResponseEntity<List<UserLayout>>  getAllUserLayout()

	{
		
		return  new ResponseEntity<List<UserLayout>>(userLayoutService.findAll(),HttpStatus.OK);
		
	}
	
	
	@GetMapping("/areas")
	public ResponseEntity<List<Areas>>  getAllAreas()

	{
		
		return  new ResponseEntity<List<Areas>>(areasService.findAll(),HttpStatus.OK);
		
	}

	@GetMapping("/areasByCoords/{id}")
	public Areas  getAreaById(@PathVariable(value = "id") int[] id)

	{
//		System.out.println(id.split(",").length);
//	int[] intarray=new int[9];
//		for (int i=0;i<id.split(",").length;i++)
//		{
//			int a=Integer.parseInt(id.split(",")[i]);
//			intarray[i]=a;
//		}


		if(areasRepository.findByCoords(id).get(0)==null)
		{
			return areasRepository.findByCoords(id).get(1);
		}else {
			return areasRepository.findByCoords(id).get(0);
		}
	}


	@PostMapping("/addareas")
	public ResponseEntity<String> addareas(@RequestBody DataDto dataDto)
	{
        // preFillColor: "transperant",

		Areas areas=new Areas(0, dataDto.getName(), dataDto.getShape(), dataDto.getCoords(),"transperant",dataDto.getFillColor(),userLayoutService.findById(dataDto.getNameId()).get());
		

//		System.out.println(areas);
		areasService.addAreas(areas);
		return new ResponseEntity<String>("record added successfully", HttpStatus.CREATED);
	}


	@PutMapping("/addareas")
	public ResponseEntity<String> addareas(@RequestBody Areas areas)
	{

		areasService.addAreas(areas);
		return new ResponseEntity<String>("record added successfully", HttpStatus.CREATED);
	}
	
	@PostMapping("/addUserLayout")
	public ResponseEntity<String> addUserLayout(@RequestBody UserLayout userLayout)
	{
		
		

		System.out.println(userLayout);
		userLayoutService.addUserLayout(userLayout);
		return new ResponseEntity<String>("record added successfully", HttpStatus.CREATED);
	}
	
	@GetMapping("/{name}")
	public ResponseEntity<Optional<UserLayout>> getUserLayoutById(@PathVariable(value = "name") String name)
	{
		Optional<UserLayout> user=userLayoutService.findById(name);
		
	return new ResponseEntity<Optional<UserLayout>>(user,HttpStatus.OK);
	}

	@PutMapping("/{name}")
	public ResponseEntity<String> editUserLayout(@PathVariable(value = "name") String name, @RequestBody UserLayout userLayout)
	{
		userLayoutService.addUserLayout( userLayout);
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
		userLayoutService.deleteUserLayout(name);
	
	
		return new ResponseEntity<String>("record deleted",HttpStatus.OK);
	}



}
