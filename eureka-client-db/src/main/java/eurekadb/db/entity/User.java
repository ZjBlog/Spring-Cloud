package eurekadb.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author : ZJ
 * @date : 18-11-8 下午1:32
 */
@Entity
public class User extends BaseEntity{

    @Column(length = 32,nullable = false)
    private String name;

    @Column(length = 32,nullable = false)
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
