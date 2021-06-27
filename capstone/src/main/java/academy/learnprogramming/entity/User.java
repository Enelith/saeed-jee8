package academy.learnprogramming.entity;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
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
/*
@NamedQueries({
	@NamedQuery(name = User.FIND_ALL_USERS, query = "SELECT u FROM User u ORDER BY u.fullName"),
	@NamedQuery(name = User.FIND_BY_EMAIL, query = "SELECT u FROM User u WHERE u.email = :email"),
	@NamedQuery(name = User.FIND_BY_PASSWORD, query = "SELECT u FROM User u WHERE u.password = :password")
})
*/
/**
 * Acceptable avec Java EE8 car @NamedQuery est devenu repeatable
 */
@NamedQuery(name = User.FIND_ALL_USERS, query = "SELECT u FROM User u ORDER BY u.fullName")
@NamedQuery(name = User.FIND_BY_EMAIL, query = "SELECT u FROM User u WHERE u.email = :email")
@NamedQuery(name = User.FIND_BY_PASSWORD, query = "SELECT u FROM User u WHERE u.password = :password")
public class User extends AbstractEntity {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public static final String FIND_ALL_USERS = "User.findAllUsers";
    public static final String FIND_BY_EMAIL = "User.findByEmail";
    public static final String FIND_BY_PASSWORD = "User.findByPassword";

    @NotNull(message = "Full name must be set")
    @Pattern(regexp = "", message = "Full name must be in alphabets")
    private String fullName; // 123456

    @NotNull(message = "Email must be set")
    @Email(message = "Email must be a valid one (ex : user@domain.com)")
    private String email;

    @NotNull(message = "Password cannot be empty")
    @Size(min = 8, message = "Password must have at least 8 characters")
    // @Pattern(regexp = "", message = "Password must be a combination of alphabets,
    // numbers and special characters")
    private String password;
    
    private String salt;

    
    
    public String getFullName() {
	return fullName;
    }

    public void setFullName(String fullName) {
	this.fullName = fullName;
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

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
