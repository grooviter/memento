package memento.csv.csv.model

import memento.model.Event

class CartItemAdded extends Event<ShoppingCart> {
    CartItem itemAdded
}
