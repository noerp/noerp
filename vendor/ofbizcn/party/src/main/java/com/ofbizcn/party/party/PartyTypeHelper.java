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

package com.ofbizcn.party.party;

import org.noerp.base.util.Debug;
import org.noerp.base.util.UtilMisc;
import org.noerp.base.util.UtilValidate;
import org.noerp.entity.Delegator;
import org.noerp.entity.GenericEntityException;
import org.noerp.entity.GenericValue;
import org.noerp.entity.util.EntityQuery;
import org.noerp.entity.util.EntityTypeUtil;

/**
 * PartyTypeHelper
 */
public class PartyTypeHelper {

    public static final String module = PartyTypeHelper.class.getName();

    /** Check if a related party is of the right party type (PERSON or PARTY_GROUP)
     *@param delegator needed Delegator
     *@param partyId a a valid Party Id string
     *@param checkedPartyType a string in {PERSON, PARTY_GROUP}
     *@return Boolean, false in case of error
     */
    public static Boolean checkPartyType(Delegator delegator, String partyId, String checkedPartyType) {
        GenericValue party = null;
        GenericValue partyType = null;
        GenericValue checkedTypeOfParty = null;
        try {
            party = EntityQuery.use(delegator).from("Party").where("partyId", partyId).queryOne();
            if (UtilValidate.isNotEmpty(party)) {
                partyType = party.getRelatedOne("PartyType", true);
                checkedTypeOfParty = EntityQuery.use(delegator).from("PartyType").where("partyTypeId", checkedPartyType).cache().queryOne();
            } else {
                return false;
            }
        } catch (GenericEntityException e) {
            Debug.logWarning(e, module);
        }
        return EntityTypeUtil.isType(partyType, checkedTypeOfParty);
    }
}
