/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */
package org.dspace.app.rest.model.hateoas;

import org.dspace.app.rest.model.CrisLayoutFieldRest;
import org.dspace.app.rest.model.hateoas.annotations.RelNameDSpaceResource;
import org.dspace.app.rest.utils.Utils;

/**
 * CrisLayoutField Rest HAL Resource. The HAL Resource wraps the REST Resource
 * adding support for the links and embedded resources
 * 
 * @author Danilo Di Nuzzo (danilo.dinuzzo at 4science.it)
 *
 */
@RelNameDSpaceResource(CrisLayoutFieldRest.NAME)
public class CrisLayoutFieldResource extends DSpaceResource<CrisLayoutFieldRest> {

    public CrisLayoutFieldResource(CrisLayoutFieldRest data, Utils utils) {
        super(data, utils);
    }

}
