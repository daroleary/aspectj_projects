package org.benevity.domain;

import org.benevity.util.DomainEntity;

/**
 * @author Darren O'Leary
 */
//@Entity
//@Table(name="inventoryItems")
public class InventoryItem extends DomainEntity {
//    @OneToOne
    private Product product;
    private int quantityOnHand;

    private InventoryItem() {
    }

    public InventoryItem(Product product) {
        this.product = product;
    }

    public int getQuantityOnHand() {
        return quantityOnHand;
    }

    public void deplete(int quantity) {
        quantityOnHand -= quantity;
    }

    public void replenish(int quantity) {
        quantityOnHand += quantity;
    }
}

