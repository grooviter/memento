package memento.guide.aggregate.bindcustom

interface DeliveryProcess {
    void requested(String clientId)
    void received()
}