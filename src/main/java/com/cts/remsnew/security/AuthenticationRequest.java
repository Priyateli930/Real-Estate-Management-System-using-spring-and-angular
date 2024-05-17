package com.remsnew.repository;



import java.util.Optional;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.remsnew.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer>{
	public Page<Users> findByCity(String city,Pageable pageable);
	public Users findByEmail(String email);
	public Optional<Users> findByUserid(int userid);
}
