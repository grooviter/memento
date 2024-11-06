package memento.guide.aggregate.nobind

interface DeliveryProcess {
    void requested(String clientId)
    void received()
}