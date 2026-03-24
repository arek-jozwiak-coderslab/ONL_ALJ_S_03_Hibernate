package pl.coderslab.student;

import org.springframework.stereotype.Service;
import pl.coderslab.student.dto.StudentDTO;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public StudentDTO getStudentById(Long id) {
        return studentRepository.findById(id)
                .map(student -> new StudentDTO(student.getId(), student.getFirstName(),
                        student.getLastName(), student.getIndexNumber(), student.getAverageGrade()))
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }
}
