package io.grooviter.memento.bookkeeper.userarea.application.port.in

import io.grooviter.memento.bookkeeper.userarea.domain.vo.AccountInfo

class UseCases {
    interface ShowAccountsQuery {
        List<AccountInfo> listAccounts()
    }
}
