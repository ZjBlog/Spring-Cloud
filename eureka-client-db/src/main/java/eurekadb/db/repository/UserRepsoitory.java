package eurekadb.db.repository;

import eurekadb.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author : ZJ
 * @date : 18-11-19 下午5:20
 */
public interface UserRepsoitory  extends JpaRepository<User,String> {

    List<User> findByIdIn(List<String> list);
}
