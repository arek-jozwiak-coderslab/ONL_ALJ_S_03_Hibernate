package pl.coderslab.student;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.student.dto.StudentDTO;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

//    private final StudentRepository studentRepository;
//
//    public StudentControler(StudentRepository studentRepository) {
//        this.studentRepository = studentRepository;
//    }

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
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
