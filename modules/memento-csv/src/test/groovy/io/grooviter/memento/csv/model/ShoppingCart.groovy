package io.grooviter.memento.csv.model

import io.grooviter.memento.model.Aggregate

class ShoppingCart extends Aggregate {
    List<CartItem> items = []

    static ShoppingCart create() {
        return new ShoppingCart(id: UUID.randomUUID()).apply(new ShoppingCartCreated())
    }

    ShoppingCart addItem(CartItem item) {
        return apply(new CartItemAdded(itemAdded: item))
    }

    ShoppingCart apply(CartItemAdded event) {
        super.apply(event)
        this.items << event.itemAdded
        return this
    }

    ShoppingCart apply(ShoppingCartCreated event) {
        super.apply(event)
        return this
    }
}
