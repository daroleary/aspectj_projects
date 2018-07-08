package org.benevity.util;

/**
 * @author Darren O'Leary
 */
//@MappedSuperclass
public abstract class DomainEntity {
//    @Id
//    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
