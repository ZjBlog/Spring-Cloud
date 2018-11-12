package eurekadb.db.entity;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * @author : ZJ
 * @date : 18-11-8 下午1:43
 */
@MappedSuperclass
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = -2963618002145345835L;

    @Id
    @Column(length = 32)
    @GeneratedValue(generator = "sys-uuid")
    @GenericGenerator(name = "sys-uuid", strategy = "uuid")
    private String id;

    @CreatedDate
    @Column(columnDefinition = "datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
    private Date createdDate;

    @LastModifiedDate
    @Column(columnDefinition = "datetime DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间'")
    private Date lastModifiedDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
