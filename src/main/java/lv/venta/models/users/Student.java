package lv.venta.models.users;

import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lv.venta.models.Application;
import lv.venta.models.Course;
import lv.venta.models.Thesis;

@Table(name = "student_table")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AttributeOverride(name = "id_person", column = @Column( name = "studentId"))
public class Student extends Person{
	
	

	@Column(name = "matriculaNo")
	private String matriculaNo;
	
	@Column(name = "isDebt")
	private boolean Debt;

	private boolean isDebt() {
		return this.Debt;
	}

	@ManyToMany
	@JoinTable(name = "student_debt_table",
	joinColumns = @JoinColumn(name = "course_id"),
	inverseJoinColumns = @JoinColumn(name = "person_id"))
	private Collection<Course> debtCourses = new ArrayList<>();
	
	@OneToMany(mappedBy = "student")
	private Collection<Thesis> thesis;

	@OneToMany(mappedBy = "author")
	@JsonBackReference
	private Collection<Application> applications;


	
	public Student(
			String personName,
		String surname,
		String personalCode,
		User user,
			String matriculaNo) {
	super(personName, surname, personalCode, user);
	this.matriculaNo = matriculaNo;
	}
	
	
	
	public void addDebtCourse(Course course) {
		if(!debtCourses.contains(course)) {
			debtCourses.add(course);
		}
	}


}


	
	

