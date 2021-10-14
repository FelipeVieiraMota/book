package com.motafelipe.api.backoffice.controller;

import com.motafelipe.api.backoffice.domains.vo.entities.StudentEntity;
import com.motafelipe.api.backoffice.models.EnvelopedData;
import com.motafelipe.api.backoffice.models.pagination.PageModel;
import com.motafelipe.api.backoffice.models.pagination.PageRequestModel;
import com.motafelipe.api.backoffice.models.students.AddressModel;
import com.motafelipe.api.backoffice.models.students.StudentModel;
import com.motafelipe.api.backoffice.services.AddressService;
import com.motafelipe.api.backoffice.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import javassist.NotFoundException;

import javax.validation.Valid;

@RestController
@RequestMapping(value="/v1/backoffice/students")
public class StudentController {

    private final StudentService studentService;
    private final AddressService addressService;

    /**
     * StudentController
     * @param studentService - studentService
     * @param addressService - addressService
     */
    @Autowired
    public StudentController(
            StudentService studentService,
            AddressService addressService) {
        this.studentService = studentService;
        this.addressService = addressService;
    }

    /**
     * Update
     * @param idStudent - idStudent
     * @param studentModel - studentModel
     * @return ResponseEntity<StudentModel>
     */
    //@Secured({"ROLE_ADMINISTRATOR"})
    @PutMapping("/{id_student}")
    public ResponseEntity<StudentModel> update(@PathVariable(name="id_student") Long idStudent, @RequestBody @Valid StudentModel studentModel){
        studentModel.setIdStudent(idStudent);
        StudentModel updatedStudentModel = this.studentService.update(studentModel);
        return ResponseEntity.ok(updatedStudentModel);
    }

    /**
     * Returns a student by id.
     * @param idStudent - identification of a single student.
     * @return ResponseEntity<EnvelopedData<StudentModel>>
     * @throws NotFoundException - Http status 404
     */
    @GetMapping("/{id_student}")
    public ResponseEntity<EnvelopedData<StudentModel>> getById(@PathVariable(name="id_student") Long idStudent) {
        return ResponseEntity.ok(new EnvelopedData<>(this.studentService.getById(idStudent)));
    }

    /**
     * Delete a student by id.
     * @param idStudent - identification of a single student.
     * @return ResponseEntity
     * @throws NotFoundException - Http status 404
     */
    //@Secured({"ROLE_ADMINISTRATOR"})
    @DeleteMapping("/{id_student}")
    public ResponseEntity deleteById(@PathVariable(name="id_student") Long idStudent) {
        this.studentService.deleteById(idStudent);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * Create a new resource.
     * @param studentModel - parameter
     * @return ResponseEntity<EnvelopedData<StudentModel>>
     */
    //@Secured({"ROLE_ADMINISTRATOR"})
    @PostMapping()
    public ResponseEntity<EnvelopedData<StudentModel>> save (@RequestBody @Valid StudentModel studentModel){
        var result = this.studentService.save(studentModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(new EnvelopedData<>(result));
    }

    /**
     * Get all resources by lazy loading.
     * @param page - page
     * @param size - size of page.
     * @return ResponseEntity<PageModel<StudentEntity>>
     */
    @GetMapping
    public ResponseEntity<PageModel<StudentModel>> getPagination(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        PageRequestModel pr = new PageRequestModel(page, size);
        PageModel<StudentModel> pm = this.studentService.getPagination(pr);
        return ResponseEntity.ok(pm);
    }

    /**
     * Create a new address resource.
     * @param addressModel
     * @return ResponseEntity<EnvelopedData<AddressModel>>
     */
    //@Secured({"ROLE_ADMINISTRATOR"})
    @PostMapping("/{id_student}/address")
    public ResponseEntity<EnvelopedData<AddressModel>> save (
            @PathVariable(name="id_student") Long idStudent,
            @RequestBody @Valid AddressModel addressModel){
        var result = this.addressService.save(idStudent, addressModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(new EnvelopedData<>(result));
    }
}