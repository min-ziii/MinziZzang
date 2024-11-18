package com.test.jpa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.test.jpa.entity.Address;
import com.test.jpa.entity.AddressNameAgeMapping;

//DAO 역할 > 데이터베이스 조작(SQL X) > 객체(Address Entity) 조작
//- JpaRepository<취급할 엔티티, 엔티티의 @ID 기본키 자료형>
public interface AddressRepository extends JpaRepository<Address, Long> {

	//추상메서드!
	Optional<Address> findByName(String name);

	Optional<Address> findByAddress(String address);

	Optional<Address> findByGender(String gender);

	Address findFirst3ByAge(int age);

	Address findByNameAndGender(String name, String gender);

	List<Address> findByGenderAndAge(String gender, int age);

	List<Address> findByGenderOrAge(String gender, int age);

	List<Address> findByAgeGreaterThan(int age);

	List<Address> findByAgeLessThan(int age);

	List<Address> findByAgeLessThanEqual(int age);

	List<Address> findByAgeBetween(int min, int max);

	List<Address> findByAddressIsNull();

	List<Address> findByAddressIsNotNull();

	List<Address> findByAgeIn(List<Integer> age);

	List<Address> findByAgeNotIn(List<Integer> age);

	List<Address> findByAddressStartingWith(String address);

	List<Address> findByAddressEndingWith(String address);

	List<Address> findByAddressContains(String address);

	List<Address> findByAddressLike(String address);

	List<Address> findByAddressNotLike(String address);

	Optional<Address> findByNameIs(String name);

	Optional<Address> findByNameEquals(String name);

	List<Address> findAllByOrderByNameAsc();

	List<Address> findAllByGenderOrderByNameAsc(String gender);

	List<Address> findAllByGenderOrderByNameDesc(String gender);

	List<Address> findAllByGenderOrderByAgeAscNameDesc(String genderagename);

	List<Address> findByGender(String string, Sort age);

	List<AddressNameAgeMapping> findAllByGender(String gender);


	
	
}
