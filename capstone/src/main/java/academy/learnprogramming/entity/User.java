package academy.learnprogramming.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author Jonathan Vinh
 */
@Entity
@Table(name = "TodoUser")
public class User extends AbstractEntity {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @NotNull(message = "Full name must be set")
    @Pattern(regexp = "", message = "Full name must be in alphabets")
    private String fullname; // 123456

    @NotNull(message = "Email must be set")
    @Email(message = "Email must be a valid one (ex : user@domain.com)")
    private String email;

    @NotNull(message = "Password cannot be empty")
    @Size(min = 8, message = "Password must have at least 8 characters")
    // @Pattern(regexp = "", message = "Password must be a combination of alphabets,
    // numbers and special characters")
    private String password;

    public String getFullname() {
	return fullname;
    }

    public void setFullname(String fullname) {
	this.fullname = fullname;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }
}
