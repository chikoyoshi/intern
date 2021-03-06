package entity;
// Generated 2017/08/18 11:58:18 by Hibernate Tools 4.3.1


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Staff generated by hbm2java
 */
@Entity
@Table(name="staff"
    ,catalog="test"
)
public class Staff  implements java.io.Serializable {


     private String id;
     private String name;
     private String tel;
     private Date hireDate;
     private Date updateDate;

    public Staff() {
    }

	
    public Staff(String id) {
        this.id = id;
    }
    public Staff(String id, String name, String tel, Date hireDate, Date updateDate) {
       this.id = id;
       this.name = name;
       this.tel = tel;
       this.hireDate = hireDate;
       this.updateDate = updateDate;
    }
   
     @Id 

    
    @Column(name="id", unique=true, nullable=false, length=10)
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    
    @Column(name="name", length=10)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    
    @Column(name="tel", length=11)
    public String getTel() {
        return this.tel;
    }
    
    public void setTel(String tel) {
        this.tel = tel;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="hire_date", length=19)
    public Date getHireDate() {
        return this.hireDate;
    }
    
    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="update_date", length=19)
    public Date getUpdateDate() {
        return this.updateDate;
    }
    
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }




}


