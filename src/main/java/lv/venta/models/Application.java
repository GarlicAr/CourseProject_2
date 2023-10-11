package lv.venta.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lv.venta.models.users.Academic_personel;
import lv.venta.models.users.Student;

import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Application_table")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ApplId")
    private Long applicationId;

    @Column(name = "Title")
    @NotNull
    private String title;


    @Column(name = "Text")
    @NotNull
    private String text;

    @ManyToOne
    @JoinColumn(name = "studentId")
    @JsonManagedReference
    private Student author;

    @ManyToMany
    @JoinTable(name = "Application_recipients",
    joinColumns = @JoinColumn(name = "ApplId"),
    inverseJoinColumns = @JoinColumn(name = "id_personel"))
    @JsonManagedReference
    private Collection<Academic_personel> recipients = new ArrayList<>();

    public Application(String title, String text, Student author, Collection<Academic_personel> recipients) {

        setTitle(title);
        setText(text);
        setAuthor(author);
        setRecipients(recipients);


    }

    private void AddRecipient(Academic_personel recipient) {

        try {

            if (recipient != null) {

                if (!recipients.contains(recipient)){

                    recipients.add(recipient);

                }

            }

        }catch (Exception e) {

            e.printStackTrace();

        }

    }

    private void RemoveRecipient(Academic_personel recipient) {

        try {

            if (recipient != null) {

                if(recipients.contains(recipient)) {

                    recipients.remove(recipient);

                }



            }

        }catch (Exception e) {

            e.printStackTrace();

        }

    }

}
