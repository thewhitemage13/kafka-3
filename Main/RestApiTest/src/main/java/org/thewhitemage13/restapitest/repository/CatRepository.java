package org.thewhitemage13.restapitest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.thewhitemage13.restapitest.entity.Cat;

@Repository
public interface CatRepository extends JpaRepository<Cat, Integer> {
}
