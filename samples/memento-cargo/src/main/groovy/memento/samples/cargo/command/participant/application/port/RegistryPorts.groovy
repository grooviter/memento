package memento.samples.cargo.command.participant.application.port
import memento.samples.cargo.command.participant.domain.Participant
interface RegistryPorts {

    interface SaveParticipantPort {
        void saveParticipant(Participant participant)
    }
}