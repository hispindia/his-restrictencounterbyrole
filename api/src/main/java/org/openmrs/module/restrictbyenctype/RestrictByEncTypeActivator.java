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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.Activator;

public class RestrictByEncTypeActivator implements Activator {

	private Log log = LogFactory.getLog(this.getClass());

	/**
	 * @see org.openmrs.module.Activator#startup()
	 */
	public void startup() {
		log.info("Starting Permission By Search Module");
	}
	
	/**
	 *  @see org.openmrs.module.Activator#shutdown()
	 */
	public void shutdown() {
		log.info("Shutting down Permission By Search Module");
	}
	
}
