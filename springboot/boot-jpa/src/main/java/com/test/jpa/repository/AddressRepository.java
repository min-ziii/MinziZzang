package com.test.jpa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.test.jpa.dto.AddressDTO;
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

	//JPQL > @Query
	//1. Address(엔터티)를 대상으로 한다.
	//2. 반드시 엔티티의 별칭(alias)을 만든다.
	//3. 컬럼은 테이블 소속을 표시한다.
	// * > All(X) alias > All(O)
	
	//@Query("select a.name from Address as a")
	
	//오라클 사용하고싶으면 > 추천 XX
	@Query(value="select name from tblAddress", nativeQuery = true)	
	
	List<String> listName();

	//alias가 All을 나타냄
	@Query("select a from Address as a")
	List<Address> listAll();

	@Query("select a from Address as a where a.gender = ?1")
	List<Address> listAll(String gender);

	@Query("select a from Address as a where a.age >= :age")
	List<Address> listAll(@Param(value="age")int age);

	@Query("select a from Address as a where a.gender = :#{#dto.gender} and a.address like '%' || :#{#dto.address} || '%'")
	List<Address> listAll(@Param("dto") AddressDTO dto);

	@Query("select new com.test.jpa.dto.AddressDTO(a.seq, a.name, a.age, a.address, a.gender, year(current_date) - a.age) from Address as a")
	List<AddressDTO> listCustomAll();


	
	
}
