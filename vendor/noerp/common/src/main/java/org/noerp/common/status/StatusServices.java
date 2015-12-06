/*******************************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *******************************************************************************/
package org.noerp.common.status;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.noerp.base.util.Debug;
import org.noerp.base.util.UtilProperties;
import org.noerp.base.util.UtilValidate;

import static org.noerp.base.util.UtilGenerics.checkList;

import org.noerp.base.util.UtilMisc;
import org.noerp.entity.Delegator;
import org.noerp.entity.GenericEntityException;
import org.noerp.entity.GenericValue;
import org.noerp.entity.util.EntityQuery;
import org.noerp.service.DispatchContext;
import org.noerp.service.ServiceUtil;

/**
 * StatusServices
 */
public class StatusServices {

    public static final String module = StatusServices.class.getName();
    public static final String resource = "CommonUiLabels";

    public static Map<String, Object> getStatusItems(DispatchContext ctx, Map<String, ?> context) {
        Delegator delegator = ctx.getDelegator();
        List<String> statusTypes = checkList(context.get("statusTypeIds"), String.class);
        Locale locale = (Locale) context.get("locale");
        if (UtilValidate.isEmpty(statusTypes)) {
            return ServiceUtil.returnError(UtilProperties.getMessage(resource, "CommonStatusMandatory", locale));
        }

        List<GenericValue> statusItems = new LinkedList<GenericValue>();
        for (String statusTypeId: statusTypes) {
            try {
                List<GenericValue> myStatusItems = EntityQuery.use(delegator)
                                                              .from("StatusItem")
                                                              .where("statusTypeId", statusTypeId)
                                                              .orderBy("sequenceId")
                                                              .cache(true)
                                                              .queryList();
                statusItems.addAll(myStatusItems);
            } catch (GenericEntityException e) {
                Debug.logError(e, module);
            }
        }
        Map<String, Object> ret =  new LinkedHashMap<String, Object>();
        ret.put("statusItems",statusItems);
        return ret;
    }

    public static Map<String, Object> getStatusValidChangeToDetails(DispatchContext ctx, Map<String, ?> context) {
        Delegator delegator = ctx.getDelegator();
        List<GenericValue> statusValidChangeToDetails = null;
        String statusId = (String) context.get("statusId");
        try {
            statusValidChangeToDetails = EntityQuery.use(delegator)
                                                    .from("StatusValidChangeToDetail")
                                                    .where("statusId", statusId)
                                                    .orderBy("sequenceId")
                                                    .cache(true)
                                                    .queryList();
        } catch (GenericEntityException e) {
            Debug.logError(e, module);
        }
        Map<String, Object> ret = ServiceUtil.returnSuccess();
        if (statusValidChangeToDetails != null) {
            ret.put("statusValidChangeToDetails", statusValidChangeToDetails);
        }
        return ret;
    }
}
