package academy.learnprogramming.beans;

import java.io.Serializable;

import javax.inject.Inject;

import academy.learnprogramming.annotations.ServiceMan;
import academy.learnprogramming.annotations.Web;
import academy.learnprogramming.interfaces.Salute;

@Web
public class QualifierWithValueBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Inject
    @ServiceMan(value = ServiceMan.ServiceType.POLICE)
    private Salute policeSalute;

    @Inject
    @ServiceMan(value = ServiceMan.ServiceType.SOLDIER)
    private Salute soldierSalute;

    private String police;
    private String soldier;
    private String name;

    public void policeSalutation() {
	police = policeSalute.salute(name);
    }

    public void solidierSalutation() {
	soldier = soldierSalute.salute(name);
    }

    public String getPolice() {
	return police;
    }

    public void setPolice(String police) {
	this.police = police;
    }

    public String getSoldier() {
	return soldier;
    }

    public void setSoldier(String soldier) {
	this.soldier = soldier;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

}
