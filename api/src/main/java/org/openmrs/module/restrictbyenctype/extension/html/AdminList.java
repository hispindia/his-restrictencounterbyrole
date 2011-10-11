package org.openmrs.module.restrictbyenctype.extension.html;

import java.util.HashMap;
import java.util.Map;

import org.openmrs.module.Extension;
import org.openmrs.module.web.extension.AdministrationSectionExt;

public class AdminList extends AdministrationSectionExt {

	public Extension.MEDIA_TYPE getMediaType() {
		return Extension.MEDIA_TYPE.html;
	}
	
	public String getTitle() {
		return "restrictbyrole.title";
	}
	
	public Map<String, String> getLinks() {
		Map<String, String> map = new HashMap<String, String>();
		//map.put("module/restrictbyenctype/restriction.list", "restrictbyenctype.manage.title");
		map.put("module/restrictbyenctype/restr.list", "restrictbyenctype.manage.title");
		return map;
	}
	
}
