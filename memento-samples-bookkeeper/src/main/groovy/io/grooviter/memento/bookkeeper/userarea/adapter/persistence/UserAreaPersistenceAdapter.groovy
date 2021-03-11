package io.grooviter.memento.bookkeeper.userarea.adapter.persistence

import io.grooviter.memento.bookkeeper.userarea.application.port.out.UserAreaPorts
import io.grooviter.memento.bookkeeper.userarea.domain.vo.AccountInfo

class UserAreaPersistenceAdapter implements UserAreaPorts.ListAccountsPort {


    @Override
    List<AccountInfo> list() {
        return null
    }
}
