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

package org.openmrs.module.restrictbyenctype;

import org.openmrs.api.APIAuthenticationException;

public class RestrictByRoleAuthorizationException extends APIAuthenticationException {

	private static final long serialVersionUID = 1L;

	public RestrictByRoleAuthorizationException() {
		super();
	}
	
	public RestrictByRoleAuthorizationException(String message) {
		super(message);
	}
	
	public RestrictByRoleAuthorizationException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public RestrictByRoleAuthorizationException(Throwable cause) {
		super(cause);
	}
	
}
