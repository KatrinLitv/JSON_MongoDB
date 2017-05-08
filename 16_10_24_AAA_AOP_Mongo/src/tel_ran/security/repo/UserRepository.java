package tel_ran.security.repo;

import org.springframework.data.repository.CrudRepository;

import tel_ran.security.UserAccount;

public interface UserRepository  extends CrudRepository<UserAccount, String>{

}
