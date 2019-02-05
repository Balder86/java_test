package de.lathspell.test.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@Table(name = "filter")
@NoArgsConstructor
public class Filter {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @OneToOne
    @JoinColumn(name = "routing_id")
    //@ToString.Exclude
    private Routing routing; 
    @Basic(optional = false)
    @Column(name = "block_international", nullable = false)
    private boolean blockInternational = false;
    @Basic(optional = false)
    @Column(name = "block_mobile", nullable = false)
    private boolean blockMobile = false;
    @Basic(optional = false)
    @Column(name = "block_landline", nullable = false)
    private boolean blockLandline = false;
    @Basic(optional = false)
    @Column(name = "block_payphone", nullable = false)
    private boolean blockPayphone = false;
    @Basic(optional = false)
    @Column(name = "block_anonymous", nullable = false)
    private boolean blockAnonymous = false;
    @Basic(optional = false)
    @Column(name = "lists_disabled", nullable = false)
    private boolean listsDisabled = false;
}
