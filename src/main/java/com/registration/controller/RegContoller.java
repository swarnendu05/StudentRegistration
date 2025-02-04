package com.registration.controller;


import com.registration.entity.Registration;
import com.registration.patload.RegistrationDTO;
import com.registration.service.Registrationservice;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.webresources.FileResource;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class RegContoller {


    private Registrationservice registrationservice;

    public RegContoller(Registrationservice registrationservice) {
        this.registrationservice = registrationservice;
    }


    //http://localhost:800/api/vi/add
    @PostMapping("/add")
    public ResponseEntity<RegistrationDTO> addRegistration(
            @RequestBody RegistrationDTO registrationDto
    ) {
        RegistrationDTO r = registrationservice.saveRegistration(registrationDto);
        return new ResponseEntity<>(r, HttpStatus.CREATED);
    }



    // delete a api using requestParam and id
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deleteapi(@PathVariable long id){
//        registrationservice.deleteRegistration(id);
//      //  List<Registration>res= registrationservice.getAllRegistration();
//        return new ResponseEntity<>(res,HttpStatus.OK);
//    }

    @PutMapping("/put/{id}")
    public String updateapi(
            @PathVariable long id,
            @RequestBody Registration registration

    ){
        registrationservice.update(id,registration);
        return "done";

    }

    // http://localhost:800/api/vi/get?pageNo=0&pageSize=5

    @GetMapping("/get")
    public List<Registration> getallRegistration(
            @RequestParam(defaultValue = "0",required = false) int pageNo,
            @RequestParam(defaultValue = "5",required = false) int pageSize,
            @RequestParam (defaultValue ="id",required = false) String sortBy,
            @RequestParam (defaultValue ="asc",required = false) String sortDir

    ){
        List<Registration>res= registrationservice.getAllRegistration(pageNo,pageSize,sortBy,sortDir);
        return res;
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getRegistrationById(@PathVariable long id) throws FileNotFoundException {


        FileReader fr= new FileReader("G://test.txt");
        Optional<Registration> res = Optional.ofNullable(registrationservice.getRegistrationById(id));



            return new ResponseEntity<>(res.get(), HttpStatus.OK);

    }





























}
