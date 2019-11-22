package com.fresh.freshdiary.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.fresh.freshdiary.model.UserInfo;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long>{
	Boolean existsByUsername(String username);
    UserInfo findByUsername(String username);
}
