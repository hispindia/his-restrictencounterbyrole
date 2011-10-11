package org.openmrs.module.restrictbyenctype;

import java.util.List;
import java.util.Set;

import org.openmrs.Encounter;
import org.openmrs.EncounterType;
import org.openmrs.Patient;
import org.openmrs.Role;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface RestrictByRoleService {
	
	public void createEncTypeRestriction(EncTypeRestriction rolePermission);
	
	public void updateEncTypeRestriction(EncTypeRestriction rolePermission);
	
	public void deleteEncTypeRestriction(EncTypeRestriction rolePermission);
	
	public EncTypeRestriction getEncTypeRestriction(Integer id);
	
	public List<EncTypeRestriction> getEncTypeRestrictions();

	public Set<EncTypeRestriction> getCurrentUserRestrictions();
	
	public List<Encounter> getEncountersByPatientWithEncTypeRestriction(Patient patient, Set<EncounterType> encTypes);
	
	public EncTypeRestriction getEncTypeRestrictionForRole(Role role);

	public EncTypeRestriction getEncTypeRestrictionByDescription(String description);

	public int countEncTypeRestrictions();

	public List<EncTypeRestriction> listlistEncTypeRestriction(int startPos,
			int pageSize);
	
}
