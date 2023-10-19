package lv.venta;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import lv.venta.models.Application;
import lv.venta.repos.IRepoApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

import lv.venta.enums.Degree;
import lv.venta.models.Comments;
import lv.venta.models.Course;
import lv.venta.models.Thesis;
import lv.venta.models.users.Academic_personel;
import lv.venta.models.users.Student;
import lv.venta.models.users.User;
import lv.venta.repos.IRepoComments;
import lv.venta.repos.IRepoCourse;
import lv.venta.repos.IRepoThesis;
import lv.venta.repos.users.IRepoAcademicPersonel;
import lv.venta.repos.users.IRepoPerson;
import lv.venta.repos.users.IRepoStudent;
import lv.venta.repos.users.IRepoUser;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class ProgInzCourseProjectApplication implements WebMvcConfigurer {

	private final LocaleChangeInterceptor localeChangeInterceptor;

	public ProgInzCourseProjectApplication(LocaleChangeInterceptor localeChangeInterceptor) {
		this.localeChangeInterceptor = localeChangeInterceptor;
	}

	@Override
	public void addInterceptors(InterceptorRegistry interceptorRegistry) {
		interceptorRegistry.addInterceptor(localeChangeInterceptor);
	}

	public static void main(String[] args) {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasenames("lang/messages");
		messageSource.setDefaultEncoding("UTF-8");
		SpringApplication.run(ProgInzCourseProjectApplication.class, args);
	}
	
	//@Bean //Calls function when system runs
	public CommandLineRunner testModel(
			IRepoCourse courseRep,
			IRepoThesis thesisRep,
			IRepoAcademicPersonel academicPersonelRep,
			IRepoPerson personRep,
			IRepoStudent studentRep,
			IRepoUser userRep,
			IRepoComments commentsRep,
			IRepoApplication applicationRep) {
		
		
		return new CommandLineRunner() {
			
			@Override
			public void run(String... args) throws Exception {

				Collection<Academic_personel> recipients = new ArrayList<>();



				Student student1 = new Student("Janis", "Ozolins", "020232-22444", null ,"123REDs");
				Academic_personel personel1 = new Academic_personel("Karina", "Skirmante", "111101-11223", null, Degree.master);


				studentRep.save(student1);
				academicPersonelRep.save(personel1);



				recipients.add(personel1);


				Application app1 = new Application("Iesniegums", "Sveiki, mans iesniegums ir loti radoss, es velejos uzzinat par iespejam...",student1 ,recipients);

				applicationRep.save(app1);


			}
		};
	}
}


