package org.openmrs.module.restrictbyenctype.db;

import java.util.List;
import java.util.Set;

import org.openmrs.Encounter;
import org.openmrs.EncounterType;
import org.openmrs.Patient;
import org.openmrs.Role;
import org.openmrs.module.restrictbyenctype.EncTypeRestriction;

public interface RestrictByRoleDAO {
	
	public void createEncTypeRestriction(EncTypeRestriction EncTypeRestriction);
	
	public void updateEncTypeRestriction(EncTypeRestriction EncTypeRestriction);
	
	public void deleteEncTypeRestriction(EncTypeRestriction EncTypeRestriction);
	
	public EncTypeRestriction getEncTypeRestriction(Integer id);
	
	public List<EncTypeRestriction> getEncTypeRestrictions();

	public List<EncTypeRestriction> getEncTypeRestrictions(Role role);

	public List<Encounter> getEncountersByPatientWithEncTypeRestriction(
			Patient patient, Set<EncounterType> encTypes);

	public EncTypeRestriction getEncTypeRestrictionForRole(Role role);
	
	public EncTypeRestriction getEncTypeRestrictionByDescription(String description);

	public int countEncTypeRestrictions();

	public List<EncTypeRestriction> listlistEncTypeRestriction(int startPos,
			int pageSize);

}
