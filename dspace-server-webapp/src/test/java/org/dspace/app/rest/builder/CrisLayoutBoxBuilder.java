/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */
package org.dspace.app.rest.builder;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.dspace.authorize.AuthorizeException;
import org.dspace.content.EntityType;
import org.dspace.content.MetadataField;
import org.dspace.core.Context;
import org.dspace.layout.CrisLayoutBox;
import org.dspace.layout.CrisLayoutField;
import org.dspace.layout.LayoutSecurity;
import org.dspace.layout.service.CrisLayoutBoxService;

public class CrisLayoutBoxBuilder extends AbstractBuilder<CrisLayoutBox, CrisLayoutBoxService> {

    private static final Logger log = Logger.getLogger(CrisLayoutBoxBuilder.class);

    private CrisLayoutBox box;

    protected CrisLayoutBoxBuilder(Context context) {
        super(context);
    }

    @Override
    public void cleanup() throws Exception {
        delete(box);
    }

    public static CrisLayoutBoxBuilder createBuilder(Context context, EntityType eType, boolean collapsed, int priority,
            boolean minor) {
        CrisLayoutBoxBuilder builder = new CrisLayoutBoxBuilder(context);
        return builder.create(context, eType, collapsed, priority, minor);
    }

    private CrisLayoutBoxBuilder create(Context context, EntityType eType, boolean collapsed, int priority,
            boolean minor) {
        try {
            this.context = context;
            this.box = getService().create(context, eType, collapsed, priority, minor);
        } catch (Exception e) {
            log.error("Error in CrisLayoutTabBuilder.create(..), error: ", e);
        }
        return this;
    }

    @Override
    public CrisLayoutBox build() throws SQLException, AuthorizeException {
        try {
            getService().update(context, box);
            context.dispatchEvents();

            indexingService.commit();
        } catch (Exception e) {
            log.error("Error in CrisLayoutBoxBuilder.build(), error: ", e);
        }
        return box;
    }

    @Override
    public void delete(CrisLayoutBox dso) throws Exception {
        try (Context c = new Context()) {
            c.turnOffAuthorisationSystem();
            CrisLayoutBox attachedBox = c.reloadEntity(box);
            if (attachedBox != null) {
                getService().delete(c, attachedBox);
            }
            c.complete();
        }

        indexingService.commit();
    }

    @Override
    protected CrisLayoutBoxService getService() {
        return crisLayoutBoxService;
    }

    public CrisLayoutBoxBuilder withShortname(String shortname) {
        this.box.setShortname(shortname);
        return this;
    }

    public CrisLayoutBoxBuilder withHeader(String header) {
        this.box.setHeader(header);
        return this;
    }

    public CrisLayoutBoxBuilder withSecurity(LayoutSecurity security) {
        this.box.setSecurity(security);
        return this;
    }

    public CrisLayoutBoxBuilder withStyle(String style) {
        this.box.setStyle(style);
        return this;
    }

    public CrisLayoutBoxBuilder withFields(Set<CrisLayoutField> fields) {
        this.box.setLayoutFields(fields);
        return this;
    }

    public CrisLayoutBoxBuilder addField(CrisLayoutField field) {
        if (this.box.getLayoutFields() == null) {
            this.box.setLayoutFields(new HashSet<>());
        }
        this.box.getLayoutFields().add(field);
        return this;
    }

    public CrisLayoutBoxBuilder withMetadataField(Set<MetadataField> fields) {
        this.box.setMetadataFields(fields);
        return this;
    }

    public CrisLayoutBoxBuilder addMetadataField(MetadataField field) {
        if (this.box.getMetadataFields() == null) {
            this.box.setMetadataFields(new HashSet<>());
        }
        this.box.getMetadataFields().add(field);
        return this;
    }

    public CrisLayoutBoxBuilder withPriority(int priority) {
        this.box.setPriority(priority);
        return this;
    }
}
