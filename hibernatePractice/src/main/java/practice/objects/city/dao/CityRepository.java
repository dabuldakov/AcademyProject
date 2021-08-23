package practice.objects.city.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import practice.objects.city.City;

public interface CityRepository extends JpaRepository<City, Long> {
}
