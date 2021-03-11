package io.grooviter.memento.bookkeeper.userarea.application.port.out

import io.grooviter.memento.bookkeeper.userarea.domain.vo.AccountInfo

class UserAreaPorts {
    interface ListAccountsPort {
        List<AccountInfo> list()
    }
}
