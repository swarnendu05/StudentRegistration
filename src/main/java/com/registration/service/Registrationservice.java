package com.registration.service;

import com.registration.Exception.DuplicateRegistration;
import com.registration.Exception.ResourceNotFound;
import com.registration.entity.Registration;
import com.registration.patload.RegistrationDTO;
import com.registration.repository.RegistrationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Registrationservice {

    private RegistrationRepository registrationRepository;

    public Registrationservice(RegistrationRepository registrationRepository, ModelMapper modelMapper) {
        this.registrationRepository = registrationRepository;
        this.modelMapper = modelMapper;
    }


    private ModelMapper modelMapper;




    public RegistrationDTO saveRegistration(RegistrationDTO registrationDto) {
        // convert to Entity From Dto
        Registration registration =  convertDtoToEntity(registrationDto);
//        Registration registration = new Registration();
//        registration.setName(registrationDto.getName());
//        registration.setEmailId(registrationDto.getEmail());
//        registration.setMobile(registrationDto.getMobile());
  if (registrationRepository.findByName(registration.getName())
                                 .isPresent()){
      throw new DuplicateRegistration("Registration "+registration.getName()+" present");
  }

  else {
      Registration save = registrationRepository.save(registration);
      return convertEntityToDto(save);
  }
    }



    public void deleteRegistration(Long id) {
        registrationRepository.deleteById(id);
    }




    public void update(long id, Registration registration) {
        Optional<Registration> opreg= registrationRepository.findById(id);
        Registration reg= opreg.get();
        reg.setName(reg.getName());
        reg.setEmail_id(reg.getEmail_id());
        reg.setMobile(reg.getMobile());
        registrationRepository.save(reg);
    }

    public List<Registration> getAllRegistration(
            int pageNO,
            int pageSize,
            String sortBy,
            String sortDir) {
      Sort sort = sortDir.equalsIgnoreCase("asc")
              ? Sort.by(sortBy).ascending()
              :Sort.by(sortBy).descending();

        Pageable page = PageRequest.of(pageNO, pageSize, sort);
        Page<Registration> regs = registrationRepository.findAll(page);
       // List<Registration> registrations = regs.getContent();

        System.out.println(page.getPageNumber());
        System.out.println(page.getPageSize());
        System.out.println(regs.getTotalPages());
        System.out.println(regs.getTotalElements());
        System.out.println(regs.isLast());
        System.out.println(regs.isFirst());

        return regs.getContent();
    }


    public Registration getRegistrationById(long id) {
        Registration registration = registrationRepository.findById(id)
                .orElseThrow(
                        ()-> new ResourceNotFound("NO CUSTOMER PRESENT WITH ID = " + id)
                );
        return registration;


    }

    Registration convertDtoToEntity(RegistrationDTO registrationDto) {
        Registration registration = modelMapper.map(registrationDto,Registration.class);
        return registration;
    }

    RegistrationDTO convertEntityToDto(Registration registration) {
        RegistrationDTO map = modelMapper.map(registration, RegistrationDTO.class);
        return map;
}

}

