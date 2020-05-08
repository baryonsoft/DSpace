/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */
package org.dspace.layout;

import java.util.Set;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.dspace.content.EntityType;
import org.dspace.content.MetadataField;
import org.dspace.core.ReloadableEntity;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "cris_layout_box")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, include = "non-lazy")
public class CrisLayoutBox implements ReloadableEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cris_layout_box_id_seq")
    @SequenceGenerator(name = "cris_layout_box_id_seq", sequenceName = "cris_layout_box_id_seq", allocationSize = 1)
    @Column(name = "id", unique = true, nullable = false, insertable = true, updatable = false)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entity_id")
    private EntityType entitytype;
    @Column(name = "type")
    private String type;
    @Column(name = "collapsed", nullable = false)
    private Boolean collapsed;
    @Column(name = "priority", nullable = false)
    private Integer priority;
    @Column(name = "shortname")
    private String shortname;
    @Column(name = "header")
    private String header;
    @Column(name = "minor", nullable = false)
    private Boolean minor;
    @Column(name = "security")
    private Integer security;
    @Column(name = "style")
    private String style;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "cris_layout_box2securityfield",
        joinColumns = {@JoinColumn(name = "box_id")},
        inverseJoinColumns = {@JoinColumn(name = "authorized_field_id")}
    )
    private Set<MetadataField> metadataFields;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "cris_layout_box2field",
        joinColumns = {@JoinColumn(name = "cris_layout_box_id")},
        inverseJoinColumns = {@JoinColumn(name = "cris_layout_field_id")}
    )
    private Set<CrisLayoutField> layoutFields;

    @Override
    public Integer getID() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EntityType getEntitytype() {
        return entitytype;
    }

    public void setEntitytype(EntityType entitytype) {
        this.entitytype = entitytype;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getCollapsed() {
        return collapsed;
    }

    public void setCollapsed(Boolean collapsed) {
        this.collapsed = collapsed;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public Boolean getMinor() {
        return minor;
    }

    public void setMinor(Boolean minor) {
        this.minor = minor;
    }

    public Integer getSecurity() {
        return security;
    }

    public void setSecurity(Integer security) {
        this.security = security;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public Set<MetadataField> getMetadataFields() {
        return metadataFields;
    }

    public void setMetadataFields(Set<MetadataField> metadataFields) {
        this.metadataFields = metadataFields;
    }

    public Set<CrisLayoutField> getLayoutFields() {
        return layoutFields;
    }

    public void setLayoutFields(Set<CrisLayoutField> layoutFields) {
        this.layoutFields = layoutFields;
    }
}
