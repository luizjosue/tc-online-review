/**
 * Copyright (C) 2005 TopCoder Inc., All Rights Reserved.
 */
package com.topcoder.onlinereview.migration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import com.topcoder.onlinereview.migration.dto.BaseDTO;
import com.topcoder.onlinereview.migration.dto.newschema.project.ProjectNew;
import com.topcoder.onlinereview.migration.dto.newschema.resource.Resource;
import com.topcoder.onlinereview.migration.dto.newschema.resource.ResourceInfo;
import com.topcoder.onlinereview.migration.dto.oldschema.ScorecardOld;
import com.topcoder.onlinereview.migration.dto.oldschema.deliverable.SubmissionOld;
import com.topcoder.util.log.Log;
import com.topcoder.util.log.LogFactory;

/**
 * The MapUtil which is used to map old id to new id.
 *
 * @author brain_cn
 * @version 1.0
 */
public class MapUtil {
    static final String CREATE_USER = "Converter";
    static final String MODIFY_USER = "Converter";
	static final File propertieFile = new File("test_files", "template_id.properties");
	static final File projectPropertieFile = new File("test_files", "project_id.properties");
	private static Properties templateIdProperties = null;

    protected static Log log = LogFactory.getLog();

    static List getMigratedProjectIds() throws Exception {
    	List list = new ArrayList();
    	Properties properties = new Properties();
        if (MapUtil.projectPropertieFile.exists()) {
        	InputStream input = new FileInputStream(MapUtil.projectPropertieFile);
        	properties.load(input);
        	input.close();
        }
        list.addAll(properties.keySet());
    	return list;
    }

    static void storeMigratedProjectId(int oldId, int newId) throws Exception {
    	Properties properties = new Properties();
        if (MapUtil.projectPropertieFile.exists()) {
        	InputStream input = new FileInputStream(MapUtil.projectPropertieFile);
        	properties.load(input);
        	input.close();
        }
        properties.setProperty(String.valueOf(oldId), String.valueOf(newId));

        OutputStream out = new FileOutputStream(MapUtil.projectPropertieFile);
        properties.store(out, "project_id(old) project_id(new)");
        out.close();
    }

    /**
     * Set base dto info.
     * 
     * @param base the base dto
     */
    static void setBaseDTO(BaseDTO base) {
    	base.setCreateUser(CREATE_USER);
    	base.setCreateDate(new Date());
    	base.setModifyUser(MODIFY_USER);
    	base.setModifyDate(new Date());
    }

    /**
     * Return the resource id with given external reference id.
     * 
     * @param project the project
     * @param loginId the login id
     * @return the resource id if found, 0 otherwise
     */
    static int getResourceId(ProjectNew project, int loginId) {
    	for (Iterator iter = project.getResourceInfos().iterator(); iter.hasNext();) {
    		ResourceInfo resourceInfo = (ResourceInfo) iter.next();
    		if (resourceInfo.getResourceInfoTypeId() == ResourceInfo.EXTERNAL_REFERENCE_ID) {
    			if (resourceInfo.getValue().equals(String.valueOf(loginId))) {
    				return resourceInfo.getResourceId();
    			}
    		}
    	}
    	return 0;
    }

    /**
     * Return the resource id with given external reference id.
     * 
     * @param project the project
     * @param loginId the login id
     * @return the resource id if found, 0 otherwise
     */
    static int getFinalReviewerId(ProjectNew project) {
    	for (Iterator iter = project.getResources().iterator(); iter.hasNext();) {
    		Resource instance = (Resource) iter.next();
    		if (instance.getResourceRoleId() == 9) {
    			// Final Reviewer is 9
    			return instance.getResourceId();
    		}
    	}
    	return 0;
    }

    /**
     * Return the resource id with given external reference id.
     * 
     * @param instance the SubmissionOld
     * @return the resource id if found, 0 otherwise
     */
    static float getAggregatorScores(SubmissionOld instance) {
		float totalScores = 0;
		int count = 0;
		for (Iterator scoreIter = instance.getScorecards().iterator(); scoreIter.hasNext();) {
    		ScorecardOld scorecard = (ScorecardOld) scoreIter.next();
    		if (scorecard.getScorecardType() == 2) {
    			// type 2 is review
    			totalScores += scorecard.getScore();
    			count++;
    		}
		}
		if (count > 0) {
			return totalScores / count;
		} else {
			return 0;
		}
    }

