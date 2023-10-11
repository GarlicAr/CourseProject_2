package lv.venta.models.users;

import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lv.venta.enums.Degree;
import lv.venta.models.Application;
import lv.venta.models.Comments;
import lv.venta.models.Thesis;

@Setter
@Getter
@Entity
@Table(name="personel_table")
@NoArgsConstructor
@AttributeOverride(name = "id_person", column = @Column(name = "id_personel"))
public class Academic_personel extends Person{
	
	
	@Column(name="degree")
	private Degree degree;
	
	@OneToMany(mappedBy = "personel")
	private Collection<Thesis> thesis;
	
	@ManyToMany(mappedBy = "reviewers")
	private Collection<Thesis> ThesisForReview = new ArrayList<>();

//	@ManyToMany(mappedBy = "recipients")
//	@JsonBackReference
//	private Collection<Application> applications = new ArrayList<>();

	@OneToMany(mappedBy = "personel")
	private Collection<Comments> comments;
	
	public Academic_personel(@NotNull @Size(min = 3, max = 15) @Pattern(regexp = "[A-Z]{1}[a-z\\ ]+") String name,
		@NotNull @Size(min = 3, max = 15) @Pattern(regexp = "[A-Z]{1}[a-z\\ ]+") String surname,
		@Size(min = 12, max = 12) @NotNull @Pattern(regexp = "[0-9]{6}-[0-9]{5}\\ ]+", message = "Neatbilstoss personas kods") String personalCode,
		User user, Degree degree) {
	super(name, surname, personalCode, user);
	this.degree = degree;
	}
	
	public void addThesisForReview(Thesis thesis) {
		if(!ThesisForReview.contains(thesis)) {
			ThesisForReview.add(thesis);
		}
	}


}
	


