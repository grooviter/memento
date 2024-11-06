package memento.guide.aggregate.full

interface DeliveryProcess {
    void requested(String clientId)
    void received()
}