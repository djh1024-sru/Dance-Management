package edu.sru.thangiah.group2.fall2023registrationsystem.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Children;

//repository interface
public interface ChildRepository extends JpaRepository<Children, String> {

	// find distinct children by childID
	@Query("SELECT DISTINCT c FROM Children c WHERE c.childID = :childID")
    List<Children> findDistinctByChildID(@Param("childID") int childID);

	// finds maximum childID to assign new child with new ID
	@Query("SELECT MAX(c.childID) FROM Children c")
	Integer findMaxChildID();
	
	// finds children by parentID
	List<Children> findByParentID(Integer parentID);
	
	// finds children by childID
	List<Children> findByChildID(Integer childID);
	
	// finds children by childID and activityID
	List<Children> findByChildIDAndActivityID(int childID, String activityID);

	// deletes children by childID, activityID, and activityLevel
	void deleteByChildIDAndActivityIDAndActivityLevel(int childID, String activityID, Integer activityLevel);
}