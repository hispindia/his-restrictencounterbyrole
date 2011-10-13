/**
 *  Copyright 2011 Society for Health Information Systems Programmes, India (HISP India)
 *
 *  This file is part of RestrictEncounterByRole module.
 *
 *  RestrictEncounterByRole module is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.

 *  RestrictEncounterByRole module is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with RestrictEncounterByRole module.  If not, see <http://www.gnu.org/licenses/>.
 *
 **/

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
