package mitrais.mahdi.learning4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mitrais.mahdi.learning4.model.*;

@Repository("unitRepository")
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findbyUnitId(int unitId);
}