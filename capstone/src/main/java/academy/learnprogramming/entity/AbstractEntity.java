package academy.learnprogramming.entity;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author Jonathan Vinh
 */
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }
}
