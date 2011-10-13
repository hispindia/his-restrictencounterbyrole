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

package org.openmrs.module.restrictbyenctype.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Encounter;
import org.openmrs.EncounterType;
import org.openmrs.Patient;
import org.openmrs.Role;
import org.openmrs.api.context.Context;
import org.openmrs.module.restrictbyenctype.EncTypeRestriction;
import org.openmrs.module.restrictbyenctype.RestrictByRoleService;
import org.openmrs.module.restrictbyenctype.db.RestrictByRoleDAO;

public class RestrictByRoleServiceImpl implements RestrictByRoleService {

	private final Log log = LogFactory.getLog(getClass());
	private RestrictByRoleDAO dao;
	
	public void createEncTypeRestriction(EncTypeRestriction EncTypeRestriction) {
		getDao().createEncTypeRestriction(EncTypeRestriction);
	}

	public void deleteEncTypeRestriction(EncTypeRestriction EncTypeRestriction) {
		getDao().deleteEncTypeRestriction(EncTypeRestriction);
	}

	public EncTypeRestriction getEncTypeRestriction(Integer id) {
		return getDao().getEncTypeRestriction(id);
	}

	public List<EncTypeRestriction> getEncTypeRestrictions() {
		return getDao().getEncTypeRestrictions();
	}
	
	public List<EncTypeRestriction> getEncTypeRestrictions(Role role) {
		return getDao().getEncTypeRestrictions(role);
	}

	public void updateEncTypeRestriction(EncTypeRestriction EncTypeRestriction) {
		getDao().updateEncTypeRestriction(EncTypeRestriction);
	}
		
	public Set<EncTypeRestriction> getCurrentUserRestrictions() {
		if (!Context.isAuthenticated())
			return null;
		Set<Role> roles = Context.getAuthenticatedUser().getAllRoles();
		Set<EncTypeRestriction> ret = new HashSet<EncTypeRestriction>();
		for (Role role : roles)
			ret.addAll(getEncTypeRestrictions(role));
		log.debug("current user has " + ret.size() + " restrictions");
		return ret;
	}

	public RestrictByRoleDAO getDao() {
		return dao;
	}

	public void setDao(RestrictByRoleDAO dao) {
		this.dao = dao;
	}

	@Override
	public List<Encounter> getEncountersByPatientWithEncTypeRestriction(
			Patient patient, Set<EncounterType> encTypes) {
		return getDao().getEncountersByPatientWithEncTypeRestriction(patient, encTypes);
	}

	@Override
	public EncTypeRestriction getEncTypeRestrictionForRole(Role role) {
		return getDao().getEncTypeRestrictionForRole(role);
	}

	@Override
	public EncTypeRestriction getEncTypeRestrictionByDescription(String description) {
		return getDao().getEncTypeRestrictionByDescription(description);
	}

	@Override
	public int countEncTypeRestrictions() {
		return getDao().countEncTypeRestrictions();
	}

	@Override
	public List<EncTypeRestriction> listlistEncTypeRestriction(int startPos,
			int pageSize) {
		return getDao().listlistEncTypeRestriction(startPos, pageSize);
	}

}
