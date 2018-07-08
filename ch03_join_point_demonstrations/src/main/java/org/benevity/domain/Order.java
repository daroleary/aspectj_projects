package org.benevity.domain;

import org.benevity.util.DomainEntity;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Darren O'Leary
 */
//@Entity
//@Table(name="orders")
public class Order extends DomainEntity {
//    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER,
//            mappedBy="order")
    private Collection<LineItem> lineItems = new ArrayList<>();
    private boolean placed;

    public void addItem(Product product, int quantity) {
        if (isPlaced()) {
            throw new IllegalStateException(
                    "Once placed, the order may not be modified");
        }
        LineItem lineItem = getItemFor(product);

        if (lineItem != null) {
            lineItem.setQuantity(lineItem.getQuantity() + quantity);
        } else {
            lineItem = new LineItem(this, product, 1);
            lineItem.setQuantity(quantity);
            lineItems.add(lineItem);
        }
    }

    public void removeItem(Product product, int quantity) {
        if (isPlaced()) {
            throw new IllegalStateException(
                    "Once placed, the order may not be modified");
        }

        LineItem lineItem = getItemFor(product);

        if (lineItem == null) {
            throw new IllegalArgumentException(
                    "Failed to get line item");
        }
        int currentQuantity = lineItem.getQuantity();
        if (currentQuantity < quantity) {
            throw new IllegalArgumentException(
                    "Removing more quantity than present");
        }
        if (currentQuantity == quantity) {
            lineItems.remove(lineItem);
        }
        lineItem.setQuantity(currentQuantity - quantity);
    }

    public boolean isPlaced() {
        return placed;
    }

    public void place() {
        placed = true;
    }

    public void cancel() {
        placed = false;
    }

    public Collection<LineItem> getLineItems() {
        return new ArrayList<LineItem>(lineItems);
    }

    public double getTotalPrice() {
        double totalPrice = 0;
        for (LineItem lineItem : getLineItems()) {
            totalPrice += lineItem.getLineTotal();
        }
        return totalPrice;
    }

    private LineItem getItemFor(Product product) {
        for (LineItem lineItem : lineItems) {
            if (lineItem.getProduct().equals(product)) {
                return lineItem;
            }
        }
        return null;
    }
}

