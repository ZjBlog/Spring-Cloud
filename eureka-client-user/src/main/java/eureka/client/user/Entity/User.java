package eureka.client.user.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

/**
 * @author : ZJ
 * @date : 18-11-8 下午1:32
 */
@Data
public class User{

    private String name;

    private String password;

    private String id;

    private Date createdDate;

    private Date lastModifiedDate;
}
