package io.grooviter.memento.csv.model

import io.grooviter.memento.model.Aggregate

import static java.util.UUID.randomUUID

class ShoppingCart extends Aggregate {
    List<CartItem> items = []

    static ShoppingCart create() {
        return new ShoppingCart(id: randomUUID()).apply(new ShoppingCartCreated())
    }

    ShoppingCart addItem(CartItem item) {
        return apply(new CartItemAdded(itemAdded: item))
    }

    @Override
    void configure() {
        bind(ShoppingCartCreated)
        bind(CartItemAdded) { agg, event ->
            agg.items << event.itemAdded
        }
    }
}