	/**
	 * Convert to resource role with given role id and resp id.
	 * 
	 * @param roleId the role id
	 * @param respId the resp id
	 * @return the resource role id
	 */
	public static int getResourceRole(int roleId, int respId) {
		switch (roleId) {
		case 1: // Designer/Developer
			return 1; // Submitter
		case 2: // Primary Screener
			return 2; // Primary Screener
		case 3: // Reviewer
			if (respId == 1) {
				// stress
				return 7; // Stress Reviewer
			} else if (respId == 2) {
				// Failure
				return 6; // Failure reviewer
			} else if (respId == 3){
				// Accuracy
				return 5; // Accuracy Reviewer
			} else {
				// Others are design reviewer
				return 4; // Reviewer
			}
		case 4: // Aggregator
			return 8; // Aggregator
		case 5: // Final Reviewer
			return 9; // Final Reviewer
		case 6: // Product Manager
			return 13; // Manager
		case 7: // Removed
			// TODO 
		}
		return 1; // Submitter
	}

	/**
	 * Check if given role id is belong to reviewer
	 * 
	 * @param roleId the roleId
	 * @return true if it's reviewer, false otherwise
	 */
	public static boolean isReviewer(int roleId) {
		switch (roleId) {
		case 2: // Primary Screener
			return true;
		case 3: // Reviewer
			return true;
		case 4: // Aggregator
			return true;
		case 5: // Final Reviewer
			return true;
		}
		return false;
	}

	/**
	 * Check if given role id is belong to submitter
	 * 
	 * @param roleId the roleId
	 * @return true if it's submitter, false otherwise
	 */
	public static boolean isSubmitter(int roleId) {
		return roleId == 1;
	}
	
	/**
	 * Map project status id.
	 * 
	 * @param statusId the old status id
	 * @return the new status id
	 */
	public static int getProjectStatusId(int statusId) {
		switch (statusId) {
		case 1: // In Progress
			return ProjectNew.PROJECT_STATUS_ACTIVE_ID;
		case 2: // Terminated
			return ProjectNew.PROJECT_STATUS_DELETED_ID;
		case 3: // Pending Start
			return ProjectNew.PROJECT_STATUS_ACTIVE_ID;
		case 4: // Completed
			return ProjectNew.PROJECT_STATUS_COMPLETED_ID;
		case 5: // All submissions failed screening
			return 5; // 'Cancelled - Failed Screening'
		case 6: // All submissions didn't reach minimum review score
			return 4; // 'Cancelled - Failed Review'
		case 7: // Remove for Reposting
			return 6; // 'Cancelled - Zero Submissions'
		}
		throw new IllegalArgumentException("invalid project status id, id: " + statusId);
	}

	/**
	 * Map project category id.
	 * 1 maps to 'Component Design' and 2 maps to 'Component Development'
	 * 
	 * @param typeId the old status id
	 * @return the new status id
	 */
	public static int getProjectCategoryId(int typeId) {
		switch (typeId) {
		case 1: // Design
			return ProjectNew.PROJECT_CATEGORY_DESIGN_ID;
		case 2: // Development
			return ProjectNew.PROJECT_CATEGORY_DEVELOPMENT_ID;
		}
		throw new IllegalArgumentException("invalid project type id, id: " + typeId);
	}
	
	/**
	 * Return response severity id with given response id.
	 * 
	 * @param responseId the response id
	 * @return the response severity
	 */
	public static int getResponseSeverity(int responseId) {
		if (responseId < 13) {
			return 1;
		} else if (responseId < 15) {
			// 13	Checkstyle has produced the following warnings
			// 14	Your submission contains personal information
			return 2;
		} else { // >= 15			
			return 3; // 15	Your submission has passed the auto screening process.
		}
	}

	public static int getScorecardQuestionId(int templateVId) {
		if (templateVId == 16) {
			return 19;
		}
		if (templateVId == 49) {
			return 50;
		}
		if (templateVId == 51) {
			return 52;
		}
		return templateVId;
	}

	public static int getScorecardId(int templateId) throws Exception {
		if (templateIdProperties == null) {
			templateIdProperties = new Properties();
	        if (propertieFile.exists()) {
	        	InputStream input = new FileInputStream(MapUtil.propertieFile);
	        	templateIdProperties.load(input);
	        	input.close();
	        }
		}
		if (templateIdProperties == null) {
			return templateId;
		}
		String scorecardId = templateIdProperties.getProperty(String.valueOf(templateId));
		if (scorecardId != null) {
			return Integer.parseInt(scorecardId);
		}
		return 1;
	}
}
