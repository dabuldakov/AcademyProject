package practice.objects.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import practice.objects.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    public User findUserByName(String name);
}
