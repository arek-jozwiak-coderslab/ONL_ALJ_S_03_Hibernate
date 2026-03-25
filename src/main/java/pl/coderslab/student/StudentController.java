package pl.coderslab.student;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.student.dto.StudentDTO;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/students")
public class StudentController {

//    private final StudentRepository studentRepository;
//
//    public StudentControler(StudentRepository studentRepository) {
//        this.studentRepository = studentRepository;
//    }

    private final StudentService studentService;
    private final Validator validator;

    public StudentController(StudentService studentService, Validator validator) {
        this.studentService = studentService;
        this.validator = validator;
    }

    @GetMapping("/test-validation")
    @ResponseBody
    public String testValidation() {

        Student student = new Student();
        student.setFirstName("A");
        Set<ConstraintViolation<Student>> constraintViolations = validator.validate(student);
        for (ConstraintViolation<Student> constraintViolation : constraintViolations) {
            System.out.println(constraintViolation.getMessage());
            System.out.println(constraintViolation.getPropertyPath());
        }


        return "Validation test";
    }

    @GetMapping("/students/{id}")
    public StudentDTO getStudentById(Long id) {
        return studentService.getStudentById(id);
    }

    @GetMapping
    public List<StudentDTO> getAllStudents() {
        return studentService.getAllStudents().stream()
                .map(student ->
                        {
                            StudentDTO studentDTO = new StudentDTO();
                            studentDTO.setId(student.getId());
                            studentDTO.setFirstName(student.getFirstName());
                            studentDTO.setLastName(student.getLastName());
                            studentDTO.setIndexNumber(student.getIndexNumber());
                            studentDTO.setAverageGrade(student.getAverageGrade());
                            return studentDTO;
                        }
                ).toList();
    }
}
